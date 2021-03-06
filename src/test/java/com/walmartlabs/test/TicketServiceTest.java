package com.walmartlabs.test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.walmartlabs.SeatHold;
import com.walmartlabs.TicketService;
import com.walmartlabs.TicketServiceImpl;

public class TicketServiceTest {
	
	TicketService ts;
	
	@Before
	public final void setup(){

	}
	
	@After
	public final void destroy(){
		
	}

	/// test NumSeatsAvailable
	@Test
	public final void testNumSeatsAvailable() {
		TicketService ts = new TicketServiceImpl();
		assertEquals(ts.numSeatsAvailable(), 20);
	}

	/// test FindAndHoldSeats
	@Test
	public final void testFindAndHoldSeats() {
		TicketService ts = new TicketServiceImpl();
		SeatHold sh = ts.findAndHoldSeats(20, "test@test.com");
		assertTrue(sh.getSeatsHeld() == 20);
		
		sh = ts.findAndHoldSeats(20, "test@test.com");
		assertTrue(sh.getSeatsHeld() == 0);
		
	}

	/// test reserveSeats
	@Test
	public final void testReserveSeats() {
		TicketService ts = new TicketServiceImpl();
		SeatHold sh = ts.findAndHoldSeats(20, "test@test.com");
		String confirmCode = ts.reserveSeats(sh.getId(), "test@test.com");
		assertNotNull(confirmCode);

		confirmCode = ts.reserveSeats(100, "test@test.com");
		assertNull(confirmCode);
	}

}
