package com.ohalfmoon.mapper;

import java.util.List;

import com.ohalfmoon.domain.AttachVO;

public interface AttachMapper {
	void insert(AttachVO vo);
	
	void delete(String uuid);
	
	List<AttachVO> findBy(Long bno);
	
	void deleteAll(Long bno);
	
	List<AttachVO> getOldFiles();
}
