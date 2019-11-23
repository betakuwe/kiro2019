package graph.vertex;

import graph.edge.Edge;
import graph.edge.NonDirectedEdge;
import java.util.Collection;
import java.util.LinkedList;

public class LinkedNonDirectedVertex extends NonDirectedVertex {
  private LinkedList<NonDirectedEdge> edges;

  @Override
  public void addEdge(NonDirectedEdge edge) {
    edges.add(edge);
  }

  @Override
  public NonDirectedEdge getEdge(int otherVertexId) {
    for (NonDirectedEdge e : edges) {
      if (e.getTo(this).getId() == otherVertexId) {
        return e;
      }
    }
    return null;
  }

  @Override
  public Collection<? extends Edge> getOuts() {
    return edges;
  }
}
