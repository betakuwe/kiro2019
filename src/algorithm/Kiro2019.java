package algorithm;

import graph.Graph;
import java.util.ArrayList;
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
    for (int i = 0; i < numG; ++i) {
      is.add(new ArrayList<>());
    }

    // put fournisseurs in the groups at random
    for (int i = 0; i < F; ++i) {
      is.get(rng.nextInt(maxG)).add(i);
    }

    // la magie
    Magie m = new Magie(is, initTemperature, coolingRate);
    m.run();

  }

}
