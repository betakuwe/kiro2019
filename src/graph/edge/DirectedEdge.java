package graph.edge;

import graph.vertex.DirectedVertex;

public class DirectedEdge extends Edge {

  public DirectedEdge(DirectedVertex from, DirectedVertex to, long cost) {
    super(from, to, cost);
  }

  @Override
  public boolean equals(Object obj) {
    DirectedEdge o;
    return obj instanceof DirectedEdge
        && from.equals((o = (DirectedEdge) obj).from)
        && to.equals(o.to);
  }
}
