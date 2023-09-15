package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.po.Business;
import com.neusoft.elmboot.po.VirtualWallet;
import com.neusoft.elmboot.po.VirtualWalletTransaction;
import com.neusoft.elmboot.service.VirtualWalletService;
import com.neusoft.elmboot.service.impl.VirtualWalletServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/VirtualWalletController")
public class VirtualWalletController {

   
    @Autowired
    private VirtualWalletService virtualWalletService;

    @RequestMapping("/getWalletbyuserId")
    public VirtualWallet getWalletbyuserId(String userid){
        return virtualWalletService.getWalletbyuserId(userid);
    }
    @RequestMapping("/getBalance")
    public BigDecimal getBalance(String userid) {
        return virtualWalletService.getBalance(userid);  }

    @RequestMapping("/debit")
    public void debit(String userId, BigDecimal amount) throws Exception {
        virtualWalletService.debit(userId,amount);
    } 
    @RequestMapping("/credit")
    public void credit(String userId, BigDecimal amount) throws Exception {
        virtualWalletService.credit(userId,amount);
    } 
    @RequestMapping("/transfer")
    public void transfer(String fromUserId, String toUserId, BigDecimal amount) throws Exception {
        virtualWalletService.transfer(fromUserId,toUserId,amount);
    } 
    @RequestMapping("/listtransactionbyid")
    public List<VirtualWalletTransaction> listtransactionbyid(String userid){
        return virtualWalletService.listtransactionbyid(userid);
    }
    @RequestMapping("/saveWallet")
    public void saveWallet(String userId){
    	virtualWalletService.saveWallet(userId);
    }

}
