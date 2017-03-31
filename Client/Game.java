import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;

public class Game extends Canvas implements Runnable {

	private static Window window;
	public static boolean running = false;
	Graphics g;

	private Thread thread;

	private Handler handler;

	private GetInputs inputs;

	public int screenHeight;
	public int screenWidth;

	public int boardXSize = 10;
	public int boardYSize = 10;

	private int grid1X;
	private int grid1Y;

	private int grid2X;
	private int grid2Y;

	// Game Constructor/ initialization
	public Game() {
		Window window = new Window(this);
		screenHeight = window.getHeight();
		screenWidth = window.getWidth();

		
		//All of below goes into the game screen creation method(after adding login functions)
		grid1X = screenWidth / 3 - (boardXSize + 1) * 16;
		grid1Y = screenHeight / 2 - (boardYSize + 1) * 16;
		grid2X = screenWidth / 3 + (boardXSize + 1) * 32;
		grid2Y = screenHeight / 2 - (boardYSize + 1) * 16;

		handler = new Handler();

		// add the gameObjects
		handler.addObject(new Grid(grid1X, grid1Y, boardXSize, boardYSize, "PLAYERGRID"));
		handler.addObject(new Grid(grid2X, grid2Y, boardXSize, boardYSize, "ENEMYGRID"));

		// create labels
		// Place the identifiers for grid locations above the grid
		for (int i = 0; i < (boardXSize); i++) {
			handler.addObject(new BoardLabels(grid1X + (i * 32), grid1Y - 32, "LABEL", i * 32, 0));
		}
		for (int i = 0; i < (boardYSize); i++) {
			handler.addObject(new BoardLabels(grid1X - 32, grid1Y + (i * 32), "LABEL", i * 32, 32));
		}

		// enemy grid labels
		for (int i = 0; i < (boardXSize); i++) {
			handler.addObject(new BoardLabels(grid2X + (i * 32), grid2Y - 32, "LABEL", i * 32, 0));
		}
		// enemy grid labels
		for (int i = 0; i < (boardYSize); i++) {
			handler.addObject(new BoardLabels(grid2X - 32, grid2Y + (i * 32), "LABEL", i * 32, 32));
		}

		// add the objects to the handler
		handler.addObject(new OutputTextBox(grid1X, grid1Y + boardYSize*32 + 128, "TEXTBOX"));
		//add fire button
		handler.addObject(new FireButton(grid2X + 32*boardXSize/2, grid2Y + boardYSize*32 + 128, "FIREBUTTON"));
		handler.addObject(new TitleLogoObject(screenWidth / 2, 64, "TITLE"));

		// Add the mouseListener
		this.addMouseListener(new GetInputs(handler));
	}

	// start the thread
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	// Stop the thread
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// The game loop
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 100000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
				if (running) {
					draw();
				}
				frames++;
			}

			if (System.currentTimeMillis() - timer > 100) {
				timer += 100;
				// System.out.println("FPS: " + frames);
				frames = 0;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		stop();
	}

	private void tick() {
		handler.tick();
	}

	// Draw method
	private void draw() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, screenWidth, screenHeight);

		// Draw Game Objects
		handler.draw(g);

		g.dispose();
		bs.show();
	}

	public static void main(String args[]) {
		new Game();
	}

}
