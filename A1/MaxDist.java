import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Scanner;


public class MaxDist {

public static void main(String[] args) {
  FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);

  int t = in.nextInt();
  for (int i = 0; i < t; i++) {
    int n = in.nextInt();
    int k = in.nextInt();

    int a[] = new int[n];
    for (int j = 0; j < n; j++) a[j] = in.nextInt();
    out.println(solve(a, k));
  }

  out.close();
}

public static int solve(int a[], int k) {
  // WRITE YOUR CODE HERE
  int n = a.length;
  int result = 0;
  int min = 0;  // Initialize the left bound of the binary search
  int max = a[n - 1] - a[0];  // Initialize the right bound of the binary search

  while (min <= max) { // Perform binary search
      int mid = min + (max - min) / 2; // Calculate the middle value between min and max
      int prev = a[0]; // Initialize the previous element to the first element of the array
      int count = 1; // Initialize the count of elements in the current subarray

      for (int i = 1; i < n; i++) { // Iterate through the array
          if (a[i] - prev >= mid) { // Check if the distance between the current element and the previous element is at least mid
              count++;
              prev = a[i];
              if (count == k) { // If we have k elements in the current subarray, we can stop searching
                  break;
              }
          }
      }

      if (count == k) { // If we have found a subarray with k elements that satisfy the condition
          result = mid; // Update the result with the current mid value
          min = mid + 1; // Move the left bound to search for larger distances
      } else {
          max = mid - 1; // Move the right bound to search for smaller distances
      }
  }

  return result; // Return the maximum distance
}



static class FastScanner {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st=new StringTokenizer("");

    String next() {
      while (!st.hasMoreTokens())
        try { 
          st=new StringTokenizer(br.readLine());				              
        } catch (IOException e) {}
      return st.nextToken();
    }
    
    int nextInt() {
      return Integer.parseInt(next());
    }
}
}

