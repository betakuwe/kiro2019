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
  Algorithm
    implement algorithm
 */
public class Main {

  public static void main(String[] args) {
    String inputFile = "src/data/input/input.in"; // todo write input file path
    GraphReader<Edge> graphReader = new GraphReader<>(inputFile);
    Algorithm algorithm = new Algorithm(graphReader.readInput());

    String outputFile = "src/data/output/output.out"; // todo write output file path
    new Writer(outputFile, algorithm).writeToFile();
  }
}
