package com.wzq.commodityservice.service.impl;

import com.wzq.commodityservice.dao.CommodityRepository;
import com.wzq.commodityservice.model.Commodity;
import com.wzq.commodityservice.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 11:39
 */
@Service(value = "commodityService")
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;


    /**
     * 根据商品id获取商品库存数量
     * @param commodityId
     * @return
     */
    @Override
    public Integer getCommodityCountById(Integer commodityId) {
        Commodity commodity = commodityRepository.findByCommodityId(commodityId);
        if (commodity != null){
            return commodity.getCommodityCount();
        }
        return null;
    }

    /**
     * 根据商品id更新商品库存数量
     * @param commodityId
     * @param amount
     */
    @Override
    @Transactional
    public void updateCommodityCountById(Integer commodityId, Integer amount) {
        Integer integer = commodityRepository.updateCommodityCount(commodityId, amount);
    }

    /**
     * 根据商品id获取商品价格
     * @param commodityId
     * @return
     */
    @Override
    public BigInteger getCommodityPriceById(Integer commodityId) {
        Commodity commodity = commodityRepository.findByCommodityId(commodityId);
        if (commodity != null){
            return commodity.getPrice();
        }
        return null;
    }
}
