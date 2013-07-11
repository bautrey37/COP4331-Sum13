package gameMain;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

import entities.Tank;
import entities.TankBody;

/**
 * This is the main class that runs the game.  
 * 
 */

public class Game extends JComponent implements KeyListener, Runnable {
	
	private static final long serialVersionUID = 1L;
	private JPanel container;
	
	private boolean running;
	private int iteration;
	private Thread game;
	
	/**
	 * Defines the values used in determining enemy count and enemy type in levels 1 - 10.	
	 * This variable is static so that it can be defined only once and used throughout the entire program's running time.
	 */
	private static int[] levelInfo;
	
	/**
	 * Manages the objects for displaying the user's tank.
	 */
	private Tank[] tank;
	
	private boolean accelLeft, accelRight;
	
	private BufferedImage background;
		
	/**
	 * Constructs a new Game JPanel for which to run the level that the user is playing on.
	 * 
	 * @param container - used to switch to other cards  
	 */
	public Game(JPanel container)
	{
		this.container = container;
		
		this.addKeyListener(this); //adds keylistener to this class
		
		
		try {
			background = ImageIO.read(new File("res/STE-Background-1.jpg"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * Add in code that configures level 1 - 10 variables, possibly by reading a file.
		 * Formatting will be decided at a later time.
		 */		
		levelInfo = new int[10];
		
	} 
	
	
	/**
	 * This method configures the initial game settings, including setting up the playing field, creating the initial
	 * enemies, and spawning the user's tank.
	 */
	public void init(int dif)
	{
		//System.out.println("Init called");
		this.requestFocusInWindow();
		//this.requestFocus();
		this.addKeyListener(this);
		
		//  Create user's tank
		tank = new Tank[2];
		tank[0] = new TankBody( getWidth() * 0.5, getHeight() * 0.9, 0, 0 );
		tank[0].setTankSpeedLimit( 10 );
		
		//makes new game thread
		
		
		start();
	}  
	
	/**
	 * Starts the thread for running the game.
	 */
	public void start() {
		running = true;
		game = new Thread(this);
		game.setPriority(Thread.MAX_PRIORITY);
		game.start();
	}
	
	/**
	 * resumes the game
	 */
	public void resume() {
		running = true;
	}
	
	/**
	 * Stops the game from running
	 */
	public void stop() {
		running = false;
	}
		
	
	/**
	 * Runs the game in its own thread.
	 */
	@Override
	public void run() {
		System.out.println("run was called");
		
		iteration = 0;
		while( running )
		{
			
			//System.out.println("Iteration #" + iteration);
			//iteration++;
			
			long time = System.currentTimeMillis();
			
			
			this.paintComponent( this.getGraphics() );;
			//this.paintEntities( this.getGraphics() );
			
			
			
			this.moveEntities();
			//System.out.println( tank[0].getX() );
			
			try
			{
				long delay = Math.max(0, 32-(System.currentTimeMillis()-time));
				
				Thread.sleep(delay);
			}
			catch(InterruptedException e)
			{
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, this);
		g.drawImage( tank[0].getImage(),
				(int)tank[0].getX() - tank[0].getImage().getWidth() / 2,
				(int)tank[0].getY() - tank[0].getImage().getHeight() / 2,
				     this );
		
	}
	
	@Override
	public void update(Graphics g) {
		
	}
	
	
	/**
	 * Handles moving all entities across the screen.
	 */
	public void moveEntities()
	{
		//  Handle tank movement.
		if( accelLeft )
			tank[0].accelerateLeft();
		if( accelRight )
			tank[0].accelerateRight();
		if( !accelLeft && !accelRight )
			tank[0].lowerTankSpeed();
		
		tank[0].move();
		
	}
	
	
	public void tick() {
		
	}
	
	public void render() {
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		//System.out.println("Key pressed");
		
		if(key == KeyEvent.VK_ESCAPE) {
			stop();
			CardLayout cl = (CardLayout)container.getLayout();
			cl.show(container, "pause");
		}
		
		
		if( key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT )
		{
			accelLeft = true;
			System.out.println("A");
		}
		if( key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT )
		{
			System.out.println("D");
			accelRight = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if( key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT )
		{
			accelLeft = false;
		}
		if( key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT )
		{
			accelRight = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	
	
}
