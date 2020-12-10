package com.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.model.Photo;

@Controller
@RequestMapping("/image/upload.do")
public class ImageController {
	
	//같은주소
	//화면 : GET 
	//UPLOAD : POST
	
		@RequestMapping(method=RequestMethod.GET)
		public String form() {
			return "image/image";
		}
		@RequestMapping(method=RequestMethod.POST)
		public String submit(Photo photo , HttpServletRequest request) throws IOException {
			/*
			 1. Photo DTO 타입으로 받기
			 1.1 자동화 : <input 태그 name=속성값이    Photo 타입 클래스의 memberfield 명과 같다
			 1.2  String submit(Photo photo 자동화
			 - Photo photo = new Photo();
			 --photo.setName("홍길동")
			 --photo.setAge(50)
			 --photo.setImage() >> 자동주입 안되요 >> 업로드한 파일명 >> 가공
			 --CommonMultipartFile file 에서 파일이름을 추출해서 
			 --photo.setImage 처리
			 */
			CommonsMultipartFile imagefile = photo.getFile();
			  System.out.println(imagefile.getName());
			  System.out.println(imagefile.getContentType());
			  System.out.println(imagefile.getOriginalFilename());
			  System.out.println(imagefile.getBytes().length);
			  
			  //POINT DB에 들어갈 파일명
			  photo.setImage(imagefile.getName());
			  
			  //실제 파일 업로드 (웹서버에 특정 폴더에 File write) 작업///////////////////
			  String filename = imagefile.getOriginalFilename();
			  String path = request.getServletContext().getRealPath("/upload");
			  			  
			  String fpath = path + "\\" + filename;
			  System.out.println(fpath);
			  FileOutputStream fs = new FileOutputStream(fpath);
			  fs.write(imagefile.getBytes());
			  fs.close();
			  ////////////////////////////////////////////////////////////////////////
			  
			  //필요한 작업  DB작업 >> 처리
			return "image/image";
			
		}
	}

