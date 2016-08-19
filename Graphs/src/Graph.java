import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Vertex {
	public String data;
	public Edge headOfEdgeList;

	public Vertex(String data) {
		this.data = data;
		this.headOfEdgeList = null;
	}

	public void addEdge(Vertex vertex) {
		Edge toAdd = new Edge(vertex);
		if (this.headOfEdgeList != null) {
			toAdd.next = this.headOfEdgeList;
		}
		this.headOfEdgeList = toAdd;
	}
}

class Edge {
	public Vertex vertex;
	public Edge next;

	public Edge(Vertex vertex) {
		this.vertex = vertex;
		this.next = null;
	}
}

public class Graph {
	public HashMap<String, Vertex> adjacencyList;
	public List<List<Boolean>> adjacencyMatrix;

	public Graph(String[] input) {
		adjacencyList = new HashMap<>();
		for (int i = 0; i < input.length; i++) {
			createVertices(input[i]);			
		}
		for(int i = 0; i < input.length ; i++){
			adjacencyListRepresentation(input[i]);
		}
	}
	
	private void createVertices(String input){
		String[] split = input.split(":");
		if(!adjacencyList.containsKey(split[0])){
			adjacencyList.put(split[0], new Vertex(split[0]));
		}
		if(!adjacencyList.containsKey(split[1])){
			adjacencyList.put(split[1], new Vertex(split[1]));
		}	
	}

	private void adjacencyListRepresentation(String input) {		
		String[] split = input.split(":");
		Vertex source = adjacencyList.get(split[0]);
		Vertex destination = adjacencyList.get(split[1]);
		source.addEdge(destination);
	}

	public static void main(String ar[]) {
		String[] input = { "a:b", "b:c", "a:c", "b:d", "c:d" };
		Graph graph = new Graph(input);
		for (Map.Entry<String, Vertex> entry : graph.adjacencyList.entrySet()) {
			System.out.print(entry.getKey() + "\t| ");
			Vertex vertex = entry.getValue();
			Edge edge = vertex.headOfEdgeList;
			while (edge != null) {
				System.out.print(" --> " + edge.vertex.data);
				edge = edge.next;
			}
			System.out.println();
		}
	}
}