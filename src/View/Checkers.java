package View;


import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;

public class Checkers extends Applet
{
	public void init()
	{
		setLayout(null); 
		setBackground(new Color(0, 150, 0)); 
		
		CheckersCanvas board = new CheckersCanvas();
		add(board);
		board.newGameButton.setBackground(Color.lightGray);
		add(board.newGameButton);
		board.resignButton.setBackground(Color.lightGray);
		add(board.resignButton);
		board.message.setForeground(Color.green);
		board.message.setFont(new Font("Serif", Font.BOLD, 14));
		add(board.message);
		board.setBounds(20, 20, 164, 164); // Note: size MUST be 164-by-164 !
		board.newGameButton.setBounds(210, 60, 100, 30);
		board.resignButton.setBounds(210, 120, 100, 30);
		board.message.setBounds(0, 200, 330, 30);
	}
} 
