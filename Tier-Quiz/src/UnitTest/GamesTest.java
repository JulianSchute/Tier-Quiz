package UnitTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import data.DataLoader;
import objects.Animal;

class GamesTest {

	@Test
	void animalLoadingTest() {
		//Testfile
		List<Animal> animals = DataLoader.loadAnimalData("\\Files\\An.txt");
		List<Animal> testAnimals = new ArrayList<Animal>();
		testAnimals.add(new Animal("Malaysia-Tiger","Panthera tigris jacksoni","Katzen (Felidae)","Katzenartige (Feliformia)",18,275,190000,250,"Malaiischen Halbinsel, Thailand"));
		assertEquals("Malysia-Tiger", animals.get(0).getName());
	}

}
