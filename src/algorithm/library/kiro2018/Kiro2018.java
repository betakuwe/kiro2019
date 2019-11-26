package algorithm.library.kiro2018;

import graph.Edge;
import graph.Graph;
import java.util.ArrayList;
import java.util.HashMap;

public class Kiro2018 {
  Graph<Edge> g;
  boolean[] isD;

  public Kiro2018(Graph<Edge> g) {
  }

  private long totalWeight(Graph<Edge> g) {
    long total = 0;
    for (int i = 0; i < g.numVertices(); ++i) {
      for (int j = 0; j < g.numVertices(); ++j) {
        Edge e = g.getEdge(i, j);
        if (e != null) {
          total += e.getCost();
        }
      }
    }
    return total;
  }

}
