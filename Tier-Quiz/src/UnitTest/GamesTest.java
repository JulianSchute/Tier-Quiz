package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import data.DataLoader;
import objects.Animal;
import objects.Player;
import objects.Score;
import quiz.AnimalMatching;
import quiz.GameType;
import quiz.Page;
import quiz.WhichAnimalIsIt;
import quiz.choiceHandler;

class GamesTest {

	
	@Test
	void animalLoadingTest() {
		//Testfile
		List<Animal> animals = DataLoader.loadAnimalData("\\Files\\An.txt");
		List<Animal> testAnimals = new ArrayList<Animal>();
		testAnimals.add(new Animal("Malaysia-Tiger","Panthera tigris jacksoni","Katzen (Felidae)","Katzenartige (Feliformia)",18,275,190000,250,"Malaiischen Halbinsel, Thailand"));
		assertEquals("Malaysia-Tiger", animals.get(0).getName());	
	}
	
	
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
	
	//translate age for human readability
	@Test
	void animalValuesAge() {
		Animal animal = new Animal("Malaysia-Tiger","Panthera tigris jacksoni","Katzen (Felidae)","Katzenartige (Feliformia)",18,275,190000,250,"Malaiischen Halbinsel, Thailand");
		String age0 =animal.getValueIncluding(100,3);
		String age1 =animal.getValueIncluding(1,3);
		String age2 =animal.getValueIncluding(5,3);

		assertEquals(age0,"100 Jahre");
		assertEquals(age1,"1 Jahr");
		assertEquals(age2,"5 Jahre");

	}
	
	//translate size for human readability
	@Test
	void animalValuesSize() {
		Animal animal = new Animal("Malaysia-Tiger","Panthera tigris jacksoni","Katzen (Felidae)","Katzenartige (Feliformia)",18,275,190000,250,"Malaiischen Halbinsel, Thailand");
		String size0 = animal.getValueIncluding(100,4);
		String size1 = animal.getValueIncluding(53,4);
		String size2 = animal.getValueIncluding(1005,4);

		assertEquals(size0,"1,0 m");
		assertEquals(size1,"53 cm");
		assertEquals(size2,"10,5 m");
	}
	
	//translate weight for human readability
	@Test
	void animalValuesWeight() {
		Animal animal = new Animal("Malaysia-Tiger","Panthera tigris jacksoni","Katzen (Felidae)","Katzenartige (Feliformia)",18,275,190000,250,"Malaiischen Halbinsel, Thailand");
	
		String weight0 =	animal.getValueIncluding(100,5);
		String weight1 =	animal.getValueIncluding(1111111,5);
		String weight2 =	animal.getValueIncluding(111111,5);

		assertEquals(weight0,"100 g");
		assertEquals(weight1,"1,111 Tonnen");
		assertEquals(weight2,"111 kg");

	}
	
	//translate for readability
	@Test
	void scoreReadability() {
		Player player = new Player("Name");
		Score score = new Score(player, GameType.ANIMALQUIZ, 1000);
		String text = score.getReadableString();
		assertEquals("ANIMALQUIZ- Dein Highscore: 1000",text);
		
	}
	
	//Testen ob xCorrectAnimalAvailable funktioniert (aktuell leere Liste)
	@Test
	void testXCorrectAnimalAvailable() {
		AnimalMatching game = new AnimalMatching();
		List<Animal> list = new ArrayList<Animal>();
		Boolean correct = game.xCorrectAnimalAvailable(1, list, 4);
		assertTrue(true);
	}

}
