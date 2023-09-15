package com.neusoft.elmboot.service.impl;

import com.neusoft.elmboot.mapper.BonuspointsMapper;
import com.neusoft.elmboot.mapper.BonuspointsTransactionMapper;
import com.neusoft.elmboot.mapper.VirtualWalletMapper;
import com.neusoft.elmboot.po.Bonuspoints;
import com.neusoft.elmboot.po.BonuspointsTranscation;
import com.neusoft.elmboot.po.VirtualWallet;
import com.neusoft.elmboot.po.VirtualWalletTransaction;
import com.neusoft.elmboot.service.BonuspointsService;
import com.neusoft.elmboot.util.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class BonuspointsServiceImpl implements BonuspointsService{
	
    @Autowired
    private BonuspointsMapper bonuspointsRepo;
    @Autowired
    private BonuspointsTransactionMapper bonuspointstranscationRepo;
    
    public Bonuspoints getBonuspointsbyuserId(String userId) {
    	 // 获取当前时间
        Date currentTime = new Date();
        // 获取用户的积分信息
        Bonuspoints bonuspointsEntity = bonuspointsRepo.getBonuspointsbyuserId(userId);
        // 过滤过期的积分交易记录
        List<BonuspointsTranscation> transactions = bonuspointstranscationRepo.listtransactionbyid(userId);
        Iterator<BonuspointsTranscation> it = transactions.iterator();
        BigDecimal balance = BigDecimal.ZERO;
        //集合类iterator_while和增强for时不能删除，否则报错
        while(it.hasNext()) {
        	BonuspointsTranscation tmp = it.next();
        	if(tmp.getExpiredTime() != null) {
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date expiredTime = sdf.parse(tmp.getExpiredTime());
					if (!expiredTime.before(currentTime)) {
						// 移除过期的交易记录ExpiredTime
		                if (tmp.isType()) {
		                    // 如果是积分收入交易
		                    balance = balance.add(tmp.getAmount());
		                } else {
		                    // 如果是积分支出交易
		                    balance = balance.subtract(tmp.getAmount());
		                }
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
        	}
        }
        bonuspointsRepo.updateBalance(userId, balance);
        return bonuspointsRepo.getBonuspointsbyuserId(userId);
    }
    
    @Transactional
    public void debit(String userId, BigDecimal amount) {
        Bonuspoints bonuspoints = bonuspointsRepo.getBonuspointsbyuserId(userId);
        try {
			bonuspoints.debit(amount);
		} catch (Exception e) {
			e.printStackTrace();
		}
        BonuspointsTranscation transactionEntity = new BonuspointsTranscation();
        transactionEntity.setAmount(amount);
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        transactionEntity.setCreateTime(dateformat.format(System.currentTimeMillis()));
        transactionEntity.setType(false);
        transactionEntity.setUserid(userId);
       // transactionEntity.setExpiredTime();
        bonuspointstranscationRepo.saveTransaction(transactionEntity);
        bonuspointsRepo.updateBalance(userId, bonuspoints.getBalance());
    }
    
    
    @Transactional
    public void credit(String userId, BigDecimal amount) {
        Bonuspoints bonuspoints = bonuspointsRepo.getBonuspointsbyuserId(userId);
        try {
			bonuspoints.credit(amount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BonuspointsTranscation transactionEntity = new BonuspointsTranscation();
        transactionEntity.setAmount(amount);
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        transactionEntity.setCreateTime(dateformat.format(System.currentTimeMillis()));
        transactionEntity.setExpiredTime(dateformat.format(System.currentTimeMillis()+2592000000L));
        transactionEntity.setType(true);
        transactionEntity.setUserid(userId);
        bonuspointstranscationRepo.saveTransaction(transactionEntity);
        bonuspointsRepo.updateBalance(userId, bonuspoints.getBalance());
    }

    public List<BonuspointsTranscation> listtransactionbyid (String userId){
        return  bonuspointstranscationRepo.listtransactionbyid(userId);
    }
    
    public void saveBonuspoints(String userId) {
    	bonuspointsRepo.saveBonuspoints(userId);
	}


}
