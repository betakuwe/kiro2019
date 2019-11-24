import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    readInput();
    solveTheProblem();
    outputFile();
  }

  private static void outputFile() {
    String output = "src/data/output/output.out";
    try {
      FileWriter fw = new FileWriter(output);
      BufferedWriter writer = new BufferedWriter(fw); // todo output file
      // todo code goes here
      writer.write("test output");
      // ...
      writer.flush();
      writer.close();
      fw.flush();
      fw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void solveTheProblem() {
    // todo
  }

  private static void readInput() {
    String input = "src/data/input/input.in";
    try {
      Scanner scanner = new Scanner(new File(input));

      while (scanner.hasNextLine()) {
        // todo code goes here
        // ...
        System.out.println(scanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
