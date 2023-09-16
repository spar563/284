import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Middle {
  public static void main(String[] args) {
    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);

    int m = in.nextInt();
    // YOUR CODE GOES HERE.
    
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Min-heap for the second half of elements
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a); // Max-heap for the first half of elements

    for (int i = 0; i < m; i++) {
      int op = in.nextInt();
      if (op == 0) {
        // Perform middle operation
        int middle = maxHeap.peek();
        out.println(middle);
      } else {
        // Perform add operation
        int x = in.nextInt();
        if (maxHeap.isEmpty() || x <= maxHeap.peek()) {
          maxHeap.add(x);
        } else {
          minHeap.add(x);
        }

        // Balance the heaps if necessary
        if (maxHeap.size() > minHeap.size() + 1) {
          minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
          maxHeap.add(minHeap.poll());
        }
      }
    }

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
