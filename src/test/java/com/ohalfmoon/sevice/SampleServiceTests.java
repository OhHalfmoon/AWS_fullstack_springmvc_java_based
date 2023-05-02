package com.ohalfmoon.sevice;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ohalfmoon.service.SampleService;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleServiceTests {
	@Autowired
	private SampleService service;

	@Test
	public void testExist() {
		assertNotNull(service);
	}
	@Test
	public void testData() {
		String data = "가나다라마바사 아자차카타파하 가나다라마바사 아자차카타파하 가나다라마바사 아자차카타파하  가나다라마바사 아자차카타파하";
		log.warn(data.length());
		service.addData(data);
	}
	
}
