
import algorithm.Kiro2019;
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

    aurelien
    entree, sortie
    parse and create graph


    randomization of initial state ben

    annealing:
      neighbour: ben
        petite modification: change the group of a f to another group ben
      energy: YC
        algo for calculating path with minimum cost in each group
        save state of costs

        calculating cost of a path





 */
public class Main {

  public static void main(String[] args) {
    String distances = "src/data/input/input.in"; // todo write input file path
    Kiro2019 algorithm = new GraphReader(distances).readInput();
    algorithm.run();

    String outputFile = "src/data/output/output.out"; // todo write output file path
    new Writer(outputFile, algorithm.getResult()).writeToFile();
  }
}
