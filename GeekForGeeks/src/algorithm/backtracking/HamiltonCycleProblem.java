package algorithm.backtracking;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

class GraphH{
	
	int[][] matrix;
	int[] vertex;
	int[] color;
	int noOfVertex;
	
	public GraphH(int[][] matrix){
		this.matrix = matrix;
		this.color = new int[matrix.length];
		this.noOfVertex = matrix.length;
		this.vertex = new int[matrix.length];
		for(int i=0; i < color.length ; i++){
			   color[i] = -1;
		}
		for(int i=0; i < vertex.length ; i++){
			vertex[i] = i;
		}
	}
	
	public GraphH(int noOfVertex,int noOfvertex){
	   this.matrix = new int[noOfVertex][noOfVertex];
	   this.color = new int[noOfvertex];
	   this.vertex=new int[noOfVertex];
	   this.noOfVertex = noOfVertex;
	   for(int i=0; i < color.length ; i++){
		   color[i] = -1;
	   }
	   for(int i=0; i < vertex.length ; i++){
			vertex[i] = i;
	   }
	}
	
	public void addEdge(int fromVertex,int toVertex){
		matrix[fromVertex][toVertex] = matrix[toVertex][fromVertex] = 1;
		vertex[fromVertex] = fromVertex;
		vertex[toVertex] = toVertex;
	}
	
	public boolean isEdge(int fromVertex,int toVertex){
		return matrix[fromVertex][toVertex] == 1;
	}
	
	public int getColorCode(int vertex){
		return color[vertex];
	}
	
	public void setColorCodeForVertex(int vertex,int colorCode){
		color[vertex] = colorCode;
	}
	
	public Set<Integer> getAdjacentVertex(int vertex){
		int[] arr = matrix[vertex];
		Set<Integer> nums = new HashSet<>();
		for(int i=0; i < arr.length ; i++){
			if(i != vertex && arr[i] == 1){
				nums.add(i);
			}
		}
	//	System.out.println("Adjacent vertexs for vertex" + vertex + " , is " + nums);
		return nums;
	}
	
	public int getVerticesCount(){
		return vertex.length;
	}
	
	public void printColoredArray(){
		for(int i=0; i < color.length ; i++){
			System.out.print(" " + color[i]);
		}
		System.out.println();
	}
}

public class HamiltonCycleProblem {
	
	GraphH graph;
	Set<Integer> visited= new LinkedHashSet<>();
	int source;
	
	public HamiltonCycleProblem(int[][] matrix){
		graph = new GraphH(matrix);
	}
	
	public void findHamiltonCycle(Integer vertex){
		
		if(source == vertex.intValue() && this.visited.size() == graph.getVerticesCount()){
			System.out.println("Hamilton Graph Found ==" + visited);
			return;
		}
		
		if(this.visited.contains(vertex)){
		//	System.out.println("vertex already visited =" + vertex);
			return;
		}
		
		this.visited.add(vertex);
		Set<Integer> adjNodes = graph.getAdjacentVertex(vertex);
		if(!adjNodes.isEmpty()){
			
			for(Integer node : adjNodes){
				findHamiltonCycle(node);
			}
			this.visited.remove(vertex);
		}
	}
	
	public static void main(String[] args){
		int graph1[][] = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
               };
		HamiltonCycleProblem hamiltonCycleProblem=new HamiltonCycleProblem(graph1);
		hamiltonCycleProblem.findHamiltonCycle(0);
	}
	
	
	
	

}
