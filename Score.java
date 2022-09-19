import java.awt.*; // we'll use java.awt.Graphics
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle{
	
//	java.lang.Object
//		java.awt.geom.RectangularShape
//			java.awt.geom.Rectangle2D
//				java.awt.Rectangle
	
	// width and height of the game
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	
	//score for player 1
	 static int player1;
	//score for player 2
	 static int player2;
	
	
	 public static int getPlayer1Score() {
		 return player1;
	 }
	 
	 public static int getPlayer2Score() {
		 return player2;
	 }
	// Initializes the height/width with the height/width of the GamePanel
	public Score(int GAME_WIDTH, int GAME_HEIGHT) {
		
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}
	
	
	public void draw(Graphics g) {
		//set color of pen to white
		
		
		
		
		//draw + fill a rectangle to display score
		g.drawRect((GAME_WIDTH /2)-90, 0, 180, 80);
		g.setColor(Color.WHITE);
		g.fillRect((GAME_WIDTH /2)-90, 0, 180, 80);
		
		// draw a line halfway across the game based on who's winning

		if (player1 > player2) 
			g.setColor(Color.BLUE);
		else if (player2 > player1)
			g.setColor(Color.RED);
		else
			g.setColor(Color.WHITE);
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		// "draw" the score as text of each player at given coordinates
		g.setColor(Color.BLACK);
		g.setFont(new Font("Consolas", Font.PLAIN, 60));
		g.drawString(String.valueOf(player1), (GAME_WIDTH /2)-85, 50);
		g.drawString(String.valueOf(player2), (GAME_WIDTH /2)+10, 50);

		//"label" the scores
		g.setFont(new Font("Consolas", Font.ITALIC,20));
		g.drawString("Player 1", (GAME_WIDTH /2)-85, 70);
		g.drawString("Player 2", (GAME_WIDTH /2)+10, 70);
		

	}
	
//	public void end(Graphics g) {
//		g.drawRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
//		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
//		
//		g.setColor(Color.WHITE);
//		g.setFont(new Font("Consolas", Font.PLAIN, 30));
//		if (player1>player2)
//			g.drawString("Player 1 won!", 50, 50);
//		else
//			g.drawString("Player 2 won!", 50, 50);
//		
//	}

}
