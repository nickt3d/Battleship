/*
 * @author Nicholas Evans & Nicholas Trembley
 */

package Server;

import java.io.*;
import java.net.*;

public class BattleShipServer{
	
	//private static boolean running; //If true the game will run/continue to loop
	private static int portNumber; //The server port number the clients will connect to
	private static int gameState; //The game state (WAITING for players, PLAYING the game, DONE the game)
	
	private static final int WAITING = 0; //State when waiting for players to connect
	private static final int PLAYING = 1; //State when the game is being played
	private static final int DONE = 2; //State when the game finished
	
	public static Game game; //The game object
	
	public static ServerSocket serverListener = null; //Socket to connect clients too
	
	public static Socket player1Socket = null; //Player 1's socket
	public static Player player1 = null; //Player 1's object
	
	public static Socket player2Socket = null; //Player 2's socket
	public static Player player2 = null; //Player 2's object
	
	/*
	 * Main method to run the battleship server
	 * @param args[]	(String)
	 * 
	 */
	public static void main(String args[]){
		initialise(); //Initialise 
		
		//Connect player one
		try{
			System.out.println("Waiting for player 1 to connect...");
			player1Socket = serverListener.accept();
			//Create player 1 (playerSocket, player's turn)
			player1 = game.createPlayer(player1Socket, true);
		}catch(IOException e){
			System.err.println("Accept failed: "+e.getMessage());
			System.exit(-1);
		}
		
		//Connect player two
		try{
			System.out.println("Waiting for player 2 to connect...");
			player2Socket = serverListener.accept();
			//Create player 2 (playerSocket, player's turn)
			player2 = game.createPlayer(player2Socket, false);
		}catch(IOException e){
			System.err.println("Accept failed: "+e.getMessage());
			System.exit(-1);
		}
		
		setGameState(PLAYING);
		
		//TEMPORARY
		try{
			player1.sendMessageToClient("Hello player 1");
			player2.sendMessageToClient("Hello player 2");
			
			player1.sendMessageToClient("Place ships");
			String player1Locations = player1.in.readLine();
			player1.parseLocations(player1Locations);
			System.out.println("Received ship coords from player 1");
			
			player2.sendMessageToClient("Place ships");
			String player2Locations = player2.in.readLine();
			player2.parseLocations(player2Locations);
			System.out.println("Received ship coords from player 2");
			
			update();
			
			System.out.println("Player 1/Player 2 lost/won");
			player1.sendMessageToClient("You lost/won");
			player2.sendMessageToClient("You lost/won");
		}catch(IOException e){
			System.err.println("I/O error: "+e.getMessage());
		}
		close();
		
	}
	/*
	 * Initialises the game by starting the server and creating a new Game object
	 */
	private static void initialise(){
		portNumber = Integer.parseInt("4444"); //Setups up the server with a port number
		try{
			serverListener = new ServerSocket(portNumber);
		}catch(IOException e){
			System.err.println("Could not listen in on port: "+portNumber+". "+e.getMessage());
			System.exit(-1);
		}
		game = new Game();
		setGameState(WAITING);
	}
	
	/*
	 * Main loop for the battleship game
	 */
	private static void update(){
		
		//TEMPORARY
		while(gameState == PLAYING){
			try{
				player1.sendMessageToClient("\nYour turn");
				player2.sendMessageToClient("\nOther player's turn");
				System.out.println("Player 1's turn");
				player1.sendMessageToClient("Fire shot");
				System.out.println("Coords received");
				player2.sendMessageToClient("Shot received");
				player2.sendMessageToClient("Shot missed/hit\n");
				System.out.println("Shot missed/hit");
				player1.sendMessageToClient("Shot missed/hit");
				player1.sendMessageToClient("Your turn is done\n");
				System.out.println("If hit check if all ships are destroyed");
				
				player2.sendMessageToClient("Your turn");
				player1.sendMessageToClient("Other player's turn");
				System.out.println("Player 2's turn");
				player2.sendMessageToClient("Fire shot");
				System.out.println("Coords received");
				player1.sendMessageToClient("Shot received");
				player1.sendMessageToClient("Shot missed/hit");
				System.out.println("Shot missed/hit");
				player2.sendMessageToClient("Shot missed/hit");
				player2.sendMessageToClient("Your turn is done\n");
				System.out.println("If hit check if all ships are destroyed");
				
				//if someone wins
				setGameState(DONE);
			}catch(IOException e){
				System.err.println("I/O error: "+e.getMessage());
			}
		}
	}
	
	/*
	 * Gets the current game state (WAITING, PLAYING, DONE)
	 */
	public static String getState(){
		return gameState+"";
	}
	
	/*
	 * Changes the game state (WAITING, PLAYING, DONE)
	 */
	public static void setGameState(int state){
		gameState = state;
	}
	
	/*
	 * Closes player I/O, sockets and shuts down server
	 */
	private static void close(){
		try{
			player2.close();
			player1.close();
			player2Socket.close();
			player1Socket.close();
			serverListener.close();
		}catch(IOException e){
			System.err.println("Unable to close a reader, writer or socket: "+e.getMessage());
			System.exit(-1);
		}
	}
	
	

}