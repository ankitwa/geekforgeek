package algorithm.backtracking;

import java.util.HashSet;
import java.util.Set;

public class EquidistanceCombination {

	int[] combination;
	Set<Integer> visited=new HashSet<>();
	int num;
	
	public EquidistanceCombination(int num){
		this.num = num;
		combination = new int[2*num];
		for(int i=0; i < combination.length ; i++){
			combination[i]=-1;
		}
	}
	
	public int[] getFreeCoupleIndex(int num){
		for(int i=0; i < combination.length ; i++){
			if(i + num + 1 < combination.length){
				if(combination[i] == -1 && combination[i+num+1] == -1){
					System.out.println("Return vacant index for value=" + num + " , index="+i + " ," + (i+num+1));
					return new int[]{i,i+num+1};
				}
				continue;
			}
		}
		return null;
	}
	
	public void findCombination(){
		if(this.visited.size() == num){
			System.out.println("Found COmbiation");
			printCombination();
		}
		else{
			for(int i=1; i <= num ; i++ ){
				if(!this.visited.contains(i)){
					int[] pos = getFreeCoupleIndex(i);
					if(pos != null){
						this.visited.add(i);
						combination[pos[0]] = i;
						combination[pos[1]] = i;			
						findCombination();
						this.visited.remove(i);
						combination[pos[0]] = -1;
						combination[pos[1]] = -1;
					}
				}
			}

		}
	}
	
	private void printCombination(){
		for(int i=0; i < 2*num ; i++){
			System.out.print(combination[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		EquidistanceCombination equidistanceCombination = new EquidistanceCombination(4);
		equidistanceCombination.findCombination();
	}
	
}
