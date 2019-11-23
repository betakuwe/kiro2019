package graph.vertex;

import graph.edge.NonDirectedEdge;
import java.util.ArrayList;
import java.util.Collection;

// matrice d'adjacence
public class MatrixNonDirectedVertex extends NonDirectedVertex {
  private ArrayList<NonDirectedEdge> edges;

  public MatrixNonDirectedVertex(int numVertices) {
    edges = new ArrayList<>(numVertices);
  }

  @Override // O(1)
  public void addEdge(NonDirectedEdge edge) {
    edges.add(edge.getTo(this).getId(), edge);
  }

  @Override // O(1)
  public NonDirectedEdge getEdge(int otherVertexId) {
    return edges.get(otherVertexId);
  }

  @Override
  public Collection<NonDirectedEdge> getOuts() {
    return edges;
  }
}
