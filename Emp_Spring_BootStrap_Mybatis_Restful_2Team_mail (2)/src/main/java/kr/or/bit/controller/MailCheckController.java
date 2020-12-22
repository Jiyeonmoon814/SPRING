package kr.or.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.bit.service.MailService;
import kr.or.bit.service.MemberRestService;
import kr.or.bit.vo.Member;
import kr.or.bit.vo.mailDto;

@Controller
public class MailCheckController {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngineFactoryBean velocityEngineFactoryBean;

	@RequestMapping("/mail.member")
	public String goIndex() {
		return "mail";
	}

	@RequestMapping("/mailjoin.member")
	public String mailJoin() {
		return "mailJoin";
	}
	@Autowired
	private MailService mailservice;
	@ResponseBody
	@RequestMapping(value = "/mailSend.member")
	private String sendEmail(HttpServletRequest request, String mail) {
		HttpSession session = request.getSession();
		mailservice.mailsend(session,mail);
		return null;
	}

	@ResponseBody
	@RequestMapping("/mailCheck.member")
	private String emailCertification(HttpServletRequest request, String mail, String code) {
		HttpSession session = request.getSession();
		String result = mailservice.emailCertification(session, mail, Integer.parseInt(code));
		return result;
	}
	
}
