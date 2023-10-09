import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bipartite {
  private static int order;
  private static int[][] adjList;
  private boolean isBipartite;

  private boolean isBipartite() {
    int[] colors = new int[order]; // 0: uncolored, 1: black, -1: white

    for (int i = 0; i < order; i++) {
      if (colors[i] == 0 && !bipartiteBFS(i, colors)) {
        return false;

      }
    }

    return true;
  }

  private boolean bipartiteBFS(int start, int[] colors) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    colors[start] = 0; // Mark the starting node as color 0

    while (!queue.isEmpty()) {
      int current = queue.poll();
      for (int neighbor = 0; neighbor < order; neighbor++) {
        if (adjList[current][neighbor] == 0) {
          if (colors[neighbor] == -1) {
            colors[neighbor] = 1 - colors[current]; // Assign the opposite color
            queue.offer(neighbor);
          } else if (colors[neighbor] == colors[current]) {
            return false;
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
      int order = Integer.parseInt(br.readLine());
      if (order == 0) {
        break;
      }

      adjList = new int[order][order];

      // Read the graph's adjacency lists
      for (int i = 0; i < order; i++) {
        String[] input = br.readLine().split(" ");
        for (String string : input) {
          if (!string.isEmpty()) {
            int neighbor = Integer.parseInt(string);
            adjList[i][neighbor] = 1;
            adjList[neighbor][i] = 1;
          }
        }
      }

      Bipartite bipartite = new Bipartite();
      System.out.println(bipartite.isBipartite() ? "1" : "0");
    }

    System.out.print(result.toString());
    br.close();
  }
}
