package com.ohalfmoon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ohalfmoon.domain.NoteVO;

public interface NoteMapper {
	// CRUD
	//노트작성
	@Insert("INSERT INTO TBL_NOTE (NOTENO, SENDER, RECEIVER, MESSAGE) VALUES(SEQ_NOTE.NEXTVAL, #{sender}, #{receiver}, #{message})")
	int insert(NoteVO vo);
	
	//단일조회
	@Select("SELECT * FROM TBL_NOTE WHERE NOTENO = #{noteNo}")
	NoteVO selectOne(Long noteNo);
	
	//수신확인
	@Update("UPDATE TBL_NOTE SET RDATE = SYSDATE WHERE NOTENO = #{noteNo}")
	int update(Long noteNo);
	
	//노트삭제
	@Delete("DELETE FROM TBL_NOTE WHERE NOTENO = #{noteNo}")
	int delete(Long noteNo);
	
	//발신

	@Select("SELECT * FROM TBL_NOTE WHERE NOTENO > 0 and SENDER = #{sender} ORDER BY 1 desc")
	List<NoteVO> sendList(String sender);
	
	//수신
	@Select("SELECT * FROM TBL_NOTE WHERE NOTENO > 0 and RECEIVER = #{receiver} ORDER BY 1 desc")
	List<NoteVO> receiveList(String receiver);
	
	//미확인
	@Select("SELECT * FROM TBL_NOTE WHERE NOTENO > 0 and RECEIVER = #{receiver} AND RDATE IS null ORDER BY 1 desc")
	List<NoteVO> receiveUncheckedList(String receiver);
}
