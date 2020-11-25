package com.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 Cons : HelloController 1개가 요청, 서비스 1개 
 서비스 요청 개수만큼 controller를 생성해야 함 
 ListController, NoticeController...
 public class HelloController implements Controller{
 	@Override
 	public ModelAndView hanleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
 	}
 }
 
 @Controller 사용하면 method 단위의 mapping 가능 
 함수단위 매칭 >> 요청당 >> 함수 한개 
  */

@Controller
public class HelloController {
	public HelloController() {
	 System.out.println("HelloController 생성자 호출");
	}
	
	@RequestMapping("/hello.do") //<a href="hello.do"></a>
	public ModelAndView hello() {
		System.out.println("[Call hello.do method]");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("greeting",getGreeting());
		mv.setViewName("Hello");
		return mv;
	}
	
	private String getGreeting() {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		String data="";
		if(hour>=6&&hour<=10) {
			data="Time for study";
		}else if(hour>=11&&hour<=13) {
			data="Lunch Time";
		}else if(hour>=14&&hour<=18) {
			data="Study hall";
		}else {
			data="Go home";
		}
		return data;
	}
}
