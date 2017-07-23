package algorithm.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PartitionOfKSubsumOfEqualSum {
	
	int[] array;
	boolean[] visited;
	ArrayDeque<ArrayDeque<Integer>> subsetOfSubsets= new ArrayDeque<>();
	int partition;
	
	int SumK = -1;
	
	public PartitionOfKSubsumOfEqualSum(int[] array,int partition){
		this.array = array;
		this.visited = new boolean[array.length];
		this.partition = array.length / partition;
	}
	
	public int sum(ArrayDeque<Integer> nums){
		int sum=0;
		for(Integer num : nums){
			sum+=num;
		}
		return sum;
	}
	
	public boolean foundSubSet(ArrayDeque<Integer> set,int index,int count){
		boolean success=false;
		System.out.println("index =" + index);
		if(count == array.length){
			System.out.println("Found Solution " + subsetOfSubsets);
			success = true;
		}else if(set.size() == partition){
				System.out.println("Found size of partition " + set);
				if(set.getFirst() == array[0]){
					SumK = sum(set);
				}
				if(SumK == sum(set)){
					System.out.println("Found Subset equal to " + SumK);
					subsetOfSubsets.add(set.clone());
				}
				foundSubSet(new ArrayDeque<>(),0, count-1);
		}else{
			if(index < array.length){
				if(visited[index] == false){
					visited[index] = true;
					set.addLast(array[index]);
					count++;
					foundSubSet(set, index+1, count);
					visited[index] = false;
					set.removeLast();
					count--;
				}
				foundSubSet(set, index+1, count);
			}
		}
		return success;
	}
	
	
	public static void main(String[] args) {
		PartitionOfKSubsumOfEqualSum partitionOfKSubsumOfEqualSum=new PartitionOfKSubsumOfEqualSum(new int[]{2, 1, 4, 5, 6},2);
		partitionOfKSubsumOfEqualSum.foundSubSet(new ArrayDeque<>(), 0, 0);
	}
	

}
