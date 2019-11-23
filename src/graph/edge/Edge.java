package graph.edge;

import graph.vertex.Vertex;

public abstract class Edge {
  protected Vertex from;
  protected Vertex to;
  private long cost;

  public Vertex getFrom() {
    return from;
  }

  public Vertex getTo(Vertex thisVertex) {
    return to;
  }

  public Vertex getTo() {
    return to;
  }

  public long getCost() {
    return cost;
  }

  public Edge(Vertex from, Vertex to, long cost) {
    this.from = from;
    this.to = to;
    this.cost = cost;
  }

}
