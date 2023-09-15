package com.neusoft.elmboot.mapper;

import com.neusoft.elmboot.po.BonuspointsTranscation;
import com.neusoft.elmboot.po.VirtualWalletTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface BonuspointsTransactionMapper {
    @Select("select * from bonuspointstranscation where userid = #{userid};")
    public List<BonuspointsTranscation> listtransactionbyid(String userid);

    @Select("insert into bonuspointstranscation (userid,orderid,amount,type,createtime,expiredtime) values(#{userid},#{orderid},#{amount},#{type},#{createTime},#{expiredTime});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public void saveTransaction(BonuspointsTranscation Transaction);
}
