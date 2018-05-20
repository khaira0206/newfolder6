
public class knightTour {
	
	public static void main(String arg[]){
	
		solveKT();
		
	}
	
	static int  N = 8;
	
	static boolean isSafe(int x, int y, int sol[][]){
		
		return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1);
		
	}
	public static void printSol( int sol[][]){
		for(int i = 0; i < N; i++ )
			{for(int j = 0; j < N; j++){
			System.out.print(sol[i][j] + " ");
			
			}
			System.out.println();
			}
		
	}
	public static boolean solveKT(){
		int sol[][] = new int[8][8];
		
		for(int i = 0; i < N; i++ ){
			for(int j = 0; j < N; j++){
				sol[i][j] = -1;
			}
		}
		int moveX[] = {2, 1, -1, -2, -2, -1, 1, 2};
		int moveY[] = {1, 2, 2, 1, -1, -2, -2, -1};
		
		sol[0][0] = 0;
		
		if(!solveKTUtil(0,0,1, sol,moveX,moveY)){
			System.out.println("Solution doesn't exit");
			return false;
		}
		else{
			printSol(sol);
			
		}
		return true;
	}
	
	public static  boolean solveKTUtil(int x, int y, int movei, int sol[][], int moveX[], int moveY[]){
		
		int k, next_x = 0, next_y = 0;
		if(movei == N * N)
			return true;
		for(k = 0; k < 8; k++){
				
				next_x = x + moveX[k];
		        next_y = y + moveY[k];
		
		
		if (isSafe(next_x,next_y, sol)){
			sol[next_x][next_y] = movei;
			if(solveKTUtil(next_x,next_y, movei + 1, sol, moveX,moveY))
				return true;
			else 
				sol[next_x][next_y] = -1;
			
		}
		}
	 return false;	
	}
	

}
