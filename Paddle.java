import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle {
	
	//which player's paddle is it
	int id;
	
	//paddle moves along y axis
	int yVelocity;
	int speed = 10;
	
	// constructor for Paddle object takes in starting position of paddle, width
	// and height and ID
	public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id;
		
		
	}
	
	
	// Is the key pressed 
	public void keyPressed(KeyEvent e) {
		
		//if the id (player) is 1 or 2
		switch(id) {
		// if 1, they operate using the w or s keys only
		case 1: 
			if(e.getKeyCode() == KeyEvent.VK_W) { // if it's w key
				setYDirection(-speed); // yVel is set to -10 (down)
				move(); //paddle moves -10 each click (in this case, that means UP towards origin)
			}
			if (e.getKeyCode() == KeyEvent.VK_S) { // if it's s key
				setYDirection(speed); //yVel is set to 10
				move(); //paddle moves 10 on each click (10 means + means away from origin)
			}
			break;
		
		// if 2, they operate using the up and down keys only
		case 2: 
			if(e.getKeyCode() == KeyEvent.VK_UP) { // if it's up key
				setYDirection(-speed); // yVel is set to -10 (down)
				move(); //paddle moves -10 each click (in this case, that means UP towards origin)
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) { // if it's down key
				setYDirection(speed); //yVel is set to 10
				move(); //paddle moves 10 on each click (10 means + means away from origin)
			}
			break;
		}
	}
	
	// same thing as above, but it sets YVel to 0 once key is not pressed
	// so now move() will not move the paddle
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1: 
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0);
				move();
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0);
				move();
			}
			break;
		case 2: 
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDirection(0);
				move();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDirection(0);
				move();
			}
			break;
		}	
	}
	
	// sets yVel to either 10, -10, or 0
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	
	// "moves" the paddle by adding yVel to position of paddle
	public void move() {
		y = y+ yVelocity;
		
	}
	//draws the paddles
	public void draw (Graphics g) {
		// if it's player 1, the color of graphic is set to blue
		if(id == 1) 
			g.setColor(Color.BLUE);
		else // else set to red
			g.setColor(Color.RED);
		// Rectangle specified is filled with whatever color is set
		g.fillRect(x, y, width, height); 
		
		// wondering where x, y, width, and height come from?
		// we extended Rectangle class, which has those fields
		// when we called super() in the constructor, those fields
		// were initialized
		
	}
}
