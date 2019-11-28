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

  private double bestEnergy = Double.MAX_VALUE;
  private double currentEnergy;
  private double[] energyGroups;

  private int stepsSinceBest = 0;
  private int step = 0;
  private double avgProb = 1;
  private boolean currentStateChanged = true;

  private final double avgProbLimit = 0.3;
  private final double initTemperature;
  private final double coolingRate;
  private Random rng = new Random();

  private double energy(int g1, int g2) { // todo to be defined
	  double sum = 0.0;
	  for(int i = 1; i <= energyGroups.length, i++) {
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

  private double dynamicProg(int grp) {
	return 0;
}

  private ArrayList<ArrayList<Integer>> neighbour(ArrayList<ArrayList<Integer>> state) { // todo to be defined
    return null;
  }

  private boolean shouldRestart() { // todo to be defined
    if (avgProb < 0.9) {
      return (double) stepsSinceBest / step > 1E-4;
    }
    return false;
  }

  public Magie(ArrayList<ArrayList<Integer>> initialState, double initTemperature, double coolingRate, int d, int u) {
    this.initialState = initialState;
    this.initTemperature = initTemperature;
    this.coolingRate = coolingRate;
    this.d = d;
    this.u = u;
    initialiseEnergyGroup();
  }

  private void initialiseEnergyGroup() {
	for(int i = 0; i < initialState.size(); i++) {
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

  private void log(String s) {

  }
}
