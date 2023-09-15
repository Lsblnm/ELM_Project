package com.neusoft.elmboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neusoft.elmboot.po.User;

@Mapper
public interface UserMapper {
	@Select("select * from user where userId=#{userId} and password=#{password}")
	public User getUserByIdByPass(User user);

	@Select("select count(*) from user where userId=#{userId}")
	public int getUserById(String userId);

	@Insert("insert into user values(#{userId},#{password},#{userName},#{userSex},#{userImg},1)")
	public int saveUser(User user);
	
	@Update("update user SET password=#{password} WHERE userId=#{userId}")
	public int updatePasswordByuserId(String userId,String password);
	
}