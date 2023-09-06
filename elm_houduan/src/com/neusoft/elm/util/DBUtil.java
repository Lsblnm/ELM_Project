package com.neusoft.elm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/*�������⣺������һ�����������������蹹��һ������:�򶩵������һ����¼��������ϸ����������¼���ӹ��ﳵ��ɾ��������Ʒ��������serviceҪ��������dao����Ϊ��ͬһ��ҵ
 service������connection������dao���ã���������threadlocal����֤�̼߳���롣
 
 * �����������:����еĹ����漰���񣬱���Ҫ�����������service�㣬���Բ������Ҫ��
 1. Ϊ��֤ͬһ���������߳��У�service���dao�㶼����һ��connection������Ҫ��Connection
�������ThreadLocal�С�service���dao��ʹ�õ�Connection����һ�ɴ�ThreadLocal��ȡ
 2. ������jdbc����dao�㣬servlet�з���service�㡣
 3. Service�㴴������connetion��dao��ر�pst��rs��dao���׳��쳣��service�����쳣
 
 */

public class DBUtil {
	
	private static final String URL = "jdbc:mysql://localhost:3306/elm?characterEncoding=utf-8";//�����ݿ������ַ���
	private static final String DRIVER = "com.mysql.jdbc.Driver";//������·��
	private static final String USERNAME = "root"; 
	private static final String PASSWORD = "7758258Xdd";//����
	
	private static final ThreadLocal<Connection> TL = new ThreadLocal();
	
	//��ȡconnection
	public static Connection getConnection() {
		Connection con = null;
		con = TL.get();
		//threadlocal�յ��򴴽�һ��
		if(con == null) {
			con = createConnection();
			TL.set(con);
		}
		return con;
	}
	
	//����һ������
	public static void beginTransaction() throws Exception{
		Connection con = null;
		con = TL.get();
		if(con == null) {
			con = createConnection();
			TL.set(con);
		}
		//����һ������
		con.setAutoCommit(false);
	}
	
	//�ύһ������
	public static void commitTransaction() throws Exception {
		Connection con = TL.get();
		con.commit();
	}

	//�ع�һ������
	public static void rollbackTransaction() throws Exception{
		Connection con = TL.get();
		con.rollback();
	}
	
	//��ѯҪ��rs
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
	
	//��ɾ���ò���rs
	public static void close(PreparedStatement pst) {
		try {
			if(pst != null) {
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//�ر�con
	public static void close() {
		Connection con = TL.get();
		try {
			if(con != null) {
				con.close();
			}
			//con��Ȼ�ر��ˣ����ǻ����߳������ҷ����ڴ��й¶
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
