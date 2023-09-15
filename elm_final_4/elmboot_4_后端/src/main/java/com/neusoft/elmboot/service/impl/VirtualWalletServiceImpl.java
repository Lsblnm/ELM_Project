package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.TransactionType;
import com.neusoft.elmboot.mapper.VirtualWalletMapper;
import com.neusoft.elmboot.mapper.VirtualWalletTransactionMapper;
import  com.neusoft.elmboot.po.VirtualWallet;
import com.neusoft.elmboot.po.VirtualWalletTransaction;
import com.neusoft.elmboot.service.VirtualWalletService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class VirtualWalletServiceImpl implements VirtualWalletService{
   //通过构造函数或者IOC框架注入
   @Autowired
   private VirtualWalletTransactionMapper transactionRepo;
   @Autowired
   private VirtualWalletMapper walletRepo;
   public VirtualWallet getWalletbyuserId(String userId) {



      VirtualWallet walletEntity = walletRepo.getWalletbyuserId(userId);

      return walletEntity;
   }

   public BigDecimal getBalance (String walletId){
      return walletRepo.getBalance(walletId);
   };
   @Transactional
   public void debit(String walletId, BigDecimal amount) {
      VirtualWallet wallet = walletRepo.getWalletbyuserId(walletId);
      try {
		wallet.debit(amount);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      VirtualWalletTransaction transactionEntity = new VirtualWalletTransaction();
      transactionEntity.setAmount(amount);
      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      transactionEntity.setCreateTime(dateformat.format(System.currentTimeMillis()));
      transactionEntity.setType("DEBIT");
      transactionEntity.setFromWalletId(walletId);
      transactionRepo.saveTransaction(transactionEntity);
      walletRepo.updateBalance(walletId, wallet.getBalance());
   }

   public void transfer (long fromWalletId,long towalletId, BigDecimal amount){};
   @Transactional
   public void credit(String userId, BigDecimal amount) {
      VirtualWallet wallet = walletRepo.getWalletbyuserId(userId);
      try {
		wallet.credit(amount);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      VirtualWalletTransaction transactionEntity = new VirtualWalletTransaction();
      transactionEntity.setAmount(amount);
      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      transactionEntity.setCreateTime(dateformat.format(System.currentTimeMillis()));
      transactionEntity.setType("CREDIT");
      transactionEntity.setFromWalletId(userId);
      transactionRepo.saveTransaction(transactionEntity);
      walletRepo.updateBalance(userId, wallet.getBalance());
   }

   @Transactional
   public void transfer(String fromWalletId, String toWalletId, BigDecimal amount)  {
      VirtualWalletTransaction transactionEntity = new VirtualWalletTransaction();
      transactionEntity.setAmount(amount);
      SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      transactionEntity.setCreateTime(dateformat.format(System.currentTimeMillis()));
      transactionEntity.setType("TRANSFER");
      transactionEntity.setFromWalletId(fromWalletId);
      transactionEntity.setToWalletId(toWalletId);
      transactionRepo.saveTransaction(transactionEntity);
      debit(fromWalletId, amount);
      credit(toWalletId, amount);
   }

   public List<VirtualWalletTransaction> listtransactionbyid (String userId){
  return  transactionRepo.listtransactionbyid(userId);
   }
   public void saveWallet(String userId)
   {
	   walletRepo.saveWallet(userId);
   }

}
