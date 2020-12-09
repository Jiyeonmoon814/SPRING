package com.email.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/register")
public class RegistrationController {
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;

	@RequestMapping(method = RequestMethod.POST)
	public String register(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("email") String emailAddress, Model model) {
		
		String status = null;
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
			helper.setFrom("Administrator");
			helper.setTo(emailAddress);
			helper.setSubject("Registration confirmation");
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("username", username);
			params.put("password", password);
			
			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "spring/emailRegistration.vm", "UTF-8", params);
			
			helper.setText(text, true);
			mailSender.send(message);
			status = "Confirmation email is sent to your address (" + emailAddress + ")";
		} catch (MessagingException e) {
			status = "There was an error in email sending. Please check your email address: " + emailAddress;
		}

		model.addAttribute("message", status);
		return "showMessage";
	}
	
	@Autowired
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
}
