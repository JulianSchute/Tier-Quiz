package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import objects.Animal;
import objects.Player;
import objects.Question;
import objects.Score;
import quiz.GameType;

public class DataLoader {

	//Load the list of animals from text file
	public static List<Animal> loadAnimalData( String filePath) {
		List<Animal> animalList = new ArrayList<Animal>();
		try {
			File currentDirFile = new File("src");
			String helper = currentDirFile.getAbsolutePath();
			String path = helper + filePath;
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(isr);

			try {
				String strLine;
				int count = 0;

				while ((strLine = reader.readLine()) != null)   {
					//Avoid Data Description

					

						var listString = strLine.split(";");

						//System.out.println(listString[i]);
						Animal animal = new Animal(listString[0],listString[1], listString[2],listString[3],Integer.parseInt(listString[4]),Integer.parseInt(listString[5]),Integer.parseInt(listString[6]),Integer.parseInt(listString[7]),listString[8]);
						animalList.add(animal);


					
					// Print the content on the console
					count++;
					//System.out.println (strLine);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return animalList;

	}

	//Load the list of questions from text file
	public static List<Question> loadQuestionData() {
		List<Question> questionList = new ArrayList<Question>();
		try {
			File currentDirFile = new File("src");
			String helper = currentDirFile.getAbsolutePath();
			String path = helper + "\\Files\\Questions.txt";
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(isr);

			try {
				String strLine;
				int count = 0;

				while ((strLine = reader.readLine()) != null)   {
					//Avoid Data Description

					if(count != 0) {

						var listString = strLine.split(";");

						//System.out.println(listString[i]);
						String[] falseAnswers = {listString[2],listString[3],listString[4],listString[5],listString[6],listString[7]};
						Question question = new Question(listString[0],listString[1],falseAnswers);
						questionList.add(question);


					}
					// Print the content on the console
					count++;
					//System.out.println (strLine);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionList;	
	}

	//Load the list of Scores from text file
	public static List<Score> loadScoreData() {
		List<Score> scoreList = new ArrayList<Score>();
		try {
			File currentDirFile = new File("src");
			String helper = currentDirFile.getAbsolutePath();
			String path = helper + "\\Files\\Scores.txt";
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(isr);

			try {
				String strLine;
				int count = 0;

				while ((strLine = reader.readLine()) != null)   {
					//Avoid Data Description



					var listString = strLine.split(";");
					//for(int i = 0; i< listString.length;i++) {
					//System.out.println(listString[i]);
					if(listString[0].equals(GameType.ANIMALMATCHING.toString())) {
						Player player = new Player(listString[1]);
						Score score = new Score(player ,GameType.ANIMALMATCHING, Integer.parseInt(listString[2]));
						if(!scoreList.contains(score)) {
							scoreList.add(score);
						}

					}
					if(listString[0].equals(GameType.ANIMALQUIZ.toString())) {
						Player player = new Player(listString[1]);
						Score score = new Score(player ,GameType.ANIMALQUIZ, Integer.parseInt(listString[2]));
						if(!scoreList.contains(score)) {
							scoreList.add(score);
						}
					}
					if(listString[0].equals(GameType.SMALLERBIGGER.toString())) {
						Player player = new Player(listString[1]);
						Score score = new Score(player ,GameType.SMALLERBIGGER, Integer.parseInt(listString[2]));
						if(!scoreList.contains(score)) {
							scoreList.add(score);
						}
					}
					if(listString[0].equals(GameType.WHICHANIMALISIT.toString())) {
						Player player = new Player(listString[1]);
						Score score = new Score(player ,GameType.WHICHANIMALISIT, Integer.parseInt(listString[2]));
						if(!scoreList.contains(score)) {
							scoreList.add(score);
						}
					}




					//}
					// Print the content on the console
					count++;
					//System.out.println (strLine);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scoreList;

	}

}
