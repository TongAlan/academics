import java.io.*;
import java.util.*;
/*
 *  COMMENT HEADER:
 *  
 *  Alan Tong
 *  Program 4 - Algorithms
 *  CHOICE: BFS & DFS
 *  
 *  Input File: The input file has to be in the format given, where 0 means no edges and anything else (greater than 0) is the weight
 *  			Use Characters for the Nodes/Vertex
 *  			Separate with no spaces between commas (ex: A,2,2,0,6,3) and no comma at the end
 *  
 *  Output File: The output file prints out the BSF and DFS in the order the nodes were visited
 * 				 Then it also prints an adjacency list with weights specified(weights were not used in this program despite it being coding with it) 
 * 				
 * 
 */


public class Program4 {

	public static void main(String[] args) throws IOException {
		int lines = 0;
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("input.txt"));
		while (reader.readLine() != null) {
			lines++;
		}
		reader.close();
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("output.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		Graph g = new Graph(lines, writer);
		g.addAll();
		
		g.BFS("D");
		g.DFS("D");
		
		
		g.printGraph(g);
		
		writer.close();
		
	}
}

class Graph {
	public ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();
	LinkedList<Edge> Graph[];
	public PrintWriter writer;
	
	
	
	public Graph(int numberOfVertex, PrintWriter writer) {
		this.writer = writer;
		Graph = new LinkedList[numberOfVertex];
		for(int i = 0; i<Graph.length;i++) {
			Graph[i] = new LinkedList<Edge>();
		}
		
		File file = new File("input.txt");
		Scanner s = null;
		
		try {
			s = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String str;
		while(s.hasNextLine()) {
			ArrayList<String> LL = new ArrayList<String>(); 
			str = s.nextLine();
			String[] sArray = str.split(",");
			for(int i = 0; i < sArray.length;i++) {
				LL.add(sArray[i]);
			}
			matrix.add(LL);
		}
		
		for(int i = 0; i<Graph.length;i++) {
			Graph[i] = new LinkedList();
		}
	}
	
	class Edge {
		String vertex;
		int weight;
		public Edge(String vertex, int weight) {
			this.vertex = vertex; 
			this.weight = weight;
		}
	}

	
	public void addEdge(int start, String end, int weight) {
		Graph[start].add(new Edge(end, weight));
	}
	
	
	public void addAll() {
		for(int i = 1; i<matrix.size();i++) {
			for(int j = 1;j<matrix.get(i).size();j++) {		
				if(Integer.parseInt(matrix.get(i).get(j)) == 0) {
					continue;
				}
				ArrayList<String> nameOfVertices = new ArrayList<String>();
				
				for(int n = 0; n < matrix.get(0).size();n++) {
					nameOfVertices.add(matrix.get(0).get(n));
				}
				
				this.addEdge(i, nameOfVertices.get(j), Integer.parseInt(matrix.get(i).get(j)));
			}
		}
	}
	
	public void printGraph(Graph g) {
		writer.write("ADJACENCY LIST WITH WEIGHTS \n");
		for(int n = 1; n< Graph.length; n++) {
			writer.write("["+matrix.get(0).get(n)+"]");
            for(Edge cur: g.Graph[n]){
            	writer.write(" -> "+cur.vertex + " ("+ cur.weight+")");
            }
            writer.write(System.getProperty( "line.separator" ));
		}
	}
	

	public void BFS(String source) {
		ArrayList<String> visited = new ArrayList<String>();
		int start = this.findStartingArray(source);
		LinkedList<String> q = new LinkedList<String>();
		q.add(source);
		visited.add(source);
		writer.write("BFS Nodes In Order: {");
		while(q.size() > 0) {
			source = q.poll();
			writer.write(source + ",");
			Iterator<Edge> i = Graph[start].listIterator();
			while(i.hasNext()) {
				String n = i.next().vertex;
				if(!visited.contains(n)) {
					visited.add(n);
					q.add(n);
				}
			}
			start = findStartingArray(q.peek());
		}
		writer.write("}\n");
	}
	
	
	public void DFS(String source) {
		writer.write("DFS Nodes In Order: {");
		ArrayList<String> visited = new ArrayList<String>();
		Stack<String> s = new Stack<String>();
		s.push(source);
		
		while(!s.isEmpty()) {
			String n = s.peek();
			s.pop();

			if(!visited.contains(n)) {
				writer.write(n + ",");
				visited.add(n);
			}

			Iterator<Edge> i = Graph[this.findStartingArray(n)].listIterator();
			while(i.hasNext()) {
				String vertex = i.next().vertex;
				if(!visited.contains(vertex)) {
					s.push(vertex);
				}
			}
		}	
		writer.write("}\n");
	}
	
	public int findStartingArray(String s) {
		for(int j = 0; j < matrix.size();j++) {
			if(matrix.get(0).get(j).equals(s)) {
				return j;
			}
		}
		return 100;
	}
	
}
