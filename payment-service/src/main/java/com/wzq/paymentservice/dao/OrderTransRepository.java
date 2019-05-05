package com.wzq.paymentservice.dao;

import com.wzq.paymentservice.model.OrderTransInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 14:31
 */
public interface OrderTransRepository extends JpaRepository<OrderTransInfo,Integer> {
}
