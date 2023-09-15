package com.neusoft.elmboot.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.elmboot.mapper.VirtualWalletMapper;
import com.neusoft.elmboot.mapper.VirtualWalletTransactionMapper;
import com.neusoft.elmboot.po.VirtualWallet;
import com.neusoft.elmboot.po.VirtualWalletTransaction;

public interface VirtualWalletService {
	   public VirtualWallet getWalletbyuserId(String userId); 

	   public BigDecimal getBalance (String walletId);
	   public void debit(String walletId, BigDecimal amount);
	   public void transfer (long fromWalletId,long towalletId, BigDecimal amount);
	   @Transactional
	   public void credit(String userId, BigDecimal amount);
	   public void transfer(String fromWalletId, String toWalletId, BigDecimal amount);
	   public List<VirtualWalletTransaction> listtransactionbyid (String userId);
	   public void saveWallet(String userId);
	  

}
