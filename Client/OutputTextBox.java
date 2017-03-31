import java.awt.Color;
import java.awt.Graphics;

public class OutputTextBox extends GameObject{

	public OutputTextBox(int x, int y, String id) {
		super(x, y, id);
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, 320, 128);
	}

}
