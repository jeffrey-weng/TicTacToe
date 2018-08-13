//package TicTacToe;

import java.util.Scanner;

public class playTTT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Non GUI version
		TTTBoard board=new TTTBoard();
		Scanner reader=new Scanner(System.in);
		int input;


		System.out.println("Welcome to Tic-Tac-Toe.");
		System.out.println("Game Modes: ");
		System.out.println("1. Two Player");
		System.out.println("2. Player vs. Computer");

		input=reader.nextInt();

		if(input==1)
			board.twoPlayer();
		else
			board.playerVsAI();
	}

}
