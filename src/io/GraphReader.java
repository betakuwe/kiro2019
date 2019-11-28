package io;

import graph.Edge;
import graph.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;

import algorithm.Kiro2019;
import algorithm.library.AlgoTSP;

public class GraphReader {
	private Kiro2019 kiro;
	private String filename;
	
  public GraphReader(String filename) {
    this.filename = filename;
    kiro = new Kiro2019();
  }

  public Kiro2019 readInput() 
  {
    try {
    	
      Scanner scanner = new Scanner(new File(filename));
      String first = scanner.nextLine();
      kiro.Q = scanner.nextInt();
      kiro.F = scanner.nextInt();
      kiro.H = scanner.nextInt();
      scanner.next();
      kiro.d = scanner.nextInt();
      kiro.u = scanner.nextInt();
      
      kiro.S = new int[kiro.u];
      kiro.M = new int[kiro.u][kiro.H];
      
      // fournissers
      for(int i = 0; i < kiro.F; i++) {
    	  	int index = scanner.nextInt();	  
    	  	kiro.S[index] = scanner.nextInt();
    	  	for(int j = 0; j < kiro.H; j++){
    	  		kiro.M[index][j] = scanner.nextInt();
      		}
      }
     
      kiro.g = new Graph(kiro.F+2);
      for(int i = 0; i < kiro.Q; i++) {
    	  int from = scanner.nextInt();
    	  int to = scanner.nextInt();
    	  int cout = scanner.nextInt();
    	  kiro.g.setDirectedEdge(from, to, cout);
      }
      
      scanner.close();
      
      return kiro;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }
}
