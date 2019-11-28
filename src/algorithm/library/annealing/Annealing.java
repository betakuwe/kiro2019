package algorithm.library.annealing;

import java.util.Random;

public class Annealing<S> {
  private static final boolean DEBUG = false;

  private S initialState;
  private S currentState;

  private S bestState;
  private double bestEnergy = Double.MAX_VALUE;
  private int stepsSinceBest = 0;

  private double initTemperature;
  private double coolingRate;
  private int maxSteps;
  private Random rng = new Random();

  private double energy(S state) { // todo to be defined
    return 0;
  }

  private S neighbour(S state) { // todo to be defined
    return null;
  }

  private boolean shouldRestart(double currentEnergy) { // todo to be defined
    return false;
  }

  public Annealing(S initialState, int maxSteps, double initTemperature, double coolingRate) {
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
      S neighbourState = neighbour(currentState);
      double currentEnergy = energy(currentState);
      updateBestState(currentEnergy);
      if (shouldRestart(currentEnergy)) {
        currentState = bestState;
      } else if (probability(energy(currentState), energy(neighbourState), temperature)
          >= rng.nextDouble()) {
        currentState = neighbourState;
      }
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
}
