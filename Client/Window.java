import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Dimension;
import java.awt.Canvas;
import javax.swing.JFrame;


public class Window extends Canvas {
	
	private static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

	private static int windowWidth = gd.getDisplayMode().getWidth();
	private static int windowHeight = gd.getDisplayMode().getHeight();

	public Window(Game game){
		JFrame frame = new JFrame();

		//variables for the window
		frame.setTitle("Battleship");
		frame.setSize(windowWidth, windowHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}

    public int getWidth(){
        return windowWidth;
    }

    public int getHeight(){
        return windowHeight;
    }
}
