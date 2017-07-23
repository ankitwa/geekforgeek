package algorithm.backtracking;

public class SudokuProblem {

	int[][] matrix;
	int gridSize=0;
	int row;
	int col;
	
	boolean success = false;
	
	
	public SudokuProblem(int[][] matrix,int gridSize){
		this.matrix = matrix;
		this.row = matrix.length;
		this.col = matrix[0].length;
		this.gridSize = gridSize;
	}
	
	public void printMatrix(){
		for(int i=0 ; i < row; i++){
			for(int j=0; j < col ; j++){
				System.out.print(" " + matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	
	public int[] getGridTopForCord(int x, int y){
		double grid_topX = Math.floor(x/gridSize) * gridSize;
		double grid_topY = Math.floor(y/gridSize) * gridSize;
		//System.out.println("Grid Top Cor : x=" + grid_topX + ", y=" + grid_topY);
		return new int[]{(int)grid_topX,(int)grid_topY};
	}
	
	
	
	private boolean checkInGrid(int element,int x, int y){
		int[] cor = getGridTopForCord(x, y);
		for(int _x=cor[0] ; _x < gridSize ; _x++){
			for(int _y=cor[1] ; _y < gridSize ; _y++){
				if(matrix[_x][_y] == element) return false;
			}
		}
		//System.out.println("element is unique in grid =" + element );
		return true;
	}
	
	
	
	private boolean checkInRow(int element,int row){
		for(int i=0; i < col; i++){
			if(matrix[row][i] == element) return false;
		}
		return true;
	}
	
	private boolean checkInCol(int element,int colm){
		for(int i=0; i < col; i++){
			if(matrix[i][colm] == element) return false;
		}
		return true;
	}
	
	private boolean checkAnyOpenPlace(){
		for(int i=0; i < row; i++){
			for(int j=0; j < col; j++){
				if(matrix[i][j] == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	public boolean checkValidElementInPosition(int element, int x, int y){
		//System.out.println("Checking validation called");
		if(x < 0 || x >= row) return false;
		if(y < 0 || y >= col) return false;
		if(matrix[x][y] != 0) return false;
		
	
		if(checkInRow(element, x) && checkInCol(element, y) && checkInGrid(element, x, y)){
			return true;
		}
		return false;
	}
	
	
	public int[] getPositionForPlacement(){
		int[] cor = new int[]{-1,-1};
		for(int i=0; i < row; i++){
			for(int j=0; j< col; j++){
				if(matrix[i][j] == 0){
					cor[0] =i;
					cor[1] = j;
				}
			}
		}
		return cor;
	}
	
	public int placeElement(int x,int y){
		for(int i=1; i <= 9 ; i++){
			if(checkValidElementInPosition(i, x, y)){
				return i;
			}
		}
		return 0;
	}
	
	
	
	boolean findSudukoConfiguration(){
			//System.out.println("Finding validation for position ; x" + x + " , y=" + y);
		    boolean result=false;
			if(!checkAnyOpenPlace()){
				System.out.println("Found Soln");
				printMatrix();
				result = true;
			}else{
				for(int i=1; i <= 9 ; i++){
					int[] pos = getPositionForPlacement();
					if(checkValidElementInPosition(i, pos[0], pos[1])){
						matrix[pos[0]][pos[1]] = i;
						if(findSudukoConfiguration()){
							//return true; // do this when only one soln is to be find.
						}
						matrix[pos[0]][pos[1]] = 0; // backtracl
					}
				}
		    }
			return result;
	}
	
	public static void main(String[] arg){
		int grid[][] = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};
		
		SudokuProblem sudokuProblem = new SudokuProblem(grid, 3);
		sudokuProblem.findSudukoConfiguration();
	}
	
	
}
