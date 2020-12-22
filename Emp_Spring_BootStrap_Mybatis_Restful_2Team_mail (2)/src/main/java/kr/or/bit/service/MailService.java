package kr.or.bit.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	// @Autowired
	// private MailSender mailSender;
	@Autowired
	private VelocityEngineFactoryBean velocityEngineFactoryBean;
	
	public void mailsend( HttpSession session,String mail) {
		int number = (int) ((Math.random() * 99999) + 100000);
		String temp = String.valueOf(number);
		String mailFrom = "관리자";
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			long start = System.currentTimeMillis();

			Map model = new HashMap();
			model.put("number", number);

			String mailBody = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngineFactoryBean.createVelocityEngine(), "mail.vm", "UTF-8", model);
			//messageHelper.setFrom("ehddus299@gmail.com");
			messageHelper.setFrom("ehddus299@gmail.com", mailFrom);
			messageHelper.setTo(mail);
			messageHelper.setSubject("회원가입 인증번호");
			messageHelper.setText(mailBody, true);
			mailSender.send(message);
			
			long end = System.currentTimeMillis();
			session.setAttribute("number"
					, number);
			session.setAttribute("mail", mail);
			System.out.println("메일전송:"+mail);
		} catch (Exception e) {
			System.out.println("오류 메세지: " + e.getMessage());
		}

	}
	public String emailCertification(HttpSession session, String mail, int code) {
		String result = "";
		try {
			String checkMail = (String)session.getAttribute("mail");
			int generationCode = (int) session.getAttribute("number");
			System.out.println("메일체크:"+mail);
			System.out.println("메일체크2:"+checkMail);
			if(generationCode == code && checkMail.equals(mail)) {
				result= "true";
			}else if(generationCode == code){ 
				result="codetrue";
			}else if(checkMail.equals(mail)){ 
				result="mailtrue";
			}else {
				result= "false";
			}
		} catch (Exception e) {
			throw e;
			
		}
		return result;
	}

}
