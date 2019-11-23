package graph;

import graph.vertex.Vertex;
import java.util.ArrayList;

public class Graph {
  private int numVertices;
  private ArrayList<Vertex /* todo what kind of vertex? */> vertices;

  public Graph(int numVertices) {
    this.numVertices = numVertices;
    vertices = new ArrayList<>(numVertices);
    for (int i = 0; i < numVertices; ++i) {
      vertices.add(null /* todo */);
    }
  }

  public void addEdge(int from, int to, long cost) {
    // todo
  }

  public long getCost(int from, int to) {
    return 0; // todo
  }
}
