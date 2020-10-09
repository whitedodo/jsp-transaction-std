/*
 * 	생성일자(Create Date): 2020-10-09
 *  주제(Subject): java.sql에서 제공하는 트랜젝션 구현
 *  파일명(Filename): TestMain.java
 *  저자(Author): Dodo / rabbit.white at daum dot net
 *  비고(Description):
 *  
 */

package com.website.example.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

import com.website.example.common.MyDataSourceFactory;
import com.website.example.service.AccountService;
import com.website.example.service.AccountServiceImpl;
import com.website.example.vo.AccountVO;

class TestMain {

	@Test
	void test() throws SQLException {
		
		DataSource ds = new MyDataSourceFactory().getOracleDataSource();
		AccountService service = new AccountServiceImpl(ds);
		
		// 1. 계정 생성
		/*
		AccountVO vo = new AccountVO();
		vo.setName("홍길동");
		vo.setBalance(10000);
		vo.setRegidate(Timestamp.valueOf("2020-10-01 10:30:00"));
		
		service.accountCreate(vo);

		vo.setName("홍길자");
		vo.setBalance(0);
		vo.setRegidate(Timestamp.valueOf("2020-10-04 23:20:00"));

		service.accountCreate(vo);
		*/
		
		// 2. 금전 거래
		service.accountTransfer("홍길동", "홍길자", 500);
		
	}

}
