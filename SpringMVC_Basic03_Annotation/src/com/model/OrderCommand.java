package com.model;

import java.util.List;

//주문서 클래스
//하나의 주문은 여러개의 상품을 가질 수 있다

//board : replay
//하나의 게시글은 여러개의 댓글을 가질 수 있다 

public class OrderCommand {
	private List<OrderItem> orderItem;  // key point ***

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

}

/*
	주문 발생
	OrderCommand command = new OrderCommand();
	
	List<OrderItem> itemlist = new ArrayList<>();
	list.add(new OrderItem(1,10,"파손주의");
	list.add(new OrderItem(2,1,"리모콘 별도 구매")
	
	command.setOrderItem(itemlist)
*/












