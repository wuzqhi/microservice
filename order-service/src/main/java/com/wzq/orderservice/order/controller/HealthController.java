package com.wzq.orderservice.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 11:09
 */
@RestController

public class HealthController {

    @RequestMapping(value = "/health")
    public String health(){
        return "ok";
    }


}
