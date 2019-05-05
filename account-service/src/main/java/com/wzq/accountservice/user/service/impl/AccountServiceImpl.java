package com.wzq.accountservice.user.service.impl;

import com.wzq.accountservice.user.dao.UserAccountRepository;
import com.wzq.accountservice.user.model.UserAccount;
import com.wzq.accountservice.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 9:45
 */
@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;


    @Override
    public BigInteger getUserBalanceByUserId(Integer userId) {
        UserAccount userAccount = userAccountRepository.findByUserId(userId);
        if (userAccount!=null){
            BigInteger accBalance = userAccount.getAccBalance();
            return accBalance;
        }
        return null;
    }

    @Override
    @Transactional
    public void updateUserBalanceByUserId(Integer userId, BigInteger amount) {
        userAccountRepository.updateUserBalanceWithUserId(userId,amount);
    }
}
