import graph.Edge;
import io.GraphReader;
import io.Writer;
import java.util.ArrayList;

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

  private static void rubbish() {
    ArrayList<String> strings = new ArrayList<>(10);
    for (int i = 0; i < 10; ++i) {
      strings.add(null);
    }
    strings.add(10, "ref");
    for (String s : strings) {
      if (s != null) {
        System.out.println(s);
      }
    }
  }
}
