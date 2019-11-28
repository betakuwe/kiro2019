package algorithm.library.annealing;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import graph.Edge;
import graph.Graph;

public class TSPAnnealing {
	private static final boolean DEBUG = false;

	private ArrayList<Integer> initialState;
	private ArrayList<Integer> currentState;

	private ArrayList<Integer> bestState;
	private double currentEnergy;
	private double bestEnergy = Double.MAX_VALUE;
	private int step = 0;
	private int stepsSinceBest = 0;
	private double averageProbability = 1;
	private boolean currentStateChanged = true;

	private double initTemperature;
	private double coolingRate;
	private int maxSteps;
	private int stepsToRestart;
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
	  if (averageProbability < 0.9) {
			return (double) stepsSinceBest / step > 1E-4;
		}
	  	return false;
	}

	public TSPAnnealing(Graph<Edge> g, ArrayList<Integer> initialState, int maxSteps, double initTemperature, double coolingRate, int stepsToRestart) {
		this.g = g;
		this.initialState = initialState;
		this.maxSteps = maxSteps;
		this.initTemperature = initTemperature;
		this.coolingRate = coolingRate;
		this.stepsToRestart = stepsToRestart;
	}

	public void run() {
		bestState = currentState = initialState;
		double temperature = initTemperature;
		for (step = 0; /*step < maxSteps*/ averageProbability > 0.3 ; ++step) {
		  if (step % 1000 == 0) {
				System.out.println("step: " + step + ", temperature: " + temperature);
				System.out.printf("current state: %s", currentState.toString());
			}

			temperature = temperature(temperature);
			ArrayList<Integer> neighbourState = neighbour(currentState);

			if (currentStateChanged) {
				currentEnergy = energy(currentState);
			}

			if (step % 1000 == 0) {
				System.out.println(", current energy: " + currentEnergy);
			}

			updateBestState(currentEnergy);

			if (step % 1000 == 0) {
				System.out.printf("best state: %s", bestState.toString());
				System.out.println(", best energy: " + bestEnergy);
			}

			if (shouldRestart(currentEnergy)) {
				currentState = bestState;
				currentStateChanged = true;
			} else if (probability(energy(currentState), energy(neighbourState), temperature)
					>= rng.nextDouble()) {
				currentState = neighbourState;
				currentStateChanged = true;
			} else {
				currentStateChanged = false;
			}

			if (step % 1000 == 0) {
				System.out.println();
			}
		}
	}

	private double temperature(double temperature) {
//		double result = temperature - coolingRate;
//		return result <= 0 ? 0 : result;
    return temperature * coolingRate;
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
		double diff = currentEnergy - neighbourEnergy;
		if (step % 1000 == 0) {
			System.out.print("Current energy - neighbour energy == " + diff);
		}
		if (diff >= 0) { // neighbour is better, move to it
			return 1;
		} else { // neighbour is worse, some chance of moving to it
			double prob = Math.exp(diff / temperature);
			updateAverageProb(prob);
			if (step % 1000 == 0) {
				System.out.println(", probability: " + prob + ", avg prob: " + averageProbability);
			}
			return prob;
		}
	}

	private void updateAverageProb(double prob) {
	  averageProbability = (averageProbability * step + prob) / (step + 1);
	}
}
