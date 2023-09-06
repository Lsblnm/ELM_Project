package com.neusoft.elm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/*事务问题：如生成一个订单所需三个步骤构成一个事务:向订单表插入一个记录、订单明细表插入多条记录，从购物车中删除所有商品。交易中service要调用三个dao，因为是同一个业
 service创建的connection得三个dao共用，所以引入threadlocal来保证线程间隔离。
 
 * 解决事务问题:后边有的功能涉及事务，必须要求事务处理放在service层，所以层次上有要求：
 1. 为保证同一次请求处理线程中，service层和dao层都共用一个connection对象，需要将Connection
对象放入ThreadLocal中。service层和dao层使用的Connection对象一律从ThreadLocal获取
 2. 事务处理jdbc放在dao层，servlet中放在service层。
 3. Service层创建销毁connetion；dao层关闭pst和rs。dao层抛出异常，service处理异常
 
 */

public class DBUtil {
	
	private static final String URL = "jdbc:mysql://localhost:3306/elm?characterEncoding=utf-8";//放数据库连接字符串
	private static final String DRIVER = "com.mysql.jdbc.Driver";//驱动类路径
	private static final String USERNAME = "root"; 
	private static final String PASSWORD = "7758258Xdd";//待定
	
	private static final ThreadLocal<Connection> TL = new ThreadLocal();
	
	//获取connection
	public static Connection getConnection() {
		Connection con = null;
		con = TL.get();
		//threadlocal空的则创建一个
		if(con == null) {
			con = createConnection();
			TL.set(con);
		}
		return con;
	}
	
	//开启一个事务
	public static void beginTransaction() throws Exception{
		Connection con = null;
		con = TL.get();
		if(con == null) {
			con = createConnection();
			TL.set(con);
		}
		//开启一个事务
		con.setAutoCommit(false);
	}
	
	//提交一个事务
	public static void commitTransaction() throws Exception {
		Connection con = TL.get();
		con.commit();
	}

	//回滚一个事务
	public static void rollbackTransaction() throws Exception{
		Connection con = TL.get();
		con.rollback();
	}
	
	//查询要关rs
	public static void close(ResultSet rs, PreparedStatement pst) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pst != null) {
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//增删改用不着rs
	public static void close(PreparedStatement pst) {
		try {
			if(pst != null) {
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//关闭con
	public static void close() {
		Connection con = TL.get();
		try {
			if(con != null) {
				con.close();
			}
			//con虽然关闭了，但是还在线程里，必须家否则内存会泄露
			TL.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	private static Connection createConnection() {
		Connection con = null;
		if(con==null) {
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	
}
