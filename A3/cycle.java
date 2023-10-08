import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cycle {

  private static int order;
  private static List<List<Integer>> adjacencyList;
  private static boolean[] visited;
  private static boolean[] stack;

  private boolean isCyclic() {
    visited = new boolean[order];
    stack = new boolean[order];

    for (int i = 0; i < order; i++) {
      if (!visited[i] && dfs(i)) {
        return true; // If any component is cyclic, return true.
      }
    }

    return false; // If all components are acyclic, return false.
  }

  private boolean dfs(int node) {
    visited[node] = true;
    stack[node] = true;

    for (int neighbor : adjacencyList.get(node)) {
      if (!visited[neighbor]) {
        if (dfs(neighbor)) {
          return true; // Recursive call found a cycle.
        }
      } else if (stack[neighbor]) {
        return true; // Cycle detected (back edge to a node in the current stack).
      }
    }

    stack[node] = false; // Remove the node from the stack after DFS traversal.
    return false;
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    while (scan.hasNextLine()) {
      String line = scan.nextLine().trim(); // Read and trim the input line.

      if (line.isEmpty()) {
        continue; // Skip empty lines in the input.
      }

      order = Integer.parseInt(line); // Parse the order (number of nodes).

      if (order == 0) {
        break; // Exit the loop if order is 0.
      }

      adjacencyList = new ArrayList<>(order);

      // Build the adjacency list for the directed graph.
      for (int i = 0; i < order; i++) {
        adjacencyList.add(new ArrayList<>());
        String input = scan.nextLine();

        if (input.trim().isEmpty()) {
          continue; // Skip empty lines in the input.
        }

        String[] parts = input.split(" ");
        for (String part : parts) {
          int neighbor = Integer.parseInt(part);
          adjacencyList.get(i).add(neighbor); // Add neighbors to the adjacency list.
        }
      }

      Cycle cycle = new Cycle();
      boolean isCyclic = cycle.isCyclic();

      if (isCyclic) {
        System.out.println("1"); // Cycle detected, print 1.
      } else {
        System.out.println("0"); // No cycle detected, print 0.
      }
    }

    scan.close();
  }
}
