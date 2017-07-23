package algorithm.backtracking;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class SetSub{
	int[] seqNumbers;
	Set<Integer> visitedNumbers;
	int K;
	
	SetSub(int[] nums,int K){
		this.seqNumbers = nums;
		this.K=K;
		visitedNumbers = new HashSet<>();
	}
	
	boolean checkSumToKMatch(Set<Integer> sumSet){
		boolean result=false;
		if(sumSet != null){
			int sum=0;
			for(Integer num : sumSet){
				sum += num;
			}
			System.out.println("Sum of Set -" + sum);
			
			if(sum == K){
				result = true;
			}
		}
		return result;
	}
	
	
	public int sum(Set<Integer> nums, int num){
		int sum=0;
		for(Integer nm : nums){
			sum += nm;
		}
		sum += num;
		return sum;
	}
	
	
	public boolean findSumOfK(LinkedHashSet<Integer> sumSet,int nextIndex){
		boolean result=false;
		System.out.println("Set in Queue-" + sumSet + " , nextIndex="+nextIndex);
		if(nextIndex >= this.seqNumbers.length){
			System.out.println("not found sum for this seq" + sumSet);
			result=false;
		}
		
		if(checkSumToKMatch(sumSet)){
				System.out.println("SUBSET FOUND =" + sumSet);
				result = true;
		}else{

			if(!this.visitedNumbers.contains(seqNumbers[nextIndex])){
				if(sum(sumSet,seqNumbers[nextIndex]) <= K){
					System.out.println("Sum of=" + sumSet + " , " + seqNumbers[nextIndex]);
					sumSet.add(seqNumbers[nextIndex]);
					this.visitedNumbers.add(seqNumbers[nextIndex]);
					if(findSumOfK(sumSet, nextIndex+1)){
						return true;
					}else{
						sumSet.remove(seqNumbers[nextIndex]);
						this.visitedNumbers.remove(seqNumbers[nextIndex]);
						return findSumOfK(sumSet, nextIndex+1);
					}
			    }
		    }else{
		    	System.out.println("number is already visited yet -" + this.seqNumbers[nextIndex]);
		    	result = findSumOfK(sumSet, nextIndex+1);
		    } 
			
		}
		return result;	
	}
	
	public void findSubset(){
		System.out.println("seq " + this.seqNumbers);
		LinkedHashSet<Integer> linkedHashSet=new LinkedHashSet<>();
		for(int i=0; i < 4; i++){
			findSumOfK(linkedHashSet,i);
		}
	}
}






public class SubsetSum {
 public static void main(String[] args) {
	 SetSub sub = new SetSub(new int[]{1,5,2,6,4}, 6);
	 sub.findSubset();
}
}
