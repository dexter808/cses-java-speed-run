
import java.io.*;
import java.util.*;

public class App {

    // Fast I/O Reader
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                if (st != null && st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        // Helper to read primitive integer arrays quickly
        int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }
    }

    public static void main(String[] args) throws IOException {
        // Initialize FastReader and FastWriter
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        // Read number of test cases (use 1 if the problem doesn't specify 't')
        int t = 1; 

        while (t-- > 0) {
            solve(in, out);
        }

        // Essential: Flush out the remaining stream before exiting
        out.flush();
    }

    // Logic implementation
    private static void solve(FastReader in, PrintWriter out) {
        int n = in.nextInt();
        if (n == 1) {
            out.println("1");
        } else if (n < 4) {
            out.println("NO SOLUTION");
        } else if (n == 4) {
            out.println("2 4 1 3");
        } else {
            // 1 4 2 5 3
            int i1 = 1;
            int i2 = ((n + 1) / 2) + 1;

            do {
                out.print(i1 + " " + i2 + " ");
                i1++;
                i2++;
            } while (i2 <= n);
            
            if (n % 2 == 1) {
                out.println(i1);
            } 
        }
    }
}
