package com.ohalfmoon.service;

import java.util.List;

import com.ohalfmoon.domain.AttachFileDTO;
import com.ohalfmoon.domain.BoardVO;
import com.ohalfmoon.domain.Criteria;

public interface BoardService {
		void register (BoardVO vo);
		
		BoardVO get(Long bno);
		
		boolean modify(BoardVO vo);
		
		boolean remove(Long bno);
		
		List<BoardVO> getList(Criteria cri);
		
		int getTotalCnt(Criteria cri);
		
		String deleteFile(AttachFileDTO dto);

}
