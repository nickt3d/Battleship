import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TitleLogoObject extends GameObject{
	
	private BufferedImage img;
	
    public TitleLogoObject(int x, int y, String id){
        super(x, y, id);
        
        //Set the title image
        try {
            img = ImageIO.read(new File("D:/GameDevelopment/Battleship/Client/BattelshipClient/src/assets/title.png"));
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    public void tick(){
    	
    }

    public void draw(Graphics g){
        g.drawImage(img, x - (img.getWidth()/2), y, null);
    }

}
