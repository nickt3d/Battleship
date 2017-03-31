import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class GridSpace extends GameObject{
	
	private BufferedImage idleImg;
	private BufferedImage selectedImg;
	
	private int rowLoc;
	private int colLoc;
	
	private int state;
	
	
    public GridSpace(int x, int y, int rowLoc, int colLoc, String id){
        super(x, y, id);
        
        this.rowLoc = rowLoc;
        this.colLoc = colLoc;
        
        //set the idle state
        state = 0;
        //set the gridspace image
        try {
            idleImg = ImageIO.read(new File("D:/GameDevelopment/Battleship/Client/BattelshipClient/src/assets/gridspace.png"));
            selectedImg = ImageIO.read(new File("D:/GameDevelopment/Battleship/Client/BattelshipClient/src/assets/selectedGridSpace.png"));
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
    }

    public void tick(){
    	
    }
    
    public void draw(Graphics g){
    	BufferedImage img;
    	
    	//Set the image
    	if(state == 0){img = idleImg;} else if(state == 1){img = selectedImg;} else {img = idleImg;}	
        g.drawImage(img, x, y, null);
        
    }

	public int getColLoc() {
		return colLoc;
	}
	
	public int getRowLoc() {
		return rowLoc;
	}
	
	public void setState(int state){
		this.state = state;
	}
	
	public int getState(){
		return state;
	}
}