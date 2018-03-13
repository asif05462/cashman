package com.wns.cashman.util;

public class ResponseBean {
	
	private int amount;
	private int totalCashLeft;	
	private String message;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getTotalCashLeft() {
		return totalCashLeft;
	}
	public void setTotalCashLeft(int totalCashLeft) {
		this.totalCashLeft = totalCashLeft;
	}
	
}
