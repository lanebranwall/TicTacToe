package game;


//TicTacToe holds a matrix to represent a Tic-Tac-Toe board, and contains methods useful for playability
public class TicTacToe {
	
	private Players[][] board = {{null,null,null},{null,null,null},{null,null,null}};
	
	
	//takes in a Player argument, and checks to see if that Player has won, returns true if they have, otherwise false
	public boolean isWinner(Players player)
	{
		for(int i = 0; i < 3; i++)
		{
			if(board[i][0] == player && board[i][1] == player && board[i][2] == player)
			{
				return true;
			}
			if(board[0][i] == player && board[1][i] == player && board[2][i] == player)
			{
				return true;
			}
		}
		if(board[0][0] == player && board[1][1] == player && board[2][2] == player)
		{
			return true;
		}
		
		if(board[0][2] == player && board[1][1] == player && board[2][0] == player)
		{
			return true;
		}
		return false;
	}
	
	//attempts to make a move. If the move is legal, returns true, otherwise false
	public boolean makeMove(Players player, int row, int column)
	{
		if(board[row][column] == null)
		{
			board[row][column] = player;
			return true;
		}
		return false;
	}
	
	//returns the board
	public Players[][] getBoard()
	{
		return board;
	}
	
	//returns true is all spaces on the board are occupied
	public boolean isFull()
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(board[i][j] == null)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	

}
