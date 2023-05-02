package com.ohalfmoon.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohalfmoon.domain.NoteVO;
import com.ohalfmoon.service.NoteService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("note")
@AllArgsConstructor
public class NoteController {
	private NoteService service;
	
	@PostMapping("new")
	public int send(@RequestBody NoteVO vo) {
		log.warn("vo");
		return service.send(vo);
	}
	
	@GetMapping("{noteNo}")
	public NoteVO getNote(@PathVariable Long noteNo) {
		log.warn(noteNo);
		return service.get(noteNo);
	}
	
	@PutMapping("{noteNo}")
	public int receive(@PathVariable Long noteNo) {
		log.warn(noteNo);
		return service.receive(noteNo);
	}
	
	@DeleteMapping("{noteNo}")
	public int remove(@PathVariable Long noteNo) {
		log.warn(noteNo);
		return service.remove(noteNo);
	}		
	
	@GetMapping("s/{id}")
	public List<NoteVO> getSendList(@PathVariable String id) {
		log.warn(id);
		return service.getSendList(id);
	}
	
	@GetMapping("r/{id}")
	public List<NoteVO> getReceiveList(@PathVariable String id) {
		log.warn(id);
		return service.getReceiveList(id);
	}
	
	@GetMapping("r/c/{id}")
	public List<NoteVO> getReceiveCheckedList(@PathVariable String id) {
		log.warn(id);
		return service.getReceiveUncheckedList(id);
	}
}
