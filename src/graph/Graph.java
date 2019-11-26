package graph;

import java.util.ArrayList;
import java.util.function.Function;

public class Graph<E> {
  private int numVertices;
  private ArrayList<ArrayList<E>> edges;

  public Graph(int numVertices) {
    this.numVertices = numVertices;
    edges = new ArrayList<>(numVertices);
    for (int i = 0; i < numVertices; ++i) {
      ArrayList<E> list = new ArrayList<>(numVertices);
      edges.add(i, list);
      for (int j = 0; j < numVertices; ++j) {
        list.add(j, null);
      }
    }
  }

  public Graph() {
    this(0);
  }

  public void addVertex() {
    ArrayList<E> list = new ArrayList<>();
    edges.add(numVertices, list);
    for (int i = 0; i < numVertices; ++i) {
      edges.get(i).add(numVertices, null);
      list.add(i, null);
    }
    numVertices++;
  }

  public void addVertices(int n) {
    for (int i = 0; i < n; ++i) addVertex();
  }

  public void deleteVertex(int v) {
    for (int i = 0; i < numVertices; ++i) {
      edges.get(v).add(i, null);
      edges.get(i).add(v, null);
    }
  }

  public int numVertices() {
    return numVertices;
  }

  public E getEdge(int v1, int v2) {
    return edges.get(v1).get(v2);
  }

  public ArrayList<ArrayList<E>> getAllEdges() {
    return edges;
  }

  // For directed graphs

  public void setDirectedEdge(int v1, int v2, E e) {
    edges.get(v1).add(v2, e);
  }

  public boolean haveDirectedEdge(int v1, int v2) {
    return edges.get(v1).get(v2) != null;
  }

  public void mapDirectedEdge(int v1, int v2, Function<E, E> f) {
    edges.get(v1).add(v2, f.apply(edges.get(v1).get(v2)));
  }

  public ArrayList<E> getOutEdges(int v) {
    return edges.get(v);
  }

  public ArrayList<E> getInEdges(int v) {
    ArrayList<E> inEdges = new ArrayList<>();
    for (int i = 0; i < numVertices; ++i) {
      E e;
      if ((e = edges.get(i).get(v)) != null) {
        inEdges.add(e);
      }
    }
    return inEdges;
  }

  // For undirected graphs

  public void setUndirectedEdge(int v1, int v2, E e) {
    setDirectedEdge(v1, v2, e);
    setDirectedEdge(v2, v1, e);
  }

  public boolean haveUndirectedEdge(int v1, int v2) {
    return haveDirectedEdge(v1, v2) && haveDirectedEdge(v2, v1);
  }

  public void mapUndirectedEdge(int v1, int v2, Function<E, E> f) {
    E result = f.apply(edges.get(v1).get(v2));
    edges.get(v1).add(v2, result);
    edges.get(v2).add(v1, result);
  }

  public ArrayList<E> getEdges(int v) {
    return getOutEdges(v);
  }
}
