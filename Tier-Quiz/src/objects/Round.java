package objects;

import java.util.ArrayList;
import java.util.List;

public class Round {
	
	private List<Animal> animals=  new ArrayList<Animal>();
	private String animalAttribute;
	private int aditionalInt;
	public int getAditionalInt() {
		return aditionalInt;
	}

	public List<Animal> getAnimals() {
		return animals;
	}

	public String getAnimalAttribute() {
		return animalAttribute;
	}

	
	
	public Round(List<Animal> animals, String animalAttribute, int aditionalInt) {
		this.animals = animals;
		this.animalAttribute = animalAttribute;
		this.aditionalInt = aditionalInt;
	}
	
	public String getRoundDefinition() {
		String returnValue = "Ordne die Tiere nach dem Attribut " + animalAttribute +" zu!";
		if(aditionalInt == 3) {
			returnValue = returnValue + " Das ist die maximale Lebenserwartung.";
		}
		if(aditionalInt == 4) {
			returnValue = returnValue + " Das ist die maximale Gr��e und es wurde die jeweils gr��te Ma�e genommen. Z.B. H�he bei der Giraffe, L�nge beim Hund und Fl�gelspannweite beim Adler!";
		}
		if(aditionalInt == 5) {
			returnValue = returnValue + " Das ist das maximale Gewicht und nicht durchschnittliche.";
		}
		
		return returnValue;
	}
	
	public String getValueIncluding(int i) {
		String returnValue = "";
		returnValue = animals.get(0).getValueIncluding(i, aditionalInt);
		return returnValue;
	}
}
