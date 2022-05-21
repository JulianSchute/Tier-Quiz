package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import data.DataLoader;
import objects.Animal;
import quiz.AnimalMatching;
import quiz.Page;
import quiz.WhichAnimalIsIt;
import quiz.choiceHandler;

class GamesTest {

	/*
	@Test
	void animalLoadingTest() {
		//Testfile
		List<Animal> animals = DataLoader.loadAnimalData("\\Files\\An.txt");
		List<Animal> testAnimals = new ArrayList<Animal>();
		testAnimals.add(new Animal("Malaysia-Tiger","Panthera tigris jacksoni","Katzen (Felidae)","Katzenartige (Feliformia)",18,275,190000,250,"Malaiischen Halbinsel, Thailand"));
		assertEquals("Malysia-Tiger", animals.get(0).getName());
	}
	*/
	
	//check number input
	@Test
	void choseGameTestNumber() {
		Page page;
		page = choiceHandler.recursiveGameChoiceHandler("1");
		String text =page.witchPageIsIt();
		assertEquals("Du hast AnimalMatching ausgewählt",text);
	}
	
	//check name input
	@Test
	void choseGameTestName() {
		Page page;
		page = choiceHandler.recursiveGameChoiceHandler("AnimalMatching");
		String text =page.witchPageIsIt();
		assertEquals("Du hast AnimalMatching ausgewählt",text);
	}
	
	//check if whichAnimalIsIt has generates duplicates
	@Test
	void noDublicates() {
		Boolean noDublicateAnimal = true;
		WhichAnimalIsIt page = new WhichAnimalIsIt();
		var animals = page.getChosenAnimals();
		for(int i = 0; i<animals.size();i++) {
			for(int j = i+1; j<animals.size();j++) {
				if(animals.get(i).equals(animals.get(j))) {
					noDublicateAnimal = false;
				}
			}
		}
		assertTrue(noDublicateAnimal);
	}

}
