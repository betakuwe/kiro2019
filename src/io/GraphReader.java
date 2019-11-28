package io;

import graph.Edge;
import graph.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import algorithm.library.AlgoTSP;

public class GraphReader {

  private final String filename;
  private static int NUM_VERTEX = 26;
  private static int NUM_EDGE = NUM_VERTEX * NUM_VERTEX;
  
  public GraphReader(String filename) {
    this.filename = filename;
  }

  public Kiro2019 readInput() {
    try {
      Scanner scanner = new Scanner(new File(filename));
        // ==================================================
        // todo write input here
      String first = scanner.nextLine();
      int Q = scanner.nextInt();
      int F = scanner.nextInt();
      int H = scanner.nextInt();
      scanner.next();
      int depotIndex = scanner.nextInt();
      int usineIndex = scanner.nextInt();
      
      // fournissers
      for(int i = 0; i < F; i++) {
    	  int index = scanner.nextInt();
    	  int coutSousTraitance = scanner.nextInt();
      }
      
      for(int i = 0; i < Q; i++) {
    	  int from = scanner.nextInt();
    	  int to = scanner.nextInt();
    	  int cout = scanner.nextInt();
    	  new Edge(cout);
      }
      
      scanner.close();
      
      return null; // todo
      // ==================================================
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
