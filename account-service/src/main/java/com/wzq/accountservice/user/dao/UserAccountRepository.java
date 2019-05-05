package com.wzq.accountservice.user.dao;

import com.wzq.accountservice.user.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

/**
 * @author wzq 武振乾
 * @date 2019/4/26 9:39
 */
public interface UserAccountRepository extends JpaRepository<UserAccount,Integer> {

    /**
     * 根据用户id获取账户信息
     * @param userId
     * @return
     */
    UserAccount findByUserId(Integer userId);

    /**
     * 更新用户账户余额
     * @param userId
     * @param balance
     */
    @Modifying
    @Query(value = "update tbl_user_acc set acc_balance = acc_balance-?2 where user_id = ?1",nativeQuery = true)
    void updateUserBalanceWithUserId(Integer userId, BigInteger balance);
}
