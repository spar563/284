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

    // Initialize disjoint-set data structure
    int table_size = n * n;
    int[] parent = new int[table_size];  // Parent array for disjoint-set
    int[] rank = new int[table_size];    // Rank array for disjoint-set
    char[][] table = initializetable(n);

    // Print the resulting maze
    printMaze(out, table, n);
    

    out.close();
  }

  static char[][] initializetable(int n) {
    int x = n * 2 + 1;
    
    char[][] table = new char[x][x];
    for (int i = 0; i < x; i++) {
        for (int j = 0; j < x; j++) {
            if (i % 2 == 0 && j % 2 == 0) {
                table[i][j] = '+';
            } else if (i % 2 == 0) {
                table[i][j] = '-';
            } else if (j % 2 == 0) {
                table[i][j] = '|';
            } else {
                table[i][j] = ' ';
            }
        }
    }
    return table;
}

static void printMaze(PrintWriter out, char[][] table, int n) {
  for (int i = 0; i < n * 2 + 1; i++) {
      for (int j = 0; j < n * 2 + 1; j++) {
          out.print(table[i][j]);
      }
      out.println();
  }
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
