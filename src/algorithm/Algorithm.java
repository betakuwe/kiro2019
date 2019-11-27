package algorithm;

import algorithm.library.annealing.Annealing;
import graph.Edge;
import graph.Graph;

// todo write algo
public class Algorithm {

  private Graph<Edge> g;

  public Algorithm(Graph g) {
    this.g = g;
  }

  public void run() {
	  new Annealing(g, 100, 100, 100).run();
  }
}
