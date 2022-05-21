package quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import data.DataLoader;
import data.DataSaver;
import data.tools;
import objects.Player;
import objects.Question;
import objects.Score;

public class AnimalQuiz extends Game {

	public List<Question> questions = new ArrayList<Question>();
	public int questionsPerGame = 6;

	public AnimalQuiz() {
		this.setType(GameType.ANIMALQUIZ);
		this.generateGame();
	}

	@Override
	public void startPage(Player player) {
		score = new Score(player, type);
		for(int i = 0; i<questionsPerGame; i++) {
			this.askQuestion(questions.get(i));
		}
		tools.scoreResult(score);
	}

	@Override
	public String witchPageIsIt() {
		return "Du hast AnimalQuiz ausgewählt";

	}


	@Override
	protected void generateGame() {
		List<Question> availableQuestions = DataLoader.loadQuestionData();
		//List<Question> availableQuestions = getAvailableQuestion();

		if(availableQuestions.size()<questionsPerGame) {
			//TODO: Throw Error or Error Log
			System.out.println("Anzahl Fragen zu niederig es gibt: " + questions.size()+ ". Du brauchst: " + questionsPerGame);
		}

		//Somehow I was able to choose n unique questions TODO: Exclude
		int[] chosenQuestions = new int[questionsPerGame];
		for(int i = 0; i<questionsPerGame; i++) {
			boolean correct = false;
			while( correct == false) {
				int number = (int) (Math.random()*availableQuestions.size());
				if(i == 0) {
					chosenQuestions[i] = number;
					correct = true;
				}else {
					boolean included = false;
					for(int j = 0; j<i; j++) {
						if(chosenQuestions[j] == number) {
							included = true;
						}
					}
					if(!included) {										
						correct = true;
						chosenQuestions[i] = number;
					}
				}
			}
		}
		System.out.println(chosenQuestions[0] + "  " +chosenQuestions[1] + "  " +chosenQuestions[2] + "  " +chosenQuestions[3] + "  " +chosenQuestions[4] + "  " +chosenQuestions[5]);
		for(int i = 0; i<questionsPerGame; i++) {
			questions.add(availableQuestions.get(chosenQuestions[i]));
			//System.out.println("Frage: " + questions.get(i).getQuestion() + " Antwort: "+questions.get(i).getCorrectAnswer());
			//System.out.println("Falsche Antworten: " +questions.get(i).getFalseAnswers()[0]+questions.get(i).getFalseAnswers()[1]+questions.get(i).getFalseAnswers()[2]+questions.get(i).getFalseAnswers()[3]+questions.get(i).getFalseAnswers()[4]+questions.get(i).getFalseAnswers()[5]);  
		}
	}

	//TODO: Exclude
	private List<Question> getAvailableQuestion() {
		//TODO: Laden aller Fragen aus XML oder JSON Datei
		//Aktuell ausführen von Methode welche Fragen generiert
		List<Question> generatedQuestions = WIPgenerateQuestion();
		for(int i =0 ; i<generatedQuestions.size();i++) {
			DataSaver.safeQuestion(generatedQuestions.get(i));
		}
		return generatedQuestions;		
	}

	//TODO: Nur provisorisch/ erste Mal
	private List<Question> WIPgenerateQuestion() {
		List<Question> generatedQuestions = new ArrayList<Question>();
		String[] answers = {"Katze", "Tiger", "Panther", "Hase", "Fuchs", "Leopard"};

		//Erste Frage
		Question question = new Question("Von welchem Tier ist der Wissenschaftliche Name 'Panthera leo'?", "Löwe", answers);
		//System.out.println(question.getQuestion()+question.getCorrectAnswer()+question.getFalseAnswers()[1]);
		generatedQuestions.add(question);

		answers = new String[6];
		answers[0] = "Aquarien"; 
		answers[1] = "Hunde"; 
		answers[2] = "Kleintiere"; 
		answers[3] = "Ziervögel"; 
		answers[4] = "Gartenteiche"; 
		answers[5] = "Aquarien"; 
		question = new Question("Welches typische Haustier ist in Deutschland am weitesten verbreitet?", "Katze", answers);
		generatedQuestions.add(question);

		answers = new String[6];
		answers[0] = "2-4 Jahre"; 
		answers[1] = "5-7 Jahre"; 
		answers[2] = "60-85 Jahre"; 
		answers[3] = "10-16 Jahre"; 
		answers[4] = "19-21 Jahre"; 
		answers[5] = "6-8 Jahre"; 
		question = new Question("Wie alt wird ein Tiger in Freiheit?", "20-25 Jahre", answers);
		generatedQuestions.add(question);

		answers = new String[6];
		answers[0] = "Antarktika"; 
		answers[1] = "Südamerika"; 
		answers[2] = "Europa"; 
		answers[3] = "Australien"; 
		answers[4] = "Asien"; 
		answers[5] = "Afrika"; 
		question = new Question("Von welchem Kontinet stammt der Waschbär?", "Nordamerika", answers);
		generatedQuestions.add(question);

		answers = new String[6];
		answers[0] = "15-20 Jahre"; 
		answers[1] = "5-7 Jahre"; 
		answers[2] = "80-89 Jahre"; 
		answers[3] = "40-50 Jahre"; 
		answers[4] = "19-24 Jahre"; 
		answers[5] = "35-40 Jahre"; 
		question = new Question("Wie alt wird ein Elefant in Freiheit?", "60-65 Jahre", answers);
		generatedQuestions.add(question);

		answers = new String[6];
		answers[0] = "15-20 Jahre"; 
		answers[1] = "5-7 Jahre"; 
		answers[2] = "1-1,5 Jahre"; 
		answers[3] = "3-4,5 Jahre"; 
		answers[4] = "2-2,5 Jahre"; 
		answers[5] = "11-12,5 Jahre"; 
		question = new Question("Wie alt wird ein Zwerghamster in Freiheit?", "1,5-2 Jahre", answers);
		generatedQuestions.add(question);

		answers = new String[6];
		answers[0] = "Antarktika, Afrika"; 
		answers[1] = "Südamerika, Afrika"; 
		answers[2] = "Europa, Nordamrika"; 
		answers[3] = "Australien, Afrika, Südamerika"; 
		answers[4] = "Asien, Australien"; 
		answers[5] = "Afrika, Australien"; 
		question = new Question("Auf welche Kontineten lebten wilde Löwe?", "Afrika, Eurasien", answers);
		generatedQuestions.add(question);

		answers = new String[6];
		answers[0] = "Katze"; 
		answers[1] = "Seehund"; 
		answers[2] = "Maus"; 
		answers[3] = "Leopard"; 
		answers[4] = "Elefant"; 
		answers[5] = "Dachs"; 
		question = new Question("Das Baby welches Tiers nennt man auch 'Welpe'?", "Wolf", answers);
		generatedQuestions.add(question);

		answers = new String[6];
		answers[0] = "Katze"; 
		answers[1] = "Hund"; 
		answers[2] = "Maus"; 
		answers[3] = "Leopard"; 
		answers[4] = "Elefant"; 
		answers[5] = "Schaf"; 
		question = new Question("Das Baby welches Tiers nennt man auch 'Heuler'?", "Seehund", answers);
		generatedQuestions.add(question);

		answers = new String[6];
		answers[0] = "Katze"; 
		answers[1] = "Hund"; 
		answers[2] = "Nashorn"; 
		answers[3] = "Kuh"; 
		answers[4] = "Hase"; 
		answers[5] = "Dachs"; 
		question = new Question("Das Baby welches Tiers nennt man auch 'Lamm'?", "Schaf", answers);
		generatedQuestions.add(question);


		return generatedQuestions;
	}

	//Handels a single Question
	private void askQuestion(Question question) {
		tools.ClearConsole();
		String[] answers = new String[4];
		System.out.println("---------------------------[Points:" + score.getPoints() +"]");
		System.out.println(question.getQuestion());
		//create position of correct answer
		int posCorrectAnswer = (int) (Math.random()*4);
		answers[posCorrectAnswer] = question.getCorrectAnswer();
		//choose wrong answers -> 1 means 123 and 3 means 345
		int posFalseAnswers = (int) (Math.random()*4);
		for(int i = 0; i<4; i++) {
			if(i != posCorrectAnswer) {
				answers[i] = question.getFalseAnswers()[posFalseAnswers];
				posFalseAnswers++;
			}
			System.out.println( (char)(i+ 65) + ") " + answers[i]);
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input;
		try {
			input = in.readLine();
			int playerAnswer= recursiveChoiceHandler(input)-65;
			if(playerAnswer == posCorrectAnswer) {
				System.out.println("Sie haben korrekt "+ answers[playerAnswer] +" geantwortet!");
				score.incPoints();
			}else {
				System.out.println("Sie haben falsch "+ answers[playerAnswer]+ " geantwortet! Die korrekte Antwort ist "+ answers[posCorrectAnswer] +"!");
				score.noPoints();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//
	public static int recursiveChoiceHandler(String input) {
		input = input.toUpperCase();
		int intInput;
		if(input.equals("A") ||input.equals("B")||input.equals("C")||input.equals("D") ) {
			intInput = input.toCharArray()[0];
			return intInput;
		}	
		System.out.println("Wir konnten leider nicht verstehen was du tun möchtest, versuche es nochmal!");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = in.readLine();
			return recursiveChoiceHandler(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return 0;
	}
}
