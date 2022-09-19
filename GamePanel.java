import java.awt.*;
import java.awt.event.*;
import java.util.*; //java.util has things like random, arraylist
import javax.swing.*; // has JPanel

public class GamePanel extends JPanel implements Runnable{
	// implements java.lang.Runnable (Runnable interface) used to start a thread using the run method
	// runnable interface only has the run() method, which we will override
	// "implements" when we want to use an interface in the class
	// interface is special class which only has abstract methods (no implementation/body inside)
	// thus, our class (GamePanel) must implement the abstract method in Runnable, run()
	
	
	// JPanel class is part of Java Swing package 
	
	static final int MAX_SCORE = 10;
	static boolean gameRun = true;
	
	// initializes fields of game width and height to make it a rectangle
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int) (GAME_WIDTH * (5.0/9));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	
	//constants for dimensions of ball, paddle
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	
	// declares a new Thread instance
	Thread gameThread; 
	
	//
	Image image;
	
	//
	Graphics graphics;
	
	//Declares Random Object - note that Random Class is part of java.util.* package 
	Random random;
	
	// Declares Paddle, Score, and Ball Objects
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	
	//scores
	int player1 = Score.getPlayer1Score();
	int player2 = Score.getPlayer2Score();
	
	public GamePanel() {
		//calls newPaddles method in this class
		newPaddles();
		//calls newBall method in this class
		newBall();
		
		//creates new instance of score class 
		score = new Score (GAME_WIDTH, GAME_HEIGHT);
		
		// Game Panel can be focused if you were to click on it/loses focus when you click on something else
		this.setFocusable(true);
		
		//
		this.addKeyListener(new AL());
		
		//sets size of the GamePanel to SCREEN_SIZE (size of game)
		this.setPreferredSize(SCREEN_SIZE);
		
		
		// create an instance of the thread class
		// this is the runnable object
		gameThread = new Thread(this);
		
		// creates a new Thread that executes code in run()
		// note that calling run() =/= create new thread, runs same thread. 
		gameThread.start(); 
		
		JLabel label = new JLabel();
		label.setText("Game Over.");
		
	}

	// we create a method newBall which is called in constructor to put new ball on screen
	public void newBall() {
		random = new Random();
		ball = new Ball ((GAME_WIDTH/2) - (BALL_DIAMETER/2), (GAME_HEIGHT / 2) - (BALL_DIAMETER/2), BALL_DIAMETER, BALL_DIAMETER);
	}
	
	// we create a method newPaddles which is called in constructor to put new paddles on screen
	public void newPaddles() {
		paddle1 = new Paddle(0, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT/2) - (PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
		
	}
	
	//inherited from JPanel
	public void paint(Graphics g) {
		//image is an off-screen image
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		
		// calls below draw method to draw paddle, score, ball OFF SCREEN
		draw(graphics); 
		
		//draw the image created in previous line ON SCREEN
		g.drawImage(image, 0, 0, this);
		
	}
	
	// we will use the "draw" method of the Paddle class, Ball class, and Score class
	public void draw (Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);

	}
	
	// move method calls the move methods of the paddles and balls 
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
		
	}
	public void checkCollision() {
		
		//bounce ball off top and bottom window edges
		if(ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
			
			
		//bounces ball off paddle 1
		
		// intersects method comes from superclass Rectangle 
			if(ball.intersects(paddle1)) {
				ball.xVelocity = Math.abs(ball.xVelocity);
				ball.xVelocity++; // more velocity
				if(ball.yVelocity > 0)
					ball.yVelocity++;
				else
					ball.yVelocity--;
				ball.setXDirection(ball.xVelocity);
				ball.setYDirection(ball.yVelocity);

			}
		
		
			
		//bounces ball off paddle 2
			if(ball.intersects(paddle2)) {
				ball.xVelocity = Math.abs(ball.xVelocity);
				ball.xVelocity++; // more velocity
				if(ball.yVelocity > 0)
					ball.yVelocity++;
				else
					ball.yVelocity--;
				ball.setXDirection(-ball.xVelocity);
				ball.setYDirection(ball.yVelocity);

			}
		
		
		//stops paddles at window edges only on y
		if(paddle1.y <= 0)
			paddle1.y=0;
		if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		if(paddle2.y <= 0)
			paddle2.y=0;
		if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
		
		
		// if ball makes it past the paddle 
		
		//give a player 2 point and creates new paddles and ball
		
		
		
		
		
		if(ball.x <= 0) {
			score.player2++;
			if(score.player2 < MAX_SCORE) {
				newPaddles();
				newBall();
			}
			else {
				//game over
				gameRun = false;
			}
		}
		
		
	
		if(ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.player1++;
			if(score.player1 < MAX_SCORE) {
				newPaddles();
				newBall();
			}
			else {
				//game over
				gameRun = false;
			}
		}
}
		
		// same for player 1
	
	
	
		
	
	
	public void run() {
		// game loop
		long lastTime = System.nanoTime(); // last time is current time
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		while(gameRun) {
			long now = System.nanoTime(); // current time
			delta  += (now - lastTime)/ns;  // elapsed time
			lastTime = now; // last time is set to current time 
			if(delta >= 1) { // if elapsed more than 1s
				move(); //move ball, paddle, score
				checkCollision(); //check for collision with anything
				repaint(); //calls paint method (in this class) to set board
				delta--; //it's been 1s so subtract that from elapsed time
							
			}
			
			
			
			
			
				
		}
		
	
		
		
	}
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);

		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}

}
