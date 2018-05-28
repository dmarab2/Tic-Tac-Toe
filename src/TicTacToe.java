import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class TicTacToe {

	public static void main(String[] args) {
		Scanner option = new Scanner(System.in);
		// this is used for letting the computer pick squares in games against the computer
		ArrayList<Integer> selection = new ArrayList();
		for(int i = 1; i <= 9; i++) {
			selection.add(i);
		}
		//random number generator for the computer
		Random random = new Random();
		GameOps gameOps = new GameOps();
		int winner = 0;
		int menuPick = 0;
		System.out.println("Welcome to Tic Tac Toe.");
		System.out.println("1:Play with Computer");
		System.out.println("2:Play with another Player");
		System.out.println("Please select 1 or 2 to make your choice: ");
		//this is simply to make sure that the player inputs a valid menu option
		while(true){
			menuPick = option.nextInt();
			if(menuPick < 1 || menuPick > 2){
				System.out.println("That is not a valid option. Please pick again. ");
				continue;
			}
			break;
		}
		char[][] board ={ {'1','2','3'}, {'4','5','6'}, {'7','8','9'} };
		for(int i = 0; i <= 8 ;){
			gameOps.print_board(board);
			gameOps.player_input(1, board, selection);
			if(gameOps.check_for_winner(board)) {
				winner = 1;
				break;
			}
			i += 1;
			if(i == 9){
				break;
			}
			gameOps.print_board(board);
			if(menuPick == 1) {
				gameOps.computer_input(board, selection, random);
			}
			else {
				gameOps.player_input(2, board, selection);
			}
			if(gameOps.check_for_winner(board)) {
				winner = 2;
				break;
			}
			i += 1;
		}
		gameOps.print_board(board);
		if(winner == 0) {
			System.out.println("This is a draw!");
		}
		else {
			System.out.println("Congratulations Player " + winner + ", you are the winner!");
		}
		
	}

}

class GameOps{
	private Scanner scanner;

	void print_board(char[][] board) {
		System.out.println("\n");
		System.out.println(board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
		System.out.println("--+---+--");
		System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
		System.out.println("--+---+--");
		System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2] + "\n");
	}
	//takes input from player(s), and puts it on the board if it is valid
	void player_input(int which, char[][] board, ArrayList<Integer> selection){
		while(true) {
			int row = 0;
			int column = 0;
			int pick = 0;
			scanner = new Scanner(System.in);
			System.out.println("Player " + which + ", enter the square you wish to mark: ");
			pick = scanner.nextInt();
			if(pick < 0 || pick > 9) {
				System.out.println("This is not a valid choice!");
				continue;
			}
			//coding magic to turn the selected int into a row and column number
			row = --pick / 3;		
			column = pick % 3;
			if(board[row][column] == 'X' || board[row][column] == 'O') {
				System.out.println("This spot has already been taken!");
				continue;
			}
			if(which == 1){							
				board[row][column] = 'X';
				//it's made sure that selections by the player aren't made by the computer as well
				Integer obj = new Integer(pick);
				selection.remove(obj);
				selection.trimToSize();
				break;
			}
			else{
				board[row][column] = 'O';
				Integer obj = new Integer(pick);
				selection.remove(obj);
				selection.trimToSize();
				break;
			}
		}
	}
	//this is only used in games against the computer
	void computer_input(char[][] board, ArrayList<Integer> selection, Random random){
		System.out.println("Player 2 is now going.");
		while(true) {
			int indexPick = random.nextInt(selection.size());
			int pick = selection.get(indexPick);
			int row = 0;
			int column = 0;
			row = --pick / 3;
			column = pick % 3;
			if(board[row][column] == 'X' || board[row][column] == 'O') {
				continue;
			}
			board[row][column] = 'O';
			selection.remove(indexPick);
			selection.trimToSize();
			break;
		}
	}
	
	boolean check_for_winner(char[][] board){
		if((board[0][0] == board[1][1] && board[0][0] == board[2][2]) || (board[0][2]) == board[1][1] && board[0][2] == board[2][0]){
			return true;
		}
		for(int space = 0; space < 3; space++) {
			if((board[space][0] == board[space][1] && board[space][0] == board[space][2]) || (board[0][space] == board[1][space] && board[0][space] == board[2][space])){
				return true;
			}
		}
		return false;
	}
}