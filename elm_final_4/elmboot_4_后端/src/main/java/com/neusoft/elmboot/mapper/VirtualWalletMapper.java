package com.neusoft.elmboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.neusoft.elmboot.po.VirtualWallet;

import java.math.BigDecimal;

@Mapper
public interface VirtualWalletMapper {
    @Select("select * from virtualwallet where userid=#{walletId}")
    public VirtualWallet getWalletbyuserId(String userId);
    @Select("select balance from virtualwallet where userid=#{walletId}")
    public BigDecimal getBalance(String walletId);
    @Select("UPDATE virtualwallet SET balance=#{balance} WHERE userid=#{userid};")
    public void updateBalance(String userid, BigDecimal balance);
    @Insert("insert into virtualwallet values(#{userId},0)")
    public void saveWallet(String userId);
}
