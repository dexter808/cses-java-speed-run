
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
        int t = in.nextInt(); 

        while (t-- > 0) {
            solve(in, out);
        }

        // Essential: Flush out the remaining stream before exiting
        out.flush();
    }

    // Logic implementation
    private static void solve(FastReader in, PrintWriter out) {
        long x = in.nextLong();
        long y = in.nextLong();
        long ans = 0;
        if (x >= y) {
            if (x % 2 == 0) {
                // decrease from x^2 - (y - 1)
                ans = x * x - y + 1;
            } else {
                ans = (x - 1) * (x - 1) + y;
            }
        } else {
            if (y % 2 == 0) {
                ans = (y - 1) * (y - 1) + x;
            } else {
                ans = y * y - x + 1;
            }
        }
        out.println(ans);
        // max will decide the range 
        // min will decide the origin
        // 
    }
}
