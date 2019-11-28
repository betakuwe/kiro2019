package algorithm;

import graph.Graph;

public class Kiro2019 {
  // annealing
  private static double initTemperature = 5000;
  private static double coolingRate = 1 - Math.exp(-15);

  // raw data
  private Graph g; // graphe oriente
  private int F; // fournisseurs
  private int H; // semaines
  private int Q; // taille de camion
  private int[] S; // couts de sous traitance, S[i] == cout de sous traitance du sommet i
  private int[][] M; // marchandise M[i][j] == marchandise de sommet i a la semaine j
  private int d; // l'index du depot
  private int u; // l'index de l'usine


}
