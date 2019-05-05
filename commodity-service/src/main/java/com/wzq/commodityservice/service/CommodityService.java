package com.wzq.commodityservice.service;

import java.math.BigInteger;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 11:39
 */
public interface CommodityService {

    Integer getCommodityCountById(Integer commodityId);

    void updateCommodityCountById(Integer commodityId,Integer amount);

    BigInteger getCommodityPriceById(Integer commodityId);
}
