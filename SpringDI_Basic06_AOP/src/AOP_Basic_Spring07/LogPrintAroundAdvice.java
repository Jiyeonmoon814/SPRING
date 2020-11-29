package AOP_Basic_Spring07;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

public class LogPrintAroundAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		System.out.println("Around Advice Invoke Start");
		System.out.println("method : " + method);
		//보조업무 구현
		Log log =	LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("[타이머 시작]"); //console 출력
		//
		
		//주업무
		  Object result = method.proceed(); // 주업무 실제 함수 호출
		//
		
		//보조업무 구현
		sw.stop();
		log.info("[타이머 종료]");
		log.info("[TIME LOG] Method :");
		log.info("[TIME LOG] process Time : " + sw.getTotalTimeMillis());
		//
		return result;
	}

}
