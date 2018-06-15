package com.walmartlabs;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
 

public class ReleaseHolds implements Runnable {

	private Map<Integer, SeatHold> booking;
	private String[] seats;
	
	public ReleaseHolds(Map<Integer, SeatHold> booking, String[] seats) {
		this.booking = booking;
		this.seats = seats;
	}
	
	@Override
	public void run() {
		while(true) {		
			SeatHold hold;
			for(Iterator<Entry<Integer, SeatHold>>it = this.booking.entrySet().iterator();it.hasNext();){
			     Entry<Integer, SeatHold> entry = it.next();
			     hold= entry.getValue();
			     long startTime = hold.getHoldTime();
				 long estimatedTime = System.currentTimeMillis() - startTime;

			     if ( !hold.isConfirmed() && estimatedTime> 5000) {
			    	 synchronized(this.seats){
			    		 int[] hs = hold.getSeats();
				    	 for (int i=0; i< hs.length; i++) {
				    		 this.seats[hs[i]] = "";
						}
				    	it.remove();  
					}
			     }
			 }
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
