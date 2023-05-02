package com.ohalfmoon.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ohalfmoon.domain.MemberVO;
import com.ohalfmoon.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("member")
@Log4j
@AllArgsConstructor
public class MemberController {
	private MemberService memberService;
	
	@GetMapping("login")
	public void login() {}
	
//	@GetMapping("chat")
//	public void chat() {}
//	
//	@PostMapping("login")
//	public String login(MemberVO vo, HttpSession session, RedirectAttributes rttr) {
//		log.warn(vo);
//		MemberVO memberVO = memberService.get(vo);
//		if (memberVO == null) {
//			rttr.addFlashAttribute("msg", "로그인 실패");
//		}
//		else {
//			session.setAttribute("member", memberVO);
//			log.warn("로그인성공");
//			
//		}
//		return "redirect:/member/chat";
//	}
//	@RequestMapping("logout")
//	public String logout(HttpSession session) {
//		log.warn("로그아웃 처리");
//		session.invalidate();
//		return "redirect:/member/chat";
//	}
//	@GetMapping("getList")
//	@ResponseBody
//	public List<MemberVO> getList() {
//		return memberService.getList();
//	}
}
