package com.walmartlabs;

public class Seat {
	private String email;
	private String seatNumber;
	private State state;

	public Seat(String seatNumber) {
	    this.seatNumber = seatNumber;
	    this.email= "";
		this.state = State.Available;
	}
	
	public String getEmail() { return this.email; }
	public String getSeatNumber() { return this.seatNumber; }
	
	public void reserve(String email){
		this.email= email;
		this.state = State.Reserved;
	}
	
	public void hold(String email) {
		this.email= email;
		this.state = State.onHold;
	}
 
	public void free(){
		this.email= "";
		this.state = State.Available;
	}
	
	public State getSeatState() {
	    return this.state;
	}

	public String toString(){
	        return  "seat:" + seatNumber + ",state:" + this.state;
	}
 
}
