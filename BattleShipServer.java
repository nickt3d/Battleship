package Server;

/* *************************************
 * 	Cs2043 - BattleShipServer
 * 	@Author: Nicholas Evans 
 * 	@Author: Nick Tremblay 3509303
 * 	@Date: 	 March 1 2017
 * 
 *  @Class:	 BattleShipServer
 *  
 *  @Description: Initializes a server for the Battleship Game. Includes the Socket listeners for the game server.
 * 
 * *************************************/

import java.io.*;
import java.net.*;

public class BattleShipServer {

	// Initialize variables
	public static GameBoard gameBoard;
	
	public static ServerSocket serverSocket;

	
	private static InputStream in;
	private static InputStreamReader inStream;
	private static BufferedReader reader;
	
	private static OutputStream out;
	private static OutputStreamWriter outStream;
	private static BufferedWriter bw;
	
	public static int gameState = 0;

	private static Socket player1Socket;
	private static Player player1;
	
	private static Socket player2Socket;
	private static Player player2;
	
	private static int numOfConnectedPlayers = 0;

	private static int portNumber;
	private static int defaultPort = 2043;

	private static boolean running = false;

	// end variable initialize


	public static void main(String args[]) {
		
		if(args.length != 1){
			portNumber = defaultPort;
		} else {
			portNumber = Integer.parseInt(args[0]);
		}
		
		initialize();
		update();
		
	}

	private static void initialize() {
		System.out.println("Initializing...");
		//create the server socket to listen for players
		try {
			serverSocket = new ServerSocket(portNumber);
			
			System.out.println("Server Created...");
		} catch (IOException e) {
			System.err.println("Could not listen in on port: " + portNumber + ". " + e.getMessage());
		}

		System.out.println("Setting up the client...");

		
		running = true;
	}

	public static void update() {

		while (running) {
			if(gameState == 0){
				
				System.out.println(printState());
				getConnections();
				
				if (numOfConnectedPlayers == 2) {

					//Create and add the players to a new game
					System.out.println("All players have connected. Starting the game.");
					gameBoard = new GameBoard();
					
					gameState = 1;
					System.out.println(printState());
					
				}
			}	

		} // end while

	}// end run
	
	
	//Check for player connections
	private static void getConnections(){
		
		try{
			System.out.println("Waiting for player 1...");
			player1Socket = serverSocket.accept();
			player1 = new Player(player1Socket);
			sendMessage("You are player 1...");
			
			numOfConnectedPlayers += 1;

		}catch(IOException e){
			System.err.println("Player failed to connect: "+e.getMessage());
		}
		
		try{
			System.out.println("Waiting for player 2...");
			player2Socket = serverSocket.accept();
			player2 = new Player(player2Socket);
			//sendMessage(player2Socket, "You are Player 2...");
			numOfConnectedPlayers += 1;

		}catch(IOException e){
			System.err.println("Player failed to connect: "+e.getMessage());
		}
	} // end getConnections
	
	//send a message to the player.
	public static void sendMessage(String msg){
		try {
			System.out.println("Sending Message...");
			out = player1Socket.getOutputStream();
			outStream = new OutputStreamWriter(out);
			bw = new BufferedWriter(outStream);
	        bw.write(msg);
	        bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	
	
	//Shut down the server
	private static void close(){		
		try {	
			player1Socket.close();
			player2Socket.close();
			serverSocket.close();
			System.out.println("Closing the server...");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		running = false;
	}

	//Print the current state
	public static String printState(){
		if(gameState == 0){
			return "Currently waiting for players...";
		} else if (gameState == 1){
			return "Game in progress...";
		} else if(gameState == 2){
			return "Game has ended.";
		} else {
			return "Couldn't get game state";
		}
	} // end printState
}