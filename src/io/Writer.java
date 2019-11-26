package io;

import algorithm.Algorithm;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

  private final String filename;
  private final Algorithm a;

  public Writer(String filename, Algorithm a) {
    this.filename = filename;
    this.a = a;
  }

  public void writeToFile() {
    try {
      FileWriter fw = new FileWriter(filename);
      BufferedWriter writer = new BufferedWriter(fw);
      // ==================================================
      // todo write output here
      writer.write("test output");



      // ==================================================
      writer.flush();
      fw.flush();
      writer.close();
      fw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
