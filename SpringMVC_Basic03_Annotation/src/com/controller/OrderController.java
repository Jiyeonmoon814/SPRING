package com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.OrderCommand;
import com.model.OrderItem;


/*
 하나의 요청 주소 >> /order/order.do 
 화면 : GET
 업무 처리 : POST 
 동시에 처리 
 
 */

@Controller
@RequestMapping("/order/order.do")
public class OrderController {
	@RequestMapping(method=RequestMethod.GET)
	public String form() {
		return "order/OrderForm"; //view의 주소를 return
		
		// /WEB-INF/views/order/OrderForm.jsp
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(OrderCommand ordercommand) {
		
		//System.out.println("ordercommand address valule : "+ordercommand);
		//System.out.println(ordercommand.getOrderItem().size());
		
		//List<OrderItem> items = ordercommand.getOrderItem();
		//for(OrderItem item : items) {
		//	System.out.println(item.toString());
		//}
		
		//OrderCommand ordercommand 자동으로 view로 forward >> orderCommand
		return "order/OrderCommited";
		
	}
}
