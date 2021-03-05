
package kr.or.bit.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MeasuringInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Measureing Pre HandlerInterceptor : controller 타기 전");
		request.setAttribute("beginingTime", System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Measureing Post HandlerInterceptor : controller 탔음 ");
		Long beginTime = (Long) request.getAttribute("beginingTime");
		Long endTime = System.currentTimeMillis();
		request.setAttribute("midTime", endTime);
		System.out.println(request.getRequestURI()+"여기까지 : "+(endTime-beginTime)/10+"m초 걸림");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Measureing AfterCompletion HandlerInterceptor : view 탔음");
		Long beginTime = (Long) request.getAttribute("beginingTime");
		Long endTime = System.currentTimeMillis();
		System.out.println(request.getRequestURI()+"완료! : "+(endTime-beginTime)/10+"m초 걸림");
	}
	//시간 측정하는 인터셉터 
	
}
