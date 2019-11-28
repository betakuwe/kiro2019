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
  private Graph g; // graphe oriente
  private int F; // nombre de fournisseurs
  private int H; // nombre de semaines
  private int Q; // taille de camion
  private int[] S; // couts de sous traitance, S[i] == cout de sous traitance du sommet i
  private int[][] M; // marchandise M[i][j] == marchandise de sommet i a la semaine j
  private int d; // l'index du depot
  private int u; // l'index de l'usine

  // final result


  // tools
  private Random rng = new Random();

  public void run() {
    // set initial state
    int maxG = F + 1;
    int numG = rng.nextInt(maxG);
    ArrayList<ArrayList<Integer>> is = new ArrayList<>(numG);
    LinkedList<Integer> bag = new LinkedList<>();
    for (int i = 0; i < numG + 1; ++i) {
      is.add(new ArrayList<>(4));
      if (i == numG) continue; // don't add sous traite into the bag
      for (int j = 0; j < 4; ++j) {
        bag.add(i);
      }
    }

    // put fournisseurs in the groups at random
    Collections.shuffle(bag);
    for (int i = 0; i < F; ++i) {
      if (rng.nextDouble() < (double) 1 / (F + 1)) { // sous traite
        is.get(numG).add(i);
      } else {
        is.get(rng.nextInt(numG)).add(i);
      }
    }


    // la magie
    Magie m = new Magie(is, initTemperature, coolingRate, d, u);
    m.run();

  }
}
