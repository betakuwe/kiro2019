package graph.vertex;

import graph.edge.DirectedEdge;
import graph.edge.Edge;
import java.util.Collection;
import java.util.LinkedList;

// liste chaînée
public class LinkedDirectedVertex extends DirectedVertex {
  private LinkedList<DirectedEdge> outs;
  private LinkedList<DirectedEdge> ins;

  @Override //O(1)
  public void addOut(DirectedEdge edge) {
    outs.add(edge);
  }

  @Override // O(1)
  public void addIn(DirectedEdge edge) {
    ins.add(edge);
  }

  @Override // O(n)
  public DirectedEdge getOutEdge(int to) {
    for (DirectedEdge e : outs) {
      if (e.getTo().getId() == to) {
        return e;
      }
    }
    return null;
  }

  @Override // O(n)
  public DirectedEdge getInEdge(int from) {
    for (DirectedEdge e : ins) {
      if (e.getFrom().getId() == from) {
        return e;
      }
    }
    return null;
  }

  @Override
  public Collection<? extends Edge> getOuts() {
    return outs;
  }
}
