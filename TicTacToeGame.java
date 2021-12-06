import java.awt.Point;

public class TicTacToeGame implements TicTacToe {

	//instances
	private Point[] moves; //9
	private BoardChoice[][] gameGrid; //3 by 3
	private int totalMoves;
	private GameState gState;
	private BoardChoice winner;
	private BoardChoice lastMove;
	
	
	public TicTacToeGame() {
		//initialize game Grid and moves
		gameGrid = new BoardChoice[3][3];
		moves = new Point[9]; //point loation of X and O
		totalMoves = 0;
		gState = GameState.IN_PROGRESS;
		lastMove = BoardChoice.OPEN;
		for(int i = 0; i < gameGrid.length; i++) {
			for(int j = 0; j < gameGrid[i].length; j++) {
				gameGrid[i][j] = BoardChoice.OPEN;
			}
		}
	}
	
	@Override
	public void newGame() {
		//renewing the gamestate
		gState = GameState.IN_PROGRESS;
		//renewing the moves
		totalMoves = 0;
		moves = new Point[9];
		lastMove = BoardChoice.OPEN;

		//renewing the blocks
		for(int i = 0; i < gameGrid.length; i++) {
			for(int j = 0; j < gameGrid[i].length; j++) {
				gameGrid[i][j] = BoardChoice.OPEN;
			}
		}
		
		
	}

	@Override
	public boolean choose(TicTacToe.BoardChoice player, int row, int col) {
		boolean ret = false;
		

		
		//make two different if for the past move
		if(!gameOver() && row >= 0 && row <= 2 && col >= 0 && col <= 2 &&
		gameGrid[row][col] == BoardChoice.OPEN) {
			if(lastMove != player) {
				ret = true;
				if(player == BoardChoice.O) {
					Point thisMove = new Point(row, col);
					gameGrid[thisMove.x][thisMove.y] = player;
					lastMove = BoardChoice.O;
					moves[totalMoves] = thisMove; 
					totalMoves++;
				}
				if(player == BoardChoice.X) {
					Point thisMove = new Point(row, col);
					gameGrid[thisMove.x][thisMove.y] = player;
					lastMove = BoardChoice.X;
					moves[totalMoves] = thisMove; 
					totalMoves++;
					if(totalMoves >= 4) {
						gameOver();
					}
				}
			}
			
			
//			System.out.println(thisMove +"  "+ player + "**************************************************************");
		}
		
		
		
		return ret;
	}

	@Override
	public boolean gameOver() {
		boolean ret = false;
		winner = BoardChoice.OPEN;
		
		//testing for each way to win		//maybe test to see if the winning row isnt open first for efficiency?
		if(totalMoves >= 4) {	
			if(gameGrid[0][0] == gameGrid[0][1] && gameGrid[0][0] == gameGrid[0][2] && !(gameGrid[0][0].equals(BoardChoice.OPEN))) {
				ret = true; //top row horozontal
				winner = gameGrid[0][0];
			}else if(gameGrid[1][0] == gameGrid[1][1] && gameGrid[1][0] == gameGrid[1][2] && !(gameGrid[1][0].equals(BoardChoice.OPEN))) {
				ret = true; //middle row horozontal
				winner = gameGrid[1][0];
			}else if(gameGrid[2][0] == gameGrid[2][1] && gameGrid[2][0] == gameGrid[2][2] && gameGrid[2][0] != BoardChoice.OPEN) {
				ret = true; //bottom row horozontal
				winner = gameGrid[2][0];
			}else if(gameGrid[0][0] == gameGrid[1][0] && gameGrid[0][0] == gameGrid[2][0] && gameGrid[0][0] != BoardChoice.OPEN) {
				ret = true; //first row vertical
				winner = gameGrid[0][0];
			}else if(gameGrid[0][1] == gameGrid[1][1] && gameGrid[0][1] == gameGrid[2][1] && gameGrid[0][1] != BoardChoice.OPEN) {
				ret = true; //middle row verticle
				winner = gameGrid[0][1];
			}else if(gameGrid[0][2] == gameGrid[1][2] && gameGrid[0][2] == gameGrid[2][2] && gameGrid[0][2] != BoardChoice.OPEN) {
				ret = true; //last row verticle
				winner = gameGrid[0][2];
			}else if(gameGrid[0][0] == gameGrid[1][1] && gameGrid[0][0] == gameGrid[2][2] && !(gameGrid[0][0].equals(BoardChoice.OPEN))) {
				ret = true; //true if diagonal right is same value
				winner = gameGrid[0][0];
			}else if(gameGrid[2][0] == gameGrid[1][1] && gameGrid[2][0] == gameGrid[0][2] && gameGrid[2][0] != BoardChoice.OPEN) {
				ret = true; //true if diagonal right is same value
				winner = gameGrid[2][0];
			}else if(gState !=GameState.X_WON || gState !=GameState.O_WON) {
				//board counter it it is 9 it is ttie game
				int board = 0;
				//tie game scenario
				for(int row = 0; row < gameGrid.length; row++) {
					for(int col = 0; col < gameGrid[row].length; col++) {
						if(gameGrid[row][col] != BoardChoice.OPEN) {
							board++;
							if(board == 9) {
								ret = true;
							}
						}
					}
				}
			}
		}
		
		
		
		return ret;
	}

	@Override
	public TicTacToe.GameState getGameState() {
	//takes in information from game over else it is default to inprogress.
		gState = GameState.IN_PROGRESS;
		
		if(gameOver()) {
			if(winner == BoardChoice.X) {
				gState = GameState.X_WON;
			}else if(winner == BoardChoice.O) {
				gState = GameState.O_WON;
			}else if(winner != BoardChoice.X && winner != BoardChoice.O) {
				gState = GameState.TIE;
			}
		}
		return gState;
	}

	@Override
	public TicTacToe.BoardChoice[][] getGameGrid() {
		
		TicTacToe.BoardChoice[][] copyGrid; 
		copyGrid = new BoardChoice[3][3];
		
		for(int i = 0; i < gameGrid.length; i++) {
			for(int j = 0; j < gameGrid[i].length; j++) {
				copyGrid[i][j] = gameGrid[i][j];
			}
		}
		
		
		return copyGrid;
	}

	@Override
	public Point[] getMoves() {
		//if moves % 2 then = x else O
		Point[] copyMoves = new Point[totalMoves];
		for(int i = 0; totalMoves > i; i++) {
			copyMoves[i] = moves[i];
		}
		
//		if(copyMoves.length % 2 ==0) {
//			copyMoves[moves.length] = BoardChoice.X;
//		}else {
////			copyMoves[moves.length] = BoardChoice.O;
//		}
			
		return copyMoves;
	}

}
