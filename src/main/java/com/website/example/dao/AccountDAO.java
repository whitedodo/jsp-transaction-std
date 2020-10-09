/*
 * 	생성일자(Create Date): 2020-10-09
 *  주제(Subject): java.sql에서 제공하는 트랜젝션 구현
 *  파일명(Filename): AccountDAO.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  비고(Description):
 *  
 */

package com.website.example.dao;

import java.sql.SQLException;

import com.website.example.vo.AccountVO;

public interface AccountDAO {

	// 계좌 생성
	public void create(AccountVO vo) throws SQLException;
	
	// 잔액 조회
	public int getBalance(String name) throws SQLException;
	
	// 플러스 계좌
	public void plus(String name, int money) throws SQLException;
	
	// 마이너스 계좌
	public void minus(String name, int money) throws SQLException;
	
	// 거래
	public void transfer(String sender, String receiver, int money) throws SQLException;
	
}
