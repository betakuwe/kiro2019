package algorithm;

import graph.Graph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Kiro2019 {
  // annealing
  private double initTemperature = 5000;
  private double coolingRate = 1 - Math.exp(-15);

  // raw data
  public Graph g; // graphe oriente
  public int F; // nombre de fournisseurs
  public int H; // nombre de semaines
  public int Q; // taille de camion
  public int[] S; // couts de sous traitance, S[i] == cout de sous traitance du sommet i
  public int[][] M; // marchandise M[i][j] == marchandise de sommet i a la semaine j
  public int d; // l'index du depot
  public int u; // l'index de l'usine
  public ArrayList<ArrayList<Integer>> result;

  // final result


  // tools
  private Random rng = new Random();

  public void run() {
    // set initial state
    int maxG = F + 1;
    int numG = rng.nextInt(maxG);
    ArrayList<ArrayList<Integer>> is = new ArrayList<>(maxG); // index 0 == sous traite, index i == group i - 1
    LinkedList<Integer> bag = new LinkedList<>();
    for (int i = 0; i < numG; ++i) {
      is.add(new ArrayList<>(4));
      if (i == 0) continue; // don't add sous traite into the bag
      for (int j = 0; j < 4; ++j) {
        bag.add(i);
      }
    }

    // put fournisseurs in the groups at random
    Collections.shuffle(bag);
    for (int i = 0; i < F; ++i) {
      if (rng.nextDouble() < (double) 1 / numG) { // sous traite
        is.get(0).add(i);
      } else {
        is.get(bag.removeFirst()).add(i);
      }
    }


    // la magie
    Magie m = new Magie(is, initTemperature, coolingRate, d, u, maxG, g, H, M, Q);
    m.run();
    this.result = m.result();
  }
  
  public ArrayList<ArrayList<Integer>> getResult() {
	  return this.result;
  }
}
