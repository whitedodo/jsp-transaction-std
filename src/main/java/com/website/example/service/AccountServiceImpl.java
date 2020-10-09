/*
 * 	생성일자(Create Date): 2020-10-09
 *  주제(Subject): java.sql에서 제공하는 트랜젝션 구현
 *  파일명(Filename): AccountServiceImpl.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  비고(Description):
 *  
 */

package com.website.example.service;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.website.example.dao.AccountDAO;
import com.website.example.dao.AccountDAOImpl;
import com.website.example.vo.AccountVO;

public class AccountServiceImpl implements AccountService {

	private AccountDAO accountDAO;
	private DataSource ds = null;
	
	public AccountServiceImpl(DataSource ds) {
		
		accountDAO = new AccountDAOImpl(ds);
		this.ds = ds;
	}

	@Override
	public void accountCreate(AccountVO vo) throws SQLException {
		
		accountDAO.create(vo);
		
	}
	
    public void accountTransfer(String sender, String receiver, int money) throws SQLException{
        
    	int balance = accountDAO.getBalance(sender); // 보내는 사람 잔액 체크
    	
        if(balance >= money){ // 보내는 돈이 잔액보다 많으면
	
        	accountDAO.transfer(sender, receiver, money);
        	
        } else{
        	System.out.println("돈 없음");
        	//throw new NoMoneyException();
        }
        
    }

	
}
