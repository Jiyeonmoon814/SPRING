package com.email.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.email.service.EmailService;
import com.email.vo.Mail;
import com.email.vo.Register;

@Controller
public class EmailController {
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;

	@Autowired
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	//////////
	@RequestMapping(value = "/Main.do", method = RequestMethod.GET)
	public String Main() {
		return "../../index";
	}

	@RequestMapping(value = "/RegisterForm.do", method = RequestMethod.GET)
	public String register() {
		return "RegisterForm";
	}

	@RequestMapping(value = "/RegisterForm.do", method = RequestMethod.POST)
	public String register(Register register, Model model) {

		String status = null;

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("Administrator");
			helper.setTo(register.getEmail());
			helper.setSubject("Registration confirmation");
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("username", register.getUsername());
			params.put("password", register.getPassword());
			
			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "spring/emailImage.vm",
					"UTF-8", params);
			helper.setText(text, true);
			helper.addInline("welcome", new File("E:\\SPRING\\Labs\\spring_velocity_email\\src\\main\\resources\\spring\\welcome.jpg"));
			
			mailSender.send(message);
			status = "Confirmation email is sent to your address (" + register.getEmail() + ")";
		} catch (MessagingException e) {
			status = "There was an error in email sending. Please check your email address: " + register.getEmail();
		}

		model.addAttribute("message", status);
		return "showMessage";
	}
	
	@RequestMapping(value = "/VeloTemplate.do", method = RequestMethod.GET)
	public String velpTemp() {
		return "VeloTemplate";
	}

	@RequestMapping(value = "/VeloTemplate.do", method = RequestMethod.POST)
	public String veloTemp(Register register, Model model) {

		String status = null;

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("Administrator");
			helper.setTo(register.getEmail());
			helper.setSubject("Registration confirmation");
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("username", register.getUsername());
			params.put("password", register.getPassword());
			
			String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "spring/"+register.getVm(),
					"UTF-8", params);
			helper.setText(text, true);
			mailSender.send(message);
			status = "Confirmation email is sent to your address (" + register.getEmail() + ")";
		} catch (MessagingException e) {
			status = "There was an error in email sending. Please check your email address: " + register.getEmail();
		}

		model.addAttribute("message", status);
		return "showMessage";
	}
	
	@RequestMapping(value = "/AttachmentsEmail.do", method = RequestMethod.GET)
	public String AttachmentsEmail() {
		return "AttachmentsEmail";
	}

	@RequestMapping(value = "/AttachmentsEmail.do", method = RequestMethod.POST)
	public String AttachmentsEmail(@ModelAttribute Mail mail, Model model) {
		String status = null;
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setFrom("Administrator");
			helper.setTo(mail.getReceiverMail());
			helper.setSubject(mail.getSubject());
			String text = mail.getMessage();
			helper.setText(text, true);
			
			String attachName = mail.getAttachment().getOriginalFilename();
			if (!mail.getAttachment().equals("")) {
				helper.addAttachment(attachName, new InputStreamSource() {
					@Override
					public InputStream getInputStream() throws IOException {
						return mail.getAttachment().getInputStream();
					}
				});
			}
			mailSender.send(message);
			status = "email is sent to your address (" + mail.getReceiverMail() + ")";
		} catch (MessagingException e) {
			status = "There was an error in email sending. Please check your email address: " + mail.getReceiverMail();
		}

		model.addAttribute("message", status);
		return "showMessage";
	}

	@RequestMapping(value = "/SummerNoteEmail.do", method = RequestMethod.GET)
	public String summerNote() {
		return "SummerNoteEmail";
	}

	@RequestMapping(value = "/SummerNoteEmail.do", method = RequestMethod.POST)
	public String summerNote(@ModelAttribute Mail mail, Model model) {
		String status = null;
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setFrom("Administrator");
			helper.setTo(mail.getReceiverMail());
			helper.setSubject(mail.getSubject());
			String text = mail.getMessage();
			helper.setText(text, true);

			mailSender.send(message);
			status = "email is sent to your address (" + mail.getReceiverMail() + ")";
		} catch (MessagingException e) {
			status = "There was an error in email sending. Please check your email address: " + mail.getReceiverMail();
		}

		model.addAttribute("message", status);
		return "showMessage";
	}
}
