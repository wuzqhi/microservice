package com.wzq.accountservice.user.service;

import java.math.BigInteger;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 9:45
 */
public interface AccountService {

    BigInteger getUserBalanceByUserId(Integer userId);

    void updateUserBalanceByUserId(Integer userId,BigInteger amount);

}
