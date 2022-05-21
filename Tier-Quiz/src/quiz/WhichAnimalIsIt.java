package quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import data.DataLoader;
import data.DataSaver;
import data.tools;
import objects.Animal;
import objects.Player;
import objects.Score;
import objects.ValuePair;

public class WhichAnimalIsIt extends Game {

	private int roundsPerGame = 3;
	private int attributesPerRound = 6;
	private List<Animal> chosenAnimals =new ArrayList<Animal>();

	public WhichAnimalIsIt() {
		this.setType(GameType.WHICHANIMALISIT);
		this.generateGame();
	}
	@Override
	public void startPage(Player player) {
		score = new Score(player, type);

		for(int i = 0; i<roundsPerGame; i++) {
			this.askRound(chosenAnimals.get(i));
		}
		tools.scoreResult(score);
	}


	@Override
	public String witchPageIsIt() {
		return "Du hast WhichAnimalIsIt ausgewählt";

	}


	@Override
	protected void generateGame() {
		List<Animal> availableAnimals = DataLoader.loadAnimalData("\\Files\\Animals.txt");
		while(chosenAnimals.size()<roundsPerGame) {
			int number =(int) (Math.random()*availableAnimals.size());
			//Avoid Duplicates
			if(!(chosenAnimals.contains(availableAnimals.get(number)))) {	

				chosenAnimals.add(availableAnimals.get(number));				
			}		
		}
	}

	private void askRound(Animal animal) {
		System.out.println("Du erhälst nach und nach Eigenschaften eines Tieres. Um dieses Spiel zu gewinnen musst du das Richtige Tier erraten!");
		System.out.println("Nach zwei Tippversuchen werden alle möglichen Tiere genannt!");
		int[] attributes = new int[attributesPerRound];
		for(int i= attributesPerRound; i>0; i--) {
			roundBuilder(animal, attributes, i);
			System.out.println("Welches Tier ist es?");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				String input = in.readLine();
				if(input.equals(animal.getName())) {
					score.addPoints(i*600);
					System.out.println("Korrekt, du hast das Tier " +animal.getName() + " erkannt!");
					return; //Abbruch
				}
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Du hast das Tier " +animal.getName() + " nicht erkannt!");
	}
	public void roundBuilder(Animal animal, int[] attributes, int tip) {
		boolean good = true;
		while(good) {	
			int number =(int) (Math.random()*8);	
			//check if it not already in attributes
			good = false;
			for(int j = 0; j< (attributesPerRound -tip); j++) {
				if(attributes[j] == number) {
					good = true;
				}
			}
			if(good == false) {
				attributes[attributesPerRound -tip] = number;
			}
		}
		
		
		for(int i = 0; i<= (attributesPerRound -tip); i++) {
			if(attributes[i] == 0) {
				System.out.println("Wissenschaftlicher Name: " + animal.getScientificName());
			}
			if(attributes[i] == 1) {
				System.out.println("Familie: " + animal.getFamily());
			}
			if(attributes[i] == 2) {
				System.out.println("Ordnung: " + animal.getOrder());
			}
			if(attributes[i] == 3) {
				System.out.println("Lebenserwartung: " + animal.getValueIncluding(animal.getLifeExpectancy(), attributes[i]));
			}
			if(attributes[i] == 4) {
				System.out.println("Größe :" + animal.getValueIncluding(animal.getSize(), attributes[i]));
			}
			if(attributes[i] == 5) {
				System.out.println("Gewicht: " + animal.getValueIncluding(animal.getWeight(), attributes[i]));
			}
			if(attributes[i] == 6) {
				System.out.println("Population: " + animal.getValueIncluding(animal.getPopulation(), attributes[i]));
			}
			if(attributes[i] == 7) {
				System.out.println("Lebensraum: " + animal.getHabitat());
			}
			
		}
		if( attributesPerRound -tip >= 2) {
			List<Animal> animals = DataLoader.loadAnimalData("\\Files\\Animals.txt");
			System.out.println("Tiere die es sein können: ");
			for(int j = 0; j<animals.size();j++) {
				System.out.print(animals.get(j).getName() +", ");
			}
			System.out.println();
			System.out.println("-");
		}
	}
	

}