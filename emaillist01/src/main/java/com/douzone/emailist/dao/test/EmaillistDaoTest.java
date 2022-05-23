package com.douzone.emailist.dao.test;

import com.douzone.emailist.vo.EmaillistVo;

public class EmaillistDaoTest {

	public static void main(String[] args) {
		
	}
	
	private static void testInsert() {
		
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName("마");
		vo.setLastName("이콜");
		vo.setEmail("test@test.com");
	}
	
	private static void testFindAll() {
		
	}
}
