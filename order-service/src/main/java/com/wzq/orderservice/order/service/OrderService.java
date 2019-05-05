package com.wzq.orderservice.order.service;

import com.software.commonservice.common.dto.OrderDto;
import com.wzq.orderservice.order.model.Order;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 10:38
 */
public interface OrderService {

    Order createOrder(Integer userId, Integer commodityId, Integer purchaseCount);

    OrderDto getOrderInfoByOrderId(Integer orderId);

    void updateOrderStatusById(Integer orderId,int payStatus);



}
