package com.wzq.accountservice.user.controller;

import com.software.commonservice.common.ResultData;
import com.wzq.accountservice.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 9:50
 */
@RestController
@RequestMapping("/rest")
public class UserAccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 查询账号余额
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserAccBalanceByUserId")
    public ResultData getUserAccBalanceByUserId(Integer userId){
        ResultData resultData = new ResultData();

        try {
            resultData.setBo(accountService.getUserBalanceByUserId(userId));
            resultData.setStatus(ResultData.Status.success);
        } catch (Exception e) {
            resultData.setStatus(ResultData.Status.error);
            resultData.setMessage("查询用户账户余额接口异常");
        }
        return resultData;
    }

    /**
     * 更新用户余额
     * @param userId
     * @param balance
     * @return
     */
    @RequestMapping(value = "/updateUserBalanceWithUserId",method = RequestMethod.GET)
    public ResultData updateUserBalanceWithUserId(Integer userId, BigInteger balance){
        ResultData resultData = new ResultData();

        try {
            accountService.updateUserBalanceByUserId(userId,balance);
            resultData.setStatus(ResultData.Status.success);
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setStatus(ResultData.Status.error);
            resultData.setMessage("更新用户账户余额异常");
        }
        return resultData;
    }
}
