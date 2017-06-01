import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class inputsFromClients {
	
	BufferedReader in;
	int portNumber = 8081;
	ServerSocket serverSocket = null;
	
	public static void main(String[] args){
		inputsFromClients s = new inputsFromClients(); // instantiate the class
		s.serverMainCode(); // run the serverMainCode method
	}
	
	public void serverMainCode(){
		ArrayList<Person> players = new ArrayList<Person>(); 
		
		try {
			this.serverSocket = new ServerSocket(portNumber); // instantiate players arrayList
			
			while(true) {
				try {
					Socket clientSocket = serverSocket.accept(); // always be listening and accepting socket connections
					LoginRunnable m = new LoginRunnable(clientSocket, players); // run the LoginRunnable class, passing in appropriate parameters
					
					new Thread(m).start(); // start the thread
					
				} catch (IOException e) { // catch errors
					System.out.println(e); // print error report
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}