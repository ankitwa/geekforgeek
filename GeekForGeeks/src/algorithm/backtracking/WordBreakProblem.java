package algorithm.backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class WordBreakProblem {
	
	String[] dict=new String[]{"i", "like", "sam", "sung","samsung","mobile","ice", 
			  "cream", "icecream", "man", "go", "mango","and"};

	String sentence="ilikeicecreamandmango";//"ilikesamsungmobile";
	
	ArrayDeque<String> solution = new ArrayDeque<>();
	
	
	boolean visited[]=new boolean[sentence.length()];
	
	public void resetVisitedFor(int index, String word,boolean val){
		for(int i=index; i < index + word.length() ; i++){
			visited[i]=val;
		}
	}
	
	public void printVisited(){
		for(int i=0; i < visited.length ; i++){
			System.out.println(" i =" + i + " , visited =" + visited[i]);
		}
	}
	
	public int getAvailableIndexInString(String word){
		//System.out.println("checking for word =" + word);
		//printVisited();
		for(int i=0; i < visited.length && ( i + word.length() <= visited.length) ; i++){
			//System.out.println("checking at i=" + i + " , comparing substring " + sentence.substring(i,i+word.length()));
			if(visited[i] == false){
				if((i + word.length() <= visited.length) && sentence.substring(i,i+word.length()).equals(word) )
				{
					System.out.println("Found Available Position");
					return i;
				}
				return -1;
			}
		}
		return -1;
	}
	
	
	public void findSentence(int count){
		//System.out.println("count =" + count + " , sent size" +  sentence.length());
		if(sentence.length() == count){
			System.out.println("FindSolution");
			System.out.println(solution);
		}else{
			for(int i=0; i < dict.length ; i++){
				int index =getAvailableIndexInString(dict[i]);
				
				if(index != -1){
					solution.addLast(dict[i]);
					resetVisitedFor(index, dict[i], true);
					findSentence(count+ dict[i].length());
					solution.removeLast();
					resetVisitedFor(index, dict[i],false);
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		WordBreakProblem wordBreakProblem = new WordBreakProblem();
		wordBreakProblem.findSentence(0);
	}
}
