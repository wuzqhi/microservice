package com.wzq.paymentservice.service.impl;

import com.software.commonservice.common.ResultData;
import com.software.commonservice.common.constants.PubConstant;
import com.software.commonservice.common.dto.OrderDto;
import com.wzq.paymentservice.dao.OrderTransRepository;
import com.wzq.paymentservice.model.OrderTransInfo;
import com.wzq.paymentservice.service.OrderTransService;
import com.wzq.paymentservice.service.feign.CommodityFeignService;
import com.wzq.paymentservice.service.feign.OrderInfoFeignService;
import com.wzq.paymentservice.service.feign.UserAccountFeignService;
import com.wzq.paymentservice.utils.DataConverterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 14:32
 */
@Service("orderTransService")
public class OrderTransServiceImpl implements OrderTransService {

    @Autowired
    private OrderTransRepository orderDao;

    @Resource
    private OrderInfoFeignService orderInfoFeignService;
    @Resource
    private UserAccountFeignService userAccountFeignService;
    @Resource
    private CommodityFeignService commodityFeignService;

    /**
     * 支付
     * @param orderId
     * @param userId
     * @return
     */
    @Override
    public OrderTransInfo makePayment(Integer orderId, Integer userId) {
        OrderDto orderDto = getOrderDtoByOrderId(orderId);
        BigInteger purchaseTotalAmount = BigInteger.ZERO;
        if (orderDto != null) {
            Integer payStatus = orderDto.getPayStatus();
            if (payStatus ==PubConstant.ORDER_HAS_PAID){
                throw new RuntimeException("订单已支付");
            }
            BigInteger commodityPrice = getCommodityPriceById(orderDto.getCommodityId());
            //计算订单的总价格
            purchaseTotalAmount = commodityPrice.multiply(new BigInteger(orderDto.getPurchaseCount() + ""));
        }

        //获取用户账户余额
        BigInteger accountBalance = getAccountBalanceById(userId);
        if (accountBalance.compareTo(purchaseTotalAmount)<0){
            throw new RuntimeException("用户账号余额不足");
        }

        //扣除用户订单金额
        userAccountFeignService.updateUserBalanceWithUserId(userId,purchaseTotalAmount);

        //更改订单状态
        orderInfoFeignService.updateOrderStatusById(orderId,PubConstant.ORDER_HAS_PAID);

        //生成订单流水
        OrderTransInfo orderTransInfo = createOrderTransInfo(orderId, purchaseTotalAmount);

        return orderTransInfo;
    }

    /**
     * 创建订单流水
     * @param orderId
     * @param purchaseTotalAmount
     * @return
     */
    private OrderTransInfo createOrderTransInfo(Integer orderId, BigInteger purchaseTotalAmount){
        OrderTransInfo orderTransInfo = new OrderTransInfo();

        orderTransInfo.setOrderId(orderId);
        orderTransInfo.setTransCount(purchaseTotalAmount);
        orderTransInfo.setCreateTime(new Date());

        return orderDao.save(orderTransInfo);
    }

    /**
     * 根据商品id获取商品价格
     * @param commodityId
     * @return
     */
    private BigInteger getCommodityPriceById(Integer commodityId){
        BigInteger commodityPrice = BigInteger.ZERO;
        ResultData result = commodityFeignService.getCommodityPriceById(commodityId);

        if (result != null) {
            Object bo = result.getBo();
            if (bo instanceof Integer){
                return new BigInteger(bo+"");
            }
        }
        return commodityPrice;
    }

    /**
     * 根据订单id获取订单信息
     * @param oderId
     * @return
     */
    private OrderDto getOrderDtoByOrderId(Integer oderId){
        ResultData resultData = orderInfoFeignService.getOrderById(oderId);
        if (resultData != null) {
            Object bo = resultData.getBo();
            if (bo instanceof Map){
                try {
                    return (OrderDto) DataConverterUtil.convertMapToBean(OrderDto.class, (Map) bo);
                } catch (Exception e) {
                    System.out.println("数据转换异常");
                }
            }
        }
        return null;
    }

    /**
     * 根据用户id获取账户余额
     * @param userId
     * @return
     */
    private BigInteger getAccountBalanceById(Integer userId){
        BigInteger accountBalance = BigInteger.ZERO;

        ResultData resultData = userAccountFeignService.getUserAccBalanceByUserId(userId);
        if (resultData != null) {
            Object bo = resultData.getBo();
            if (bo instanceof Integer){
                return new BigInteger(bo+"");
            }
        }
        return accountBalance;
    }
}
