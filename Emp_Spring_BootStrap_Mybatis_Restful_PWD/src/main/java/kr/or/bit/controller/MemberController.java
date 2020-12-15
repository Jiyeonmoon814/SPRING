package kr.or.bit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.bit.service.MemberService;
import kr.or.bit.vo.Member;

@Controller
public class MemberController {
	
	private MemberService memberservice;
	@Autowired
	public void setEmpservice(MemberService memberservice) {
		this.memberservice = memberservice;
	}
	
	@RequestMapping(value="/signup.member",method=RequestMethod.GET)
	public String insertView(Member member) {
		return "join/MemberSignup";
	}
	
	@RequestMapping(value="/signup.member",method=RequestMethod.POST)
	public String insert(Member member) {
		String url = memberservice.insertMember(member);
		return "redirect:" + url;
	}
	
	
}
