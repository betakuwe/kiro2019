package graph.edge;

import graph.vertex.NonDirectedVertex;

public class ComplexNonDirectedEdge extends NonDirectedEdge {
  private static int numEdges = 0;
  private final int id;

  public ComplexNonDirectedEdge(NonDirectedVertex from,
      NonDirectedVertex to,
      long cost) {
    super(from, to, cost);
    id = numEdges++;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof ComplexNonDirectedEdge && id == ((ComplexNonDirectedEdge) obj).id;
  }
}
