import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BoardLabels extends GameObject {
	
	private BufferedImage img;
	private int imgX;
	private int imgY;

	public BoardLabels(int x, int y, String id, int imgX, int imgY) {
		super(x, y, id);
		
		this.imgX = imgX;
		this.imgY = imgY;
		//Get the image for this label
		
		try {
			img = ImageIO.read(new File("D:/GameDevelopment/Battleship/Client/BattelshipClient/src/assets/label.png"));
			img = img.getSubimage(imgX, imgY, 32, 32);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	

}
