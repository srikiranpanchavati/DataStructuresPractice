import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Vertex {
	public String data;
	public Edge headOfEdgeList;

	public Vertex(String data) {
		this.data = data;
		this.headOfEdgeList = null;
	}

	public Edge addEdge(Vertex vertex) {
		Edge toAdd = new Edge(vertex);
		if (this.headOfEdgeList != null) {
			toAdd.next = this.headOfEdgeList;
		}
		return toAdd;
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
	public HashMap<String, Vertex> vertexMap;

	public Graph(String[] input) {
		vertexMap = new HashMap<>();
		for (int i = 0; i < input.length; i++) {
			String[] split = input[i].split(":");
			if (!vertexMap.containsKey(split[0])) {
				vertexMap.put(split[0], new Vertex(split[0]));
			}
			Vertex vertex = vertexMap.get(split[0]);
			vertex.headOfEdgeList = vertex.addEdge(new Vertex(split[1]));
		}
	}

	public static void main(String ar[]) {
		String[] input = { "a:b", "b:c", "a:c", "b:d", "c:d" };
		Graph graph = new Graph(input);
		for (Map.Entry<String, Vertex> entry : graph.vertexMap.entrySet()) {
			System.out.print(entry.getKey() + "\t|\t");
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