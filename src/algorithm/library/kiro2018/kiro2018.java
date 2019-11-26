package algorithm.library.kiro2018;

import graph.Edge;
import graph.Graph;

public class kiro2018 {
  Graph<Edge> g;
  Boolean[] isDistributor;

  public kiro2018(Graph<Edge> g) {
    this.g = g;
    isDistributor = new Boolean[g.order()];
  }
}
