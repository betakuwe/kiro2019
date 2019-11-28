package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import graph.Graph;

public class Magie {
  private static final boolean DEBUG = false;

  private ArrayList<ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>> magie = new ArrayList<>(); // group, semaine, tournee, fournisseur, qte

  private Graph graph;

  private ArrayList<ArrayList<Integer>> initialState;
  private ArrayList<ArrayList<Integer>> currentState;
  private ArrayList<ArrayList<Integer>> bestState;
  private int d;
  private int u;
  public int H; // nombre de semaines
  public int[][] M; // marchandise M[i][j] == marchandise de sommet i a la semaine j
  public int Q; // taille de camion

  private int i1;
  private int i2;

  private int bestEnergy = Integer.MAX_VALUE;
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
    int sum = 0;
    for(int i = 0; i < energyGroups.length; i++) {
      if(i == i1) {
        sum += measureGroupCost(i1, state);
      } else if(i == i2) {
        sum += measureGroupCost(i2, state);
      } else {
        sum += energyGroups[i];
      }
    }
    return sum;
  }

  private int measureGroupCost(int index, ArrayList<ArrayList<Integer>> state) {
    int cost = 0;
    for(int h = 0; h < H; ++h) {
      int numF = state.get(index).size();
      ArrayList<Integer> g = new ArrayList<>(numF);
      Collections.copy(g, state.get(index));
      int[] Ma = new int[numF]; // march at sommet g.get(f)
      int totalM = 0;
      for (int f = 0; f < numF; ++f) {
        Ma[f] = M[g.get(f)][h];
        totalM += Ma[f];
      }
      int f = d, t = 0;
      while(totalM > 0) {
        int q = Q;
        while (!g.isEmpty()) {
          int minC = Integer.MAX_VALUE;
          int minI = 0;
          for (int i = 0; i < g.size(); ++i) {
            int c = graph.getEdge(f, g.get(i));
            if (c < minC) {
              minC = c;
              minI = i;
            }
          }
          cost += minC;
          if (q <= minC) {
            totalM -= q;
            Ma[minI] -= q;
            q = 0;
          } else {
            totalM -= minC;
            q -= minC;
            Ma[minI] = 0;
          }
          magie.get(index - 1).get(h).get(t).get(t).add(g.get(minI), minC);
        }
        t++;
      }
    }
    return cost;
  }

  private ArrayList<ArrayList<Integer>> neighbour(ArrayList<ArrayList<Integer>> state) { // todo to be defined
    // deep copy
    ArrayList<ArrayList<Integer>> newState = new ArrayList<>(state.size());
    for (int i = 0; i < state.size(); ++i) {
      ArrayList<Integer> l = new ArrayList<>();
      Collections.copy(l, state.get(i));
      newState.add(i, l);
    }

    int x1 = rng.nextInt(newState.size());
    int x2 = rng.nextInt(newState.size() + 1); // might create new group

    // remove from x1
    ArrayList<Integer> gi1 = newState.get(x1);
    int v = gi1.remove(rng.nextInt(gi1.size()));
    if (gi1.isEmpty()) {
      newState.remove(x1);
      i1 = i2 = x2 - 1;
      for (int i = i1; i < energyGroups.length - 1; ++i) {
        energyGroups[i] = energyGroups[i + 1];
      }
    }
    i1 = x1;
    i2 = x2;

    // put in x2
    if (x2 == newState.size()) { // create new group
      ArrayList<Integer> l = new ArrayList<>(4);
      l.add(v);
      newState.add(x2, l);
    } else {
      newState.get(x2).add(v);
    }
    return newState;
  }

  private boolean shouldRestart() { // todo to be defined
    if (avgProb < 0.9) {
      return (double) stepsSinceBest / step > 1E-4;
    }
    return false;
  }

  public Magie(ArrayList<ArrayList<Integer>> initialState, double initTemperature, double coolingRate, int d, int u, int maxG, Graph graph, int H, int[][] M, int Q) {
    this.initialState = initialState;
    this.initTemperature = initTemperature;
    this.coolingRate = coolingRate;
    this.d = d;
    this.u = u;
    this.energyGroups = new int[maxG];
    this.graph = graph;
    this.H = H;
    this.M = M;
    this.Q = Q;
    initialiseEnergyGroup();
  }

  private void initialiseEnergyGroup() {

    for(int i = 1; i <= energyGroups.length; i++) {
      energyGroups[i] = measureGroupCost(i, initialState);
    }
    ArrayList<Integer> soustraiter = initialState.get(0);
    int sum = 0;
    for(Integer i : soustraiter) {
      sum += i;
    }
    energyGroups[0] = sum;
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
