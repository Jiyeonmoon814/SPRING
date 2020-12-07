package kr.or.bit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import kr.or.bit.service.KoreaMemberService;
import kr.or.bit.vo.KoreaMember;

@Controller
public class KoreaMemberController {
	@Autowired
	private View jsonview;
	
	@Autowired
	private KoreaMemberService kmservice;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value="/Login.member",method=RequestMethod.GET)
	public String login() {
		return "Login";
	}
	
	@RequestMapping(value="/Register.member", method=RequestMethod.GET)
	public String register() {
		return "KmRegister";
	}
	
	@RequestMapping(value="/Register.member", method=RequestMethod.POST)
	public String register(KoreaMember koreamember){
		int result=0;
		String viewpage="";
		
		//회원가입 암호화
		koreamember.setPwd(this.bCryptPasswordEncoder.encode(koreamember.getPwd()));
		System.out.println(koreamember.toString());
		result=kmservice.insertMember(koreamember);
		if(result>0) {
			System.out.println("Insert Member");
			viewpage="redirect:/index.member";
		}else {
			System.out.println("Fail Insert");
			viewpage="/Register.member";
		}
		return viewpage;
	}
}
