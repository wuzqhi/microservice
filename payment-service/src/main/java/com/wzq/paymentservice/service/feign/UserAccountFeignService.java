package com.wzq.paymentservice.service.feign;

import com.software.commonservice.common.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 15:16
 */
@FeignClient(name = "${feign.remote.account-service}",path = "/rest")
public interface UserAccountFeignService {

    @RequestMapping(value = "/getUserAccBalanceByUserId",method = RequestMethod.GET)
    public ResultData getUserAccBalanceByUserId(@RequestParam(value = "userId") Integer userId);

    @RequestMapping(value = "/updateUserBalanceWithUserId",method = RequestMethod.GET)
    public ResultData updateUserBalanceWithUserId(@RequestParam(value = "userId") Integer userId,
                                                  @RequestParam(value = "balance") BigInteger balance);

}
