package io;

import graph.Edge;
import graph.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import algorithm.AlgoTSP;
import algorithm.Algorithm;

public class GraphReader {

  private final String filename;
  private static int NUM_VERTEX = 15;
  private static int NUM_EDGE = NUM_VERTEX * NUM_VERTEX;
  
  public GraphReader(String filename) {
    this.filename = filename;
  }

  public AlgoTSP readInput() {
    try {
      Scanner scanner = new Scanner(new File(filename));

      Graph<Edge> g = new Graph<>(NUM_VERTEX);
      
      int i = 0;
      int j = 0;
      for(int k = 0; k < NUM_EDGE; k++) {
        // ==================================================
        // todo write input here
    	  int distance = Integer.parseInt(scanner.next());
    	  if(i != j) {
    		  g.setDirectedEdge(i, j, new Edge(distance));
    	  }
    	  j++;
    	  if(j % NUM_VERTEX == 0) {
    		  i++;
    		  j = 0;
    	  }
      }
      
      scanner.close();
      
      return new AlgoTSP(g); // todo
      // ==================================================
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
