package com.taobao.idst.bigbang.sync.travelInfoRetrive;

import lombok.Data;

import java.util.Date;

@Data
public class TravleRequest {
    private long priceMin;
    private long priceMax;
    private Date startTime;
    private Date endTime;
}
