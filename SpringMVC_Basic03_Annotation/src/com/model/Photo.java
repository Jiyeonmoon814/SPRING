package com.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/*
 DB에 
 create table photo(
 name
 age
 image >> uploaded filename, not an actual file >> 1.jpg, 2.png... 
 )*/
public class Photo {
	private String name;
	private int age;
	private String image; // actual filenames 
	//CommonsMultipartFile file 통해서 파일명을 추출 
	
	//point ***
	private CommonsMultipartFile file; // variable which put uploaded file information
	//image : <input type="file" name="file"
	
	public CommonsMultipartFile getFile() {
		return file;
	}
	
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
       
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
}
