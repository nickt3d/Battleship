/*
 * @author Nicholas Evans & Nicholas Trembley
 */

package Server;

import java.net.*;
import java.io.*;

public class Player{
	
	private boolean playersTurn; //True if it's this player's turn, false otherwise
	public GameBoard gameboard; //Player's game board
	public Socket socket; //The player's socket to connect to the server
	public BufferedReader in; //Get input from the client
	private PrintWriter out; //Give output to the client
	
	public int[][] shipLocations; //2D array of all this player's ship locations
	
	/*
	 * Player constructor
	 * @param socket	The socket the player will use to connect to the server
	 * @param turn		If it's currently this player's turn or not
	 */
	public Player(/*String locations,*/ Socket socket, boolean turn){
		this.socket = socket;
		this.playersTurn = turn;
		try{
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.out = new PrintWriter(socket.getOutputStream(), true);
		}catch(IOException e){
			System.err.println("Unable to read or write to the client: "+e.getMessage());
		}
	}
	
	private void createShip(String location){
		
	}
	
	/*
	 * Parses the String of ship locations for this player into
	 * a 2D array
	 * @param locations		A string of all ships names and their locations
	 */
	public void parseLocations(String locations){
		
	}
	
	/*
	 * Checks if all the ship cells on the game board have been hit
	 * @return true		If all ship cells on the gameboard are hit
	 */
	public boolean playerLose(){
		int sunkCounter = 0;
		for(int i=0; i<gameboard.getShips().length; i++){
			if(gameboard.getShips()[i].shipStatus()){
				sunkCounter++;
			}
		}
		if(sunkCounter == gameboard.getShips().length){
			return true;
		}else{
			return false;
		}
	}
	
	/*
	 * Gets if it's the player's turn or not
	 * @return true		If it's the player's turn
	 */
	public boolean getStatus(){
		return playersTurn;
	}
	
	/*
	 * Sets the player's turn
	 * Used at end of turn to switch players turn
	 */
	public void setStatus(){
		if(playersTurn){
			playersTurn = false; 
		}else{
			playersTurn = true;
		}
	}
	
	/*
	 * Sends coords of player's shot if it is a valid shot
	 * @param x		The x location of the shot being taken (int)
	 * @param y		The y location of the shot being taken (int)
	 * @throws IOException
	 */
	public void takeShot(int x, int y) throws IOException{
		if(isShotValid(x,y)){
			//Send x and y coord with converted position ex: "A1" (y,x)
			this.sendMessageToClient(gameboard.convertPos(y,x));
		}
	}
	
	/*
	 * Checks if the inputed x and y coords are valid on the game board
	 * @param x		The x location of the shot being checked (int)
	 * @param y		The y location of the shot being checked (int)
	 * @return true		If the x and y location is a valid shot
	 */
	private boolean isShotValid(int x, int y){
		if(gameboard.checkGameBoardCell(x,y)){
			return true;
		}else{
			return false;
		}
	}
	
	/*
	 * Sends a String message to the client
	 * @param message	The message that will be sent to the client (String)
	 * @throws IOException
	 */
	public void sendMessageToClient(String message)throws IOException{
		this.out.println(message);
	}
	
	/*
	 * Receives a message from the client
	 * @throws IOException
	 */
	public void getMessageFromClient()throws IOException{
		this.in.readLine();
	}
	
	/*
	 * Closes the I/O of this player
	 * @throws IOException
	 */
	public void close()throws IOException{
		this.out.close();
		this.in.close();
	}

}