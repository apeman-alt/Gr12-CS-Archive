/**
 * 
 * Josh Muszka
 * 
 * September 21, 2021
 * 
 * This program creates objects for bands that contain the name, nationality, #1 hit song, lead
 * singer, and year they were formed. These objects are then stored in an ArrayList and printed
 * to the console. Then the program deletes all the bands that aren't British from the ArrayList,
 * and then reprints the new ArrayList, displaying only bands from the UK.
 * 
 **/

import java.util.ArrayList;

public class ArrayListOLD {

	public static void main (String[] args) {

		ArrayList<Band1> bandList = new ArrayList<Band1>(); // ArrayList


		System.out.println("LIST OF BANDS\n");

		// assign values to all object parameters
		Band1 b1 = new Band1 ("The Beatles", "UK", "Here Comes the Sun",
				"John Lennon & Paul McCartney", 1960);
		Band1 b2 = new Band1 ("Green Day", "US", "Basket Case", 
				"Billy Joe Armstrong", 1987);
		Band1 b3 = new Band1 ("Guns N' Roses", "US", "Sweet Child O' Mine", 
				"Axl Rose", 1985);
		Band1 b4 = new Band1 ("AC/DC", "Australia", "Back in Black", "Bon Scott"
				, 1973);
		Band1 b5 = new Band1 ("The Rolling Stones", "UK", "Paint It, Black", 
				"Mick Jagger", 1962);

		// add each object to the ArrayList
		bandList.add(b1);
		bandList.add(b2);
		bandList.add(b3);
		bandList.add(b4);
		bandList.add(b5);

		printList(bandList); // print full ArrayList of objects
		deleteItems(bandList); // delete all objects that contain non-British bands from array

		// reprint updated ArrayList that only contains British bands
		System.out.println("********\n");
		System.out.println("BRITISH BANDS\n");
		printList (bandList);


	}


	// print ArrayList
	static void printList (ArrayList<Band1> bandList) {
		/*for (int i = 0; i < bandList.size(); i++) {
			Band b = bandList.get(i);
			System.out.println(b.toString() + "\n");
		} */

		for (Band1 b : bandList) {
			System.out.println(b + "\n");
		}
	}

	// delete objects that contain non-British bands
	static void deleteItems (ArrayList<Band1> bandList) {
		for (int i = 0; i < bandList.size(); i++) {
			Band1 b = bandList.get(i);

			if (! b.bNationality.equals("UK")) {
				bandList.remove(i);
				i--;
			}
		}

	}
}



class Band {
	String bName, bNationality, bBestSong, bLeadSinger;
	int yearFormed = 0;

	// create object parameters
	Band(String bName, String bNationality, String bBestSong, String bLeadSinger
			, int yearFormed) {
		this.bName = bName;
		this.bNationality = bNationality;
		this.bBestSong = bBestSong;
		this.bLeadSinger = bLeadSinger;
		this.yearFormed = yearFormed;
	}


	// method to print all information stored in object
	public String toString() {
		String s = "Name: " + bName + "\n" + "Country: " + bNationality
				+ "\n" + "#1 Hit: " + bBestSong + "\n" + "Lead Singer: "
				+ bLeadSinger + "\n" + "Formed: " + yearFormed;
		return s;
	}
}
