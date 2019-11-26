package io;

import graph.Edge;
import graph.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphReader<A> {

  private final String filename;

  public GraphReader(String filename) {
    this.filename = filename;
  }

  public A readInput() {
    try {
      Scanner scanner = new Scanner(new File(filename));

      Graph<Edge> g = new Graph<>();

      while (scanner.hasNextLine()) {
        // ==================================================
        // todo write input here



        System.out.println(scanner.nextLine());
      }
      return null; // todo
      // ==================================================
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
