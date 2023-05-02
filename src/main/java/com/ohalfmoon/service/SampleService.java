package com.ohalfmoon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ohalfmoon.mapper.SampleMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
@Transactional
public class SampleService {
	private SampleMapper mapper;
	
	public void addData(String data) {
		log.warn("insert1()");
		mapper.insert1(data);
		log.warn("insert2()");
		mapper.insert2(data);
		log.warn("end");		
	}
	
	
	public void addData2(String data) {
		log.warn("insert1()");
		mapper.insert1(data);
		log.warn("insert2()");
		mapper.insert2(data);
		log.warn("insert2()");
		mapper.insert2(data);
		log.warn("insert2()");
		mapper.insert2(data);
		log.warn("end");		
	}
}
