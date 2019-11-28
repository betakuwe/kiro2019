package algorithm.library.annealing;

import java.util.Random;

public class Annealing<S> {
  private static final boolean DEBUG = false;

  private S initialState;
  private S currentState;
  private S bestState;

  private double bestEnergy = Double.MAX_VALUE;
  private double currentEnergy;

  private int stepsSinceBest = 0;
  private int step = 0;
  private double avgProb = 1;
  private boolean currentStateChanged = true;

  private final double avgProbLimit = 0.3;
  private final double initTemperature;
  private final double coolingRate;
  private Random rng = new Random();

  private double energy(S state) { // todo to be defined
    return 0;
  }

  private S neighbour(S state) { // todo to be defined
    return null;
  }

  private boolean shouldRestart() { // todo to be defined
    if (avgProb < 0.9) {
      return (double) stepsSinceBest / step > 1E-4;
    }
    return false;
  }

  public Annealing(S initialState, double initTemperature, double coolingRate) {
    this.initialState = initialState;
    this.initTemperature = initTemperature;
    this.coolingRate = coolingRate;
  }

  public void run() {
    bestState = currentState = initialState;
    double temperature = initTemperature;
    for (int step = 0; avgProb >= avgProbLimit; ++step) {
      temperature = temperature(temperature);
      S neighbourState = neighbour(currentState);
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

  private void updateCurrentEnergy(S currentState) {
    if (currentStateChanged) {
      currentEnergy = energy(currentState);
    }
  }

  public S result() {
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
