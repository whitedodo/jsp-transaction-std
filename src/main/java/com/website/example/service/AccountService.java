/*
 * 	생성일자(Create Date): 2020-10-09
 *  주제(Subject): java.sql에서 제공하는 트랜젝션 구현
 *  파일명(Filename): AccountService.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  비고(Description):
 *  
 */

package com.website.example.service;

import java.sql.SQLException;

import com.website.example.vo.AccountVO;

public interface AccountService {
	
	public void accountCreate(AccountVO vo) throws SQLException;
	public void accountTransfer(String sender, String receiver, int money) throws SQLException;
	
}
