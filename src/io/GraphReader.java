package io;

import graph.Edge;
import graph.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;

import algorithm.library.AlgoTSP;

public class GraphReader {
	
	private static final String filename = "Instance-propre_dgOt8J4.csv";
	private Graph g; // graphe oriente
	private int F; // fournisseurs
	private int H; // semaines
	private int Q; // taille de camion
	private int[] S; // couts de sous traitance, S[i] == cout de sous traitance du sommet i
	private int[][] M; // marchandise M[i][j] == marchandise de sommet i a la semaine j
	private int d; // l'index du depot
	private int u; // l'index de l'usine
	
	public Graph readInput(){
    
		try {
	      Scanner scanner = new Scanner(new File(filename));
	      Q = scanner.nextInt();
	      F = scanner.nextInt();
	      H = scanner.nextInt();
	      d = scanner.nextInt();
	      u = scanner.nextInt();
	      
	      S = new int[u];
	      M = new int[u][H];
	      
	      for(int i = 0; i < F; i++){
	    	int id = scanner.nextInt();
	    	
	    	S[id] = scanner.nextInt();
	    	
	    	for(int j = 0; j < H; j++){
		    	M[id][j] = scanner.nextInt();
		    }
	    	
	      }
	      
	      g = new Graph(F+2);
	      for(int i = 0; i < ((F+2)*(F+2)); i++){
	    	  g.setDirectedEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
	      }
	      
	      scanner.close();
	      
	      return g;
	    } 
	    catch (FileNotFoundException e) {
	      e.printStackTrace();
	      return null;
	    }
	}

	public int getQ()
	{
		return Q;
	}
	public int getF()
	{
		return F;
	}
	public int getH()
	{
		return H;
	}
	public int getd()
	{
		return d;
	}
	public int getu()
	{
		return u;
	}
	
	public int[] getS()
	{
		return S;
	}
	public int[][] getM()
	{
		return M;
	}
}
