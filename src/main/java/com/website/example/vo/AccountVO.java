/*
 * 	생성일자(Create Date): 2020-10-09
 *  주제(Subject): java.sql에서 제공하는 트랜젝션 구현
 *  파일명(Filename): AccountVO.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  비고(Description):
 *  
 */

package com.website.example.vo;

import java.sql.Timestamp;

public class AccountVO {

	private int idx;
	private String name;
	private int balance;
	private Timestamp regidate;
	
	public int getIdx() {
		return idx;
	}
	
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Timestamp getRegidate() {
		return regidate;
	}
	
	public void setRegidate(Timestamp regidate) {
		this.regidate = regidate;
	}
	
}
