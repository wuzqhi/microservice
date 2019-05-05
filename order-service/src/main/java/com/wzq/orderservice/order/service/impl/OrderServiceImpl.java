package com.wzq.orderservice.order.service.impl;

import com.software.commonservice.common.ResultData;
import com.software.commonservice.common.constants.PubConstant;
import com.software.commonservice.common.dto.OrderDto;
import com.wzq.orderservice.order.dao.OrderRepository;
import com.wzq.orderservice.order.model.Order;
import com.wzq.orderservice.order.service.feign.CommodityFeignService;
import com.wzq.orderservice.order.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 10:40
 */
@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Resource
    private CommodityFeignService commodityFeignService;


    /**
     * 创建订单
     * @param userId
     * @param commodityId
     * @param purchaseCount
     * @return
     */
    @Override
    public Order createOrder(Integer userId, Integer commodityId, Integer purchaseCount) {
        ResultData resultData = commodityFeignService.getCommodityCountById(commodityId);

        if (resultData!=null && resultData.getBo()!=null){
            Object bo = resultData.getBo();
            if (bo instanceof Integer){
                int commodityCnt = (Integer) bo;
                if (commodityCnt>purchaseCount){
                    //创建订单信息记录
                    Order order = createOrderRecord(commodityId, userId, purchaseCount);
                    //扣减库存
                    commodityFeignService.updateStockByCommodityId(commodityId,purchaseCount);

                    return order;
                }else {
                    throw new RuntimeException("商品库存不足");
                }
            }
        }
        return null;
    }

    /**
     * 根据订单id获取订单信息
     * @param orderId
     * @return
     */
    @Override
    public OrderDto getOrderInfoByOrderId(Integer orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        if (order != null) {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(order,orderDto);
            return orderDto;
        }
        return null;
    }

    /**
     * 根据订单id更新订单状态
     * @param orderId
     * @param payStatus
     */
    @Override
    @Transactional
    public void updateOrderStatusById(Integer orderId, int payStatus) {
        orderRepository.updateOrderStatusById(orderId,payStatus);
    }

    private Order createOrderRecord(Integer commodityId,Integer userId,Integer purchaseCount){
        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityId(commodityId);
        order.setPurchaseCount(purchaseCount);
        order.setCreateTime(new Date());
        order.setPayStatus(PubConstant.ORDER_WAIT_FOR_PAID);

        return orderRepository.save(order);

    }
}
