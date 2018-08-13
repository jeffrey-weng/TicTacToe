//package TicTacToe;

import java.util.Random;
import java.util.Scanner;

public class TTTBoard {

	private char [][] board; ///ttt array

	public TTTBoard(){
		board=new char[3][3];
		reset();

	}	

	//reset board
	public void reset(){
		for(int row=0; row<3; row++){
			for(int column=0; column<3; column++){
				board[row][column]='-';

			}}}

	//return representation of the ttt board
	public String toString(){
		String result="\n";
		for(int row=0; row<3; row++){
			for(int column=0; column<3; column++){
				result+=board[row][column] + " ";
			}

			result+="\n";
		}
		return result;
	}

	//check if placement of X or O is on a valid (unoccupied square)
	public boolean placeXorO(char s, int row, int column){
		if(board[row-1][column-1]=='-'){
			board[row-1][column-1]=s;
			return true;
		}
		else return false;
	}

	//see if board is full

	public boolean full(){
		for(int row=0; row<3; row++)
			for(int column=0; column<3; column++)
				if(board[row][column]=='-')
					return false;
		return true;
	}


	//return '-' if there is no winner "X" or "O" if there is a winner
	public char getWinner(){
		//represents the different ways to win
		String[]triples= new String[8];
		//find the content of the rows
		for(int column=0; column<3; column++)
			triples[column]=getColumn(column);

		for(int row=0; row<3; row++)
			triples[3+row]=getRow(row);

		//win by diagonals
		triples[6]=getDiagonals(0);
		triples[7]=getDiagonals(2);

		//to find xxx ooo or -

		for(int i=0; i<8; i++)
			if(" XXX".equals(triples[i]))
				return 'X';
			else if(" OOO".equals(triples[i]))
				return 'O';

		return '-';
	}
	//----------------------------BLOCK HUMAN THREATS OF VICTORY---------------------------------------------

	//if a move was made to block a possible human win, return true.

	public boolean HorizontalBlock(char player, char computer){


		for(int i=0; i<3; i++){


			if(board[i][0]==player && board[i][1]==player){
				if(board[i][2]==player || board[i][2]==computer);

				else{
					board[i][2]=computer;
					return true;
				}
			}
			if(board[i][1]==player && board[i][2]==player){
				if(board[i][0]==player || board[i][0]==computer);

				else{
					board[i][0]=computer;
					return true;
				}
			}

			if(board[i][0]==player && board[i][2]==player){
				if(board[i][1]==player || board[i][1]==computer);

				else{
					board[i][1]=computer;
					return true;
				}
			}

		}
		return false;
	}


	public boolean VerticalBlock(char player, char computer){


		for(int i=0; i<3; i++){

			if(board[0][i]==player && board[1][i]==player){
				if(board[2][i]==player || board[2][i]==computer);

				else{
					board[2][i]=computer;
					return true;		
				}
			}	  


			if(board[1][i]==player && board[2][i]==player){
				if(board[0][i]==player || board[0][i]==computer);

				else{
					board[0][i]=computer;
					return true;
				}
			}

			if(board[0][i]==player && board[2][i]==player){
				if(board[1][i]==player || board[1][i]==computer);

				else{
					board[1][i]=computer;
					return true;
				}
			}

		}
		return false;
	}

	public boolean DiagonalBlock(char player, char computer){

		if((board[1][1]==player && board[0][0]==player)){
			if(board[2][2]==player || board[2][2]==computer);

			else{
				board[2][2]=computer;
				return true;
			}
		}

		if(board[1][1]==player && board[2][2]==player){
			if(board[0][0]==player || board[0][0]==computer);

			else{
				board[0][0]=computer;
				return true;
			}
		}

		if(board[0][0]==player && board[2][2]==player){
			if(board[1][1]==player || board[1][1]==computer);

			else{
				board[1][1]=computer;
				return true;
			}

		}

		if(board[0][2]==player && board[1][1]==player){
			if(board[2][0]==player || board[2][0]==computer);

			else{
				board[2][0]=computer;
				return true;
			}
		}

		if(board[2][0]==player && board[1][1]==player){
			if(board[0][2]==player || board[0][2]==computer);

			else{
				board[0][2]=computer;
				return true;

			}
		}

		if(board[0][2]==player && board[2][0]==player){
			if(board[1][1]==player || board[1][1]==computer);

			else{
				board[1][1]=computer;
				return true;
			}
		}

		return false;
	}
	//------------------------------HUMAN THREATS ELIMINATED------------------------------------------------------


	//COMPUTER PLAYS FOR THE WIN!!!!
	//methods return true if a move was made that made the computer win.

	public boolean HorizontalWin(char player, char computer){
		for(int i=0; i<3; i++){
			if(board[i][0]==board[i][1] && board[i][0]==computer){
				if(board[i][2]!=player && board[i][2]!=computer){
					board[i][2]=computer;
					return true;
				}
				else;

			}
			if(board[i][1]==board[i][2] && board[i][1]==computer){
				if(board[i][0]!=player && board[i][0]!=computer){
					board[i][0]=computer;
					return true;

				}
				else;
			}
			if(board[i][0]==board[i][2] && board[i][0]==computer){
				if(board[i][1]!=player && board[i][1]!=computer){
					board[i][1]=computer;
					return true;
				}
				else;
			}

		}

		return false;
	}

	public boolean VerticalWin(char player, char computer){
		for(int i=0; i<3; i++){

			if(board[0][i]==board[1][i] && board[1][i]==computer){
				if(board[2][i]!=player && board[2][i]!=computer){
					board[2][i]=computer;
					return true;
				}
				else;		
			}	  

			if(board[1][i]==computer && board[2][i]==computer){
				if(board[0][i]!=player && board[0][i]!=computer){
					board[0][i]=computer; 
					return true;
				}
				else;
			}

			if(board[0][i]==computer && board[2][i]==computer){
				if(board[1][i]!=player && board[1][i]!=computer){
					board[1][i]=computer;
					return true;
				}
				else;
			}

		}
		return false;
	}

	public boolean DiagonalWin(char player, char computer){
		if(board[1][1]==board[0][0] && board[1][1]==computer){
			if(board[2][2]!=player && board[2][2]!=computer){
				board[2][2]=computer;
				return true;
			}
			else;		
		}	  

		if(board[1][1]==board[2][2] && board[1][1]==computer){
			if(board[0][0]!=player && board[0][0]!=computer){
				board[0][0]=computer; 
				return true;
			}
			else;
		}

		if(board[0][0]==board[2][2] && board[2][2]==computer){
			if(board[1][1]!=player && board[1][1]!=computer){
				board[1][1]=computer;
				return true;
			}
			else;
		}

		if(board[0][2]==board[1][1] && board[0][2]==computer){
			if(board[2][0]!=player && board[2][0]!=computer){
				board[2][0]=computer;
				return true;
			}
			else;
		}

		if(board[2][0]==board[1][1] && board[1][1]==computer){
			if(board[0][2]!=player && board[0][2]!=computer){
				board[0][2]=computer;
				return true;
			}
			else;

		}

		if(board[0][2]==board[2][0] && board[0][2]==computer){
			if(board[1][1]!=player && board[1][1]!=computer){
				board[1][1]=computer;
				return true;
			}
			else;
		}
		return false;
	}
	//-------------------------COMPUTER WINS completed-----------------------------

	//used in the getWinner method.



	//return content of column
	private String getColumn(int column){
		String result=" ";
		for(int row=0; row<3; row++)
			result+=board[row][column];
		return result;
	}

	//return content of row
	private String getRow(int row){
		String result=" ";
		for(int column=0; column<3; column++)
			result+=board[row][column];
		return result;
	}
	//return diagonals
	private String getDiagonals(int startRow){
		String result=" ";
		if(startRow==0)
			for(int i=0; i<3; i++)
				result+=board[i][i];
		else
			for(int i=0; i<3; i++)
				result+=board[2-i][i];
		return result;
	}

	public void twoPlayer(){
		//instantiate the scanner and ttt board

		Scanner reader= new Scanner(System.in);
		TTTBoard board= new TTTBoard();

		//Display the TTT board
		System.out.println(board);

		//Decide who goes first

		Random gen=new Random();
		char player;
		if(gen.nextInt(2)==1)
			player='O';
		else 
			player='X';

		//loop to play the game
		while(board.getWinner()=='-' && !board.full()){

			//prompt user to move
			System.out.println(player+ "'s turn");
			System.out.println("Enter the row and column(row, space, column:) ");

			int row=reader.nextInt();
			int column=reader.nextInt();

			//see if move is legal

			boolean success=board.placeXorO(player, row, column);
			if(!success)
				System.out.println("Space is occupied!");
			else{
				System.out.println(board);
				if(player=='X')
					player='O';
				else
					player='X';
			}


		}
		char winner=board.getWinner();
		if(winner!='-')
			System.out.println(winner+ "s win!");
		else
			System.out.println("It's a draw!");
	}

	public void playerVsAI(){
		//instantiate the scanner and ttt board

		Scanner reader= new Scanner(System.in);
		TTTBoard board= new TTTBoard();

		//Display the TTT board
		//System.out.println(board);

		//Decide who goes first

		Random gen=new Random();
		char player;
		char computer;
		boolean playerTurn;
		int row=-1;
		int column=-1;
		boolean success = false;

		if(gen.nextInt(2)==1){
			player='O';
			computer='X';
			playerTurn=false;
		}
		else {
			player='X';
			computer='O';
			playerTurn=true;
		}
		if(playerTurn)System.out.println(board);

		//loop to play the game
		while(board.getWinner()=='-' && !board.full()){

			//prompt user to move
			if(playerTurn){
				System.out.println(player+ "'s (your) turn");
				System.out.println("Enter the row and column(row, space, column:) ");

				row=reader.nextInt();
				column=reader.nextInt();

				success=board.placeXorO(player, row, column);
				if(!success)
					System.out.println("Space is occupied!");
				else{
					System.out.println(board);
					playerTurn=!playerTurn;

				}

			}
			else{

				//prioritize winning whenever possible, then blocking human win potential, else make random move.
				if (board.HorizontalWin(player, computer));
				else if (board.VerticalWin(player, computer));
				else if (board.DiagonalWin(player, computer));

				else if(board.HorizontalBlock(player,computer));
				else if( board.VerticalBlock(player,computer));
				else if (board.DiagonalBlock(player,computer));

				else{
					int c1=gen.nextInt(3)+1;
					int c2=gen.nextInt(3)+1;

					while(board.placeXorO(computer, c1, c2)==false){
						c1=gen.nextInt(3)+1;
						c2=gen.nextInt(3)+1;
					}
					board.placeXorO(computer, c1, c2);
				}
				System.out.println("Computer move:");
				System.out.println(board);
				playerTurn=!playerTurn;
			}
			//see if move is legal


			//	System.out.println(board);
		}
		char winner=board.getWinner();
		if(winner!='-'){
			if(winner==computer)
				System.out.println(winner+"s (computer) win!");
			else System.out.println(winner+ "s (you) win!");
		}
		else
			System.out.println("It's a draw!");

		//System.out.println(board);
	}

}









