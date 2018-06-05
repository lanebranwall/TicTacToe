package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game{

	static Players currentPlayer = Players.X;
	static boolean gameOver = false;
	static TicTacToe gameBoard = new TicTacToe();
	static JLabel sideMessage = new JLabel("X's Turn!");
	static JFrame window = new JFrame();
	static JButton[][] boardButtons = {{new JButton(), new JButton(), new JButton()}, {new JButton(), new JButton(), new JButton()},{new JButton(), new JButton(), new JButton()}};

	public static void main(String[] args)
	{
		setUpWindow();
	}

	public static void initialize()
	{
		currentPlayer = Players.X;
		gameOver = false;
		gameBoard = new TicTacToe();
		sideMessage = new JLabel("X's Turn");
		window = new JFrame();
	}


	//sets up the JFrame window for the Tic-Tac-Toe game
	public static void setUpWindow()
	{
		//set up for the JFrame window as a whole
		JPanel board = new JPanel(new GridLayout(3,3)); //3x3 grid containing buttons represents Tic-Tac-Toe board
		JPanel sideDisplay = new JPanel(new FlowLayout()); //A side panel displayed to the right of the game grid
		window.setSize(640, 480);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Tic-Tac-Toe");
		window.setVisible(true);

		//set up for the board JPanel
		board.setPreferredSize(new Dimension(480,480));
		board.setLayout(new GridLayout(3,3));
		for(int i = 0; i < 3; i++) //set up all buttons on the game board in a 3x3 grid
		{
			for(int j = 0; j < 3; j++)
			{
				board.add(boardButtons[i][j]);
				boardButtons[i][j].addActionListener(new BoardButton());
				boardButtons[i][j].setName(String.valueOf(i) + j); //Button's name is used to identify its corresponding grid coordinates
			}
		}

		//Set up for the sideDisplay JPanel
		sideDisplay.setPreferredSize(new Dimension(160,480));
		sideDisplay.add(sideMessage);
		JButton reset = new JButton("reset");
		reset.addActionListener(new ResetButton());
		sideDisplay.add(reset);


		window.add(board);
		window.add(sideDisplay, BorderLayout.EAST);
	}


//resets the game to the starting turn. 
	static void reset()
	{
		for(int i = 0; i < 3; i++) //set up all buttons on the game board in a 3x3 grid
		{
			for(int j = 0; j < 3; j++)
			{
				boardButtons[i][j].setText("");

			}
		}
		gameBoard = new TicTacToe();
		currentPlayer = Players.X;
		sideMessage.setText("X's Turn");
		gameOver = false;
	}
}

//ActionListener for the Tic-Tac-Toe game buttons
//when pressed a button updates the gameboard, and what it displays appropriately
//also checks to see if the move made puts the game into a finished state.
class BoardButton implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		TicTacToe board = Game.gameBoard;
		Players player = Game.currentPlayer;
		JButton buttonClicked = (JButton)e.getSource();
		int row = Character.getNumericValue(buttonClicked.getName().charAt(0)); //which row the button corresponds to
		int column = Character.getNumericValue(buttonClicked.getName().charAt(1)); //which column the button corresponds to

		if(board.makeMove(player, row, column) && !Game.gameOver) //continue if the move is legal and the game is not already finished
		{
			if(player == Players.X) 
			{
				buttonClicked.setText("X");
				if(board.isWinner(player))
				{
					Game.gameOver = true;
					Game.sideMessage.setText("X wins!");
				}
				else if(board.isFull())
				{
					Game.gameOver = true;
					Game.sideMessage.setText("Cat's game!");
				}
				else
				{
					Game.currentPlayer = Players.O;
					Game.sideMessage.setText("O's turn");
				}
			}
			else if(player == Players.O)
			{
				buttonClicked.setText("O");
				if(board.isWinner(player))
				{
					Game.gameOver = true;
					Game.sideMessage.setText("O wins!");
				}
				else if(board.isFull())
				{
					Game.gameOver = true;
					Game.sideMessage.setText("Cat's game!");
				}
				else
				{
					Game.currentPlayer = Players.X;
					Game.sideMessage.setText("X's turn");
				}
			}
		}

	}
}

//ActionListener for the reset button
//destroys the current window, reinitializes static variables, and creates a new window
class ResetButton implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Game.reset();
	}

}





