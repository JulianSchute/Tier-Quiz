package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import objects.Animal;
import objects.Player;
import objects.Question;
import objects.Score;

public class DataSaver {

	public static void safePlayer(ArrayList<Player> playerList) {


	}

	//Save a score in a text file
	public static void safeScore(Score score) {
		File currentDirFile = new File("src");
		String helper = currentDirFile.getAbsolutePath();
		String path = helper + "\\Files\\Scores.txt";
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(path,true));
			 try {				 	
					writer.append(score.toString());
					writer.append("\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   
	}
	
	//Save a question in a text file
	public static void safeQuestion(Question question) {
		File currentDirFile = new File("src");
		String helper = currentDirFile.getAbsolutePath();
		String path = helper + "\\Files\\Questions.txt";
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(path,true));
			 try {				 	
					writer.append(question.toString());
					writer.append("\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   
	}
	
	//Save an animal in a text file
	public static void safeAnimal(Animal animal) {
		File currentDirFile = new File("src");
		String helper = currentDirFile.getAbsolutePath();
		String path = helper + "\\Files\\Animals.txt";
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(path,true));
			 try {				 	
					writer.append(animal.toString());
					writer.append("\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   
	}
	
	//Delete and Create a new animal list
	public static void editAnimal(List<Animal> animals) {
		File currentDirFile = new File("src");
		String helper = currentDirFile.getAbsolutePath();
		String path = helper + "\\Files\\Animals.txt";
		BufferedWriter writer;
		
		try {
			writer = new BufferedWriter(new FileWriter(path));
			try {
				writer.write("");
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		for(int i = 0; i<animals.size();i++) {
			safeAnimal(animals.get(i));
		}
	}

}
