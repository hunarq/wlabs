package com.walmartlabs;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;


public class TicketServiceImpl implements TicketService {
	private static int NO_OF_ROWS = 4;
	private static int SEATS_IN_ROW = 5;
	private static int NO_OF_SEATS = NO_OF_ROWS * SEATS_IN_ROW;
	private static int TIMER_INTERVAL_SECONDS = 1;
	int bookingId = 1;
	Timer releaseTimer;
	Map<Integer,SeatHold>  holds = new ConcurrentHashMap<Integer,SeatHold>();
	Seat[] seats = new Seat[NO_OF_SEATS];

 
    public TicketServiceImpl(){
    	// create seats
		for(int i = 0; i < NO_OF_SEATS ; i++)
		{
		    seats[i] = new Seat( "" + (1+i));
		}
		releaseTimer = new Timer();
		releaseTimer.schedule(new HoldReleaser(this.holds, this.seats), 0, TIMER_INTERVAL_SECONDS * 1000);
	}
 
	@Override
	public int numSeatsAvailable() {
		int availableSeats = 0;
		// Count all available seats
		for(int i = 0; i < NO_OF_SEATS ; i++)
		{
		    if (seats[i].getSeatState() == State.Available)
		    	availableSeats++;
		}
		return availableSeats;
	}

	@Override
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		SeatHold sh = new SeatHold();
		int[] holds = new int[numSeats];
		int c = 0;
		if ( numSeats<1) {
			return null;
		}
		synchronized(this.seats){
			for (int i=0; i< NO_OF_SEATS; i++){
				if (this.seats[i].getSeatState() == State.Available ) {
					this.seats[i].hold(customerEmail);
					holds[c++] = i;
				}
				if (c == numSeats) {
					break;
				}
				
			}
		}

		sh.setHoldTime(System.currentTimeMillis());
		sh.setSeats(holds);
		sh.setSeatsHeld(c);
		sh.setId(bookingId++);
		this.holds.put(sh.getId(), sh);
		this.printStage();
		return sh;
	}

	@Override
	public String reserveSeats(int seatHoldId, String customerEmail) {
		Random rand = new Random();
		int num = rand.nextInt(9000000) + 1000000;
		
		SeatHold sh =this.holds.get(seatHoldId);
		if (sh != null) {
			int[] holdSeats = sh.getSeats();
			synchronized(this.seats){
				for ( int i=0; i< holdSeats.length; i++) {
					this.seats[holdSeats[i]].reserve(customerEmail);
				}
				sh.setConfirmCode( "C" + num );
			}
			this.printStage();
			return sh.getConfirmCode();
		} else {
			return null;
		}

	}

	private void printStage() {
		for (int i=0; i< this.seats.length; i++){
			System.out.print( " [" + this.seats[i]  + "] ");
			if ( (i+1) % SEATS_IN_ROW == 0)
				System.out.println("");
		}
		System.out.println("===============");
	}

}

