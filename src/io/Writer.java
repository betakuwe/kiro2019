package io;

import algorithm.Kiro2019;
import java.util.ArrayList;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

  private final String filename;
  private ArrayList<ArrayList<Integer>> C;

  public Writer(String filename, ArrayList<ArrayList<Integer>> C) 
  {
    this.filename = filename;
    this.C = C;
  }

  public void writeToFile() {
    try {
		FileWriter fw = new FileWriter(filename);
		BufferedWriter writer = new BufferedWriter(fw);
		
		writer.newLine();
		writer.write("x " + C.get(0).size() + " f");
		for(int i : C.get(0))
			writer.write(" " + i);

		writer.newLine();
		writer.write("y ");

		writer.newLine();
		writer.write("z " + (C.size()-1));
		
		for(int i = 1; i < C.size(); i++) {
			writer.newLine();
			writer.write("C " + (i-1) + " n " + C.get(i).size() + " f");
			for(int j = 0; j < C.get(i).size(); j++)
			{
				writer.write(" " + C.get(i).get(j));
			}
		}
		  
		writer.flush();
		fw.flush();
		writer.close();
		fw.close();
    
    } 
    
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
