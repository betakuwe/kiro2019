package algorithm.library;

import java.util.ArrayList;
import java.util.Collections;

import algorithm.library.annealing.TSPAnnealing;
import graph.Edge;
import graph.Graph;

public class AlgoTSP {

	private Graph<Edge> graph;
	private ArrayList<Integer> bestResult;
	private ArrayList<Integer> initialState;
	private static int maxSteps = 1500;
	private static int stepsToRestart = 100000;
	private static double initTemperature = 5000;
	private static double coolingRate = 1 - Math.exp(-15);

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
	  }
}
