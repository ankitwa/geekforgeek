package algorithm.backtracking;

import java.util.HashMap;
import java.util.Map;

public class MatchAPatternWithooutRecursion {
	
	Map<Character,String> patternMap=new HashMap<>();
	
	String pattern;
	boolean[] patternVisited;
	
	String sentence;
	boolean[] visitedSentence;
	
	public MatchAPatternWithooutRecursion(String pattern,String sentence){
		this.pattern = pattern;
		this.patternVisited = new boolean[pattern.length()];
		
		this.sentence = sentence;
		this.visitedSentence = new boolean[pattern.length()];
	}
	
	public void findMatch(int senIndex,int pattIndex){
		if(senIndex == sentence.length() && pattIndex == pattern.length()){
			System.out.println("pattern found matching..");
			printOutput();
		}else{
			
			if(pattIndex < pattern.length() && senIndex < sentence.length() && patternMap.get(pattern.charAt(pattIndex)) != null){
				this.patternVisited[pattIndex] = true;
				String str = patternMap.get(pattern.charAt(pattIndex));
				if( senIndex + str.length() <= sentence.length()){
				//	System.out.println("Found Key K=" + pattern.charAt(pattIndex) + " , value=" +str);
				
				//
				//	System.out.println("Comparing in sentence =" + sentence.substring(senIndex,senIndex + str.length()) );
				
					if( senIndex + str.length()-1 < sentence.length() && sentence.substring(senIndex,senIndex + str.length()).equals(str)){
					//	System.out.println("Found string in sentence " + sentence.substring(senIndex,senIndex + str.length()));
					//	System.out.println("Found String = senIndex=" + senIndex + "," + pattIndex);
					//	System.out.println("Found String = next senIndex=" + (senIndex + str.length()) + " , patIndex" + (pattIndex+1) );
						findMatch(senIndex + str.length(), pattIndex + 1);
					}
				}
			}else{
				for(int i=1; i <= sentence.length(); i++){
					if(senIndex + i < sentence.length() && (pattIndex + 1) < pattern.length()){
						//System.out.println("Putting in Map K=" + pattern.charAt(pattIndex) + " , value=" + sentence.substring(senIndex,senIndex + i));
						patternMap.put(pattern.charAt(pattIndex), sentence.substring(senIndex,senIndex + i));
						findMatch(senIndex,pattIndex);
						patternMap.remove(pattern.charAt(pattIndex));
					}
				}
			}
		}
	}
	
	public void printOutput() {
		for(Character ch : patternMap.keySet()){
			System.out.println(ch + "-->" + patternMap.get(ch));
		}
	}
	
	public static void main(String[] args) { 
		MatchAPatternWithooutRecursion matchAPatternWithooutRecursion = new MatchAPatternWithooutRecursion("gg", "GeeksforGeeks");
		matchAPatternWithooutRecursion.findMatch(0,0);
		//matchAPatternWithooutRecursion.printOutput();
	}
	

}
