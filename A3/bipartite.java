import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bipartite {
  private int order;
  private List<List<Integer>> adjList;
  private boolean isBipartite;

  private boolean isBipartite() {
    int[] colors = new int[order];
    Arrays.fill(colors, -1); // -1 represents no color (unvisited)

    for (int i = 0; i < order; i++) {
      if (colors[i] == -1) {
        if (!bipartiteBFS(i, colors)) {
          return false;
        }
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
      int currentColor = colors[current];

      for (int neighbor : adjList.get(current)) {
        if (colors[neighbor] == currentColor) {
          return false; // Not bipartite if neighboring nodes have the same color
        }

        if (colors[neighbor] == -1) {
          colors[neighbor] = 1 - currentColor; // Assign the opposite color (0 or 1)
          queue.offer(neighbor);
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

      Bipartite bipartiteDetector = new Bipartite();
      bipartiteDetector.order = order;
      bipartiteDetector.adjList = new ArrayList<>(order);
      for (int i = 0; i < order; i++) {
        bipartiteDetector.adjList.add(new ArrayList<>());
      }

      // Read the graph's adjacency lists
      for (int i = 0; i < order; i++) {
        String[] input = br.readLine().split(" ");
        for (String string : input) {
          if (!string.isEmpty()) {
            int neighbor = Integer.parseInt(string);
            bipartiteDetector.adjList.get(i).add(neighbor);
          }
        }
      }

      bipartiteDetector.isBipartite = bipartiteDetector.isBipartite(); // Update isBipartite field
      result.append(bipartiteDetector.isBipartite ? "1\n" : "0\n");
    }

    System.out.print(result.toString());
    br.close();
  }
}
