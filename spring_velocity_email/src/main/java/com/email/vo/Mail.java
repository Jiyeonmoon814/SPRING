package com.email.vo;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Mail {
	
	private String receiverMail;
	private String subject;
	private String message;
	private CommonsMultipartFile attachment;
	
	public String getReceiverMail() {
		return receiverMail;
	}
	public void setReceiverMail(String receiverMail) {
		this.receiverMail = receiverMail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CommonsMultipartFile getAttachment() {
		return attachment;
	}
	public void setAttachment(CommonsMultipartFile attachment) {
		this.attachment = attachment;
	}
}
