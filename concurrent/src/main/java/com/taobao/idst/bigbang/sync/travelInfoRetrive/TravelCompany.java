package com.taobao.idst.bigbang.sync.travelInfoRetrive;

import lombok.Data;

import java.util.Random;

@Data
public class TravelCompany {
    private String name;
    private String address;
    private long price = new Random().nextInt();

    public TravelQuota getQuota(TravleRequest request) {
        return new TravelQuota();
    }
}
