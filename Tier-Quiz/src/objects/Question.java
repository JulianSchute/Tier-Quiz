package objects;

public class Question {
	
	//--properties-------------------------------
	private String question;
	private String correctAnswer;
	private String[] falseAnswers = new String[6];	
	//-------------------------------------------
	
	//--methods----------------------------------
	public Question(String question, String correctAnswer, String[] falseAnswers) {
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.falseAnswers = falseAnswers;
	}

	public String getQuestion() {
		return question;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public String[] getFalseAnswers() {
		return falseAnswers;
	}
	
	public String toString() {
		
		return (question+";"+correctAnswer+";"+falseAnswers[0]+";"+falseAnswers[1]+";"+falseAnswers[2]+";"+falseAnswers[3]+";"+falseAnswers[4]+";"+falseAnswers[5]);
	}
	
	//--methods----------------------------------
}
