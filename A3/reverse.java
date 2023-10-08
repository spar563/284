import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Reverse {
   public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        String inputLine;
        while ((inputLine = reader.readLine()) != null) {
            int order = Integer.parseInt(inputLine);

            if (order == 0) {
                break;
            }

            List<List<Integer>> graph = new ArrayList<>(order);

            for (int i = 0; i < order; i++) {
                graph.add(new ArrayList<>());
                String connections = reader.readLine();

                if (connections != null && !connections.trim().isEmpty()) {
                    String[] connectionParts = connections.split(" ");
                    for (String connection : connectionParts) {
                        int neighborNode = Integer.parseInt(connection);
                        graph.get(i).add(neighborNode);
                    }
                }
            }

            List<List<Integer>> reversedGraph = reverseGraph(graph);

            writer.println(order);
            for (List<Integer> neighbors : reversedGraph) {
                for (int neighbor : neighbors) {
                    writer.print(neighbor + " ");
                }
                writer.println();
            }
        }

        writer.println(0);
        reader.close();
        writer.close();
    }

    private static List<List<Integer>> reverseGraph(List<List<Integer>> graph) {
        int order = graph.size();
        List<List<Integer>> reversedGraph = new ArrayList<>(order);

        for (int i = 0; i < order; i++) {
            reversedGraph.add(new ArrayList<>());
        }

        for (int node = 0; node < order; node++) {
            List<Integer> neighbors = graph.get(node);

            for (int neighbor : neighbors) {
                reversedGraph.get(neighbor).add(node);
            }
        }

        return reversedGraph;
    }
  }