package algorithm.backtracking;

import java.util.HashSet;
import java.util.Set;

public class SubsetProblem {
	
	int[] elements = new int[]{1,6,2,3,4};
	Set<Integer> visited=new HashSet();
	int K=6;
	
	
	public int getElementLessThanOrEqualToK(int lower){
		int i=lower;
		for(; i < elements.length; i++){
			if(elements[i] == K || elements[i] < K ){
				return i;
			}
		}
		return i;
	}
	
	public void findSubset(){
		int index=0;
		int trv = index;
		Set<Integer> nums=new HashSet<>();
		while(true){
			if(visited.contains(trv)){
				continue;
			}
			if(getElementLessThanOrEqualToK(trv) < elements.length){
				int element = getElementLessThanOrEqualToK(trv);
				nums.add(element);
				visited.add(trv);
				if(element == K){
					System.out.println(nums);
					index++;
				}else{
					trv++;
				}
			}
		}
		
		
	}
	
	
	
	
	
	

}
