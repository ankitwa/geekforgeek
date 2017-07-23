package algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

public class TugOfWar {
	
	int[] numbers;
	boolean[] subset;
	List<Integer> result=new ArrayList<>();
	int minDiff=Integer.MAX_VALUE;
	
	public TugOfWar(int[] numbers){
		this.numbers = numbers;
		this.subset=new boolean[numbers.length];
	}
	
	
	public void setResult(boolean[] subset){
		result.clear();
		for(int i=0; i < subset.length ; i++){
			if(subset[i]){
				result.add(numbers[i]);
			}
		}
	}
	
	public void printSubset(int prevSum,int currentElement,int count){
		if(count == numbers.length/2){
			System.out.println("Count of element in subset reached half");
			int sum=0;
			for(int i=0; i < subset.length ; i++){
				if(subset[i]){
					sum+=numbers[i];
				}else{
					sum-=numbers[i];
				}
			}
			if(Math.abs(sum) < minDiff){
				System.out.println("Min Value Set");
				minDiff = Math.abs(sum);
				setResult(subset);
			}
		}else{
			if(currentElement >= numbers.length) return;
			if(count < Math.floor(numbers.length/2)){
				System.out.println("Adding given number in subset =" + numbers[currentElement] );
				prevSum += numbers[currentElement];
				count++;
				subset[currentElement]=true;
				printSubset(prevSum, currentElement+1, count);
				System.out.println("Backtracking....");
				prevSum -= numbers[currentElement];
				count--;
				subset[currentElement]=false;
				printSubset(prevSum, currentElement+1, count);
				
			}
		}
	}
	
	public void printSoln(){
		System.out.println("Result" + result);
	}
	
	
	public static void main(String[] args){
		TugOfWar tugOfWar=new TugOfWar(new int[]{23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4});
		tugOfWar.printSubset(0, 0, 0);
		tugOfWar.printSoln();
		
	}
	
	
	 
	
	

}
