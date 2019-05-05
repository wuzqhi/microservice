package com.wzq.paymentservice.service;

import com.wzq.paymentservice.model.OrderTransInfo;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 14:31
 */
public interface OrderTransService {


    OrderTransInfo makePayment(Integer orderId,Integer userId);

}
