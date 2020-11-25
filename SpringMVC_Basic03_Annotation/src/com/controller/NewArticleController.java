package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.NewArticleCommand;
import com.service.ArticleService;

/*
  Client's request
 1.화면 (글쓰기, 로그인) : write.do
 2.처리 (글쓰기 완료 처리, 로그인 완료 처리) : writeok.do 
 >> 요청 주소가 **다름** 
 * 
 * 
 Client's request
 Spring 에서는 요청 주소 하나로 화면 ,처리를 판단함 
 판단의 근거 >> 전송방식이 GET or POST *** 
 /article/newArticle.do >> if method is GET >> VIEW 
  * 					   if method is POST >> SERVICE 
 1.화면 >> GET >> view
 2.처리 >> POST >> SERVICE >> insert, update
 
 */


@Controller
@RequestMapping("/article/newArticle.do")
//"/article/newArticle.do" >> index html href 
public class NewArticleController {
	
	private ArticleService articleservice;
	
	@Autowired
	public void setArticleservice(ArticleService articleservice) {
		this.articleservice = articleservice;
	}
	
	//5.x.x >> @GetMapping(), @PostMapping()
	@RequestMapping(method=RequestMethod.GET) // 화면 함수 실행
	public String form() { //함수 RETURN TYPE STRING VIEW ADDR 
		return "article/newArticleForm";
		//viewResolver : >> /WEB-INF/views/+article/newArticleForm+".jst" 
	}
	
	/*
	   1. 가장 전통적인 방법 Client 데이터 받아서 DTO 연결 (Spring 에서 x) 
	     
	   @RequestMapping(method = RequestMethod.POST) //처리 insert 해주세요
	   public ModelAndView submit(HttpServletRequest request) {
		   NewArticleCommand article = new NewArticleCommand();
		   article.setParentId(Integer.parseInt(request.getParameter("parentId")));
		   article.setTitle(request.getParameter("title"));
		   article.setContent(request.getParameter("content"));
		   
		   articleservice.writeArticle(article);
		   //처리 완료
		   
		   ModelAndView mv = new ModelAndView();
		   mv.addObject("newArticleCommand", article);
		   mv.setViewName("article/newArticleSubmitted");
		   
		   return mv;
	   }
	   */

	   /*
	     2. Spring 에서 parameter 를  DTO 객체 받기
	     2.1 자동화 >> 전제조건 : <input name="값" >> 값이 DTO 객체 >> member field 명과 동일
	     
	     submit(NewArticleCommand command) 
	     1. 자동  DTO 객체 생성  >> NewArticleCommand article = new NewArticleCommand();
	     2. 넘어온 parameter setter 통해서 자동 주입
	     3. NewArticleCommand 객체는  IOC 컨테이너 안에 자동 생성   >> id="newArticleCommand"
	     
	       >>  public ModelAndView submit()
	       ModelAndView mv = new ModelAndView();
		   mv.addObject("newArticleCommand", command);
		   mv.setViewName("article/newArticleSubmitted");
		   return mv;
	       위 코드를 사용해도 문제 없어요
	       조금더 코드를 줄여 .....
	        >> mv.addObject("newArticleCommand", command); 
	             자동 생성
	       NewArticleCommand() >> IOC 컨테이너 생성
	       <bean id="newArticleCommand"   class="">
	       >> forward >> view >> newArticleCommand 이름 객체 
	    
	    
	    public String submit(NewArticleCommand command) {
	    1. NewArticleCommand 객체 생성
	    2. setter 자동 주입
	    3. forward 되는 페이지에 자동 전달 (key) >> newArticleCommand
	    
	    3. 객체의 이름(key) 자동 생성되는 것이 싫어요
	    public String submit(@ModelAttribute("Articledata") NewArticleCommand command) 
	    
	    @ModelAttribute("Articledata") 가 아래 두줄의 코드를 대체 
	    
	    NewArticleCommand Articledata = new NewArticleCommand();
	    mv.addObject("Articledata",Articledata);
	    
	    
	    */
	
	@RequestMapping(method=RequestMethod.POST) // 서비스를 실행
	public String submit(@ModelAttribute("Articledata")NewArticleCommand command) {
		//System.out.println(command.toString());
		//mv.addObject("newArticleCommand",command);
		//자동생성
		
		//@ModelAttribute
		//mv.addObject("Articledata",command);
		
		articleservice.writeArticle(command);
		
		return "article/newArticleSubmitted";
	}
}
