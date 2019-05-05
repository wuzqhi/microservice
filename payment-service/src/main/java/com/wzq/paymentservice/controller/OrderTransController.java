package com.wzq.paymentservice.controller;

import com.software.commonservice.common.ResultData;
import com.wzq.paymentservice.model.OrderTransInfo;
import com.wzq.paymentservice.service.OrderTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 16:08
 */
@RestController
@RequestMapping(value = "/rest/orderTrans")
public class OrderTransController {

    @Autowired
    private OrderTransService orderTransService;

    /**
     * 支付接口
     * @param orderId
     * @param userId
     * @return
     */
    @RequestMapping(value = "/makePayment",method = RequestMethod.GET)
    public ResultData makePayment(Integer orderId,Integer userId){
        ResultData resultData = new ResultData();
        resultData.setStatus(ResultData.Status.error);
        try {
            OrderTransInfo orderTransInfo = orderTransService.makePayment(orderId, userId);
            resultData.setBo(orderTransInfo);
            resultData.setStatus(ResultData.Status.success);
        } catch (RuntimeException e) {
            resultData.setMessage(e.getMessage());
        }catch (Exception e){
            resultData.setMessage("创建订单流水异常");
        }
        return resultData;
    }

}
