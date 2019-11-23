package graph.edge;

import graph.vertex.DirectedVertex;
import graph.vertex.Vertex;

public class ComplexDirectedEdge extends DirectedEdge {
  private static int numEdges = 0;
  private final int id;

  public ComplexDirectedEdge(DirectedVertex from, DirectedVertex to, long cost) {
    super(from, to, cost);
    id = numEdges++;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof ComplexDirectedEdge && id == ((ComplexDirectedEdge) obj).id;
  }
}
