/*
 * @author Nicholas Evans & Nicholas Trembley
 */

package Server;

import java.net.*;

public class Game{
	
	public Player player1; //Player Object that holds the info for player 1
	public Player player2; //Player Object that holds the info for player 2
	public GameBoard gameBoard;
	
	/*
	 * Game Constructor
	 * Creates GameBoard with default size
	 */
	public Game(){
		gameBoard = new GameBoard();
	}
	
	/*
	 * Game Constructor
	 * Creates square GameBoard with given size
	 * @param size		The size of the game board's width and height (int)
	 */
	public Game(int size){
		gameBoard = new GameBoard(size);
	}
	
	/*
	 * Game Constructor
	 * Creates GameBoard with given width and height
	 * @param width		The width of the game board (int)
	 * @param height	The height of the game board (int)
	 */
	public Game(int width, int height){
		gameBoard = new GameBoard(width, height);
	}
	
	/*
	 * Sets what player is currently playing
	 * @return int		The current player (1 or 2)
	 */
	public int CurrentPlayer(){
		if(player1.getStatus()){
			return 1;
		}else{
			return 2;
		}
	}
	
	/*
	 * Ends one player's turn and starts the other's
	 */
	public void playerEndTurn(){
		player1.setStatus(); //Switch player 1's turn
		player2.setStatus(); //Switch player 2's turn
	}
	
	/*
	 * Creates a player for the game
	 * @param socket		The socket the player will use to connect to the server
	 * @param turn			If it's currently the player's turn (boolean)
	 * @return Player		A player object @see Player
	 */
	public Player createPlayer(Socket socket, boolean turn){
		Player player = new Player(socket, turn);
		return player;
	}
	
	public void gameStatus(){
		//
	}

}