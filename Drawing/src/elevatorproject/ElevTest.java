/**
 * 
 * Josh Muszka
 * 
 * December 13, 2021
 * 
 * This program creates two Elevator objects and simulates them
 * turning on/off their power, moving up/down floors, opening/closing
 * doors, and adding/removing people, using the custom-made Elevator class
 * 
 */

package elevatorproject;

public class ElevTest {

	public static void main (String[] args) {
		
		Elevator east = new Elevator(5);
		Elevator south = new Elevator();
		
		Elevator.setPowerState(true); //turn on elevators
		
		System.out.println(east.toString());
		
		//south.goToFloor(1);
		
		south.openDoors();
		south.addPeople(10);
		south.closeDoors();
		System.out.println(south.toString());
		
		south.goToFloor(4);
		south.openDoors();
		south.removePeople(3);
		south.closeDoors();
		System.out.println(south.toString());
		
		south.goToFloor(Elevator.topFloor);
		south.openDoors();
		south.removePeople(south.getNumOfPeople());
		south.closeDoors();
		System.out.println(south.toString());
		
		east.openDoors();
		east.addPeople(8);
		east.closeDoors();
		System.out.println(east.toString());
		
		east.goToFloor(8);
		east.openDoors();
		east.addPeople(30);
		east.closeDoors();
		System.out.println(east.toString());
		
		east.goToFloor(Elevator.topFloor + 1);
		
		east.goToFloor(-5);
		
		east.goToFloor(2);
		south.goToFloor(2);
		
		east.openDoors();
		
		Elevator.setPowerState(false); //power failure
		
		east.up();
		south.up();
		east.openDoors();
		south.openDoors();
		east.closeDoors();
		south.closeDoors();
		
		Elevator.setPowerState(true); //restore power
		Elevator.setPowerState(true);
		
		south.goToFloor(1);
		for (int i = south.getFloor(); i < 10; i++) {
			
			south.up();
			south.openDoors();
			south.closeDoors();
			
		}
		System.out.println(south.toString());
		
		
	}
	
}
