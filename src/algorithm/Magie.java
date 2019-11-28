package algorithm;

import java.util.ArrayList;
import java.util.Random;

public class Magie {
  private static final boolean DEBUG = false;
  
  private ArrayList<ArrayList<Integer>> initialState;
  private ArrayList<ArrayList<Integer>> currentState;
  private ArrayList<ArrayList<Integer>> bestState;
  private int d;
  private int u;

  private int i1;
  private int i2;

  private int bestEnergy = Double.MAX_VALUE;
  private int currentEnergy;
  private int[] energyGroups;

  private int stepsSinceBest = 0;
  private int step = 0;
  private double avgProb = 1;
  private boolean currentStateChanged = true;

  private final double avgProbLimit = 0.3;
  private final double initTemperature;
  private final double coolingRate;
  private Random rng = new Random();

  private int energy(ArrayList<ArrayList<Integer>> state) { // todo to be defined
	  int sum = 0.0;
	  int g1 = i1 + 1;
	  int g2 = i2 + 2;
	  for(int i = 0; i < energyGroups.length; i++) {
		  if(i == g1) {
			  sum += dynamicProg(g1);
		  } else if(i == g2) {
			  sum += dynamicProg(g2);
		  } else {
			  sum += energyGroups[i];
		  }
	  }
	  return sum;
  }

  private int dynamicProg(int grp) {
	  ArrayList<Integer> listOfGroups = currentState.get(grp);
	  int size = listOfGroups.size() + 2;
	  int[] dp = new int[size];
	  dp[0] = 0.0;
	  for(int i = 1; i < size - 1; i++) {
		  dp[i] = Double.MAX_VALUE;
	  }
	  for(int i = 1; i < listOfGroups.size(); i++) {
		  
	  }
	  return 0.0;
}

  private ArrayList<ArrayList<Integer>> neighbour(ArrayList<ArrayList<Integer>> state) { // todo to be defined
    int x1 = rng.nextInt(state.size());
    int x2 = rng.nextInt(state.size() + 1); // might create new group

    // remove from x1
    ArrayList<Integer> gi1 = state.get(x1);
    int v = gi1.remove(rng.nextInt(gi1.size()));
    if (gi1.isEmpty()) {
      state.remove(x1);
      i1 = i2 = x2 - 1;
      for (int i = i1; i < energyGroups.length - 1; ++i) {
        energyGroups[i] = energyGroups[i + 1];
      }
    }

    // put in x2
    if (x2 == state.size()) { // create new group
      ArrayList<Integer> l = new ArrayList<>(4);
      l.add(v);
      state.add(x2, l);
    } else {
      state.get(x2).add(v);
    }


  }

  private boolean shouldRestart() { // todo to be defined
    if (avgProb < 0.9) {
      return (double) stepsSinceBest / step > 1E-4;
    }
    return false;
  }

  public Magie(ArrayList<ArrayList<Integer>> initialState, double initTemperature, double coolingRate, int d, int u, int maxG) {
    this.initialState = initialState;
    this.initTemperature = initTemperature;
    this.coolingRate = coolingRate;
    this.d = d;
    this.u = u;
    this.energyGroups = new int[maxG];
    initialiseEnergyGroup();
  }

  private void initialiseEnergyGroup() {
	for(int i = 1; i <= energyGroups.length; i++) {
		energyGroups[i] = dynamicProg(i);
	}
  }

public void run() {
    bestState = currentState = initialState;
    double temperature = initTemperature;
    for (int step = 0; avgProb >= avgProbLimit; ++step) {
      temperature = temperature(temperature);
      ArrayList<ArrayList<Integer>> neighbourState = neighbour(currentState);
      updateCurrentEnergy(currentState);
      updateBestState(currentEnergy);
      if (shouldRestart()) {
        currentState = bestState;
        currentStateChanged = true;
      } else if (probability(energy(currentState), energy(neighbourState), temperature)
          >= rng.nextDouble()) {
        currentState = neighbourState;
        currentStateChanged = true;
      } else {
        currentStateChanged = false;
      }
    }
  }

  private double temperature(double temperature) {
    return temperature * coolingRate;
  }

  private void updateCurrentEnergy(ArrayList<ArrayList<Integer>> currentState) {
    if (currentStateChanged) {
      currentEnergy = energy(currentState);
    }
  }

  public ArrayList<ArrayList<Integer>> result() {
    return bestState;
  }

  private void updateBestState(int currentEnergy) {
    if (currentEnergy > bestEnergy) {
      stepsSinceBest++;
      return;
    } else if (currentEnergy < bestEnergy) {
      bestState = currentState;
      bestEnergy = currentEnergy;
    }
    stepsSinceBest = 0;
  }

  private double probability(int currentEnergy, int neighbourEnergy, double temperature) {
    if (currentEnergy >= neighbourEnergy) { // neighbour is better, move to it
      return 1;
    } else { // neighbour is worse, some chance of moving to it
      return Math.exp((currentEnergy - neighbourEnergy) / temperature);
    }
  }

  private void log(String s) {

  }
}
