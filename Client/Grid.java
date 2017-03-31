import java.awt.Graphics;
import java.awt.event.MouseListener;

public class Grid extends GameObject{
	
	private int boardXSize;
	private int boardYSize;
	private int imgPx = 32;
	private int padding = 0;
	
	private int gridStartX;
	private int gridStartY;
	private int gridEndX;
	private int gridEndY;
	
	
	private GetInputs listener;
	
	public GridSpace[][] gameBoard;

	public Grid(int x, int y, int boardXSize, int boardYSize, String id) {
		//x,y is the position of the game board on the screen
		super(x, y, id);
		
		this.boardXSize = boardXSize;
		this.boardYSize = boardYSize;
		gameBoard = new GridSpace[boardXSize][boardYSize];
		
		gridStartX = x;
		gridStartY = y;
		gridEndX = x + boardXSize * (imgPx + padding);
		gridEndY = y + boardYSize * (imgPx + padding);

		//add gridspaces
		for(int i = 0; i < boardXSize; i++){
			for(int j = 0; j < boardYSize; j++){
				gameBoard[i][j] = new GridSpace(x + (i*imgPx) + (i * padding), y + (j*imgPx) + (j*padding), i, j, "GRIDSPACE");
			}
		}
	}

	
	@Override
	public void tick() {

	}

	@Override
	public void draw(Graphics g) {

		//draw the grid
		for(int i = 0; i < boardXSize; i++){
			for(int j = 0; j < boardYSize; j++){
				gameBoard[i][j].draw(g);
			}
		}
	}
	
	public int getGridStartX(){
		return gridStartX;	
	}
	
	public int getGridStartY(){
		return gridStartY;	
	}
	
	public int getGridEndX(){
		return gridEndX;
	}
	
	public int getGridEndY(){
		return gridEndY;
	}
	
	public int getBoardSizeX(){
		return boardXSize;
	}
	public int getBoardSizeY(){
		return boardYSize;
		
	}
	
	public GridSpace getGridSpace(int x, int y){
		GridSpace gridSpace = gameBoard[x][y];
		return gridSpace;
	}


	public int getImgPx() {
		return imgPx;
	}
	
	public void resetGrid(){
		//draw the gridspaces
		for(int i = 0; i < boardXSize; i++){
			for(int j = 0; j < boardYSize; j++){
				gameBoard[i][j].setState(0);
			}
		}
		
	}

}
