package com.ohalfmoon.controller;

import java.security.Principal;
import java.util.List;

import javax.imageio.spi.RegisterableService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ohalfmoon.domain.AttachVO;
import com.ohalfmoon.domain.BoardVO;
import com.ohalfmoon.domain.Criteria;
import com.ohalfmoon.domain.PageDto;
import com.ohalfmoon.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("board/*")
@Data
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("list")
	public void list(Model model, Criteria cri) {
		log.info("list()");
		log.warn(cri);
		model.addAttribute("list", boardService.getList(cri));
		model.addAttribute("page", new PageDto(boardService.getTotalCnt(cri), cri));
		
	}
	
    @GetMapping({"get", "modify"})
    public void get(Model model, Long bno, @ModelAttribute("cri") Criteria cri) {
        log.warn("get() or modify()");
        log.warn(bno);
        log.warn(cri);
        
        model.addAttribute("board", boardService.get(bno));
    }
    
    @GetMapping("{bno}")
    public String getByPath(Model model, @PathVariable Long bno) {
        log.warn(bno);
        model.addAttribute("board", boardService.get(bno));
        return "board/get";
    }
    
    @GetMapping("register")
    @PreAuthorize("isAuthenticated()")
    public void Register() {}
	
	@PostMapping("register")
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO vo, RedirectAttributes rttr, Criteria cri) {
		log.warn("register");
		log.warn(vo);
		boardService.register(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addAttribute("pageNum", 1);
		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:/board/list";
	}
	@PreAuthorize("isAuthenticated() and principal.username eq #board.writer")
	@PostMapping("modify")
	public String modify(BoardVO vo, RedirectAttributes rttr, Criteria cri) {

		log.info("modify");
		log.info(vo);
		log.info(cri);
		if (boardService.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
	}
		return "redirect:/board/list" + cri.getFullQueryString();
	}
	
	@PreAuthorize("isAuthenticated() and principal.username eq #writer")
    @PostMapping("remove")
    public String remove(String writer, Long bno, RedirectAttributes rttr, Criteria cri) {
        log.info("remove");
        log.info(bno);
        log.info(cri);
        List<AttachVO> list = boardService.get(bno).getAttachs();
        if(boardService.remove(bno)) {
        	for(AttachVO vo : list) {
        		boardService.deleteFile(vo);
        	}
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list" + cri.getFullQueryString();
    }
    

}
