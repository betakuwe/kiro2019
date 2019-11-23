package graph.vertex;

import graph.edge.NonDirectedEdge;

public abstract class NonDirectedVertex extends Vertex {
  public abstract void addEdge(NonDirectedEdge edge);
  public abstract NonDirectedEdge getEdge(int otherVertexId);
}
