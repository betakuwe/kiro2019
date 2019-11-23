package graph.edge;

import graph.vertex.NonDirectedVertex;
import graph.vertex.Vertex;

public class NonDirectedEdge extends Edge {

  public NonDirectedEdge(NonDirectedVertex from, NonDirectedVertex to, long cost) {
    super(from, to, cost);
  }

  @Override
  public NonDirectedVertex getTo(Vertex vertex) {
    if (to.equals(vertex)) {
      return (NonDirectedVertex) from;
    } else if (from.equals(vertex)) {
      return (NonDirectedVertex) to;
    } else {
      return null;
    }
  }

  @Override
  public boolean equals(Object obj) {
    NonDirectedEdge o;
    return obj instanceof NonDirectedEdge
        && ((from.equals((o = (NonDirectedEdge) obj).from) && to.equals(o.to))
            || (from.equals(o.to) && to.equals(o.from)));
  }
}
