package algorithm.backtracking;

import java.util.ArrayList;

import javax.swing.plaf.synth.SynthOptionPaneUI;

/**
 * Represent position on Maze
 * @author ankit
 *
 */
class Position{
	int x;
	int y;
	boolean visited;
	int value;
	
	
	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Position(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(! (obj instanceof Position)) return false;
		if(obj == this) return true;
		Position pos = (Position) obj;
		if(this.x == pos.x && this.y == pos.y) return true;
		return false;
	}
	
}

class Maze{
	
	Position[][] matrix;
	int x;
	int y;
	
	Position[][] pathMatrix;
	
	public Maze(int x, int y){
		matrix = new Position[x][y];
		this.x = x;
		this.y=y;
		
		this.pathMatrix = new Position[x][y];
		
	}
	
	public Maze(int[][] mat){
		this.x = mat.length;
		this.y = mat[0].length;
		this.matrix = getPositionMatrix(mat);
		this.pathMatrix = new Position[mat.length][mat[0].length];
	}
	
	private final Position[][] getPositionMatrix(int[][] mat){
		Position[][] positionMat = new Position[mat.length][mat[0].length];
		for(int i=0; i < x; i++){
			for(int j=0; j < y; j++){
				positionMat[i][j] = new Position(i, j);
				positionMat[i][j].setValue(mat[i][j]);
			}
		}
		return positionMat;
	}
	
	
	public void setInValidMatrixPosition(Position position){
		matrix[position.getX()][position.getY()].setValue(0);
	}
	
	public boolean isValidPositionOnMaze(Position position){
		
		if(position.getX() < 0 || position.getX() >= x){
			System.out.println("invalid x position " + position);
			return false;
		}
		
		if(position.getY() < 0 || position.getY() >= y){
			System.out.println("invalid y position " + position);
			return false;
		}
		
		if(matrix[position.getX()][position.getY()].isVisited()) {
			System.out.println("invalide position already visited =" + position);
			return false;
		};
		
		System.out.println("Postion is valid =" + position);
		return true;
	}
	
	public ArrayList<Position> getValidPositionForMove(Position position){
		if(!isValidPositionOnMaze(position)) return null;
		
		
		ArrayList<Position> positions = new ArrayList<>();
		// checking downward move
		Position downwardPos = new Position(position.getX()+1, position.getY());
		if(isValidPositionOnMaze(downwardPos) && matrix[downwardPos.getX()][downwardPos.getY()].getValue() != 0){
			positions.add(downwardPos);
		}else{
			System.out.println("invalid downward postion ="+ downwardPos);
		}
		
		Position forwardPosition = new Position(position.getX(), position.getY()+1);
		if(isValidPositionOnMaze(forwardPosition) && matrix[forwardPosition.getX()][forwardPosition.getY()].getValue() != 0){
			positions.add(forwardPosition);
		}else{
			System.out.println("invalid forward postion ="+ forwardPosition);
		}
		System.out.println("returning valid positions to move" + positions);
		return positions;
	}
	
	
	public void startMoving(Position start,Position end){
		if(start.equals(end)){
			System.out.println("found destination");
			return;
		}		
		ArrayList<Position> positions= getValidPositionForMove(start);
		if(positions == null || positions.isEmpty()){
			System.out.println("No Valid position to move");
			System.out.println("Marking current position to invalid" + start);
			setInValidMatrixPosition(start);
		}else{
			matrix[start.getX()][start.getY()].setVisited(true);
			for(Position pos : positions ){
				pathMatrix[pos.getX()][pos.getY()] = new Position(start.getX(),start.getY());
				startMoving(pos, end);
			}
		}
	}
	
	public void printMaze(){
		for(int i=0 ; i < this.x ; i++){
			for(int j=0 ; j < this.y ; j++){
				System.out.print(this.matrix[i][j].getValue() + " ");
			}
			System.out.println("\n");
		}
	}
	
	public void printPositionMatixMaze(){
		for(int i=0 ; i < this.x ; i++){
			for(int j=0 ; j < this.y ; j++){
				System.out.print(this.pathMatrix[i][j] + " ");
			}
			System.out.println("\n");
		}
	}
	
}




public class RatInMaze {

	public static void main(String[] args){
		int[][] matrix = new int[][]{{1, 0, 0, 0},
        {1, 1, 0, 1},
        {0, 1, 0, 0},
        {1, 1, 1, 1}};
        
        Maze maze = new Maze(matrix);
        maze.printMaze();
        maze.startMoving(new Position(0, 0), new Position(3,3));
        maze.printMaze();
        maze.printPositionMatixMaze();
	}
}
