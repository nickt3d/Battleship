import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GetInputs implements MouseListener {

	public int xPos;
	public int yPos;

	private Handler handler;

	public GetInputs(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		// Check all the game objects
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (!tempObject.getId().equals("FIREBUTTON")) {
				FireButton fire = (FireButton) tempObject;

				int objectX = (x - fire.getX()) / fire.getWidth();
				int objectY = (y - fire.getY()) / fire.getHeight();
	
				if ((objectX > (fire.getWidth() - 1) || x - fire.getStartX() < 0 || (objectY > fire.getHeight() - 1)
						|| y - fire.getStartY() < 0)) {
					System.out.println(
							"Entered on the FireButton at: " + (x - fire.getStartX()) + " " + (y - fire.getStartY()));

					fire.setState(2);
				}
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		// Check all the game objects
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId().equals("FIREBUTTON")) {
				FireButton fire = (FireButton) tempObject;

				int objectX = (x - fire.getX()) / fire.getWidth();
				int objectY = (y - fire.getY()) / fire.getHeight();
	
				if (!(objectX > (fire.getWidth() - 1) || x - fire.getStartX() < 0 || (objectY > fire.getHeight() - 1)
						|| y - fire.getStartY() < 0)) {
					System.out.println(
							"Entered on the FireButton at: " + (x - fire.getStartX()) + " " + (y - fire.getStartY()));

					fire.setState(0);
				}
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		////////////////////////////////////////////////
		// Reset the gameboard selections///////////////
		////////////////////////////////////////////////
		for (int i2 = 0; i2 < handler.object.size(); i2++) {
			GameObject tempObject2 = handler.object.get(i2);
			if (tempObject2.getId().equals("PLAYERGRID")) {
				Grid playerGrid = (Grid) tempObject2;
				playerGrid.resetGrid();
			} else if (tempObject2.getId().equals("ENEMYGRID")) {
				Grid enemyGrid = (Grid) tempObject2;
				enemyGrid.resetGrid();
			}
		}

		// Check all the game objects
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			//////////////////////////////////////////////////////////////
			// Handle the fire button
			//////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////
			if (tempObject.getId().equals("FIREBUTTON")) {
				FireButton fire = (FireButton) tempObject;

				int objectX = (x - fire.getX()) / fire.getWidth();
				int objectY = (y - fire.getY()) / fire.getHeight();
				// Check if the click was inside the button
				if (!(objectX > (fire.getWidth() - 1) || x - fire.getStartX() < 0 || (objectY > fire.getHeight() - 1)
						|| y - fire.getStartY() < 0)) {
					System.out.println(
							"Clicked on the FireButton at: " + (x - fire.getStartX()) + " " + (y - fire.getStartY()));

					System.out.println("Clicked the fire button");

					fire.setState(1);
				}
			}

			///////////////////////////////////////////////
			// Handle the player grid//////////////////////
			///////////////////////////////////////////////
			if (tempObject.getId().equals("PLAYERGRID")) {
				Grid playerGrid = (Grid) tempObject;

				int gridSpaceX = (x - playerGrid.getGridStartX()) / playerGrid.getImgPx();
				int gridSpaceY = (y - playerGrid.getGridStartY()) / playerGrid.getImgPx();

				// Check if the click was inside the grid
				if (!(gridSpaceX > (playerGrid.getBoardSizeX() - 1) || x - playerGrid.getGridStartX() < 0
						|| (gridSpaceY > playerGrid.getBoardSizeY() - 1) || y - playerGrid.getGridStartY() < 0)) {
					System.out.println("Clicked on the player grid at: " + (x - playerGrid.getGridStartX()) + " "
							+ (y - playerGrid.getGridStartY()));
					System.out.println("X space: " + gridSpaceX + " Y space: " + gridSpaceY);

					// Change the selected gridSpace state
					playerGrid.getGridSpace(gridSpaceX, gridSpaceY).setState(1);

				}

				/////////////////////////////////////////////////////
				// Handle the enemy Grid/////////////////////////////
				/////////////////////////////////////////////////////
			} else if (tempObject.getId().equals("ENEMYGRID")) {
				Grid enemyGrid = (Grid) tempObject;

				int gridSpaceX = (e.getX() - enemyGrid.getGridStartX()) / enemyGrid.getImgPx();
				int gridSpaceY = (e.getY() - enemyGrid.getGridStartY()) / enemyGrid.getImgPx();

				// Check if the click was inside the grid
				if (!(gridSpaceX > (enemyGrid.getBoardSizeX() - 1) || x - enemyGrid.getGridStartX() < 0
						|| (gridSpaceY > enemyGrid.getBoardSizeY() - 1) || y - enemyGrid.getGridStartY() < 0)) {
					System.out.println("Clicked on the Enemy grid at: " + (x - enemyGrid.getGridStartX()) + " "
							+ (y - enemyGrid.getGridStartY()));

					System.out.println("X space: " + gridSpaceX + " Y space: " + gridSpaceY);

					// Change the gridSpace state
					enemyGrid.getGridSpace(gridSpaceX, gridSpaceY).setState(1);

				}

			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		// Check all the game objects
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId().equals("FIREBUTTON")) {
				FireButton fire = (FireButton) tempObject;

				int objectX = (x - fire.getX()) / fire.getWidth();
				int objectY = (y - fire.getY()) / fire.getHeight();
				// Check if the click was inside the button
				if (!(objectX > (fire.getWidth() - 1) || x - fire.getStartX() < 0 || (objectY > fire.getHeight() - 1)
						|| y - fire.getStartY() < 0)) {

					System.out.println("Released the fire button"); // Replace
																	// with
																	// method
																	// for
																	// shooting

					fire.setState(0);
				}
			}
		}
	}

}
