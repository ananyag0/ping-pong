import java.awt.*; // java.awt.* is a package has classes like java.awt.Frame, java.awt.Window, java.awt.Container, java.awt.Component
import java.awt.event.*; // obsolete class 
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame{ 
	// GameFrame is-a JFrame (inherits its variables + methods)
	
	/*java.lang.Object
	 *	java.awt.Component
	 *		java.awt.Container
	 *			java.awt.Window
	 *				java.awt.Frame
	 *					javax.swing.JFrame
	 *						GameFrame
	*/
	
	GamePanel panel;
	
	public  GameFrame() {
		
		//create a GamePanel called "panel" using the GamePanel class constructor
		panel = new GamePanel();
		
		//adding the panel to the GameFrame 
		this.add(panel);
		
		// set title of the GameFrame to Pong Game
		this.setTitle("Pong Game");
		
		// cannot resize the GameFrame
		this.setResizable(false);
		
		//Set Background of the GameFrame to black
		this.setBackground(Color.black);
		
		//In the GameFrame, the X button with exit the program
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		
		// makes GameFrame "pack" or wrap whatever the subcomponents (in this case GamePanel) are
		this.pack();
		
		//make the GameFrame visible
		this.setVisible(true);
		
		//centers GameFrame / GUI on screen rather than top-left
		this.setLocationRelativeTo(null);
	}

}
