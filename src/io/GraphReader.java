package io;

import graph.Edge;
import graph.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import algorithm.Kiro2019;

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
      scanner.next();
      kiro.Q = scanner.nextInt();
      scanner.next();
      kiro.F = scanner.nextInt();
      scanner.next();
      kiro.H = scanner.nextInt();
      scanner.nextLine();
      scanner.next();
      kiro.d = scanner.nextInt();
      scanner.nextLine();
      scanner.next();
      kiro.u = scanner.nextInt();
      scanner.nextLine();
      
      kiro.S = new int[kiro.u];
      kiro.M = new int[kiro.u][kiro.H];
     
      
      // fournissers
      for(int i = 0; i < kiro.F; i++) {
    	  scanner.next();
    	  	int index = scanner.nextInt();	
    	  	scanner.next();
    	  	kiro.S[index] = scanner.nextInt();
    	  	scanner.next();
    	  	for(int j = 0; j < kiro.H; j++){
    	  		kiro.M[index][j] = scanner.nextInt();
      		}
    	  	scanner.nextLine();
      }
     
      int numEdges = (kiro.F + 2) * (kiro.F + 2);
      kiro.g = new Graph(kiro.F + 2);
      for(int i = 0; i < numEdges; i++) {
    	  scanner.next();
    	  int from = scanner.nextInt();
    	  int to = scanner.nextInt();
    	  scanner.next();
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
