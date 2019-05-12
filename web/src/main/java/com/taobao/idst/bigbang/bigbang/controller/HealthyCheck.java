package com.taobao.idst.bigbang.bigbang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthyCheck {

    @RequestMapping(value = "/sayhello")
    private String hello() {
        return "hello world";
    }
}
