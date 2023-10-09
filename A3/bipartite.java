import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bipartite {
  private int order;
  private List<List<Integer>> adjList;
  private boolean isBipartite;

  private boolean isBipartite() {
    int[] colors = new int[order];
    Arrays.fill(colors, -1); // -1 represents no color (unvisited)

    for (int i = 0; i < order; i++) {
      if (colors[i] == -1) {
        if (!bipartiteDFS(i, colors, 0)) {
          return false;
        }
      }
    }

    return true;
  }

  private boolean bipartiteDFS(int node, int[] colors, int color) {
    colors[node] = color;

    for (int neighbor : adjList.get(node)) {
      if (colors[neighbor] == color) {
        return false; // Not bipartite if neighboring nodes have the same color
      }

      if (colors[neighbor] == -1 && !bipartiteDFS(neighbor, colors, 1 - color)) {
        return false; // Recursively visit neighbors with the opposite color
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
