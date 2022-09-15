/**
 * 
 * Josh Muszka
 * 
 * December 13, 2021
 * 
 * This Elevator class is used for the elevator objects in the
 * ElevTest.java program
 * It has constant variables for manufacturer, top floor, maximum occupants, as well
 * as other variables for power state, floor, number of occupants, and door status (open or closed)
 * It has methods to set/get the power state, get number of people on board, add or remove people,
 * open and close the doors, move the elevator up or down one floor, get the floor 
 * number, set the floor number manually
 * 
 */

package elevatorproject;

public class Elevator {

	//static variables
	final static String manufacturer = "Otis";
	final static int topFloor = 19;
	final static int maxOccupants = 25;
	
	static boolean powerOn = false;

	//instance variables
	private int floor = 1;
	private int numOfPeople = 0;
	private boolean doorsOpen = false;


	//constructors

	Elevator() {

	}

	Elevator(int floor) {

		this.floor = floor;
		if (floor < 0 || floor > topFloor) {
			floor = 1;
		}

	}

	

	//static methods

	static void setPowerState(boolean input) { //change power state

		if (input) { //if true is entered
			
			if (!powerOn) { //if power isn't already on
				powerOn = true;
				System.out.println("Turning on");
			}
			
		}

		if (!input) { //if false is entered
			
			if (powerOn) { //if power isn't already off
				powerOn = false;
				System.out.println("Turning off");
			}
			
		}


	}

	static void getPowerState() { //print power state to console

		if (powerOn) {
			System.out.println("Elevators are online");
		}

		if (!powerOn) {
			System.out.println("Elevators are offline");
		}

	}


	
	//instance methods

	void up() { //move elevator up one floor

		if (powerOn) { //if power is on

			if (!doorsOpen) { //if doors are closed
				if (floor == topFloor) { //if elevator is already at top floor
					System.out.println("ERROR: Already at top floor");
				}
				else {
					floor++;
				}

			}
			else { //if doors are open
				System.out.println("ERROR: Please close the doors");
			}

		}
		else { //if power is off
			System.out.println("ERROR: Please turn on the power");
		}

	}

	void down() { //move elevator down one floor

		if (powerOn) { //if power is on

			if (!doorsOpen) { //if doors are closed
				if (floor == 1) { //if elevator is already at ground floor
					System.out.println("ERROR: Already at bottom floor");
				}
				else {
					floor--;
				}

			}
			else { //if doors are open
				System.out.println("ERROR: Please close the doors");
			}

		}
		else { //if power is off
			System.out.println("ERROR: Please turn on the power");
		}

	}

	int getFloor () { //return what floor elevator is on

		return floor;

	}

	void goToFloor(int n) { //send elevator to a specified floor

		if (powerOn) { //if power is on

			if (!doorsOpen) { //if doors are closed

				if (n > topFloor) { //if user tries to make elevator go higher than top floor
					System.out.println("ERROR: Max floor is " + topFloor);
				}
				else if (n < 1) { //if user tries to make elevator go lower than bottom floor
					System.out.println("ERROR: Cannot go lower than 1");
				}
				else if (n == floor){ //if user tries to travel to the current floor
					System.out.println("Already at this floor");
				}
				else {
					floor = n;
				}

			}
			else { //if doors are open
				System.out.println("ERROR: Please close the doors");
			}

		}
		else { //if power is off
			System.out.println("ERROR: Please turn on the power");
		}



	}

	void openDoors() { //open the elevator doors

		if (powerOn) { //if power on

			if (!doorsOpen) { //if doors are closed
				doorsOpen = true;
			}
			else {
				System.out.println("ERROR: Doors are already open");
			}

		}
		else { //if power off
			System.out.println("ERROR: Please turn on the power");
		}

	}

	void closeDoors() { //close the elevator doors

		if (powerOn) { //if power on

			if (doorsOpen) { //if doors are open
				doorsOpen = false;
			}
			else {
				System.out.println("ERROR: Doors are already closed");
			}

		}
		else { //if power off
			System.out.println("ERROR: Please turn on the power");
		}

	}

	int getNumOfPeople () { //return number of people currently in elevator
		return numOfPeople;
	}
	
	void addPeople (int n) { //add people to elevator

		if (n > 0) { //if number of people entered is more than zero

			if (n + numOfPeople <= maxOccupants) { //if maximum number of occupants won't be exceeded

				if (doorsOpen) { //if doors are open
					numOfPeople += n;
				}
				else {
					System.out.println("ERROR: Doors are closed");
				}

			}
			else { //if current people + added people is greater than maximum occupancy
				System.out.println("ERROR: Number of people exceeds maximum occupancy");
			}

		}
		else { //if number of people entered is less than zero (no such thing as negative people)
			System.out.println("ERROR: Number of people must be greater than zero");
		}

	}

	void removePeople (int n) { //remove people from elevator

		if (n > 0) { //if number of people entered is more than zero

			if (n <= numOfPeople) { //if people removed isn't greater than people currently on board

				if (doorsOpen) { //if doors open
					numOfPeople -= n;
				}
				else {
					System.out.println("ERROR: Doors are closed");
				}

			}
			else { //if number of people removed is more than number on board (can't have negative amount of people)
				System.out.println("ERROR: That's more people than are currently on-board");
			}

		}
		else { //if number of people entered is less than zero (no such thing as negative people)
			System.out.println("ERROR: Number of people must be greater than zero");
		}

	}


	@Override
	public String toString() { //return all information about the elevator

		String s = "MANUFACTURER: " + manufacturer + "\t" +
				"FLOOR: " + floor + "\t" +
				"NUMBER OF PEOPLE: " + numOfPeople + "\t" + 
				"DOORS: "+ (doorsOpen? "Open" : "Closed");

		return s;


	}



}
