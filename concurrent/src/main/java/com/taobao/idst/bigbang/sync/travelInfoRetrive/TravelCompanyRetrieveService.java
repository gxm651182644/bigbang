package com.taobao.idst.bigbang.sync.travelInfoRetrive;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.concurrent.*;


public class TravelCompanyRetrieveService implements Callable<TravelQuota> {
    public static final Integer SUCCESS = 0;
    public static final Integer FAIL = -1;

    private TravelCompany company;
    private TravleRequest request;

    @Override
    public TravelQuota call() {
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return company.getQuota(request);
    }

    public TravelQuota buildMockQuota(Integer code) {
        TravelQuota quota = new TravelQuota();
        quota.setCode(code);
        return quota;
    }

    public TravelCompanyRetrieveService(TravelCompany company, TravleRequest request) {
        this.company = company;
        this.request = request;
    }

    List<TravelQuota> getRankedTravelInfo(ExecutorService executorService,
                                          TravleRequest request,
                                          Set<TravelCompany> companies,
                                          Comparator<TravelQuota> quotaComparable,
                                          long time, TimeUnit timeUnit) throws Exception {
        List<TravelCompanyRetrieveService> retrieveServices = new ArrayList<>();
        for (TravelCompany company : companies) {
            retrieveServices.add(new TravelCompanyRetrieveService(company, request));
        }

        List<Future<TravelQuota>> futures = executorService.invokeAll(retrieveServices, time, timeUnit);

        List<TravelQuota> quotas = new ArrayList<>(companies.size());
        Iterator<TravelCompanyRetrieveService> serviceIterable = retrieveServices.iterator();
        for (Future<TravelQuota> future : futures) {
            TravelCompanyRetrieveService service = serviceIterable.next();
            try {
                TravelQuota quota = future.get();
                quotas.add(quota);
            } catch (InterruptedException e) {
                quotas.add(buildMockQuota(FAIL));
            } catch (ExecutionException e) {
                quotas.add(buildMockQuota(FAIL));
            }
        }

        Collections.sort(quotas, quotaComparable);
        return quotas;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        TravleRequest request = new TravleRequest();
        Set<TravelCompany> companies = new HashSet<TravelCompany>() {
            {
                add(new TravelCompany());
                add(new TravelCompany());
                add(new TravelCompany());
            }
        };

        TravelCompany company = new TravelCompany();
        TravelCompanyRetrieveService companyRetrieveService = new TravelCompanyRetrieveService(company, request);
        List<TravelQuota> quotas = new ArrayList<>();
        try {
            quotas = companyRetrieveService.getRankedTravelInfo(executorService, request, companies, new TravelQuota(),
                    1, TimeUnit.SECONDS);
            System.out.println(JSON.toJSONString(quotas));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
