package game;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;
import java.util.Random;

/**
 * A very simple example of how to use the Game base class.
 * 
 * Here, we provide a constructor for our game, override the JPanel
 * paintComponent() method, and write a simple main() method that creates and
 * starts the game.
 * 
 * @author sdexter72
 * @author Oren Lahav
 */
public class SpaceMassacreGame extends Game implements GameEventsListener{

	/**
	 * The lone 'object' in our simple game.
	 */
	Ship ship1;
	Ship ship2;
	Vector <Laser> vecOfLasersShip = new Vector <Laser>();
	//we will use a random generator for creating random parameters for each star or alien object.
	Random randomGenerator = new Random();
	int randomNumber = randomGenerator.nextInt(30)+1;
	//creating 8 aliens
	EvilAlien1[] aliens = new EvilAlien1[8];
	//creating 300 stars
	Star[] stars = new Star[300];
	int width = 854;
	int height = 480;
	
	/**
	 * a method to check whether a certain object's shape contains the same points as another object's shape, which in our case 
	 * will cause a collision
	 * @param laser
	 */
	public void checkCollisions(Vector <Laser> laser) {
		
		for (int i =0; i < laser.size(); i++)
		{
			for (int j=0; j < aliens.length; j++)
			{
				if (aliens[j].collide(laser.elementAt(i)))
				{
					aliens[j].takeHit();
					laser.elementAt(i).hitTarget();
				}
					
			}
		}
	}
	
	public void shotFired(int x, int y, Color color) 
	{	
		vecOfLasersShip.add(new Laser(x + 60, y, color));	
	}
	
	public void shipMoved(int x, int y, int shipNumber) {
			if (shipNumber == 1) {
				ship1.move(x, y);
			} else {
				ship2.move(x, y);
			}
		
		
	}
	
	/**
	 * This constructor invokes the super constructor, then creates a ship
	 * object (which doesn't do very much)
	 * 
	 * @param name
	 * @param inWidth
	 * @param inHeight
	 */
	
	public SpaceMassacreGame(String name, int inWidth, int inHeight) {
		super(name, inWidth, inHeight);
		setBackground(Color.BLACK);
		
		this.width = inWidth;
		this.height = inHeight;
		
		// each star out of the 300 will get random position on the screen
		for(int i=0; i<stars.length; i++)
		{
			int x1 = randomGenerator.nextInt(inWidth);
			int y1 = randomGenerator.nextInt(inHeight);
			stars[i] = new Star(x1, y1, inWidth, inHeight);
		}
	
		// each alien will get random parameters for movement, direction, rotation, speed, and starting point
		for (int i =0 ; i<aliens.length; i++)
		{	
			int x1 = inWidth + 200;
			int y1 = randomGenerator.nextInt(inHeight)+70;
			int s1 = randomGenerator.nextInt(30)+5;
			int r1 = randomGenerator.nextInt(5)+1;
			//the random generator will create a negative number, in order for the aliens to move west (towards out ship).
			int h1 = randomGenerator.nextInt(4)-5;
			int v1 = randomGenerator.nextInt(4)-1;	
			aliens[i] = new EvilAlien1(x1, y1, s1, r1, h1, v1, inWidth, inHeight);
		}
	
	}
	



	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// If constructor has not yet completed, do nothing.
		// This fixes a NullPointerException that was sometimes thrown
		if (ship1 == null) {
			return;
		}
		// Draw the ship in red
		if (ship1 != null) {
			ship1.paint(g);
		}
		//draw second ship in green
		if (ship2 != null) {
			ship2.paint(g);
		}
		// draw the stars in white
		g.setColor(Color.WHITE);
		for(int i = 0; i < stars.length; i++)
		{
			stars[i].paint(g);
		}
		// draw the aliens in magneta
		g.setColor(Color.magenta);
		for(int i =0; i < aliens.length; i++)
		{
			aliens[i].paint(g);
		}
		g.setColor(Color.RED);
		for (int i = 0; i<vecOfLasersShip.size() ; i++)
		{
			vecOfLasersShip.elementAt(i).paint(g);
		
		}
		
		checkCollisions(vecOfLasersShip);
		
	}
	
	public void setPlayer(int playerNumber)
	{
		int x = width / 10;
		int y = height / 2;
		
		//creating our ships
		ship1 = new Ship(x, y - 20, width, height, Color.RED, playerNumber == 1 ? this : null, this, playerNumber);
		ship2 = new Ship(x, y + 20, width, height, Color.GREEN, playerNumber == 2 ? this : null, this, playerNumber);
	}

	/**
	 * In main, we create a new SimpleGame, make sure it has the keyboard focus
	 * (which it will need when we implement code to control game action with
	 * keyboard), and start the game.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		
		SpaceMassacreGame game = new SpaceMassacreGame("Simple Game", 1280, 720);
		game.requestFocus();
		game.startGame();

	}






}
