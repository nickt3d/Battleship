/*
 * @author Nicholas Evans & Nicholas Trembley
 */

package Server;

public class GameBoard{
	private final int WIDTH; //Width of the game board
	private final int HEIGHT; //Height of the game board
	private final int EMPTY = 0; //Empty cell on the game board
	private final int HIT = 1; //Hit cell on the game board
	private final int MISS = 2; //Missed cell on the game board
	private final int SHIP = 3; //Ship cell on the game board
	
	private Ship[] ships; //Array of a player's ship objects
	private int[][] boardSpaces; //The array for the game board
	private final int DEFAULTSIZE = 10; //The default game board size
	private int[][] shotLoc; //Array of all available shot locations
	private int[][] hits; //Array of all shots that have hit
	private int[][] misses; //Array of all shots that have missed
	
	/*
	 * GameBoard Constructor
	 * Uses the default game width and height
	 */
	public GameBoard(){
		this.WIDTH = this.DEFAULTSIZE;
		this.HEIGHT = this.DEFAULTSIZE;
		createBoard(WIDTH, HEIGHT);
	}
	
	/*
	 * GameBoard Constructor 
	 * Takes in one given size for width and height to make a square GameBoard
	 */
	public GameBoard(int size){
		this.WIDTH = size;
		this.HEIGHT = size;
		createBoard(WIDTH, HEIGHT);
	}
	
	/*
	 * GameBoard Constructor 
	 * Takes in  a given width and a given height for any size GameBoard
	 */
	public GameBoard(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		createBoard(WIDTH, HEIGHT);
	}
	
	/*
	 * Creates a fresh GameBoard with all empty spaces
	 * Creates an array of length x*y (GameBoard width*height) for all possible
	 * shot locations, hits and misses
	 * @param width		The width of the GameBoard
	 * @param height	The height of the GameBoard
	 */
	public void createBoard(int width, int height){
		boardSpaces = new int[width][height]; //Board with x width and y height
		//Fill the GameBoard with EMPTY cells
		for(int i=0; i<boardSpaces.length; i++){
			for(int j=0; j<boardSpaces[i].length; j++){
				boardSpaces[i][j] = EMPTY;
			}
		}
		shotLoc = new int[width*height][2]; //2D array that will store all possible shot locations
		hits = new int[width*height][2]; //2D array that will store all possible hit locations
		misses = new int[width*height][2]; //2D array that will store all possible miss locations
	}
	
	/*
	 * Checks the GameBoard to make sure the given x and y coords are available
	 * inside the GameBoard, and haven't already been shot at
	 * @param x			The x location on the GameBoard being checked
	 * @param y			The y location on the GameBoard being checked
	 * @return true		If the cell on the GameBoard is EMPTY or has a SHIP
	 */
	public boolean checkGameBoardCell(int x, int y){
		if((x >= WIDTH || x < 0)&&(y >= HEIGHT || y < 0) || boardSpaces[x][y] == HIT || boardSpaces[x][y] == MISS){
			return false;
		}else if(boardSpaces[x][y] == EMPTY || boardSpaces[x][y] == SHIP){
			return true;
		}else{
			return false;
		}
	}
	
	/*
	 * Gets the height of the GameBoard
	 * @return int		The height of the GameBoard
	 */
	public int getHeight(){
		return HEIGHT;
	}
	
	public boolean shipIsOnPosition(int x, int y, Ship ship){
		
	}
	
	/*
	 * Gets the width of the GameBoard
	 * @return int		The width of the GameBoard
	 */
	public int getWidth(){
		return WIDTH;
	}
	
	/*
	 * Converts the given x and y coords (int,int) to a string
	 * with y being a char (char,int) ex: convertPos(1,1) would
	 * return a String "A1"
	 * @param y				The y position being converted
	 * @param x				The x position being converted
	 * @return String		The converted (y,x) position
	 */
	public String convertPos(int y, int x){
		//Using 65 because A's Dec is 65
		//So y position 0 would be A, 1 would be B, etc...
		char yPos = (char) (y+65);
		return ""+yPos+""+x;
	}
	
	/*
	 * Gets the entire array of ships
	 * @return Ship[]	The entire array of this GameBoard's Ships
	 */
	public Ship[] getShips(){
		return ships;
	}
	
	/*
	 * Prints out all of the cells that have SHIPS on them
	 */
	//Change later to print each ship separate
	//Currently just prints all x y coords of ships starting at 0 0 and going to last corner of game board
	public void printPositions(){
		for(int i=0; i<boardSpaces.length; i++){
			for(int j=0; j<boardSpaces[i].length; j++){
				//All ship locations hit or not
				if(boardSpaces[i][j] == SHIP || boardSpaces[i][j] == HIT){
					System.out.println("("+i+", "+j+")");
				}
			}
		}
	}
	
	/*
	 * Prints all the cells that have been HIT (x,y)
	 */
	public void printHits(){
		//Printing from the array of misses
		for(int i=0; i<hits.length; i++){
			System.out.println("("+hits[i][0]+", "+hits[i][1]+")");
		}
	}
	
	/*
	 * Prints all the cells that have been MISSED (x,y)
	 */
	public void printMiss(){
		//Printing from the array of misses
		for(int i=0; i<misses.length; i++){
			System.out.println("("+misses[i][0]+", "+misses[i][1]+")");
		}
	}
	
	
	//All remaining available shots or all hits and misses?
	public void printShots(){
		
	}
}