package com.neusoft.elmboot.mapper;


import com.neusoft.elmboot.po.VirtualWalletTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface VirtualWalletTransactionMapper {

    @Select("insert into transaction (amount,CreateTime,Type,FromWalletId,ToWalletId) values(#{amount},#{CreateTime},#{Type},#{FromWalletId},#{ToWalletId});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public void saveTransaction(VirtualWalletTransaction Transaction);

    @Select("select * from transaction where FromWalletId = #{userid};")
    public List<VirtualWalletTransaction> listtransactionbyid(String userid);


}
