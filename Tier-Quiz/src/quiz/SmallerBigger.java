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
import objects.Option;
import objects.Player;
import objects.Round;
import objects.Score;
import objects.ValuePair;

public class SmallerBigger extends Game{

	public int animalsPerGame = 7; //shouldn't be more then 26
	public int roundsPerGame = 3;	//max 4, only one round for every attribute
	public int allowedErrorsPerRound = 3;
	public int errors = 3;	
	private List<Round> rounds=  new ArrayList<Round>();
	private List<Animal> availableAnimals =  new ArrayList<Animal>();

	public SmallerBigger() {
		this.setType(GameType.SMALLERBIGGER);
		this.generateGame();
	}
	@Override
	public void startPage(Player player) {
		score = new Score(player, type);

		for(int i = 0; i<roundsPerGame; i++) {
			this.askRound(rounds.get(i));
		}
		tools.scoreResult(score);

	}

	@Override
	public String witchPageIsIt() {
		return "Du hast SmallerBigger ausgewählt";

	}

	@Override
	protected void generateGame() {
		availableAnimals = DataLoader.loadAnimalData("\\Files\\Animals.txt");
		this.chooseRounds();	

	}
	//Choose which attributes should be played
	private void chooseRounds() {
		//There are 8 possible attributes to generate
		int[] intRounds = new int[roundsPerGame];
		for(int i =0; i<intRounds.length; i++) {
			boolean correct = false;
			while( correct == false) {
				int number =(int) ((Math.random()*4)+3);
				System.out.println("Zahl: " + number);
				if(i == 0 && xCorrectAnimalAvailable(number)) {				
					intRounds[i] = number;
					correct = true;
				}else {
					boolean included = false;
					for(int j = 0; j<i; j++) {
						if(intRounds[j] == number) {
							included = true;
						}
					}
					if(!included && xCorrectAnimalAvailable(number)) {										
						correct = true;
						intRounds[i] = number;
					}
				}
			}
		}
		for(int i =0; i<intRounds.length; i++) {
			chooseAnimalsForRound(intRounds[i]);
		}
	}

	//Choose the animals for one round
	private void chooseAnimalsForRound(int attribute) {
		List<Animal> chosenAnimals =  new ArrayList<Animal>();



		//lifeExpectancy
		if(attribute ==3) {
			while(chosenAnimals.size()<animalsPerGame) {
				int number =(int) (Math.random()*availableAnimals.size());
				//Avoid Duplicates
				if(!(chosenAnimals.contains(availableAnimals.get(number)))) {
					//Avoid animals with equal attribute
					boolean alreadyContains = false;
					for(int i =0; i<chosenAnimals.size();i++) {
						if(chosenAnimals.get(i).getLifeExpectancy() ==availableAnimals.get(number).getLifeExpectancy()) {
							alreadyContains = true;
						}
					}
					if(alreadyContains == false) {
						chosenAnimals.add(availableAnimals.get(number));
					}
				}
			}
			rounds.add(new Round(chosenAnimals,"lifeExpectancy",attribute));
		}

		//size
		if(attribute ==4) {
			while(chosenAnimals.size()<animalsPerGame) {
				int number =(int) (Math.random()*availableAnimals.size());
				//Avoid Duplicates
				if(!(chosenAnimals.contains(availableAnimals.get(number)))) {
					//Avoid animals with equal attribute
					boolean alreadyContains = false;
					for(int i =0; i<chosenAnimals.size();i++) {
						if(chosenAnimals.get(i).getSize() ==availableAnimals.get(number).getSize()) {
							alreadyContains = true;
						}
					}
					if(alreadyContains == false) {
						chosenAnimals.add(availableAnimals.get(number));
					}
				}
			}
			rounds.add(new Round(chosenAnimals,"size",attribute));
		}

		//weight
		if(attribute ==5) {
			while(chosenAnimals.size()<animalsPerGame) {
				int number =(int) (Math.random()*availableAnimals.size());
				//Avoid Duplicates
				if(!(chosenAnimals.contains(availableAnimals.get(number)))) {
					//Avoid animals with equal attribute
					boolean alreadyContains = false;
					for(int i =0; i<chosenAnimals.size();i++) {
						if(chosenAnimals.get(i).getWeight() ==availableAnimals.get(number).getWeight()) {
							alreadyContains = true;
						}
					}
					if(alreadyContains == false) {
						chosenAnimals.add(availableAnimals.get(number));
					}
				}
			}
			rounds.add(new Round(chosenAnimals,"weight",attribute));
		}
		//Population
		if(attribute ==6) {
			while(chosenAnimals.size()<animalsPerGame) {
				int number =(int) (Math.random()*availableAnimals.size());
				//Avoid Duplicates
				if(!(chosenAnimals.contains(availableAnimals.get(number)))) {
					//Avoid animals with equal attribute
					boolean alreadyContains = false;
					for(int i =0; i<chosenAnimals.size();i++) {
						if(chosenAnimals.get(i).getPopulation() ==availableAnimals.get(number).getPopulation()||availableAnimals.get(number).getPopulation()==0) {
							alreadyContains = true;
						}
					}
					if(alreadyContains == false) {
						chosenAnimals.add(availableAnimals.get(number));
					}
				}
			}
			rounds.add(new Round(chosenAnimals,"Population",attribute));
		}
	}

	//Tests if it is possible to generate the game, enough animals for round
	private boolean xCorrectAnimalAvailable(int attribute) {
		int count = 0;

		//LifeExpectancy
		if(attribute == 3) {
			List<Integer> list = new ArrayList<Integer>();
			for(int i =0; i<availableAnimals.size();i++) {
				if(i == 0) {
					list.add(availableAnimals.get(i).getLifeExpectancy());
					count++;
				}
				if(!list.contains(availableAnimals.get(i).getLifeExpectancy())) {
					list.add(availableAnimals.get(i).getLifeExpectancy());
					count++;
				}			
				if(list.size()>=animalsPerGame) {
					System.out.println("Genug Daten Vorhanden! LifeExpectancy");
					return true;
				}
			}			
		}

		//size
		if(attribute == 4) {
			List<Integer> list = new ArrayList<Integer>();
			for(int i =0; i<availableAnimals.size();i++) {
				if(i == 0) {
					list.add(availableAnimals.get(i).getSize());
					count++;
				}
				if(!list.contains(availableAnimals.get(i).getSize())) {
					list.add(availableAnimals.get(i).getSize());
					count++;
				}			
				if(list.size()>=animalsPerGame) {
					System.out.println("Genug Daten Vorhanden! size");
					return true;
				}
			}			
		}

		//weight
		if(attribute == 5) {
			List<Integer> list = new ArrayList<Integer>();
			for(int i =0; i<availableAnimals.size();i++) {
				if(i == 0) {
					list.add(availableAnimals.get(i).getWeight());
					count++;
				}
				if(!list.contains(availableAnimals.get(i).getWeight())) {
					list.add(availableAnimals.get(i).getWeight());
					count++;
				}			
				if(list.size()>=animalsPerGame) {
					System.out.println("Genug Daten Vorhanden! weight");
					return true;
				}
			}			
		}

		//population
		if(attribute == 6) {
			List<Integer> list = new ArrayList<Integer>();
			for(int i =0; i<availableAnimals.size();i++) {
				if(i == 0 && availableAnimals.get(i).getPopulation() != 0) {
					list.add(availableAnimals.get(i).getPopulation());
					count++;
				}
				if((!list.contains(availableAnimals.get(i).getPopulation())&& availableAnimals.get(i).getPopulation() != 0)) {
					list.add(availableAnimals.get(i).getPopulation());
					count++;
				}			
				if(list.size()>=animalsPerGame) {
					System.out.println("Genug Daten Vorhanden! population");
					return true;
				}
			}			
		}
		return false;
	}

	//Handles one round and ask the player to play
	private void askRound(Round round) {
		List<ValuePair> pairList = new ArrayList<ValuePair>();
		//Data simplifier

		if(round.getAditionalInt() == 3) {
			for( int i = 0; i<animalsPerGame;i++) {
				var pair = new ValuePair(round.getAnimals().get(i).getName(), (round.getAnimals().get(i).getLifeExpectancy()+""));
				pairList.add(pair);
			}
		}
		if(round.getAditionalInt() == 4) {
			for( int i = 0; i<animalsPerGame;i++) {
				var pair = new ValuePair(round.getAnimals().get(i).getName(), (round.getAnimals().get(i).getSize()+""));
				pairList.add(pair);
			}
		}
		if(round.getAditionalInt() == 5) {
			for( int i = 0; i<animalsPerGame;i++) {
				var pair = new ValuePair(round.getAnimals().get(i).getName(), (round.getAnimals().get(i).getWeight()+""));
				pairList.add(pair);
			}
		}
		if(round.getAditionalInt() == 6) {
			for( int i = 0; i<animalsPerGame;i++) {
				var pair = new ValuePair(round.getAnimals().get(i).getName(), (round.getAnimals().get(i).getPopulation()+""));
				pairList.add(pair);
			}
		}

		System.out.println("Daten vereinfacht");
		List<ValuePair> correctPairList = new ArrayList<ValuePair>();
		int number = (int) (Math.random()*animalsPerGame);
		correctPairList.add(pairList.get(number));
		pairList.remove(number);

		Boolean roundDone = false;
		errors = this.allowedErrorsPerRound;
		while(roundDone == false) {
			this.roundBuilder(round, pairList, correctPairList );
			this.roundQuestion(round, pairList,correctPairList);

			if(errors == 0) {
				roundDone = true;
			}

			if(correctPairList.size() == animalsPerGame) {
				roundDone = true;
			}
		}
		//set all correct, to learn from your mistakes
		correctPairList.addAll(pairList);
		pairList.clear();
		System.out.println("Auflösung:");
		this.roundBuilder(round, pairList, correctPairList);
	}
	//Creates current state of the game
	private void roundBuilder(Round round, List<ValuePair> pairList, List<ValuePair> correctPairList) {
		System.out.println(round.getRoundDefinition());
		//Errors
		System.out.print("--------------------------------------------[");
		for(int i = 0; i<allowedErrorsPerRound;i++) {
			if(i<errors) {
				System.out.print("X");
			}else {
				System.out.print("-");
			}			
		}	
		System.out.println("]");

		int maxHeigth = animalsPerGame *4+2;
		int startGuessedAnimals = (maxHeigth/2) -(2 *((correctPairList.size()+1)/2))-6;
		int startAnimals = (maxHeigth/2) -(4 *((pairList.size()-1)/2))-5;
		//sort the correctPairList
		for(int i = 0; i<correctPairList.size();i++) {
			for(int j = 0; j<correctPairList.size();j++) {
				if(Integer.parseInt(correctPairList.get(i).getAttribute())>Integer.parseInt(correctPairList.get(j).getAttribute())) {
					ValuePair helpPair = correctPairList.get(i);
					correctPairList.set(i, correctPairList.get(j));
					correctPairList.set(j,helpPair);
				}
			}
		}
		int guessedAnimal = 0;
		int animal = 0;
		for(int i = 0; i<maxHeigth;i++) {
			Boolean gV = false;
			Boolean aV = false;
			for(int j = 0; j<correctPairList.size(); j++) {
				if(i == startGuessedAnimals + (guessedAnimal*2) && guessedAnimal<(correctPairList.size()*2+1)) {
					if(guessedAnimal % 2 == 0) {
						System.out.print( (guessedAnimal) +". ");
					}else {					
						System.out.print( (guessedAnimal) +". " + correctPairList.get((guessedAnimal-1)/2).getAnimal());		
						System.out.print(" Wert: " + round.getValueIncluding(Integer.parseInt(correctPairList.get((guessedAnimal-1)/2).getAttribute())));
					}
					guessedAnimal++;
					gV =true;
				}
			}
			if(gV == false) {
				System.out.print("\t\t\t\t");
			}
			for(int j = 0; j<pairList.size(); j++) {
				if(i == startAnimals + (animal*4)&& animal<pairList.size()) {
					System.out.println("\t"+ (char)(animal+65) +". " + pairList.get(animal).getAnimal());
					animal++;
					aV = true;
				}
			}
			if(aV == false) {
				System.out.println();
			}
		}	
	}
	
	//Ask the player which animal should be placed
	private void roundQuestion(Round round, List<ValuePair> pairList, List<ValuePair> correctPairList) {
		System.out.println("Wähle ein Tier welches du einordnen willst!");
		int playerAnswerAnimal = -1;
		int playerAnswerPosition = -1;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));	
		String input;
		try {
			input = in.readLine();
			playerAnswerAnimal = recursiveChoiceHandlerAnimal(input, pairList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("An welche Position will du das Tier einordnen? Oben steht die höchste Zahl!");
		in = new BufferedReader(new InputStreamReader(System.in));	
		try {
			input = in.readLine();
			playerAnswerPosition = recursiveChoiceHandlerPosition(input, correctPairList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Wich Animal needs to be checked
		if(playerAnswerPosition == 0) {
			int corA = Integer.parseInt(correctPairList.get((((playerAnswerPosition+1)-1)/2)).getAttribute());
			int gueA = Integer.parseInt(pairList.get(playerAnswerAnimal).getAttribute());
			if(gueA >corA)  {
				System.out.println("Die Antwort ist richtig!");
				correctPairList.add(pairList.get(playerAnswerAnimal));
				pairList.remove(pairList.get(playerAnswerAnimal));
				score.incPoints();
			}else {
				errors = errors -1;
				score.noPoints();
				System.out.println("Die Antwort ist falsch!");
			}
		}else {
			if(playerAnswerPosition == ((correctPairList.size()*2))) {
				int corA = Integer.parseInt(correctPairList.get((((playerAnswerPosition-1)-1)/2)).getAttribute());
				int gueA = Integer.parseInt(pairList.get(playerAnswerAnimal).getAttribute());
				if(gueA <corA)  {
					System.out.println("Die Antwort ist richtig!");
					correctPairList.add(pairList.get(playerAnswerAnimal));
					pairList.remove(pairList.get(playerAnswerAnimal));
					score.incPoints();
				}else {
					errors = errors -1;
					score.noPoints();
					System.out.println("Die Antwort ist falsch!");

				}
			}else {
				int corA1 = Integer.parseInt(correctPairList.get((((playerAnswerPosition+1)-1)/2)).getAttribute());
				int corA2 = Integer.parseInt(correctPairList.get((((playerAnswerPosition-1)-1)/2)).getAttribute());
				int gueA = Integer.parseInt(pairList.get(playerAnswerAnimal).getAttribute());
				if( gueA >corA1  && gueA <corA2)  {
					System.out.println("Die Antwort ist richtig!");
					correctPairList.add(pairList.get(playerAnswerAnimal));
					pairList.remove(pairList.get(playerAnswerAnimal));
					score.incPoints();
				}else {
					errors = errors -1;
					score.noPoints();
					System.out.println("Die Antwort ist falsch!");
				}
			}
		}

	}
	
	//Handles the animal selection
	public int recursiveChoiceHandlerAnimal(String input, List<ValuePair> pairList) {
		input = input.toUpperCase();
		int intInput;
		for(int i = 0; i<pairList.size(); i++) {
			if(input.length()>0) {
				intInput = input.toCharArray()[0]-65;

				if(intInput == i ){
					return intInput;
				}
			}
		}	
		System.out.println("Wir konnten leider nicht verstehen was du tun möchtest, versuche es nochmal!");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = in.readLine();
			return recursiveChoiceHandlerAnimal(input,pairList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return 0;
	}
	
	//Handles the position selection, only even counts, because odds are given, and positions can go up to multiple inputs
	public int recursiveChoiceHandlerPosition(String input, List<ValuePair> correctPairList) {
		int intInput = 0;	
		if(input.length()>0) {
			for(int j = 0; j < input.length();j++) {
				int help = (input.toCharArray()[input.length()-1-j]-48); //invertieren der eingabe
				intInput = (int) (intInput + help * Math.pow(10, j));
			}

			for(int i = 0; i<(correctPairList.size()*2+1); i++) {

				if(intInput == i && intInput % 2 == 0){
					return intInput;
				}
			}
		}

		System.out.println("Wir konnten leider nicht verstehen was du tun möchtest, versuche es nochmal!");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = in.readLine();
			return recursiveChoiceHandlerPosition(input, correctPairList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return 0;
	}
}
