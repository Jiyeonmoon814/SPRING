package AOP_Basic_Spring07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;





public class Program {

	public static void main(String[] args) {
		//proxy 객체 생성하고 
		//proxy 통해서 실행
		//xml 설정넘겨서 작업
		ApplicationContext context = 
				new GenericXmlApplicationContext("classpath:AOP_Basic_Spring07/ApplicationContext.xml");
		Calc calc = (Calc)context.getBean("proxy");
		
		//System.out.println(calc.ADD(500, 600));
		System.out.println(calc.SUB(5, 9));
	}

}
