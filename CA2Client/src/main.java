import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class main {

	public static void main(String[] args) {
		
		String host = "127.0.0.1"; // assign host address
		int portNumber = 8081; // assign port
		
		Socket clientSocket;
		PrintWriter out;
		BufferedReader in;
		BufferedReader stdIn;
		InputStreamReader ir;
		
		while (true) {
			try {
				clientSocket = new Socket(host, portNumber); // instantiate clientSocket with appropriate parameters
				
				out = new PrintWriter(clientSocket.getOutputStream(), true); 
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//				ir = new InputStreamReader(clientSocket.getInputStream());
				stdIn = new BufferedReader(new InputStreamReader(System.in));
				
				System.out.println("1. Register Player");
				System.out.println("2. Enter Score");
				System.out.println("3. Who is winning");
				System.out.println("Enter Choice: ");

				int userInput1 = Integer.parseInt(stdIn.readLine());
				
				switch(userInput1) {
				
				case 1:
					System.out.println("Register");
					out.println("register"); // send "register" string to server
					
					System.out.println("Enter your name: "); 
					String name = stdIn.readLine(); // read in for name variable
					out.println(name); // send value to server
					
					System.out.println("Registration Complete. Competition Code: " + in.readLine()); // use info from server for info output
					break;
					
				case 2:
					System.out.println("Enter Score");
					out.println("score"); // send "score" to server
					
					System.out.println("What is your competition code? ");
					String code = stdIn.readLine(); // read in for code variable
					out.println(code); // send to server
					
					System.out.println("What is your score?");
					String score = stdIn.readLine(); // read in for score variable
					out.println(score); // send to server
					
					System.out.println(in.readLine()); // print out info from server
					
					break;
					
				case 3:
					System.out.println("Who is winning?");
					out.println("high"); // send "high" to server
					
					System.out.println("The Winning player is " + in.readLine()); // print off information from server
					System.out.println("With a score of " + in.readLine()); // print off information from server
					
					break;
					}
			
			} catch (IOException e) {
				System.out.println(e);
			}
			
			
		}
	}

}
