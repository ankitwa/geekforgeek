package algorithm.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Graph{
	
	int[][] matrix;
	int[] vertex;
	int[] color;
	int noOfVertex;
	
	public Graph(int[][] matrix){
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
	
	public Graph(int noOfVertex,int noOfvertex){
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




public class MColoringProblem {
	
	List<Integer> visited = new ArrayList<Integer>();
	Set<Integer> colors=new HashSet<Integer>();
	Graph graph;
	
	public MColoringProblem(int[][] matrix,int col){
		graph = new Graph(matrix);
		for(int i=1; i <= col; i++){
			colors.add(i);
		}
	}
	
	
	public Set<Integer> getAdjacentColors(Integer vertex){
		Set<Integer> adjacentColors = new HashSet<>();
		Set<Integer> adjacentVertices = graph.getAdjacentVertex(vertex);
		for(Integer ver : adjacentVertices){
			if(graph.getColorCode(ver) != -1){
				adjacentColors.add(graph.getColorCode(ver));
			}
		}
		//System.out.println("Returning Adjacent Colour for vertex " + vertex + " is " + adjacentColors);
		return adjacentColors;
	}
	
	
	public void colorThisVertex(Integer vertex){
		if(this.visited.size() == graph.getVerticesCount()){
		//	System.out.println("All Vertices Colored ");
			graph.printColoredArray();
		}
		//System.out.println("Coloring for Vertex =" + vertex);
		if(this.visited.contains(vertex)){
		//	System.out.println("Coloring for Vertex is already Done " + vertex);
			return;
		}
		
		Set<Integer> adjacenColors = getAdjacentColors(vertex);
		Set<Integer> avalableColors = new HashSet();
		for(Integer col : colors){
			if(!adjacenColors.contains(col)){
				avalableColors.add(col);
			}
		}
		//System.out.println("Availabe color for vertex is " + avalableColors);
		if(avalableColors.isEmpty()){
			return;
		}else{
			
			for(Integer code : avalableColors){
			//	System.out.println("Color Selected For Vertex =" + vertex + " , is " + code);
				graph.setColorCodeForVertex(vertex, code);
				this.visited.add(vertex);
			//	System.out.println("color vertex count =" + visited);
				
				if(this.visited.size() == graph.getVerticesCount()){
				//	System.out.println("All Vertices Colored ");
					graph.printColoredArray();
				}else{
					for(Integer ver : graph.getAdjacentVertex(vertex)){
						colorThisVertex(ver);
					}
				}
			//	System.out.println("Backtracking stared for vertex " + vertex);
				graph.setColorCodeForVertex(vertex, -1);
				this.visited.remove(vertex);
				
			}
		}
		
		
		
	}
	
	public static void main(String[] args){
		
		 int graph[][] = {{0, 1, 1, 1},
		            {1, 0, 1, 0},
		            {1, 1, 0, 1},
		            {1, 0, 1, 0},
		        };
		 int m = 3; // Number of colors
		 MColoringProblem mColoringProblem=new MColoringProblem(graph, m);
		 mColoringProblem.colorThisVertex(0);
	}
	
	
	
	

}
