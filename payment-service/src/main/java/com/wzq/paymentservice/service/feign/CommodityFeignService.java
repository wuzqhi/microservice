package com.wzq.paymentservice.service.feign;

import com.software.commonservice.common.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 15:05
 */
@FeignClient(name = "${feign.remote.commodity-service}",path = "/rest")
public interface CommodityFeignService {

    @RequestMapping(value = "/commodity/getCommodityPriceById",method = RequestMethod.GET)
    public ResultData getCommodityPriceById(@RequestParam(value = "commodityId") Integer commodityId);
}
