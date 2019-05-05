package com.wzq.commodityservice.dao;

import com.wzq.commodityservice.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 11:34
 */
public interface CommodityRepository extends JpaRepository<Commodity,Integer> {

    /**
     * 根据商品id获取商品信息
     * @param commodityId
     * @return
     */
    Commodity findByCommodityId(Integer commodityId);

    @Modifying
    @Query(value = "update tbl_commodity_info set commodity_count=commodity_count-?2 where commodity_id=?1",nativeQuery = true)
    Integer updateCommodityCount(Integer commodityId,Integer amount);

}
