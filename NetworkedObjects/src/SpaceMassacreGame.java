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
public class SpaceMassacreGame extends Game implements ShootListener{

	/**
	 * The lone 'object' in our simple game.
	 */
	Ship ship;
	Vector <Laser> vecOfLasers = new Vector <Laser>();
	//we will use a random generator for creating random parameters for each star or alien object.
	Random randomGenerator = new Random();
	int randomNumber = randomGenerator.nextInt(30)+1;
	//creating 8 aliens
	EvilAlien1[] aliens = new EvilAlien1[8];
	//creating 300 stars
	Star[] stars = new Star[300];
	
	public void checkCollisions() {
		
		for (int i =0; i < vecOfLasers.size(); i++)
		{
			for (int j=0; j < aliens.length; j++)
			{
				if (aliens[j].collide(vecOfLasers.elementAt(i)))
				{
					aliens[j].takeHit();
					vecOfLasers.elementAt(i).hitTarget();
				}
					
			}
		}
	}
	
	public void shotFired(int x, int y) 
	{	
		vecOfLasers.add(new Laser(ship.getOffsetX()+60, ship.getOffsetY()));	
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
		int y = 350;
		int x = 70;
		
		//creating our ship
		ship = new Ship(x, y, this, this);
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
			int x1 = 1800;
			int y1 = randomGenerator.nextInt(700)+70;
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
		if (ship == null) {
			return;
		}
		// Draw the ship in red
		g.setColor(Color.RED);
		ship.paint(g);
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
		for (int i = 0; i<vecOfLasers.size() ; i++)
		{
			vecOfLasers.elementAt(i).paint(g);
		
		}
		
		checkCollisions();
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
