package com.walmartlabs;

import java.util.Scanner;

public class Reserve {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TicketService ts = TicketServiceImpl.getInstance();
		String email = "test@example.com";
		SeatHold sh;
		String confirmCode= null;
		int av=10;
		int num = 0;
		Scanner scanner= new Scanner(System.in);

		while (num != 999) {
			System.out.print("Number of Seats?");
			num = scanner.nextInt();
			 av = ts.numSeatsAvailable();
			 System.out.println("Number of available seats: " + av);
			 if (av >= num) {
				sh =ts.findAndHoldSeats(num, email);
				if (sh != null) {
					System.out.println("seats holdId : " + sh.getId());
				} else {
					System.out.println("zero seats requested, no reservation made");
				}
				scanner = new Scanner(System.in);
				System.out.println("To reserve, type holdId: (0) to skip ");
				num = scanner.nextInt();
				if ( num > 0 ) {
					confirmCode = ts.reserveSeats(num, email);
					System.out.println("Confirmation code:" + confirmCode);
				}
			 }	 
			 System.out.println("Next....");
		 }
	}

}
