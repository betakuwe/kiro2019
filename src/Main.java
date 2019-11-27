import algorithm.AlgoTSP;
import algorithm.Algorithm;
import graph.Edge;
import io.GraphReader;
import io.Writer;

/* todo list
  Main
    input filename
    output filename
  io.GraphReader
    input
  io.Writer
    output
  graph.Edge
    define edge
  algorithm.Algorithm
    implement algorithm
 */
public class Main {

  public static void main(String[] args) {
    String distances = "src/data/input/input.in"; // todo write input file path
    AlgoTSP algorithm = new GraphReader(distances).readInput();
    algorithm.run();

    String outputFile = "src/data/output/output.out"; // todo write output file path
    new Writer(outputFile, algorithm).writeToFile();
  }
}
