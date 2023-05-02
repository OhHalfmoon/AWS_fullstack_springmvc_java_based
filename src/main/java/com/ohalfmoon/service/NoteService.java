package com.ohalfmoon.service;

import java.util.List;

import com.ohalfmoon.domain.NoteVO;

public interface NoteService {
	int send(NoteVO vo);
	
	NoteVO get(Long noteNo);
	
	int receive(Long noteNo);
	
	int remove(Long noteNo);
	
	List<NoteVO> getSendList (String id);
	
	List<NoteVO> getReceiveList (String id);
	
	List<NoteVO> getReceiveUncheckedList (String id);

}
