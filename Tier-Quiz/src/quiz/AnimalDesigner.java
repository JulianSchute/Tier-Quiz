package quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import data.DataLoader;
import data.DataSaver;
import data.tools;
import objects.Animal;
import objects.Player;

public class AnimalDesigner extends Page {

	private boolean fastMode = false;
	private Animal animalToEdit = null;

	public AnimalDesigner() {

	}
	@Override
	public void startPage(Player player) {
		System.out.println("Was möchtest du tun? \nA - Ein Tier erstellen \nB - Ein Tier bearbeiten \nC - Ein Tier löschen \nE - Verlassen");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = in.readLine();
			int result = recursiveChoiceHandler(input);
			activateFastMode();
			if(result == 0) {
				Animal animal = new Animal();
				this.addAnimal(animal);
			}
			if(result == 1) {
				animalToEdit = chooseAnimal("Welches Tier willst du bearbeiten?");
				this.addAnimal(animalToEdit);
				this.editFile(animalToEdit, true);
			}
			if(result == 2) {
				animalToEdit = chooseAnimal("Welches Tier willst du löschen?");
				this.editFile(animalToEdit, false);

			}
			//E will just continue and close the page
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void witchPageIsIt() {
		System.out.println("Du hast AnimalDesigner ausgewählt");

	}


	public static int recursiveChoiceHandler(String input) {
		input = input.toUpperCase();
		if(input.equals("A") ) {

			return 0;
		}
		if(input.equals("B") ) {

			return 1;
		}
		if(input.equals("C") ) {

			return 2;
		}
		if(input.equals("E") ) {

			return 3;
		}
		System.out.println("Wir konnten leider nicht verstehen was du tun möchtest, versuche es nochmal!");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = in.readLine();
			System.out.println(input);
			return recursiveChoiceHandler(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 3;		
	}

	public void addAnimal(Animal animal) {
		boolean answerOk = false;
		do {
			animal.setName( designName());
			if(fastMode == false) {
				try {
					System.out.println("Zufrieden mit der Eingabe?");
					answerOk =tools.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}while(answerOk == false && fastMode == false);

		answerOk = false;
		do {
			animal.setScientificName( designScientificName());
			if(fastMode == false) {
				try {
					System.out.println("Zufrieden mit der Eingabe?");
					answerOk =tools.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}while(answerOk == false && fastMode == false);

		answerOk = false;
		do {
			animal.setFamily( designFamily());
			if(fastMode == false) {
				try {
					System.out.println("Zufrieden mit der Eingabe?");
					answerOk =tools.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}while(answerOk == false && fastMode == false);

		answerOk = false;
		do {
			animal.setOrder( designOrder());
			if(fastMode == false) {
				try {
					System.out.println("Zufrieden mit der Eingabe?");
					answerOk =tools.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}while(answerOk == false && fastMode == false);

		answerOk = false;
		do {
			animal.setLifeExpectancy( designLifeExpectancy());
			if(fastMode == false) {
				try {
					System.out.println("Zufrieden mit der Eingabe?");
					answerOk =tools.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}while(answerOk == false && fastMode == false);

		answerOk = false;
		do {
			animal.setSize( designSize());
			if(fastMode == false) {
				try {
					System.out.println("Zufrieden mit der Eingabe?");
					answerOk =tools.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}while(answerOk == false && fastMode == false);

		answerOk = false;
		do {
			animal.setWeight( designWeight());
			if(fastMode == false) {
				try {
					System.out.println("Zufrieden mit der Eingabe?");
					answerOk =tools.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}while(answerOk == false && fastMode == false);

		answerOk = false;
		do {
			animal.setPopulation( designPopulation());
			if(fastMode == false) {
				try {
					System.out.println("Zufrieden mit der Eingabe?");
					answerOk =tools.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}while(answerOk == false && fastMode == false);

		answerOk = false;
		do {
			animal.setHabitat( designHabitat());
			if(fastMode == false) {
				try {
					System.out.println("Zufrieden mit der Eingabe?");
					answerOk =tools.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}while(answerOk == false && fastMode == false);

		DataSaver.safeAnimal(animal);
	}

	private Animal chooseAnimal(String question) {
		List<Animal> animals = DataLoader.loadAnimalData("\\Files\\Animals.txt");
		System.out.println(question);
		System.out.println("");
		for(int i = 0; i<animals.size();i++) {
			System.out.println(animals.get(i).getName());
		}
		//needed
		while(true) {
			System.out.println(question);
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				String input = in.readLine();
				for(int i = 0; i<animals.size();i++) {
					if(animals.get(i).getName().equals(input)) {
						return animals.get(i);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	private void editFile(Animal animal, boolean swapAnimal) {
		List<Animal> animals = DataLoader.loadAnimalData("\\Files\\Animals.txt");
		for(int i = 0; i<animals.size();i++) {
			if(animals.get(i).getName().equals(animal.getName())) {
				animals.remove(i);
			}
		}
		if(swapAnimal == true) {
		animals.add(animal);
		}
		DataSaver.editAnimal(animals);
	}
	
	public void activateFastMode() {
		System.out.println("Möchtest du beim AnimalDesigner Sicherheitsfragen ehalten. So wie diese hier!");
		try {
			if(!tools.accept()) {
				fastMode = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	//For the animal name
	public String designName() {
		if(animalToEdit != null) {
			System.out.println("Möchtest du den Wert " +  animalToEdit.getName() +" verändern!");
			try {
				if(!tools.accept()) {
					return animalToEdit.getName();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String input = "";
		System.out.println("Wie lautet der Name des Tiers?");
		while(input.equals("")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				input = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return input;	
	}
	//For the animal scientific name
	public String designScientificName() {
		if(animalToEdit != null) {
			System.out.println("Möchtest du den Wert " +  animalToEdit.getScientificName() +" verändern!");
			try {
				if(!tools.accept()) {
					return animalToEdit.getScientificName();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String input = "";
		System.out.println("Wie lautet der Wissenschaftliche Name des Tiers?");
		while(input.equals("")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				input = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return input;	
	}

	//For the animal Family
	public String designFamily() {
		if(animalToEdit != null) {
			System.out.println("Möchtest du den Wert " +  animalToEdit.getFamily() +" verändern!");
			try {
				if(!tools.accept()) {
					return animalToEdit.getFamily();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String input = "";
		System.out.println("Wie lautet die Familie des Tiers?");
		while(input.equals("")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				input = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return input;	
	}

	//For the animal Order
	public String designOrder() {
		if(animalToEdit != null) {
			System.out.println("Möchtest du den Wert " +  animalToEdit.getOrder() +" verändern!");
			try {
				if(!tools.accept()) {
					return animalToEdit.getOrder();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String input = "";
		System.out.println("Wie lautet die Ordnung des Tiers?");
		while(input.equals("")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				input = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return input;	
	}

	//For the animal Habitat
	public String designHabitat() {
		if(animalToEdit != null) {
			System.out.println("Möchtest du den Wert " +  animalToEdit.getHabitat() +" verändern!");
			try {
				if(!tools.accept()) {
					return animalToEdit.getHabitat();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String input = "";
		System.out.println("Wo lebt das Tier? Länder oder Kontinente nur kein ; benutzen!");
		while(input.equals("")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				input = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return input;	
	}

	//For the animal Life Expectancy
	public int designLifeExpectancy() {
		if(animalToEdit != null) {
			System.out.println("Möchtest du den Wert " +  animalToEdit.getLifeExpectancy() +" verändern! In Jahren.");
			try {
				if(!tools.accept()) {
					return animalToEdit.getLifeExpectancy();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String input = "";
		System.out.println("Wie alt wird das Tier? '5' bedeutet 5 Jahre.");
		while(input.equals("")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				input = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Integer.parseInt(input);	
	}

	//For the animal Size
	public int designSize() {
		if(animalToEdit != null) {
			System.out.println("Möchtest du den Wert " +  animalToEdit.getSize() +" verändern! In Centimeter.");
			try {
				if(!tools.accept()) {
					return animalToEdit.getSize();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String input = "";
		System.out.println("Wie groß ist das Tier? In Centimeter. '100' ist ein Meter. Es zählt bei jedem der größte Wert. Beim Hund die Länge. Bei der Giraffe die Höhe und beim Adler die Flügelspannweite.");
		while(input.equals("")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				input = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Integer.parseInt(input);	
	}

	//For the animal Weight
	public int designWeight() {
		if(animalToEdit != null) {
			System.out.println("Möchtest du den Wert " +  animalToEdit.getWeight() +" verändern! In Gramm. '1000' ist gleich ein Kilogramm.");
			try {
				if(!tools.accept()) {
					return animalToEdit.getWeight();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String input = "";
		System.out.println("Wie schwer ist das Tier?");
		while(input.equals("")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				input = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Integer.parseInt(input);	
	}

	//For the animal Population
	public int designPopulation() {
		if(animalToEdit != null) {
			System.out.println("Möchtest du den Wert " +  animalToEdit.getPopulation() +" verändern! '0' bedeutet keine Werte. Es gibt viel zu viele Tauben auf der Welt.");
			try {
				if(!tools.accept()) {
					return animalToEdit.getPopulation();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String input = "";
		System.out.println("Wie Groß ist die Population des Tiers? '100' ist gleich es gibt 100 Tiere Weltweit. '0' steht für keine Angaben.");
		while(input.equals("")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				input = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Integer.parseInt(input);	
	}


	//TODO: Repeat 8 Times for all attributes

}
