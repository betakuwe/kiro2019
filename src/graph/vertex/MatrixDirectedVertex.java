package graph.vertex;

import graph.edge.DirectedEdge;
import graph.edge.Edge;
import java.util.ArrayList;
import java.util.Collection;

// matrice d'adjacence
public class MatrixDirectedVertex extends DirectedVertex {
  private ArrayList<DirectedEdge> outs;
  private ArrayList<DirectedEdge> ins;

  public MatrixDirectedVertex(int numVertices) {
    outs = new ArrayList<>(numVertices);
    ins = new ArrayList<>(numVertices);
  }

  @Override // O(1)
  public void addOut(DirectedEdge edge) {
    outs.add(edge.getTo().getId(), edge);
  }

  @Override // O(1)
  public void addIn(DirectedEdge edge) {
    outs.add(edge.getFrom().getId(), edge);
  }

  @Override // O(1)
  public DirectedEdge getOutEdge(int to) {
    return outs.get(to);
  }

  @Override // O(1)
  public DirectedEdge getInEdge(int from) {
    return outs.get(from);
  }

  @Override
  public Collection<? extends Edge> getOuts() {
    return outs;
  }
}
