package com.wzq.commodityservice.controller;

import com.software.commonservice.common.ResultData;
import com.wzq.commodityservice.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 11:45
 */
@RestController
@RequestMapping(value = "/rest/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @RequestMapping(value = "/getCommodityCountById",method = RequestMethod.GET)
    public ResultData getCommodityCountById(Integer commodityId){
        ResultData resultData = new ResultData();

        try {
            resultData.setBo(commodityService.getCommodityCountById(commodityId));
            resultData.setStatus(ResultData.Status.success);
        }
        catch (Exception e){
            resultData.setStatus(ResultData.Status.error);
            resultData.setMessage("调用商品服务层异常！");
        }

        return resultData;
    }

    @RequestMapping(value = "/updateStockByCommodityId")
    public ResultData updateStockByCommodityId(@RequestParam(value = "commodityId") Integer commodityId,
                                               @RequestParam(value = "amount") Integer amount){
        ResultData resultData = new ResultData();

        try {
            commodityService.updateCommodityCountById(commodityId,amount);
            resultData.setStatus(ResultData.Status.success);
        } catch (Exception e) {
            resultData.setStatus(ResultData.Status.error);
            resultData.setMessage("调用修改商品库存异常");
        }
        return resultData;
    }

    /**
     * 获取商品价格
     * @param commodityId
     * @return
     */
    @RequestMapping(value = "/getCommodityPriceById",method = RequestMethod.GET)
    public ResultData getCommodityPriceById(Integer commodityId){
        ResultData resultData = new ResultData();

        try {
            BigInteger price = commodityService.getCommodityPriceById(commodityId);
            resultData.setBo(price);
            resultData.setStatus(ResultData.Status.success);
        } catch (Exception e) {
            resultData.setStatus(ResultData.Status.error);
            resultData.setMessage("获取商品价格异常");
        }
        return resultData;
    }
}
