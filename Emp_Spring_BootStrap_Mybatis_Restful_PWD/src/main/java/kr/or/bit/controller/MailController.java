package kr.or.bit.controller;


import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.bit.vo.mailDto;

@Controller
public class MailController {
	@Autowired
	private JavaMailSender mailSender;
	//@Autowired
	//private MailSender mailSender;
	@Autowired
	private VelocityEngineFactoryBean velocityEngineFactoryBean;



	
	@RequestMapping(value="/mail/mailSending")
	public String send(mailDto maildto) {
		int number = (int)((Math.random()*99999)+100000);
		String temp = String.valueOf(number);
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
			
			Map model = new HashMap();
			model.put("title", maildto.getTitle());
			model.put("content", maildto.getContent());
			model.put("number", number);
			String mailBody = VelocityEngineUtils.mergeTemplateIntoString(velocityEngineFactoryBean.createVelocityEngine(), maildto.getVm(),"UTF-8", model);
			messageHelper.setFrom(maildto.getFrom());
			System.out.println("length:"+maildto.getTo().length);
			for(int i=0; i < maildto.getTo().length; i++) {
				System.out.println("메일주소:" + maildto.getTo()[i]);
			messageHelper.setTo(new String[] {maildto.getTo()[i]});
			messageHelper.setSubject(maildto.getTitle());
			messageHelper.setText(mailBody,true);
			mailSender.send(message);
				System.out.println("i:"+i);
			}
			
		} catch (Exception e) {
			System.out.println("오류 메세지: "+e.getMessage());
		}
		
		return "mail";
	}
	
		 
	
}
