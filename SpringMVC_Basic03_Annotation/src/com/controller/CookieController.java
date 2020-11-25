package com.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {
	
	@RequestMapping("/cookie/make.do")
	public String make(HttpServletResponse response) {
		response.addCookie(new Cookie("atuh","1004")); //servlet 동일
		return "cookie/CookieMake";
	}
	
	@RequestMapping("/cookie/view.do")
	public String view(@CookieValue(value="auth",defaultValue="1007") String auth) {
		//public String view(HttpServletRequest reqeust) 전통적인 방식
		//request.getCookies() 기존 servlet 문제 없음 
		System.out.println("Read a cookie value at Client : "+auth);
		return "cookie/CookieMake";
	}
}
