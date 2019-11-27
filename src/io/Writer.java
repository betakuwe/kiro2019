package io;

import algorithm.AlgoTSP;
import algorithm.Algorithm;
import graph.Edge;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {

  private final String filename;
  private final AlgoTSP a;

  public Writer(String filename, AlgoTSP a) {
    this.filename = filename;
    this.a = a;
  }

  public void writeToFile() {
    try {
      FileWriter fw = new FileWriter(filename);
      BufferedWriter writer = new BufferedWriter(fw);
      // ==================================================
      // todo write output here
      ArrayList<Integer> list = a.getResult();
      int size = list.size();
      int i = 0;
      for(i = 0; i < size - 1; i++) {
    	  writer.write(list.get(i));
    	  writer.write("->");
      }
      writer.write(list.get(i));
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
