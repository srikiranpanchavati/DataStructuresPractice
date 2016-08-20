import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Vertex {
	public String data;
	public Edge headOfEdgeList;
	public boolean visited;
	public Vertex parent;

	public Vertex(String data) {
		this.data = data;
		this.headOfEdgeList = null;
		this.visited = false;
		this.parent = null;
	}

	public void addEdge(Vertex vertex, int edgeDistance) {
		Edge toAdd = new Edge(vertex, edgeDistance);
		if (this.headOfEdgeList != null) {
			toAdd.next = this.headOfEdgeList;
		}
		this.headOfEdgeList = toAdd;
	}
}

class Edge {
	public Vertex vertex;
	public Edge next;
	public int edgeDistance;

	public Edge(Vertex vertex, int edgeDistance) {
		this.vertex = vertex;
		this.edgeDistance = edgeDistance;
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
		for (int i = 0; i < input.length; i++) {
			adjacencyListRepresentation(input[i]);
		}
	}

	private void createVertices(String input) {
		String[] split = input.split(":");
		if (!adjacencyList.containsKey(split[0])) {
			adjacencyList.put(split[0], new Vertex(split[0]));
		}
		if (!adjacencyList.containsKey(split[1])) {
			adjacencyList.put(split[2], new Vertex(split[2]));
		}
	}

	private void adjacencyListRepresentation(String input) {
		String[] split = input.split(":");
		Vertex source = adjacencyList.get(split[0]);
		Vertex destination = adjacencyList.get(split[2]);
		int edgeDistance = Integer.parseInt(split[1]);
		source.addEdge(destination, edgeDistance);
	}

	public void BFS(String src) {
		Vertex root = null;
		if (this != null && this.adjacencyList != null && this.adjacencyList.containsKey(src)) {
			root = this.adjacencyList.get(src);
		}
		if (root == null) {
			return;
		}
		Queue<Vertex> queue = new LinkedList<>();
		queue.add(root);
		queue.add(null);
		root.visited = true;
		while (!queue.isEmpty()) {
			root = queue.remove();
			if (root == null) {
				System.out.println();
				continue;
			}
			System.out.print(root.data);
			if (root.parent != null) {
				System.out.print(" (" + root.parent.data + ")\t");
			}
			Edge edge = root.headOfEdgeList;
			while (edge != null) {
				if (edge.vertex != null && !edge.vertex.visited) {
					queue.add(edge.vertex);
					edge.vertex.visited = true;
					edge.vertex.parent = root;
				}
				edge = edge.next;
			}
			queue.add(null);
		}
	}

	public static void main(String ar[]) {
		String[] input = { "a:10:a", "a:5:b", "b:5:a", "b:5:c", "c:5:b", "a:11:c", "c:11:a", "b:7:d", "d:7:b", "c:12:d",
				"d:12:c" };
		Graph graph = new Graph(input);

		graph.BFS("a");

		for (Map.Entry<String, Vertex> entry : graph.adjacencyList.entrySet()) {
			System.out.print(entry.getKey() + "\t| --> ");
			Vertex vertex = entry.getValue();
			Edge edge = vertex.headOfEdgeList;
			while (edge != null) {
				System.out.print(edge.vertex.data + " (" + edge.edgeDistance + "), ");
				edge = edge.next;
			}
			System.out.println();
		}
	}
}