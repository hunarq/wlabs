package com.walmartlabs;

public class SeatHold {
	
	private int id;
	private int[] seats = null;
	private String confirmcode = null;
	private long holdTime;
	
	public int getId() {
		return id;
	}
	
	public void setId(int value){
		this.id = value;
	}
	public int[] getSeats(){
		return seats;
	}
	
	public void setSeats(int[] seats){
		this.seats = seats;
	}
	public Boolean isConfirmed(){
		return (confirmcode != null);
	}
	
	public String getConfirmCode() {
		return this.confirmcode;
	}
	public void setConfirmCode(String code) {
		this.confirmcode = code;
	}

	public void setHoldTime(long tm) {
		this.holdTime = tm;
	}
	public long getHoldTime() {
		return this.holdTime;
	}

}
