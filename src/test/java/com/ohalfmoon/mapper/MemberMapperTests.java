package com.ohalfmoon.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ohalfmoon.domain.MemberVO;

public class MemberMapperTests {
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	public void testInsert() {
		MemberVO vo =new MemberVO();
		
	}
}
