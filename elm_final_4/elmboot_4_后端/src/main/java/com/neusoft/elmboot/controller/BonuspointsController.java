package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.po.*;
import com.neusoft.elmboot.service.BonuspointsService;
import com.neusoft.elmboot.service.impl.BonuspointsServiceImpl;
import com.neusoft.elmboot.service.impl.VirtualWalletServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BonuspointsController")
public class BonuspointsController {

    // 通过构造函数或者 IOC 框架注入
    @Autowired
    private BonuspointsService bonuspointsService;

    @RequestMapping("/getBonuspointsbyuserId")
    public Bonuspoints getBonuspointsbyuserId(String userid){
        return bonuspointsService.getBonuspointsbyuserId(userid);
    }
//    @RequestMapping("/getBalance")
//    public BigDecimal getBalance(String userid) {
//        return virtualWalletService.getBalance(userid);  } // 查询余额
//
    
    @RequestMapping("/debit")
    public void debit(String userId, BigDecimal amount) throws Exception {
        bonuspointsService.debit(userId,amount);
    } 
    
    // 提现、出账
    @RequestMapping("/credit")
    public void credit(String userId, BigDecimal amount) throws Exception {
        bonuspointsService.credit(userId,amount);
    } 
    // 充值、入账
//    @RequestMapping("/transfer")
//    public void transfer(String fromUserId, String toUserId, BigDecimal amount) throws Exception {
//        virtualWalletService.transfer(fromUserId,toUserId,amount);
//    } // 转账
    @RequestMapping("/listtransactionbyid")
    public List<BonuspointsTranscation> listtransactionbyid(String userid){
        return bonuspointsService.listtransactionbyid(userid);
    }//查询流水
    
    @RequestMapping("/saveBonuspoints")
    public void saveBonuspoints(String userId){
        bonuspointsService.saveBonuspoints(userId);
    }

}
