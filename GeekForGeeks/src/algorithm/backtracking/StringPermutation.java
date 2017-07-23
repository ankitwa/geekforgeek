package algorithm.backtracking;

import java.util.logging.Logger;

/**
 * Print All Possible combination of string using backtracking.
 * @author ankit
 *
 */
public class StringPermutation {

	static Logger logger = Logger.getLogger("StringPermutation");

	private static void swap(StringBuilder str,int left, int right){
	//	logger.info("StringBuild = " + str + ", left=" + left + " , right=" + right );

		if(left >= right){
			return;
		}

	//	logger.info("exchanging = [" + str.charAt(left) + "," + str.charAt(right) + "]");

		char tmp = str.charAt(left);
		str.setCharAt(left,str.charAt(right));
		str.setCharAt(right,tmp);
	}


	private static void permute(StringBuilder str,int left,int right){
		if(left == right){ // return if left and right are equal.
			System.out.println(str);
			return;
		}else{
			for(int i=left; i <= right; i++){ // swap through one by one rest all element
				swap(str,left,i);
				permute(str,left+1,right);
				swap(str,left,i);
			}
		}
	}

	public static void main(String[] arg){
		permute(new StringBuilder("abcd"),0,3);
	}



}
