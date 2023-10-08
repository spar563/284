import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bipartite {
  private static int order; // Number of nodes
  private static int[][] graph; // Adjacency matrix

  private boolean isBipartite() {
    int[] colors = new int[order]; // Store the color of each node (0 or 1)


    for (int i = 0; i < order; i++) {
      if (colors[i] == -1) {
        if (!bfs(i, colors)) {
          return false; // If any component is not bipartite, return false.
        }
      }
    }

    return true; // If all components are bipartite, return true.
  }

  private boolean bfs(int startNode, int[] colors) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(startNode);
    colors[startNode] = 1; // Color the start node with 0.

    while (!queue.isEmpty()) {
      int currentNode = queue.poll();

      for (int neighbor = 0; neighbor < order; neighbor++) {
        if (graph[currentNode][neighbor] == 1) {
          if (colors[neighbor] == 0) {
            colors[neighbor] = -colors[currentNode]; // Assign the opposite color.
            queue.offer(neighbor);
          } else if (colors[neighbor] == colors[currentNode]) {
            return false; // Graph is not bipartite if adjacent nodes have the same color.
          }
        }
      }
    }

    return true;
  }

  public static void main(String[] args) {
    FastScanner scan = new FastScanner();

    while(true) {
      order = scan.nextInt();
      if (order == 0) {
        break;
      }

      graph = new int[order][order];

      for (int i = 0; i < order; i++) {
        String[] neighbors = scan.nextLine().split(" ");
        for (String neighbor : neighbors) {
          int neighborNode = Integer.parseInt(neighbor);
          graph[i][neighborNode] = 1;
          graph[neighborNode][i] = 1;
        }
      }
    }
    Bipartite bipartite = new Bipartite();
    System.out.println(bipartite.isBipartite() ? "1" : "0");
    
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

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
      }
      return str;
    }
  }
}
