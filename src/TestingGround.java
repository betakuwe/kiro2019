import java.util.ArrayList;

public class TestingGround {
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
