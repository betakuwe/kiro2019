package io;

import graph.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphReader<E> {

  private final String filename;

  public GraphReader(String filename) {
    this.filename = filename;
  }

  public Graph<E> readInput() {
    try {
      Scanner scanner = new Scanner(new File(filename));

      Graph<E> g = new Graph<>();

      while (scanner.hasNextLine()) {
        // ==================================================
        // todo code goes here



        System.out.println(scanner.nextLine());
        // ==================================================
      }
      return g;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
