package com.wzq.orderservice.order.dao;

import com.wzq.orderservice.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 10:37
 */
public interface OrderRepository extends JpaRepository<Order,Integer> {

    Order findByOrderId(Integer orderId);

    /**
     * 修改订单状态
     * @param orderId
     * @param payStatus
     */
    @Modifying
    @Query(value = "update tbl_order_info set pay_status = ?2 where order_id = ?1",nativeQuery = true)
    void updateOrderStatusById(Integer orderId,int payStatus);

}
