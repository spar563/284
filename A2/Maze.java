import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Maze {
  public static void main(String[] args) {
    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);

    int n = in.nextInt();
    int m = in.nextInt();

    // YOUR CODE GOES HERE.
    /* 
    1. Create a data structure to represent the table/grid.
    2. Initialize the grid with walls (e.g., using a 2D array).
    3. Iterate through the pairs of adjacent cells, and for each pair, check if there is a path between them (you can use algorithms like Depth-First Search or Union-Find to check connectivity).
    4. If there is no path between the cells, destroy the wall between them.
    5. Output the resulting table/grid. 
    */

    out.close();
  }

  static class FastScanner {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

    String next() {
      while (!st.hasMoreTokens())
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
        }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }
}
