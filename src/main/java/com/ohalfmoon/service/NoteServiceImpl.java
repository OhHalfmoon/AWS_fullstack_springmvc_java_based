package com.ohalfmoon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ohalfmoon.domain.NoteVO;
import com.ohalfmoon.mapper.NoteMapper;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class NoteServiceImpl implements NoteService{
	private NoteMapper mapper;

	@Override
	public int send(NoteVO vo) {
		return mapper.insert(vo);
	}

	@Override
	public NoteVO get(Long noteNo) {
		return mapper.selectOne(noteNo);
	}

	@Override
	public int receive(Long noteNo) {
		return mapper.update(noteNo);
	}

	@Override
	public int remove(Long noteNo) {
		return mapper.delete(noteNo);
	}

	@Override
	public List<NoteVO> getSendList(String id) {
		return mapper.sendList(id);
	}

	@Override
	public List<NoteVO> getReceiveList(String id) {
		return mapper.receiveList(id);
	}

	@Override
	public List<NoteVO> getReceiveUncheckedList(String id) {
		return mapper.receiveUncheckedList(id);
	}
	
	
}
