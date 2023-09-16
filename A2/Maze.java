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

    // Initialize the disjoint-set data structure for each cell
    initializeDisjointSet(parent, rank);

    // Process each pair of adjacent cells
    processPairs(in, parent, rank, table, n, m);

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

static void initializeDisjointSet(int[] parent, int[] rank) {
  for (int i = 0; i < parent.length; i++) {
      parent[i] = i;
      rank[i] = 0;
  }
}

static void processPairs(FastScanner in, int[] parent, int[] rank, char[][] table, int n, int m) {
  for (int i = 0; i < m; i++) {
      int a = in.nextInt();
      int b = in.nextInt();

      if (union(parent, rank, a, b, n, table)) {
          int x1 = a / n;
          int y1 = a % n;
          int x2 = b / n;
          int y2 = b % n;

          if (x1 == x2) {
              table[x1 * 2 + 1][Math.min(y1, y2) * 2 + 1 + 1] = ' ';
          } else {
              table[Math.min(x1, x2) * 2 + 1 + 1][y1 * 2 + 1] = ' ';
          }
      }
  }
}

static void printMaze(PrintWriter out, char[][] table, int n) {
  for (int i = 0; i < n * 2 + 1; i++) {
      for (int j = 0; j < n * 2 + 1; j++) {
          out.print(table[i][j]);
      }
      out.println();
  }
}

static boolean union(int[] parent, int[] rank, int x, int y, int n, char[][] table) {
  int rootX = find(parent, x);
  int rootY = find(parent, y);

  if (rootX == rootY) {
      return false;
  }

  if (rank[rootX] < rank[rootY]) {
      parent[rootX] = rootY;
  } else if (rank[rootX] > rank[rootY]) {
      parent[rootY] = rootX;
  } else {
      parent[rootY] = rootX;
      rank[rootX]++;
  }

  return true;
}

static int find(int[] parent, int x) {
  if (parent[x] != x) {
      parent[x] = find(parent, parent[x]);
  }
  return parent[x];
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
