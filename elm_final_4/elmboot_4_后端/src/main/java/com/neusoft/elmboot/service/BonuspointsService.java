package com.neusoft.elmboot.service;

import java.math.BigDecimal;
import java.util.List;

import com.neusoft.elmboot.po.Bonuspoints;
import com.neusoft.elmboot.po.BonuspointsTranscation;

public interface BonuspointsService {
    
    public void debit(String userId, BigDecimal amount);
    public void credit(String userId, BigDecimal amount);
    public Bonuspoints getBonuspointsbyuserId(String userId) ;

    public List<BonuspointsTranscation> listtransactionbyid (String userId);
    public void saveBonuspoints(String userId);

}
