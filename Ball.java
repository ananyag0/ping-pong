import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle{
	
	Random random;
	int xVelocity;
	int yVelocity;
	int initSpeed = 2;
	
	
	//constructor which takes location + dimensions of ball
	public Ball(int x, int y, int width, int height) {
		//calls constructor of parent class
		super(x, y, width, height);
		
		//creates a new instance of the Random class (creates a new random number generator)
		random = new Random();
		
		// creates a new random integer between [0, 2) to figure out which way (side/side, up/down) the ball will travel
		int randomXDirection = random.nextInt(2);
		
		//if randomXDirection is 0, it becomes -1, else it stays 1
		if (randomXDirection == 0)
			randomXDirection--;
		
		//XDirection is set to ( 1 or -1 )* 2
		setXDirection(randomXDirection * initSpeed);
		
		//same for yDirection
		int randomYDirection = random.nextInt(2);
		if (randomYDirection == 0)
			randomYDirection--;
		setYDirection(randomYDirection * initSpeed);
		
		
	}
	//XVel and YVel are each set to either -2 or 2 
	//On x axis, 2 means towards right, -2 means towards left
	// On y axis, 2 means down and -2 means up
	// this is because the origin is on the top left
	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
	}
	
	public void setYDirection(int randomYDirection) {
		yVelocity = randomYDirection;
	}
	
	// moves the ball based on xVelocity/yVelocity
	public void move() {
		x += xVelocity;
		y += yVelocity;
		
	}
	
	//draws a ball
	public void draw(Graphics g) {
		g.setColor(Color.WHITE); //white color
		g.fillOval(x, y, height, width); //location/dimension of ball
		//x, y, width, and height come from super() constructor call, they're
		// fields of the rectangle superclass
		
		
	}

}
