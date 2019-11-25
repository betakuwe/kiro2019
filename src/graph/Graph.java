package graph;


import java.util.ArrayList;

public class Graph {
  private int numVertices;
  private ArrayList<ArrayList<Integer>> costs;

  public Graph(int numVertices) {
    this.numVertices = numVertices;
    costs = new ArrayList<>(numVertices);
    for (int i = 0; i < numVertices; ++i) {
      ArrayList<Integer> costsFromThisVertex = new ArrayList<>();
      costs.add(i, costsFromThisVertex);
      for (int j = 0; j < numVertices; ++j) {
        costsFromThisVertex.add(null);
      }
    }
  }

  public int getCost(int v1, int v2) {
    return costs.get(v1).get(v2);
  }

  public void setCost(int v1, int v2, int cost) {
    costs.get(v1).add(v2, cost);
  }

  public void deleteVertex(int v) {
    for (int i = 0; i < numVertices; ++i) {
// todo

    }
  }

  public ArrayList<Integer>
}
