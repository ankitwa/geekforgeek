package algorithm.backtracking;

import java.util.ArrayDeque;

public class AllPalindromePartitian {
	
	String string;
	ArrayDeque<String> partitions = new ArrayDeque<>();

	public AllPalindromePartitian(String str){
		this.string = str;
	}
	
	public boolean isPalimdrome(String str){
		StringBuilder br=new StringBuilder(str);
		return br.reverse().toString().equals(str);
		
	}
	
	public void findPalindromePartition(int count,int index){
		if(count == string.length()){
			System.out.println(partitions);
		}else{
			
			if(index <= string.length()){
			
				for(int i=1; i <= string.length(); i++){
					if(index+i <= string.length()){
						System.out.println(" index =" + index + ", index+i" + (index + i));
						if(isPalimdrome(string.substring(index,index+i))){
							partitions.add(string.substring(index,index+i));
							findPalindromePartition(count+i, index+i);
							partitions.removeLast();
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		AllPalindromePartitian allPalindromePartitian=new AllPalindromePartitian("geeks");
		allPalindromePartitian.findPalindromePartition(0, 0);
	}
	
	
	
}
