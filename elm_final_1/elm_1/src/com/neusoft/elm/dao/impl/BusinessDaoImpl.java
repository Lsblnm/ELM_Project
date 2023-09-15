package com.neusoft.elm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.elm.po.Business;
import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.po.Admin;
import com.neusoft.elm.util.DBUtil;

public class BusinessDaoImpl implements BusinessDao{

	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs  = null;
	
	@Override
	public List<Business> listBusiness() {
		
		//这回调用者依据长度来判断是否查询出数据
		//dao层方法返回一个对象则初始设为Null,若为集合则直接New
		List<Business> list = new ArrayList();
		String sql = "select * from business order by businessId";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {	
				//把当前对象每一个数据封装到当前商家对象对应属性里
				Business business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setPassword(rs.getString("password"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
				list.add(business);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pst, con);
		}
		//必须要释放资源，否则会引起内存泄露，无论是否异常都要执行
		
		return list;
	}
	
	@Override
	public List<Business> listBusiness(String businessName, String businessAddress) {

		List<Business> list = new ArrayList();
		//开发技巧：只要前面有一个无条件成立的条件，则后面条件都可以加"and"
		//若频繁修改字符串，不用string因为实际上是增加很多对象，效率低下
		StringBuffer sql = new StringBuffer("select * from business where 1=1 ");
		
		//模糊查询
		if(businessName != null && !businessName.equals("")) {
			sql.append("and businessName like '%" + businessName + "%' ");
		}
		if(businessAddress != null && !businessAddress.equals("")) {
			sql.append("and businessAddress like '%" + businessAddress + "%' ");
		}
		
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			while(rs.next()) {	
				//把当前对象每一个数据封装到当前商家对象对应属性里
				Business business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setPassword(rs.getString("password"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
				list.add(business);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pst, con);
		}
		//必须要释放资源，否则会引起内存泄露，无论是否异常都要执行
		
		return list;
	}
	
	@Override
	public int saveBusiness(String businessName) {
		
		int businessId = 0;
		String sql = "insert into business(businessName, password) values(?,'123')";
		
		try {
			con = DBUtil.getConnection();
			//设置返回自增长列值
			pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, businessName);
			//用于执行SQl更新语句（insert/update/delete）
			pst.executeUpdate();
			//获取自增长列值（一行一列）
			rs = pst.getGeneratedKeys();
			if(rs.next()) {
				//返回的不知道列名，故用数字代表第几列
				businessId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pst, con);
		}	
		
		return businessId;
	}
	
	@Override
	public int removeBusiness(int businessId) {
		
		int result = 0;
		String delFoodSql = "delete from food where businessId=?";
		String delBusinessSql = "delete from business where businessId=?";
		
		try {
			con = DBUtil.getConnection();
			//保证二者同时完成，用到事务。JDBC不自动提交事务即为一个事务
			con.setAutoCommit(false);
			
			pst = con.prepareStatement(delFoodSql);
			pst.setInt(1, businessId);
			pst.executeUpdate();
			
			pst = con.prepareStatement(delBusinessSql);
			pst.setInt(1, businessId);
			//返回更新的行数
			result = pst.executeUpdate();
			 
			//都成功，正常提交。否则回滚，恢复DB到该事务前的状态
			//int a=10/0;用于测试回滚
			con.commit();
			
		} catch (SQLException e) {
			result = 0;
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.close(null, pst, con);//未用到rs
		}	

		return result;
	}
	
	@Override
	public Business getBusinessByIdByPass(Integer businessId, String password) {
		
		Business business = null;
		String sql = "select * from business where businessId=? and password=?";
		
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, businessId);
			pst.setString(2, password);
			rs = pst.executeQuery();
			
			while(rs.next()) {	
				//把当前对象每一个数据封装到当前商家对象对应属性里
				business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setPassword(rs.getString("password"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pst, con);
		}
		//必须要释放资源，否则会引起内存泄露，无论是否异常都要执行
		
		return business;
	}
	
	@Override
	public Business getBusinessById(Integer businessId) {
		
		Business business = null;
		String sql = "select * from business where businessId=?";
		
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, businessId);
			rs = pst.executeQuery();
			
			while(rs.next()) {	
				//把当前对象每一个数据封装到当前商家对象对应属性里
				business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setPassword(rs.getString("password"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pst, con);
		}
		//必须要释放资源，否则会引起内存泄露，无论是否异常都要执行
		
		return business;
	}
	
	@Override
	public int updateBusiness(Business business) {
		
		int result = 0;
		String sql = "update business set businessName=?,businessAddress=?,businessExplain=?,starPrice=?,deliveryPrice=? where businessId=?";
		
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, business.getBusinessName());
			pst.setString(2, business.getBusinessAddress());
			pst.setString(3, business.getBusinessExplain());
			pst.setDouble(4, business.getStarPrice());
			pst.setDouble(5, business.getDeliveryPrice());
			pst.setInt(6, business.getBusinessId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, pst, con);//未用到rs
		}	

		return result;
	}
	
	@Override
	public int updateBusinessPassword(Integer businessId, String password) {
		
		int result = 0;
		String sql = "update business set password=? where businessId=?";
		
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, password);
			pst.setInt(2, businessId);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, pst, con);//未用到rs
		}	

		return result;
	}
	
}
