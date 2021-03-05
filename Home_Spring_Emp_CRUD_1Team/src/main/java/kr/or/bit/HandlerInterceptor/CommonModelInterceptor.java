package kr.or.bit.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.or.bit.dto.EmpDto;
import kr.or.bit.service.EmpService;

public class CommonModelInterceptor extends HandlerInterceptorAdapter{
	@Autowired
	private EmpService empService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("CommonModel PreHandlerInterceptor : Controller 타기전");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("CommonModel PostHandlerInterceptor : Emp 객체 공유");
		String empno = request.getParameter("empno");
		EmpDto empdto = empService.selectByEmpno(empno);
		modelAndView.addObject("emp", empdto);
	}
}
