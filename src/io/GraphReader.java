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

  public AlgoTSP readInput() {
    try {
      Scanner scanner = new Scanner(new File(filename));
        // ==================================================
        // todo write input here

      
      scanner.close();
      
      return null; // todo
      // ==================================================
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
