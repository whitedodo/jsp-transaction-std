/*
 * 	생성일자(Create Date): 2020-10-09
 *  주제(Subject): java.sql에서 제공하는 트랜젝션 구현
 *  파일명(Filename): AccountDAOImpl.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  비고(Description):
 *  
 */

package com.website.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.website.example.common.JDBCUtil;
import com.website.example.vo.AccountVO;

// Transaction - 자바
public class AccountDAOImpl implements AccountDAO {

	private final String CREATE_ACCOUNT = "insert into account_tbl" + 
								"(name, balance, regidate) values(?, ?, ?)";
	
	private final String SELECT_BALANCE = "select * from account_tbl where name = ?";
	
	private final String UPDATE_MINUS = "update account_tbl set balance = (select balance from account_tbl where name = ?) - ? " + 
										"where name = ?";
	
	private final String UPDATE_PLUS = "update account_tbl set balance = (select balance from account_tbl where name = ?) + ? " + 
									   "where name = ?";
	
	private DataSource ds = null;
	
	public AccountDAOImpl(DataSource ds) {
		this.ds = ds;
	}
	
	// 단일 쿼리에서의 트랜젝션 방법
	@Override
	public void create(AccountVO vo) throws SQLException {

		Connection conn = ds.getConnection();
		PreparedStatement pstmt = null;
		
		try { 
			conn.setAutoCommit(false);	// 트랜젝션 시작
			
			pstmt = conn.prepareStatement(CREATE_ACCOUNT);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getBalance());
			pstmt.setTimestamp(3, vo.getRegidate());
			
			pstmt.executeUpdate();
			
			conn.commit();
			
		}catch(SQLException e) {
			
			conn.rollback();
			e.getMessage();
			
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}

	@Override
	public int getBalance(String name) throws SQLException {
		
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		try { 
			conn.setAutoCommit(false);	// 트랜젝션 시작
			pstmt = conn.prepareStatement(SELECT_BALANCE);
			
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			if ( rs.next() ) {
				result = rs.getInt(3);
			}
			
			conn.commit();
			
		}catch(SQLException e) {
			
			conn.rollback();
			e.getMessage();
			
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
		return result;
	}

	@Override
	public void plus(String name, int money) throws SQLException {

		Connection conn = ds.getConnection();
		PreparedStatement pstmt = null;
		
		try { 
			
			conn.setAutoCommit(false);
			
			// plus, minus 다 확인 후에 commit처리 해야 함.
			// 그래서 지금 바로 트랜젝션을 구현하면 안 됨
			pstmt = conn.prepareStatement(UPDATE_PLUS);
			pstmt.setString(1, name);
			pstmt.setInt(2, money);
			pstmt.setString(3, name);
			
			pstmt.executeUpdate();
	    	System.out.println(money);

			conn.commit();
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			conn.rollback();
			
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}

	@Override
	public void minus(String name, int money) throws SQLException {
		

		Connection conn = ds.getConnection();
		PreparedStatement pstmt = null;
		
		try { 
			
			conn.setAutoCommit(false);

	    	// 예외 발생시키기(트랜젝션 의도적 발생)
	    	if(true){
	    		throw new SQLException(); // 의도적 예외 발생
	   	    }
	   	    
			// plus, minus 다 확인 후에 commit처리 해야 함.
			// 그래서 지금 바로 트랜젝션을 구현하면 안 됨
			pstmt = conn.prepareStatement(UPDATE_MINUS);
			pstmt.setString(1, name);
			pstmt.setInt(2, money);
			pstmt.setString(3, name);
			
			pstmt.executeUpdate();
			
			conn.commit();
			
		}catch(SQLException e) {
			
			conn.rollback();
			System.out.println(e.getMessage());
			
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
		
	}

	@Override
	public void transfer(String sender, String receiver, int money) throws SQLException {

		Connection conn = ds.getConnection();
		PreparedStatement pstmt = null;
		
		try { 
			
			conn.setAutoCommit(false);

			/*
	    	// 예외 발생시키기(트랜젝션 의도적 발생)
	    	if(true){
	    		throw new SQLException(); // 의도적 예외 발생
	   	    }
	   	    */
	   	    
			// plus, minus 다 확인 후에 commit처리 해야 함.
			// 그래서 지금 바로 트랜젝션을 구현하면 안 됨
			pstmt = conn.prepareStatement(UPDATE_MINUS);
			
			// 주는 분
			pstmt.setString(1, sender);
			pstmt.setInt(2, money);
			pstmt.setString(3, sender);
			
			pstmt.executeUpdate();
			
			// 받는 분
			pstmt = conn.prepareStatement(UPDATE_PLUS);
			pstmt.setString(1, receiver);
			pstmt.setInt(2, money);
			pstmt.setString(3, receiver);
			
			pstmt.executeUpdate();
			
			conn.commit();
			
		}catch(SQLException e) {
			
			conn.rollback();
			System.out.println(e.getMessage());
			
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}

}
