package algorithm.library;

import java.util.ArrayList;
import java.util.Collections;

import algorithm.library.annealing.TSPAnnealing;
import graph.Edge;
import graph.Graph;

public class AlgoTSP {

	private ArrayList<Integer> bestResult;
	private ArrayList<Integer> initialState;
	private static int maxSteps = 1500;
	private static int stepsToRestart = 100000;
	private static double initTemperature = 5000;
	private static double coolingRate = 1 - Math.exp(-15);

	  public ArrayList<Integer> getResult() {
		return bestResult;
	  }


	  
	  public ArrayList<Integer> setInitialState() {
		  // todo
		  ArrayList<Integer> ans = new ArrayList<Integer>();

		  Collections.shuffle(ans);
		  ans.add(ans.size(), ans.get(0));
		  return ans;
	  }

	  public void run() {
	  }
}
