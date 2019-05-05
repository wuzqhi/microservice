package com.wzq.orderservice.order.controller;

import com.software.commonservice.common.ResultData;
import com.wzq.orderservice.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 11:02
 */
@RestController
@RequestMapping("/rest/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单记录
     * @param userId
     * @param commodityId
     * @param purchaseCount
     * @return
     */
    @RequestMapping(value = "/createOrder")
    public ResultData createOrder(@RequestParam(value = "userId")Integer userId,
                                  @RequestParam(value = "commodityId")Integer commodityId,
                                  @RequestParam(value = "purchaseCount")Integer purchaseCount){

        ResultData resultData = new ResultData();
        resultData.setStatus(ResultData.Status.error);
        if (commodityId ==0 || purchaseCount==0){
            resultData.setMessage("参数传入有误");
        }
        try {
            resultData.setBo(orderService.createOrder(userId,commodityId,purchaseCount));
            resultData.setStatus(ResultData.Status.success);
        } catch (RuntimeException e) {
            resultData.setMessage(e.getMessage());
        }catch (Exception e){
            resultData.setMessage("调用订单服务异常");
        }

        return resultData;
    }

    /**
     * 更新订单支付状态
     * @param orderId
     * @param payStatus
     * @return
     */
    @RequestMapping(value = "/updateOrderStatusById",method = RequestMethod.GET)
    public ResultData updateOrderStatusById(@RequestParam(value = "orderId")Integer orderId,
                                            @RequestParam(value = "payStatus")Integer payStatus){
        ResultData resultData = new ResultData();

        try {
            orderService.updateOrderStatusById(orderId,payStatus);
            resultData.setStatus(ResultData.Status.success);
        } catch (Exception e) {

            resultData.setStatus(ResultData.Status.error);
            resultData.setMessage("调用订单服务异常");
        }
        return resultData;
    }

    @RequestMapping(value = "/getOrderById",method = RequestMethod.GET)
    public ResultData getOrderById(@RequestParam(value = "orderId")Integer orderId){
        ResultData resultData = new ResultData();

        try {
            resultData.setBo(orderService.getOrderInfoByOrderId(orderId));
            resultData.setStatus(ResultData.Status.success);
        } catch (Exception e) {

            resultData.setStatus(ResultData.Status.error);
            resultData.setMessage("调用订单服务异常");
        }
        return resultData;
    }

}
