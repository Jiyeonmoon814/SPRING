package com.model;

//상품 1개 정보를 담을 수 있는 DTO 클래스
public class OrderItem {
		private int itemid;
		private int number;
		private String remark;
		public int getItemid() {
			return itemid;
		}
		public void setItemid(int itemid) {
			this.itemid = itemid;
		}
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		@Override
		public String toString() {
			return "OrderItem [itemid=" + itemid + ", number=" + number + ", remark=" + remark + "]";
		}
		
		
}
