package objects;

public class Option {

	String value;
	Boolean guessed = false;
	
	public Option(String value) {
		this.value = value;
	}

	public Boolean getGuessed() {
		return guessed;
	}

	public void setGuessed(Boolean guessed) {
		this.guessed = true;
	}

	public String getValue() {
		return value;
	}
	
}
