package kr.or.bit.vo;

public class mailDto {
	private String[] to;
	private String from;
	private String title;
	private String content;
	private String vm;
	private String numberCheck;
	private String number;

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNumberCheck() {
		return numberCheck;
	}
	public void setNumberCheck(String numberCheck) {
		this.numberCheck = numberCheck;
	}
	public String getVm() {
		return vm;
	}
	public void setVm(String vm) {
		this.vm = vm;
	}

	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
