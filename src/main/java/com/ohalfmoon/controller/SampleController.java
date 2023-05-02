package com.ohalfmoon.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohalfmoon.domain.SampleVO;

import lombok.extern.log4j.Log4j;
/** 
 * controller 란?
 * 요청과 응답처리 제어
 * request : 1. URL(포트 이후) 2. 파라미터 수집 3. HTTP Method(GET, POST, PUT, DELETE)... attr, session, cookie
 * response : 1. MIME-TYPE(JSP) cf.forward, viewResolver 2. header(text/html, application/json, application/octet-stream)
 */
@RestController
@RequestMapping("sample")
@Log4j
public class SampleController {
	@GetMapping(value="getText", produces="text/plain; charset=utf-8")
	public String getText() {
		return "안녕하세요";
	}
	@GetMapping("getSample")
	public SampleVO getSample() {
		return new SampleVO(112, "스타", "로드");
	}
	@GetMapping("getList")
	public List<SampleVO>getList() {
		return IntStream.rangeClosed(1, 10).mapToObj(i -> new SampleVO(i, "first "+i, "last "+i)).collect(Collectors.toList());
	}
	
	@GetMapping("getMap")
	public Map<String, SampleVO> getMap() {
		Map<String, SampleVO> map = new HashMap<>();
		map.put("first", new SampleVO(111,"그루트", "주니어"));
		return map;
	}	
	
	@GetMapping(value="check", params={"height", "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		SampleVO vo = new SampleVO(0, String.valueOf(height), String.valueOf(weight));
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);			
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		return result;	
	}
	
	@GetMapping("product/{cat}/{pid}")
	public String[] getPath (@PathVariable String cat, @PathVariable String pid) {
		return new String[] {"category : " + cat, "productid : " + pid};
	}
	@GetMapping("product/{cat2}/{pid2}/{test}")
	public String[] getPath2 (String cat2, String pid2) {
		return new String[] {"category : " + cat2, "productid : " + pid2};
	}
	
	@GetMapping("sample")
	public SampleVO convert (@RequestBody SampleVO sampleVO) {
		log.warn(sampleVO);
		return sampleVO;
	}
	
}
