package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.Bonuspoints;
import com.neusoft.elmboot.po.VirtualWallet;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

@Mapper
public interface BonuspointsMapper {
    @Select("select * from bonuspoints where userid=#{userId}")
    public Bonuspoints getBonuspointsbyuserId(String userId);
    
    @Select("UPDATE bonuspoints SET balance=#{balance} WHERE userid=#{userid};")
    public void updateBalance(String userid, BigDecimal balance);
    
    @Insert("insert into Bonuspoints values(#{userId},100)")
    public void saveBonuspoints(String userId);
    
}
