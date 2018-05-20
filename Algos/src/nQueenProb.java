public class nQueenProb {

   final int N  = 4;
	
	void printSolution(int board[][]){
		for(int i = 0; i < N; i ++){
			for(int j = 0; j < N; j++){
				System.out.print(" " +board[i][j] + " ");
			}
			System.out.println();
		}
	
	}
	boolean isSafe(int board[][], int row, int col){
		
		int i, j;
		for( i = 0; i < col ; i++)
			if(board[row][i] == 1)
				return false;
		for(i = row,  j = col; i>0 && j>0 ; i--,j--)
			if(board[i][j] == 1)
				return false;
		for(i = row,  j = col; i<N && j>0 ; i++,j--)
			if(board[i][j] == 1)
				return false;
			
			return true;
	
	}
	
	boolean solveNQ(){
		int board[][] = { {0, 0, 0, 0},
		        {0, 0, 0, 0},
		        {0, 0, 0, 0},
		        {0, 0, 0, 0}};
		if(!solveNQUtil(board,0))
			System.out.println("Solution doesn't exist");
		printSolution(board);
		return true;
	}
	boolean solveNQUtil(int board[][], int col){
		
		
		return true;
		
	}
	
	
}
