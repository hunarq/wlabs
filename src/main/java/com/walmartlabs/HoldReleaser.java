package com.walmartlabs;

import java.util.Iterator;
import java.util.Map;
import java.util.TimerTask;
import java.util.Map.Entry;


class HoldReleaser extends TimerTask {	
	private static long EXPIRED_TIME_IN_SEC = 25000;
	private Map<Integer, SeatHold> holds;
	private Seat[] seats;

	public HoldReleaser(Map<Integer, SeatHold> holds, Seat[] seats) {
		this.holds = holds;
		this.seats = seats;
	}

	public void run() {
		// We are checking for expired element from map every second
		RelaseExpiredHolds();
	}

	private void RelaseExpiredHolds() {
		SeatHold hold;
		Iterator<Entry<Integer, SeatHold>> it = this.holds.entrySet().iterator();
		while (it.hasNext()) {
		     Entry<Integer, SeatHold> entry = it.next();
		     hold= entry.getValue();
		     long startTime = hold.getHoldTime();
			 long estimatedTime = System.currentTimeMillis() - startTime;

		     if ( !hold.isConfirmed() && estimatedTime> EXPIRED_TIME_IN_SEC) {
		    	 synchronized(this.seats){
		    		 int[] hs = hold.getSeats();
			    	 for (int i=0; i< hs.length; i++) {
			    		 this.seats[hs[i]].free();
					}
			    	it.remove();  
				}
		     }
		 }
	}
}