package View;


import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class CheckersCanvas extends Canvas implements ActionListener, MouseListener
{

	Button resignButton; 
	Button newGameButton; 
	Label message; 
	CheckersData board; 			// I could probably pull the board from GeneralPlayer
	boolean gameInProgress; 
	int currentPlayer; 				// I can pull the current player from the player function from ThirtyFiveRepBoard.player()
	int selectedRow, selectedCol; 	// This can be a Move object, containing both new and old coordinates

	CheckersMove[] legalMoves; 		// We already have an array of legal moves - ThirtyFiveRepBoard.actions()


	public CheckersCanvas()
	{
		setBackground(Color.black);
		addMouseListener(this);
		setFont(new Font("Serif", Font.BOLD, 14));
		resignButton = new Button("Resign");
		resignButton.addActionListener(this);
		newGameButton = new Button("New Game");
		newGameButton.addActionListener(this);
		message = new Label("", Label.CENTER);
		board = new CheckersData();
		doNewGame();
	}


	public void actionPerformed(ActionEvent evt)
	{
		Object src = evt.getSource();
		
		if (src == newGameButton)
		{
			doNewGame();
		}
		else if (src == resignButton)
		{
			doResign();
		}
	}


	void doNewGame()
	{
		if (gameInProgress == true)
		{
			message.setText("Finish the current game first!");
			return;
		}
		board.setUpGame(); 					// The pieces are already set-up in initBoard, or the board variable.
		currentPlayer = CheckersData.RED; 	// Black goes first bitch
		legalMoves = board.getLegalMoves(CheckersData.RED); // List of legal moves in ThirtyFiveRepBoard.actions()
		selectedRow = -1; // IDK if we even need this.
		message.setText("Red:  Make your move.");
		gameInProgress = true;
		newGameButton.setEnabled(false);
		resignButton.setEnabled(true);
		repaint();
	}


	void doResign()
	{
		if (gameInProgress == false)
		{
			message.setText("There is no game in progress!");
			return;
		}
		if (currentPlayer == CheckersData.RED)
		{
			gameOver("RED resigns.  BLACK wins.");
		}
		else
		{
			gameOver("BLACK resigns.  RED winds.");
		}
	}


	void gameOver(String str)
	{
		message.setText(str);
		newGameButton.setEnabled(true);
		resignButton.setEnabled(false);
		gameInProgress = false;
	}

	
	// This method needs to be completely revamped
	void doClickSquare(int row, int col)
	{
		// This is called by mousePressed() when a player clicks on the
		// square in the specified row and col. It has already been checked
		// that a game is, in fact, in progress.
		/*
		 * If the player clicked on one of the pieces that the player can move,
		 * mark this row and col as selected and return. (This might change a
		 * previous selection.) Reset the message, in case it was previously
		 * displaying an error message.
		 */
		for (int i = 0; i < legalMoves.length; i++)
		{
			if (legalMoves[i].fromRow == row && legalMoves[i].fromCol == col)
			{
				selectedRow = row;
				selectedCol = col;
				if (currentPlayer == CheckersData.RED)
				{
					message.setText("RED:  Make your move.");
				}
				else
				{
					message.setText("BLACK:  Make your move.");
				}
				repaint();
				return;
			}
		}
		/*
		 * If no piece has been selected to be moved, the user must first select
		 * a piece. Show an error message and return.
		 */
		if (selectedRow < 0)
		{
			message.setText("Click the piece you want to move.");
			return;
		}
		/*
		 * If the user clicked on a squre where the selected piece can be
		 * legally moved, then make the move and return.
		 */
		for (int i = 0; i < legalMoves.length; i++)
			if (legalMoves[i].fromRow == selectedRow
					&& legalMoves[i].fromCol == selectedCol
					&& legalMoves[i].toRow == row && legalMoves[i].toCol == col)
			{
				doMakeMove(legalMoves[i]);
				return;
			}
		/*
		 * If we get to this point, there is a piece selected, and the square
		 * where the user just clicked is not one where that piece can be
		 * legally moved. Show an error message.
		 */
		message.setText("Click the square you want to move to.");
	} // end doClickSquare()

	
	
	// Also needs to be revamped
	void doMakeMove(CheckersMove move)
	{
		// Thiis is called when the current player has chosen the specified
		// move. Make the move, and then either end or continue the game
		// appropriately.
		board.makeMove(move);
		/*
		 * If the move was a jump, it's possible that the player has another
		 * jump. Check for legal jumps starting from the square that the player
		 * just moved to. If there are any, the player must jump. The same
		 * player continues moving.
		 */
		if (move.isJump())
		{
			legalMoves = board.getLegalJumpsFrom(currentPlayer, move.toRow,
					move.toCol);
			if (legalMoves != null)
			{
				if (currentPlayer == CheckersData.RED)
					message.setText("RED:  You must continue jumping.");
				else
					message.setText("BLACK:  You must continue jumping.");
				selectedRow = move.toRow; // Since only one piece can be moved,
											// select it.
				selectedCol = move.toCol;
				repaint();
				return;
			}
		}
		/*
		 * The current player's turn is ended, so change to the other player.
		 * Get that player's legal moves. If the player has no legal moves, then
		 * the game ends.
		 */
		if (currentPlayer == CheckersData.RED)
		{
			currentPlayer = CheckersData.BLACK;
			legalMoves = board.getLegalMoves(currentPlayer);
			if (legalMoves == null)
				gameOver("BLACK has no moves.  RED wins.");
			else if (legalMoves[0].isJump())
				message.setText("BLACK:  Make your move.  You must jump.");
			else
				message.setText("BLACK:  Make your move.");
		}
		else
		{
			currentPlayer = CheckersData.RED;
			legalMoves = board.getLegalMoves(currentPlayer);
			if (legalMoves == null)
				gameOver("RED has no moves.  BLACK wins.");
			else if (legalMoves[0].isJump())
				message.setText("RED:  Make your move.  You must jump.");
			else
				message.setText("RED:  Make your move.");
		}
		/*
		 * Set selectedRow = -1 to record that the player has not yet selected a
		 * piece to move.
		 */
		selectedRow = -1;
		/*
		 * As a courtesy to the user, if all legal moves use the same piece,
		 * then select that piece automatically so the use won't have to click
		 * on it to select it.
		 */
		if (legalMoves != null)
		{
			boolean sameStartSquare = true;
			for (int i = 1; i < legalMoves.length; i++)
				if (legalMoves[i].fromRow != legalMoves[0].fromRow
						|| legalMoves[i].fromCol != legalMoves[0].fromCol)
				{
					sameStartSquare = false;
					break;
				}
			if (sameStartSquare)
			{
				selectedRow = legalMoves[0].fromRow;
				selectedCol = legalMoves[0].fromCol;
			}
		}
		/* Make sure the board is redrawn in its new state. */
		repaint();
	} // end doMakeMove();


	public void update(Graphics g)
	{
		// The paint method completely redraws the canvas, so don't erase
		// before calling paint().
		paint(g);
	}


	public void paint(Graphics g)
	{
		// Draw checkerboard pattern in gray and lightGray. Draw the
		// checkers. If a game is in progress, hilite the legal moves.
		/* Draw a two-pixel black border around the edges of the canvas. */
		g.setColor(Color.black);
		g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
		g.drawRect(1, 1, getSize().width - 3, getSize().height - 3);
		/* Draw the squares of the checkerboard and the checkers. */
		for (int row = 0; row < 8; row++)
		{
			for (int col = 0; col < 8; col++)
			{
				if (row % 2 == col % 2)
					g.setColor(Color.lightGray);
				else
					g.setColor(Color.gray);
				g.fillRect(2 + col * 20, 2 + row * 20, 20, 20);
				switch (board.pieceAt(row, col))
				{
				case CheckersData.RED:
					g.setColor(Color.red);
					g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
					break;
				case CheckersData.BLACK:
					g.setColor(Color.black);
					g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
					break;
				case CheckersData.RED_KING:
					g.setColor(Color.red);
					g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
					g.setColor(Color.white);
					g.drawString("K", 7 + col * 20, 16 + row * 20);
					break;
				case CheckersData.BLACK_KING:
					g.setColor(Color.black);
					g.fillOval(4 + col * 20, 4 + row * 20, 16, 16);
					g.setColor(Color.white);
					g.drawString("K", 7 + col * 20, 16 + row * 20);
					break;
				}
			}
		}
		/*
		 * If a game is in progress, hilite the legal moves. Note that
		 * legalMoves is never null while a game is in progress.
		 */
		if (gameInProgress)
		{
			// First, draw a cyan border around the pieces that can be moved.
			g.setColor(Color.cyan);
			for (int i = 0; i < legalMoves.length; i++)
			{
				g.drawRect(2 + legalMoves[i].fromCol * 20,
						2 + legalMoves[i].fromRow * 20, 19, 19);
			}
			// If a piece is selected for moving (i.e. if selectedRow >= 0),
			// then
			// draw a 2-pixel white border around that piece and draw green
			// borders
			// around eacj square that that piece can be moved to.
			if (selectedRow >= 0)
			{
				g.setColor(Color.white);
				g.drawRect(2 + selectedCol * 20, 2 + selectedRow * 20, 19, 19);
				g.drawRect(3 + selectedCol * 20, 3 + selectedRow * 20, 17, 17);
				g.setColor(Color.green);
				for (int i = 0; i < legalMoves.length; i++)
				{
					if (legalMoves[i].fromCol == selectedCol
							&& legalMoves[i].fromRow == selectedRow)
						g.drawRect(2 + legalMoves[i].toCol * 20,
								2 + legalMoves[i].toRow * 20, 19, 19);
				}
			}
		}
	} // end paint()


	public Dimension getPreferredSize()
	{
		// Specify desired size for this component. Note:
		// the size MUST be 164 by 164.
		return new Dimension(164, 164);
	}


	public Dimension getMinimumSize()
	{
		return new Dimension(164, 164);
	}


	public void mousePressed(MouseEvent evt)
	{
		// Respond to a user click on the board. If no game is
		// in progress, show an error message. Otherwise, find
		// the row and column that the user clicked and call
		// doClickSquare() to handle it.
		if (gameInProgress == false)
			message.setText("Click \"New Game\" to start a new game.");
		else
		{
			int col = (evt.getX() - 2) / 20;
			int row = (evt.getY() - 2) / 20;
			if (col >= 0 && col < 8 && row >= 0 && row < 8)
				doClickSquare(row, col);
		}
	}


	public void mouseReleased(MouseEvent evt)
	{
	}


	public void mouseClicked(MouseEvent evt)
	{
	}


	public void mouseEntered(MouseEvent evt)
	{
	}


	public void mouseExited(MouseEvent evt)
	{
	}
} 




