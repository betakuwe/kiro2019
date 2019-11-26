package algorithm.library.annealing;

import graph.Graph;
import java.util.Random;

public class Annealing {

  private Graph initialState;
  private Graph currentState;

  private Graph bestState;
  private double bestEnergy;
  private int stepsSinceBest = 0;

  private int maxSteps;
  private Random rng = new Random();

  private double energy(Graph state) { // todo to be defined
    return 0;
  }

  private Graph neighbour(Graph state) { // todo to be defined
    return null;
  }

  private double temperature(double value) { // todo to be defined
    return value;
  }

  private boolean shouldRestart(double currentEnergy) { // todo to be defined
    return false;
  }

  public Annealing(Graph initialState, int maxSteps) {
    this.initialState = initialState;
    this.maxSteps = maxSteps;
  }

  public void run() {
    bestState = currentState = initialState;
    double temperature;
    for (int step = 0; step < maxSteps; ++step) {
      temperature = temperature((double) maxSteps / (step + 1));
      Graph neighbourState = neighbour(currentState);
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

  public Graph result() {
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
    if (neighbourEnergy <= currentEnergy) { // neighbour is better, move to it
      return 1;
    } else { // neighbour is worse, some chance of moving to it
      return Math.exp((currentEnergy - neighbourEnergy) / temperature);
    }
  }
}
