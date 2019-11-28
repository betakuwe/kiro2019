package io;

import algorithm.library.AlgoTSP;
import algorithm.Algorithm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
      for(int i = 0; i < a.getResult().size(); i++) {
    	  writer.write(a.getResult().get(i).toString());
    	  writer.write(" ");
      }
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
