package graph.vertex;

import graph.edge.DirectedEdge;

public abstract class DirectedVertex extends Vertex {
  public abstract void addOut(DirectedEdge edge);
  public abstract void addIn(DirectedEdge edge);
  abstract public DirectedEdge getOutEdge(int to);
  abstract public DirectedEdge getInEdge(int from);
}
