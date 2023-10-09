import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Bipartite {
  private static int order;
  private static int[][] adjMatrix;
  private static int[] colors;

  private static boolean isBipartite() {
    colors = new int[order]; // 0: uncolored, 1: color 1, 2: color 2

    for (int i = 0; i < order; i++) {
      if (colors[i] == 0) {
        if (!bipartiteBFS(i)) {
          return false;
        }
      }
    }

    return true;
  }

  private static boolean bipartiteBFS(int start) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    colors[start] = 1; // Start with color 1

    while (!queue.isEmpty()) {
      int current = queue.poll();
      int currentColor = colors[current];

      for (int neighbor = 0; neighbor < order; neighbor++) {
        if (adjMatrix[current][neighbor] == 1) {
          if (colors[neighbor] == 0) {
            colors[neighbor] = 3 - currentColor; // Assign the opposite color (1 or 2)
            queue.offer(neighbor);
          } else if (colors[neighbor] == currentColor) {
            return false; // Not bipartite if neighboring nodes have the same color
          }
        }
      }
    }

    return true;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder result = new StringBuilder();

    while (true) {
      order = Integer.parseInt(br.readLine());
      if (order == 0) {
        break;
      }

      adjMatrix = new int[order][order];

      // Read the graph's adjacency lists
      for (int i = 0; i < order; i++) {
        String[] input = br.readLine().split(" ");
        for (String string : input) {
          if (!string.isEmpty()) {
            int neighbor = Integer.parseInt(string);
            adjMatrix[i][neighbor] = 1;
            adjMatrix[neighbor][i] = 1;
          }
        }
      }

      if (isBipartite()) {
        result.append("1\n");
      } else {
        result.append("0\n");
      }
    }

    System.out.print(result.toString());
    br.close();
  }
}
