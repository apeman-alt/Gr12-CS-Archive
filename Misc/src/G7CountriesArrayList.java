/**
 * 
 * Josh Muszka
 * 
 * September 21, 2021
 * 
 * This program creates objects for every country in the G7 that contain the name, continent, GDP,
 * population, official language, and currency. These objects are then stored in an ArrayList 
 * and printed to the console. Then the program deletes all the countries that aren't European from the ArrayList,
 * and then reprints the new ArrayList, displaying only countries located on the continent of Europe.
 * 
 **/
import java.util.ArrayList;

public class G7CountriesArrayList {


	// print ArrayList
	static void printList(ArrayList<Country> countryList) {

		for (Country c : countryList) {
			System.out.println(c + "\n");
		}
	}

	// delete objects that contain non-European countries
	static void deleteItem(ArrayList<Country> countryList) {

		for (int i = 0; i < countryList.size(); i++) {
			Country c = countryList.get(i);

			if (! c.continent.equals("Europe")) { //if country is not in Europe
				countryList.remove(i);
				i--;
			}
		}
	}

	public static void main (String[] args) {

		ArrayList<Country> countryList = new ArrayList<>(); // ArrayList

		// assign values to all object parameters
		Country c1 = new Country("Canada", "North America", 1.979, 38.1, "English & French", "Canadian Dollar");
		Country c2 = new Country("France", "Europe", 3.232, 67.4, "French", "Euro");
		Country c3 = new Country("Germany", "Europe", 4.743, 83.2, "German", "Euro");
		Country c4 = new Country("Italy", "Europe", 2.610, 60.3, "Italian", "Euro");
		Country c5 = new Country("Japan", "Asia", 5.586, 125.4, "Japanese", "Japanese Yen");
		Country c6 = new Country("United Kingdom", "Europe", 3.174, 67.9, "English", "British Pound");
		Country c7 = new Country("United States", "North America", 22.675, 331.4, "None", "US Dollar");

		// add each object to the ArrayList
		countryList.add(c1);
		countryList.add(c2);
		countryList.add(c3);
		countryList.add(c4);
		countryList.add(c5);
		countryList.add(c6);
		countryList.add(c7);

		System.out.println("MEMBER STATES OF THE G7\n\n");
		printList(countryList); // print full ArrayList of objects
		deleteItem(countryList); // delete all objects that contain non-European countries from the ArrayList

		System.out.println("\n************************\n\n\nEUROPEAN NATIONS OF THE G7\n\n");
		printList(countryList); // reprint updated ArrayList that only contains European countries
	}
}



class Country {

	String name, continent, officialLanguage, currency;
	double GDP = 0.0;
	double population = 0.0;

	// create object parameters
	Country(String name, String continent, double GDP, double population, String officialLanguage, String currency) {
		this.name = name;
		this.continent = continent;
		this.GDP = GDP;
		this.population = population;
		this.officialLanguage = officialLanguage;
		this.currency = currency;
	}

	// method to print all information stored in object
	public String toString() {
		String s = "Country: " + name +"\nContinent: " + continent + "\nGDP: $" + GDP + " trillion USD" + "\nPopulation: " + population
				+ " million" + "\nOfficial Language: " + officialLanguage + "\nCurrency: " + currency;				
		return s;
	}
}