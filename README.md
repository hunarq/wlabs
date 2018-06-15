Ticket Service
==================

Overview:
------------

Simple ticket service that facilitates the discovery, temporary hold, and final reservation of seats within a high-demand performance
venue.
For example, see the seating arrangement below.
----------[[ STAGE ]]----------
---------------------------------
sssssssssssssssssssssssssssssssss
sssssssssssssssssssssssssssssssss
sssssssssssssssssssssssssssssssss
sssssssssssssssssssssssssssssssss
sssssssssssssssssssssssssssssssss
sssssssssssssssssssssssssssssssss
sssssssssssssssssssssssssssssssss
sssssssssssssssssssssssssssssssss
sssssssssssssssssssssssssssssssss



Ticket Service  provides thefollowing functions:

Find the number of seats available within the venue
Note: available seats are seats that are neither held nor reserved.
Find and hold the best available seats on behalf of a customer
Note: each ticket hold should expire within a set number of seconds.
Reserve and commit a specific group of held seats for a customer

 
 Build:
 ------------
 
 mvn clean install or mvn clean package
 
 
 Run:
 ------------
 
 jar -jar WalmartLabs-0.0.1-SNAPSHOT.jar
 

