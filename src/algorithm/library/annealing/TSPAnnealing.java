package algorithm.library.annealing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import graph.Edge;
import graph.Graph;

public class TSPAnnealing {

	  private ArrayList<Integer> initialState;
	  private ArrayList<Integer> currentState;

	  private ArrayList<Integer> bestState;
	  private double bestEnergy;
	  private int stepsSinceBest = 0;

	  private double initTemperature;
	  private double coolingRate;
	  private int maxSteps;
	  private Random rng = new Random();
	  private Graph<Edge> g;

	  private double energy(ArrayList<Integer> state) { // todo to be defined
	    int result = 0;
	    int size = state.size() - 1;
	    for(int i = 0; i < size; i++) {
	    	result += g.getEdge(state.get(i), state.get(i + 1)).getCost();
	    }
		return result;
	  }

	  private ArrayList<Integer> neighbour(ArrayList<Integer> state) { // todo to be defined
		  //int numSwaps = rng.nextInt(state.size());
		  Iterator<Integer> iter = state.iterator();
		  ArrayList<Integer> copy = new ArrayList<>();
		  while(iter.hasNext()) {
			  copy.add(iter.next());
		  }
		  copy.remove(copy.get(copy.size() - 1));
		  int i = rng.nextInt(copy.size());
		  int j = rng.nextInt(copy.size());
		  Collections.swap(copy, i, j);
		  
		  copy.add(copy.size(),copy.get(0));
		  return copy;
	  }

	  private boolean shouldRestart(double currentEnergy) { // todo to be defined
	    return stepsSinceBest > 100;
	  }

	  public TSPAnnealing(Graph<Edge> g, ArrayList<Integer> initialState, int maxSteps, double initTemperature, double coolingRate) {
	    this.g = g;
		this.initialState = initialState;
	    this.maxSteps = maxSteps;
	    this.initTemperature = initTemperature;
	    this.coolingRate = coolingRate;
	  }

	  public void run() {
	    bestState = currentState = initialState;
	    double temperature = initTemperature;
	    for (int step = 0; step < maxSteps; ++step) {
	      temperature *= coolingRate;
	      ArrayList<Integer> neighbourState = neighbour(currentState);
	      double currentEnergy = energy(currentState);
	      System.out.println(currentEnergy);
	      for(int i = 0; i < bestState.size(); i++) {
	    	  System.out.printf("%d ", bestState.get(i));
	      }
	      updateBestState(currentEnergy);
	      if (shouldRestart(currentEnergy)) {
	        currentState = bestState;
	      } else if (probability(energy(currentState), energy(neighbourState), temperature)
	          >= rng.nextDouble()) {
	        currentState = neighbourState;
	      }
	    }
	  }

	  public ArrayList<Integer> result() {
	    return bestState;
	  }

	  private void updateBestState(double currentEnergy) {
	    if (currentEnergy > bestEnergy) {
	      stepsSinceBest++;
	      return;
	    } else if (currentEnergy < bestEnergy) {
	      bestState = currentState;
	      bestEnergy = currentEnergy;
	    }
	    stepsSinceBest = 0;
	  }

	  private double probability(double currentEnergy, double neighbourEnergy, double temperature) {
	    if (currentEnergy >= neighbourEnergy) { // neighbour is better, move to it
	      return 1;
	    } else { // neighbour is worse, some chance of moving to it
	      return Math.exp((currentEnergy - neighbourEnergy) / temperature);
	    }
	  }
}
