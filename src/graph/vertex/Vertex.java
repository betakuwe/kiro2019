package graph.vertex;

import graph.edge.Edge;
import java.util.Collection;

public abstract class Vertex {
  private static int numVertices = 0;
  private final int id;

  public int getId() {
    return id;
  }

  public Vertex() {
    id = numVertices++;
  }

  @Override
  public String toString() {
    return String.valueOf(id);
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Vertex && this.id == ((Vertex) obj).id;
  }

  public abstract Collection<? extends Edge> getOuts();
}
