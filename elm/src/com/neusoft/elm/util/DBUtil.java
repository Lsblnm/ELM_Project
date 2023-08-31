package com.neusoft.elm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/elm?serverTimezone=GMT%2B8&characterEncoding=utf-8";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";

	private static final ThreadLocal<Connection> TL = new ThreadLocal<Connection>();

	private static Connection createConnection() {
		Connection con = null;
		if (con == null) {
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return con;
	}

	// ��ȡconnection
	public static Connection getConnection() {
		Connection con = null;
		con = TL.get();
		if (con == null) {
			con = createConnection();
			TL.set(con);
		}
		return con;
	}

	// ����һ������
	public static void beginTransaction() throws Exception {
		Connection con = null;
		con = TL.get();
		if (con == null) {
			con = createConnection();
			TL.set(con);
		}
		con.setAutoCommit(false);
	}

	// �ύһ������
	public static void commitTransaction() throws Exception {
		Connection con = TL.get();
		con.commit();
	}

	// �ع�һ������
	public static void rollbackTransaction() throws Exception {
		Connection con = TL.get();
		con.rollback();
	}

	// �رո�����Դ
	public static void close(ResultSet rs, PreparedStatement pst) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// �رո�����Դ
	public static void close(PreparedStatement pst) {
		try {
			if (pst != null) {
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// �رո�����Դ
	public static void close() {
		Connection con = TL.get();
		try {
			if (con != null) {
				con.close();
			}
			// ������Ҫ��������������ڴ���������⡣
			TL.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
