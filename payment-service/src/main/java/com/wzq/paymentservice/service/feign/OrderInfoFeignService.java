package com.wzq.paymentservice.service.feign;

import com.software.commonservice.common.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 14:33
 */
@FeignClient(name = "${feign.remote.order-service}",path = "/rest")
public interface OrderInfoFeignService {


    /**
     * 根据订单id获取订单信息
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/order/getOrderById",method = RequestMethod.GET)
    public ResultData getOrderById(@RequestParam(value = "orderId") Integer orderId);

    /**
     * 根据订单id更新订单状态
     * @param orderId
     * @param payStatus
     * @return
     */
    @RequestMapping(value = "/order/updateOrderStatusById",method = RequestMethod.GET)
    public ResultData updateOrderStatusById(@RequestParam(value = "orderId") Integer orderId,
                                            @RequestParam(value = "payStatus") Integer payStatus);


}
