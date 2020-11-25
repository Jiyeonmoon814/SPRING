package com.service;

import org.springframework.stereotype.Service;

import com.model.NewArticleCommand;
/*
@Service
public class ArticleService

xml 상단에
<context:component-scan base-package="com.service" />
클래스 위에 @Service 있으면 자동  빈 객체 생성
*/


//@Service
public class ArticleService {
		public ArticleService(){
			System.out.println("ArticleService 생성자 호출");
		}
		
		public void writeArticle(NewArticleCommand command) {
			//DAO 객체 생성
			//DAO 객체  insert 함수 호출
			System.out.println("글쓰기 작업 완료 : " + command.toString());
		}
}
