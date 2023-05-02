package com.ohalfmoon.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ohalfmoon.domain.MemberVO;
import com.ohalfmoon.domain.NoteVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class NoteMapperTests {
//	 @Autowired
//	 private NoteMapper noteMapper;
//	 @Autowired
//	 private MemberMapper memberMapper;
//	 
//	 @Test
//	 public void testInsert2() {
//		 List<MemberVO> members = memberMapper.getList();
//		 int i = 37;
//		 for(MemberVO vo : members) {
//			 for(MemberVO vo2 : members) {
//				 NoteVO noteVO = new NoteVO();
//				 noteVO.setSender(vo.getId());
//				 noteVO.setReceiver(vo2.getId());
//				 noteVO.setMessage("mapper 테스트 발송 :: " + i++);
//				 noteMapper.insert(noteVO);
//			 }
//		 }
//	 }
//	 
//	 	 
//	 @Test
//	 public void testInsert() {
//		 NoteVO noteVO = new NoteVO();
//		 noteVO.setSender("javaman");
//		 noteVO.setReceiver("id3");
//		 noteVO.setMessage("mapper 테스트 발송");
//		 
//		 noteMapper.insert(noteVO);
//	 }
//	 @Test
//	 public void testSelectOne() {
//		 log.warn(noteMapper.selectOne(1L));
//	 }
//	 
//	 @Test
//	 public void testUpdate() {
//		 noteMapper.update(1L);
//	 }
//	 
//	 @Test
//	 public void testDelete() {
//		 noteMapper.delete(3L);
//	 }
//	 
//	 @Test
//	 public void testSendList() {
//		 noteMapper.sendList("id1").forEach(log::warn);
//	 }
//	 
//	 @Test
//	 public void testReceiveList() {
//		 noteMapper.receiveList("id2").forEach(log::warn); 		 
//	 }
//	 
//	 @Test
//	 public void testReceiveUncheckedList() {
//		 noteMapper.receiveUncheckedList("javaman").forEach(log::warn);
//	 }
	 
}
