package algorithm;

import algorithm.library.annealing.Annealing;
import graph.Edge;
import graph.Graph;

// todo write algo
public class Algorithm<S> {

  private Graph<Edge> graph;
  private S bestResult;
  private S initialState;
  private static int maxSteps = 1000;
  private static double initTemperature = 1000;
  private static double coolingRate = 0.7;

  public S getResult() {
	return bestResult;
  }

  public Algorithm(Graph<Edge> graph) {
    this.graph = graph;
    bestResult = null;
    initialState = setInitialState();
  }
  
  public S setInitialState() {
	  // todo
	  return null;
  }

  public void run() {
	  Annealing<S> x = new Annealing<S>(initialState, maxSteps, initTemperature, coolingRate);
	  x.run();
	  bestResult = x.result();
  }
}
