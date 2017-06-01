import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public class LoginRunnable implements Runnable {

	protected Socket clientSocket = null;
	ArrayList<Person> players;
	
	public LoginRunnable(Socket clientSocket, ArrayList<Person> players) { // constructor
		this.clientSocket = clientSocket; // assign variables values
		this.players = players;
	}
	
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream(), true);
				
				while (true) {
					
					String userInput = in.readLine();
					switch(userInput) {
					
					case "register":
						String nameInput = in.readLine();
						Person tmp = new Person(nameInput); // instantiate Person object using user input for name parameter
						players.add(tmp); // add object to arrayList
						
						out.println(tmp.code); // send to client
						break; // break case
						
					case "score":
						String compCode;
						int compScore;
	
						compCode = in.readLine(); // read from client for variable
						compScore = Integer.parseInt(in.readLine()); // read in from client variable
						
						for (int i = 0; i < players.size(); i++) // for loop to run through entries in player arrayList
						{
							Person tmp1 = players.get(i); // Person object is always the i iteration of objects in players arrayList
							if (tmp1.code.equals(compCode)) // if the object code is equal to the code entered by the user
							{
								tmp1.score = compScore; // that objects score is equal to the value of the score entered by the user
								int position = players.indexOf(tmp1); // set the position variable to the int value of the index of tmp1
								players.set(position, tmp1); //replace the entry is position with the values present in tmp1
							}
						}
						out.println("Score Registered"); // send info to client
						break;
					
					case "high":
						int topScore = 1000;
						int winnerScore = 0;
						String winnerName = null;
						for (int i = 0; i < players.size(); i++) // for loop to run through every entry in the player arrayList
						{
							Person tmp1 = players.get(i); // Person object is always the i iteration of objects in players arrayList
							if (tmp1.score < topScore)  // if the object score is lower than the stored score
							{
								winnerScore = tmp1.score;
								winnerName = tmp1.name;
								
								int position = players.indexOf(tmp1); // set the position variable to the int value of the index of tmp1
								players.set(position, tmp1); //replace the entry is position with the values present in tmp1
							}
						}
						out.println(winnerName); // send to client
						out.println(winnerScore); // send to client
						break;
					}
				}
			
		}catch (IOException e) { // catch errors
			System.out.println(e); // print error report
		}
	}

}
