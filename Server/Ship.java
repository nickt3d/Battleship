/*
 * @author Nicholas Evans & Nicholas Trembley
 */

package Server;

public class Ship{
	private int length;//Length of the ship
	public String shipName; //Name of the ship/the ship's ID
	public int[] shipPos = new int[2]; //Starting x and y coord of the ship
	private int orientation; //1 is horizontal, 2 is vertical
	public boolean isSunk; //true if the ship is sunk
	
	public Ship(int x, int y, int length, int orientation){
		this.shipPos[0] = x; //Ships starting x coord
		this.shipPos[1] = y; //Ships starting y coord
		this.length = length; 
		this.orientation = orientation;
	}
	
	/*
	 * Whether all cells this ship is on are hit or not
	 * @return true		If the ship is sunk, false otherwise
	 */
	public boolean shipStatus(){
		return isSunk;
	}
	
	/*
	 * Gets the length of the ship
	 * @return int		The length of the ship
	 */
	public int getLength(){
		return length;
	}
	
	/*
	 * Gets the ship's name
	 * @return String		The name of the ship
	 */
	public String getShipName(){
		return shipName;
	}

}