package algorithm.backtracking;


import java.util.ArrayDeque;

class PositionLPM{
	int x;
	int y;
	
	public PositionLPM(int x,int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PositionLM other = (PositionLM) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PositionLM [x=" + x + ", y=" + y + "]";
	}
}


public class LargetPossiblePathWithHurdleInMatrix{

	private int[][] matrix;
	int len;
	int breadth;
	
	PositionLPM src;
	PositionLPM des;
	
	ArrayDeque<Position> finalSol = new ArrayDeque<>();
	ArrayDeque<Position> solution=new ArrayDeque<>();
	
	int maxPath = Integer.MIN_VALUE;
	
	public LargetPossiblePathWithHurdleInMatrix(int[][] matrix){
		this.matrix = matrix;
		this.breadth = matrix.length;
		this.len = matrix[0].length;
		//markUnsafeCell();
		printMatrix();
	}
	
	public void printMatrix(){
		for(int i=0; i < breadth ; i++){
			for(int j=0; j < len ; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private void copyPath(ArrayDeque<Position> pos){
		finalSol = new ArrayDeque<>();
		for(Position p : pos){
			finalSol.add(p);
		}
	}
	
	
	private void markUnsafeCell(){
		for(int i=0; i < matrix.length ; i++){
			for(int j=0;  j < matrix[0].length; j++){
				if(matrix[i][j] == 0){
					markUnsafeAdjacentCell(i,j);
				}
			}
		}
	}
	
	private void markUnsafeAdjacentCell(int x,int y){
		// forward
		if(x >= 0 && x < breadth && y >= 0 && (y+1) < len){
			this.matrix[x][y+1] = -1; 
		}
		// backward
		if(x >= 0 && x < breadth && (y-1) >= 0 && (y-1) < len){
			this.matrix[x][y-1] = -1; 
		}
		// below
		if((x + 1) >= 0 && (x + 1) < breadth && y >= 0 && (y) < len){
			this.matrix[x+1][y] = -1; 
		}
		// Above
		if((x-1) >= 0 && (x-1) < breadth && y >= 0 && y < len){
			this.matrix[x-1][y] = -1; 
		}
	}
	
	public void findPath(int x,int y){
		//System.out.println("size of soln=" + solution.size());
		if(des.x == x &&  des.y == y){
			//System.out.println("Found Path");
			if(solution.size() > maxPath){
				maxPath = solution.size();
				copyPath(solution);
				System.out.println("Final Sol =" + finalSol);
			}
		}else{
				if(this.matrix[x][y] != -2 && this.matrix[x][y] != -1 && this.matrix[x][y] != 0 ){
					this.matrix[x][y] = -2;
					solution.addLast(new Position(x, y));
					
					// for below
					if((x >= 0 && x + 1 < breadth) && y >= 0 && y < len){						
						findPath(x+1, y);
					}
					
					// for above
					if((x -1 >= 0 && x - 1 < breadth) && y >= 0 && y < len){
						findPath(x-1, y);
					}
					
					// for forward
					if((x >= 0 && x  < breadth) && y + 1 >= 0 && y + 1 < len){
						findPath(x, y+1);
					}
					
					// for back
					if((x >= 0 && x < breadth) && y -1  >= 0 && y-1 < len){
						findPath(x, y-1);
					}
					
					this.matrix[x][y] = 1;
					solution.removeLast();
				}else{
					//System.out.println("Already Visited : x=" + x + ", y=" + y);
				}
			
		}
	}
	
	public void findSol(){
		for(int i=0; i < breadth ; i++){
			findPath(i, 0);
		}
	}
	
	public void setSource(int x,int y){
		this.src = new PositionLPM(x, y);
	}
	
	public void setDestination(int x,int y){
		this.des = new PositionLPM(x, y);
	}
	
	
	public static void main(String[] args) {
		
		int[][] mat =
		{
				        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				        { 1, 1, 0, 1, 1, 0, 1, 1, 0, 1 },
				        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
		};
		
		LargetPossiblePathWithHurdleInMatrix largetPossiblePathWithHurdleInMatrix = new LargetPossiblePathWithHurdleInMatrix(mat);
		largetPossiblePathWithHurdleInMatrix.setSource(0, 0);
		largetPossiblePathWithHurdleInMatrix.setDestination(1, 7);
		largetPossiblePathWithHurdleInMatrix.findSol();
		System.out.println("-----------------------------------");
		System.out.println("Min Path =" + largetPossiblePathWithHurdleInMatrix.maxPath);
		System.out.println("Final Path=" + largetPossiblePathWithHurdleInMatrix.finalSol);
				
	}
	
	
}