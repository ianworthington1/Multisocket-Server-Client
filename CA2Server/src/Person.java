import java.util.Random;

public class Person {
	
	public String name;
	public String code;
	public int score;
	
	public Person(String name) { // constructor
		this.name = name;
		this.code = genCode(); // run the genCode method
		this.score = 0;
	}
	
	private synchronized String genCode(){ // generates random 8 digit code to use as player code
		int charLength = 8;
		return String.valueOf(charLength < 1 ? 0 : // generate random 8 character digit
			new Random().nextInt((8*(int) Math.pow(10,  charLength -1)) -1) + 
			(int) Math.pow(10, charLength -1));
	}

}