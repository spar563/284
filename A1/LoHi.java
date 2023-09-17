import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Scanner;

 
public class LowHi {
	
	public static void main(String[] args) {
    FastScanner in = new FastScanner();
 		PrintWriter out = new PrintWriter(System.out);

		int n = in.nextInt();
    int a[] = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }
    int m = in.nextInt();
    for (int i = 0; i < m; i++) {
      int low = in.nextInt();
      int hi = in.nextInt();

      out.println(count_between(a, low, hi));
    }
    out.close();
	}

  public static int count_between(int a[], int lo, int hi) {
    // WRITE YOUR CODE HERE
  int loIndex = binarySearchFirst(a, lo); // Find the first index where a value is equal or greater than low
        int hiIndex = binarySearchLast(a, hi);   // Find the last index where a value is equal or less than high
        return hiIndex - loIndex + 1; // Calculate the number of elements between low and high (inclusive)
    }

    public static int binarySearchFirst(int a[], int target) {
        int left = 0, right = a.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int binarySearchLast(int a[], int target) {
        int left = 0, right = a.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left-1;
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

