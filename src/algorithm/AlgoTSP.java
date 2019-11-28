package algorithm;

import java.util.ArrayList;
import java.util.Collections;

import algorithm.library.annealing.TSPAnnealing;
import graph.Edge;
import graph.Graph;

public class AlgoTSP {

	private Graph<Edge> graph;
	private ArrayList<Integer> bestResult;
	private ArrayList<Integer> initialState;
	private static int maxSteps = 1000000;
	private static double initTemperature = 1500000;
	private static double coolingRate = 0.999999999;

	  public ArrayList<Integer> getResult() {
		return bestResult;
	  }

	  public AlgoTSP(Graph<Edge> graph) {
	    this.graph = graph;
	    initialState = setInitialState();
	    bestResult = null;
	  }
	  
	  public ArrayList<Integer> setInitialState() {
		  // todo
		  int numV = graph.getNumVertices();
		  ArrayList<Integer> ans = new ArrayList<Integer>();
		  for(int i = 0; i < numV; i++) {
			  ans.add(i);
		  }
		  Collections.shuffle(ans);
		  ans.add(ans.size(), ans.get(0));
		  return ans;
	  }

	  public void run() {
		  TSPAnnealing x = new TSPAnnealing(graph, initialState, maxSteps, initTemperature, coolingRate);
		  x.run();
		  bestResult = x.result();
	  }
}
