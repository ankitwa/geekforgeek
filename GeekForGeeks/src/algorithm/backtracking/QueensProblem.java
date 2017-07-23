package algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

class QPosition{
	int x;
	int y;
	
	public QPosition(int x, int y){
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
		QPosition other = (QPosition) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}

class Queen{
	
	QPosition position;
	
	Queen(){
		
	}
	
	Queen(int x,int y){
		position = new QPosition(x, y);
	}
	
	Queen(QPosition position){
		this.position = position;
	}
	
	public void moveTo(int x,int y){
		this.position = new QPosition(x, y);
	}
	
	public void moveTo(QPosition position){
		this.position = position;
	}

	public QPosition getPosition() {
		return position;
	}

	public void setPosition(QPosition position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Queen [position=" + position + "]";
	}
	
	
	
	
}

class Board{
	
	int matrix[][];
	int x;
	int y;
	ArrayList<Queen> placedQueens;
	
	Board(int x,int y){
		this.matrix = new int[x][y];
		this.x = x;
		this.y = y;
		placedQueens = new ArrayList<>();
	}
	
	private boolean isNoTonDiagonal(QPosition one,QPosition sec){
		if((one.getX() - one.getY()) == (sec.getX() - sec.getY())){
			//System.out.println("Found on righ diagonal -" + one + "," + sec);
			return false;
		}
		if((one.getX() + one.getY()) == (sec.getX() + sec.getY())){
			//System.out.println("Found on righ diagonal -" + one + "," + sec);
			return false;
		}
		//System.out.println("Not on any diagonal");
		return true;
	}
	
	
	private boolean checkValidPositionToPlaceQueen(QPosition position){
		//System.out.println("checking position is availbe for queen -" + position);
		boolean result=true;;
		for(Queen queen : placedQueens){
			QPosition pos = queen.getPosition();
			//System.out.println("comparing existing queen position =" + pos + ", TO =" + position );
			if(pos.getX() == position.getX() || pos.getY() == position.getY() || !isNoTonDiagonal(pos, position)){
				result=false;
			}
		}
		//System.out.println("Returing valid position as --> " + result + ", pos =" + position);
		return result;
	}
	
	boolean placeQueen(List<Queen> queens,int count){
		boolean result=false;
		//System.out.println("VALID QUEEN POSIION" + placedQueens);
		if(placedQueens.size() == queens.size()){
			//System.out.println("All Queens Placed on board");
			result=true;
		}else{
			for(int i=0; i < this.x ; i++){
				for(int j=0; j < this.y ; j++){
					QPosition position = new QPosition(i, j);
					if(checkValidPositionToPlaceQueen(position)){
						//System.out.println("Position is perfect for queen no -"  + count + ", postion=" + position);
						this.matrix[i][j] = 1;
						queens.get(count).setPosition(position);
						placedQueens.add(queens.get(count));
						if(placeQueen(queens,count+1)){
							result = true;
							break;
						}
						//System.out.println("Other queen is not able to place backtracking to previous state");
						this.matrix[i][j] = 0;
						placedQueens.remove(count);
					}
				}
				if(result) break;
			}
		}
		return result;
	}	
	
	
	void printBoard(){
		for(int i=0; i < this.x; i++){
			for(int j=0; j < this.y; j++){
				System.out.print(this.matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
}



public class QueensProblem {

	public static void main(String[] args){
		int size=8;
		Board board = new Board(size,size);
		List<Queen> queens =new ArrayList<>();
		for(int i=0; i < size ; i++){
			queens.add(new Queen());
		}
		board.placeQueen(queens, 0);
		board.printBoard();
		
	}
}
