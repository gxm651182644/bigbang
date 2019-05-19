package com.taobao.idst.bigbang.sync.travelInfoRetrive;

import lombok.Data;

import java.util.Comparator;
import java.util.Random;

@Data
public class TravelQuota implements Comparator<TravelQuota> {
    private Integer code;
    private String address;
    private long price;
    private double similarity;
    private double score = new Random().nextDouble();

    @Override
    public int compare(TravelQuota o1, TravelQuota o2) {
        return o1.score > o2.score ? 1 : 0;
    }
}
