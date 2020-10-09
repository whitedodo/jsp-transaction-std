/*
 * 	생성일자(Create Date): 2020-10-09
 *  주제(Subject): java.sql에서 제공하는 트랜젝션 구현
 *  파일명(Filename): JDBCUtil.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  비고(Description):
 *  
 */

package com.website.example.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

public class JDBCUtil {
	
	private static final String driverName = "oracle.jdbc.driver.OracleDriver";
	private static final String jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String userId = "{사용자 계정명}";
	private static final String userPwd = "{비밀번호}";
	
	public static Connection getConnection() {

		try {
			
			Class.forName(driverName);	
			return DriverManager.getConnection(jdbcUrl, userId, userPwd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	public static void close(PreparedStatement stmt, Connection conn) {
		
		if (stmt != null) {
			
			try {
				
				if (!stmt.isClosed())
					
					stmt.close();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			} finally {
				
				stmt = null;
				
			}
			
		} // end of if
		
		if (conn != null) {
			
			try {
				
				if (!conn.isClosed())
					conn.close();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			} finally {
				
				conn = null;
				
			}
			
		} // end of if
		
	}

	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		
		if (rs != null) {
			
			try {
				
				if (!rs.isClosed())
					rs.close();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			} finally {
				
				rs = null;
				
			}
			
		} // end of if
		
		if (stmt != null) {
			
			try {
				
				if (!stmt.isClosed())
					stmt.close();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			} finally {
				stmt = null;
			}
			
		} // end of if
		
		if (conn != null) {
			
			try {
				
				if (!conn.isClosed())
					conn.close();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			} finally {
				
				conn = null;
				
			}
			
		} // end of if
				
	}
}
