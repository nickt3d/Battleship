package Server;

public class GameBoard {
	
	private final int WIDTH = 10;
	private final int HEIGHT = 10;
	
	private int [][] gameBoard;
	
	

	public GameBoard(){
		
		//create the game board array
		gameBoard = new int[HEIGHT][WIDTH];
		for (int x = 0; x < 10; x++){
			for (int y = 0; y < 10; y++){
				gameBoard[x][y] = 0;
			}
		}
	}
	
	
	public void printBoard(){
		for (int x = 0; x < 10; x++){
			for (int y = 0; y < 10; y++){
				System.out.print(gameBoard[x][y] + " ");
			}
			System.out.println();
		}
	}
}
