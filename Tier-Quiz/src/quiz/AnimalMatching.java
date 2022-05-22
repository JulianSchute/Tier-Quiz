package quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.DataLoader;
import data.DataSaver;
import data.tools;
import objects.Animal;
import objects.Option;
import objects.Player;
import objects.Round;
import objects.Score;
import objects.ValuePair;

public class AnimalMatching extends Game{

	public int animalsPerGame = 6;
	public int roundsPerGame = 3;
	public int allowedErrorsPerRound = 3;
	public int errors = 3;
	private List<Round> rounds=  new ArrayList<Round>();
	private List<Animal> availableAnimals =  new ArrayList<Animal>();

	public AnimalMatching() {
		this.setType(GameType.ANIMALMATCHING);
		this.generateGame();
	}

	//Caution animalsPerGame isn't adjustable
	public AnimalMatching(int animalsPerGame, int roundsPerGame, int allowedErrorsPerRound) {
		this.animalsPerGame = animalsPerGame;
		this.roundsPerGame = roundsPerGame;
		this.allowedErrorsPerRound = allowedErrorsPerRound;
		this.setType(GameType.ANIMALMATCHING);
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
		return "Du hast AnimalMatching ausgewählt";

	}

	@Override
	protected void generateGame() {
		// TODO Auto-generated method stub
		availableAnimals = DataLoader.loadAnimalData("\\Files\\Animals.txt");
		this.chooseRounds();		
	}

	private void chooseRounds() {
		//There are 8 possible attributes to generate
		int[] intRounds = new int[roundsPerGame];
		for(int i =0; i<intRounds.length; i++) {
			boolean correct = false;
			while( correct == false) {
				int number =(int) (Math.random()*8);
				System.out.println("Zahl: " + number);
				if(i == 0 && xCorrectAnimalAvailable(number, availableAnimals, animalsPerGame)) {				
					intRounds[i] = number;
					correct = true;
				}else {
					boolean included = false;
					for(int j = 0; j<i; j++) {
						if(intRounds[j] == number) {
							included = true;
						}
					}
					if(!included && xCorrectAnimalAvailable(number, availableAnimals, animalsPerGame)) {										
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
	//We can only choose categories with X unique answers
	public boolean xCorrectAnimalAvailable(int attribute, List<Animal> availableAnimal, int animalsPerGame ) {
		int count = 0;
		//scientificName
		if(attribute == 0) {
			List<String> list = new ArrayList<String>();
			for(int i =0; i<availableAnimal.size();i++) {
				if(i == 0) {
					list.add(availableAnimal.get(i).getScientificName());
					count++;
				}
				if(!list.contains(availableAnimal.get(i).getScientificName())) {
					list.add(availableAnimal.get(i).getScientificName());
					count++;
				}			
				if(list.size()>=animalsPerGame) {
					System.out.println("Genug Daten Vorhanden! ScName");
					return true;
				}
			}			
		}

		//Family
		if(attribute == 1) {
			List<String> list = new ArrayList<String>();
			for(int i =0; i<availableAnimal.size();i++) {
				if(i == 0) {
					list.add(availableAnimal.get(i).getFamily());
					count++;
				}
				if(!list.contains(availableAnimal.get(i).getFamily())) {
					list.add(availableAnimal.get(i).getFamily());
					count++;
				}			
				if(list.size()>=animalsPerGame) {
					System.out.println("Genug Daten Vorhanden! Family");
					return true;
				}
			}			
		}

		//Order
		if(attribute == 2) {
			List<String> list = new ArrayList<String>();
			for(int i =0; i<availableAnimal.size();i++) {
				if(i == 0) {
					list.add(availableAnimal.get(i).getOrder());
					count++;
				}
				if(!list.contains(availableAnimal.get(i).getOrder())) {
					list.add(availableAnimal.get(i).getOrder());
					count++;
				}			
				if(list.size()>=animalsPerGame) {
					System.out.println("Genug Daten Vorhanden! Order");
					return true;
				}
			}			
		}

		//LifeExpectancy
		if(attribute == 3) {
			List<Integer> list = new ArrayList<Integer>();
			for(int i =0; i<availableAnimal.size();i++) {
				if(i == 0) {
					list.add(availableAnimal.get(i).getLifeExpectancy());
					count++;
				}
				if(!list.contains(availableAnimal.get(i).getLifeExpectancy())) {
					list.add(availableAnimal.get(i).getLifeExpectancy());
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
			for(int i =0; i<availableAnimal.size();i++) {
				if(i == 0) {
					list.add(availableAnimal.get(i).getSize());
					count++;
				}
				if(!list.contains(availableAnimal.get(i).getSize())) {
					list.add(availableAnimal.get(i).getSize());
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
			for(int i =0; i<availableAnimal.size();i++) {
				if(i == 0) {
					list.add(availableAnimal.get(i).getWeight());
					count++;
				}
				if(!list.contains(availableAnimal.get(i).getWeight())) {
					list.add(availableAnimal.get(i).getWeight());
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
			for(int i =0; i<availableAnimal.size();i++) {
				if(i == 0 && availableAnimal.get(i).getPopulation() != 0) {
					list.add(availableAnimal.get(i).getPopulation());
					count++;
				}
				if((!list.contains(availableAnimal.get(i).getPopulation())&& availableAnimal.get(i).getPopulation() != 0)) {
					list.add(availableAnimal.get(i).getPopulation());
					count++;
				}			
				if(list.size()>=animalsPerGame) {
					System.out.println("Genug Daten Vorhanden! population");
					return true;
				}
			}			
		}

		//habitat
		if(attribute == 7) {
			List<String> list = new ArrayList<String>();
			for(int i =0; i<availableAnimal.size();i++) {
				if(i == 0) {
					list.add(availableAnimal.get(i).getHabitat());
					count++;
				}
				if(!list.contains(availableAnimal.get(i).getHabitat())) {
					list.add(availableAnimal.get(i).getHabitat());
					count++;
				}			
				if(list.size()>=animalsPerGame) {
					System.out.println("Genug Daten Vorhanden! Habitat");
					return true;
				}
			}			
		}

		return false;
	}
	//Choose fitting animals
	private void chooseAnimalsForRound(int attribute) {
		List<Animal> chosenAnimals =  new ArrayList<Animal>();

		//ScientificName
		if(attribute ==0) {
			while(chosenAnimals.size()<animalsPerGame) {
				int number =(int) (Math.random()*availableAnimals.size());
				//Avoid Duplicates
				if(!(chosenAnimals.contains(availableAnimals.get(number)))) {
					//Avoid animals with equal attribute
					boolean alreadyContains = false;
					for(int i =0; i<chosenAnimals.size();i++) {
						if(chosenAnimals.get(i).getScientificName().equals(availableAnimals.get(number).getScientificName())) {
							alreadyContains = true;
						}
					}
					if(alreadyContains == false) {
						chosenAnimals.add(availableAnimals.get(number));
					}
				}
			}
			rounds.add(new Round(chosenAnimals,"ScientificName",attribute));
		}

		//family
		if(attribute ==1) {
			while(chosenAnimals.size()<animalsPerGame) {
				int number =(int) (Math.random()*availableAnimals.size());
				//Avoid Duplicates
				if(!(chosenAnimals.contains(availableAnimals.get(number)))) {
					//Avoid animals with equal attribute
					boolean alreadyContains = false;
					for(int i =0; i<chosenAnimals.size();i++) {
						if(chosenAnimals.get(i).getFamily().equals(availableAnimals.get(number).getFamily())) {
							alreadyContains = true;
						}
					}
					if(alreadyContains == false) {
						chosenAnimals.add(availableAnimals.get(number));
					}
				}
			}
			rounds.add(new Round(chosenAnimals,"Family",attribute));
		}

		//Order
		if(attribute ==2) {
			while(chosenAnimals.size()<animalsPerGame) {
				int number =(int) (Math.random()*availableAnimals.size());
				//Avoid Duplicates
				if(!(chosenAnimals.contains(availableAnimals.get(number)))) {
					//Avoid animals with equal attribute
					boolean alreadyContains = false;
					for(int i =0; i<chosenAnimals.size();i++) {
						if(chosenAnimals.get(i).getOrder().equals(availableAnimals.get(number).getOrder())) {
							alreadyContains = true;
						}
					}
					if(alreadyContains == false) {
						chosenAnimals.add(availableAnimals.get(number));
					}
				}
			}
			rounds.add(new Round(chosenAnimals,"Order",attribute));
		}

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

		//Habitat
		if(attribute ==7) {
			while(chosenAnimals.size()<animalsPerGame) {
				int number =(int) (Math.random()*availableAnimals.size());
				//Avoid Duplicates
				if(!(chosenAnimals.contains(availableAnimals.get(number)))) {
					//Avoid animals with equal attribute
					boolean alreadyContains = false;
					for(int i =0; i<chosenAnimals.size();i++) {
						if(chosenAnimals.get(i).getHabitat().equals(availableAnimals.get(number).getHabitat())) {
							alreadyContains = true;
						}
					}
					if(alreadyContains == false) {
						chosenAnimals.add(availableAnimals.get(number));
					}
				}
			}
			rounds.add(new Round(chosenAnimals,"Habitat",attribute));
		}
	}
	//AskRound
	private void askRound(Round round) {
		List<ValuePair> pairList = new ArrayList<ValuePair>();
		//Data simplifier
		if(round.getAditionalInt() == 0) {
			for( int i = 0; i<animalsPerGame;i++) {				
				var pair = new ValuePair(round.getAnimals().get(i).getName(), round.getAnimals().get(i).getScientificName());
				pairList.add(pair);
			}
		}
		if(round.getAditionalInt() == 1) {
			for( int i = 0; i<animalsPerGame;i++) {
				var pair = new ValuePair(round.getAnimals().get(i).getName(), round.getAnimals().get(i).getFamily());
				pairList.add(pair);
			}
		}
		if(round.getAditionalInt() == 2) {
			for( int i = 0; i<animalsPerGame;i++) {
				var pair = new ValuePair(round.getAnimals().get(i).getName(), round.getAnimals().get(i).getOrder());
				pairList.add(pair);
			}
		}
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
		if(round.getAditionalInt() == 7) {
			for( int i = 0; i<animalsPerGame;i++) {
				var pair = new ValuePair(round.getAnimals().get(i).getName(), round.getAnimals().get(i).getHabitat());
				pairList.add(pair);
			}
		}
		System.out.println("Daten vereinfacht");

		//Choose randomization
		List<Option> attributes = new ArrayList<Option>(); //Boolean for allready used or not
		List<String> animals = new ArrayList<String>();
		//fill with dummy values
		for(int i =0; i<animalsPerGame;i++) {			
			attributes.add(new Option("empty"));
			animals.add("empty");
		}		

		for(int i =0; i<animalsPerGame;i++) {
			boolean correct = false;
			while( correct == false) {
				int number =(int) (Math.random()*animalsPerGame);			
				if(attributes.get(number).getValue().equals("empty")) {
					correct = true;													
					attributes.set(number, new Option(pairList.get(i).getAttribute()) );
				}
			}
			correct = false;
			while( correct == false) {
				int number =(int) (Math.random()*animalsPerGame);
				if(animals.get(number).equals("empty")) {
					correct = true;				
					animals.set(number,pairList.get(i).getAnimal());
				}
			}
		}
		Boolean roundDone = false;
		errors = this.allowedErrorsPerRound;
		while(roundDone == false) {
			this.roundBuilder(round, pairList, attributes, animals);
			this.roundQuestion(round, pairList, attributes, animals);

			if(errors == 0) {
				roundDone = true;
			}
			boolean everythingSolved = true;
			for(int i = 0; i<animalsPerGame; i++) {
				if(attributes.get(i).getGuessed() == false) {
					everythingSolved = false;
				}
			}
			if(everythingSolved == true) {
				roundDone = true;
			}
		}
		for(int i = 0; i<animalsPerGame; i++) {
			attributes.get(i).setGuessed(true);
		}
		System.out.println("Auflösung:");
		this.roundBuilder(round, pairList, attributes, animals);

	}

	//builds the Game Layout
	public void roundBuilder( Round round, List<ValuePair> pairList, List<Option> attributes, List<String> animals) {
		//Round Question
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

		//values
		for(int i = 0; i<animalsPerGame; i++) {
			//attributes
			if(attributes.get(i).getGuessed() == false) {
				if(round.getAditionalInt()== 3 ||round.getAditionalInt()== 4 ||round.getAditionalInt()== 5 ||round.getAditionalInt()== 6) {
					int help = Integer.parseInt(attributes.get(i).getValue());
					System.out.println(i +". " + round.getValueIncluding(help));
				}else {
					System.out.println(i +". " + attributes.get(i).getValue());
				}
			}else {
				System.out.println(i +". ");
			}
			//animals
			System.out.println("\t\t\t"+ (char)(i+65) +". " + animals.get(i));
			//find if done
			boolean done = false;
			String value = "";
			//guessedAttributes
			for(int j = 0; j<animalsPerGame;j++) {
				if(pairList.get(j).getAnimal().equals(animals.get(i))) {
					for(int h = 0; h<animalsPerGame;h++) {
						if(pairList.get(j).getAttribute().equals(attributes.get(h).getValue())) {
							if(attributes.get(h).getGuessed() ==true){
								done = true;
								value =attributes.get(h).getValue();
							}
						}
					}
				}
			}
			if(done == true) {
				if(round.getAditionalInt()== 3 ||round.getAditionalInt()== 4 ||round.getAditionalInt()== 5 ||round.getAditionalInt()== 6) {
					int help = Integer.parseInt(value);
					System.out.println( "\t\t\t"+ round.getValueIncluding(help));
				}else {
					System.out.println("\t\t\t" + value);
				}			
			}else {
				System.out.println();
			}
		}

	}
	//asks the player
	public void roundQuestion( Round round, List<ValuePair> pairList, List<Option> attributes, List<String> animals) {

		System.out.println("Welches Attribut willst du zuordnen. Antworte mit 0-" + (animalsPerGame-1));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input;
		int playerAnswerAttribute = -1;
		int playerAnswerAnimal = -1;

		//read player answer
		try {
			input = in.readLine();
			playerAnswerAttribute = recursiveChoiceHandlerAttribute(input, attributes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Welches Tier willst du zuordnen. Antworte mit A-" + ((char)(animalsPerGame-1+65)));
		in = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = in.readLine();
			playerAnswerAnimal = recursiveChoiceHandlerAnimal(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//check if answer is correct
		boolean correctAnswer = false;
		for(int i = 0; i<animalsPerGame; i++) {
			if(pairList.get(i).getAnimal().equals(animals.get(playerAnswerAnimal)) && pairList.get(i).getAttribute().equals(attributes.get(playerAnswerAttribute).getValue())) {
				attributes.get(playerAnswerAttribute).setGuessed(true);
				correctAnswer = true;
				score.incPoints();
			}						
		}
		if(correctAnswer == false) {
			errors = errors -1;
			score.noPoints();
		}
	}

	public int recursiveChoiceHandlerAnimal(String input) {
		input = input.toUpperCase();
		int intInput;
		for(int i = 0; i<animalsPerGame; i++) {
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
			return recursiveChoiceHandlerAnimal(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return 0;
	}
	public int recursiveChoiceHandlerAttribute(String input, List<Option> attributes) {
		int intInput;		
		for(int i = 0; i<animalsPerGame; i++) {
			if(input.length()>0) {
				intInput = input.toCharArray()[0]-48;

				if(intInput == i&& attributes.get(i).getGuessed() == false){
					return intInput;
				}
			}
		}

		System.out.println("Wir konnten leider nicht verstehen was du tun möchtest, versuche es nochmal!");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = in.readLine();
			return recursiveChoiceHandlerAttribute(input, attributes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return 0;
	}
}
