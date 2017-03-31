import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FireButton extends GameObject{

	BufferedImage imgIdle;
	BufferedImage imgDown;
	BufferedImage imgEntered;
	
	private int state = 0;
	
	private final int WIDTH;
	private final int HEIGHT;
	
	public FireButton(int x, int y, String id) {
		super(x, y, id);
		
		try {
			imgIdle = ImageIO.read(new File("D:/GameDevelopment/Battleship/Client/BattelshipClient/src/assets/firebuttonidle.png"));
			imgDown = ImageIO.read(new File("D:/GameDevelopment/Battleship/Client/BattelshipClient/src/assets/firebuttondown.png"));
			imgEntered = ImageIO.read(new File("D:/GameDevelopment/Battleship/Client/BattelshipClient/src/assets/firebuttonentered.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		WIDTH = imgIdle.getWidth();
		HEIGHT = imgIdle.getHeight();
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void draw(Graphics g) {
		BufferedImage img;
		//Set the image
    	if(state == 0){img = imgIdle;} else if(state == 1){img = imgDown;} else if (state == 2){img = imgEntered;} else {img = imgIdle;}
    	
        g.drawImage(img, x, y, null);
		
	}

	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight(){
		return HEIGHT;
	}

	public void setState(int state) {
		this.state = state;
		
	}
	
	public int getStartX() {
		return x;
	}

	public int getStartY() {
		return y;
	}
}
