package com.neusoft.elm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neusoft.elm.dao.AdminDao;
import com.neusoft.elm.po.Admin;
import com.neusoft.elm.util.DBUtil;


//实现类起名带impl
public class AdminDaoImpl implements AdminDao{
	
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs  = null;
	
	@Override
	public Admin getAdminByNameByPass(String adminName, String password) {
		
		Admin admin = null;//初始化一定要null而不是new，若未查出来则为空指针，有利于调用者判断是否查询出数据！
		String sql = "select * from admin where adminName=? and password=?";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			//依据需求有两个参数
			pst.setString(1, adminName);
			pst.setString(2, password);
			rs = pst.executeQuery();
			//可能返回多个结果故用while
			//是将数据库数据转为java对象数据过程
			while(rs.next()) {	
				admin = new Admin();
				admin.setAdminID(rs.getInt("adminId"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pst, con);
		}
		//必须要释放资源，否则会引起内存泄露，无论是否异常都要执行
		
		return admin;
	}
}
