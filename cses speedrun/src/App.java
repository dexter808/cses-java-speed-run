
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
    // Think of it int layer wise set
    // set 1 -> (n-1)*(n-1)
    // set 2 -> (2*n-1)
    // within set 1 + within set 2 + between set 1 and set 2
    // prev answer + 
    private static void solve(FastReader in, PrintWriter out) {
        int n = in.nextInt();
        long k1 = n;
        long s = (k1 * (k1+1)) / 2;
        if (s%2 == 1) {
            out.println("NO");
        } else {
            out.println("YES");
            boolean[] c = new boolean[n + 1];
            long sm = s / 2;
            int k = n;
            int ct = 0;
            while (sm != 0) {
                if (sm >= k) {
                    sm -= k;
                    c[k] = true;
                    k--;
                    ct++;
                } else {
                    c[(int)sm] = true;
                    ct++;
                    break;
                }
            } 
            out.println(ct);
            for(int i = 1; i <= n; i++) {
                if (c[i]) {
                    out.print(i + " ");
                }
            }
            out.println();

            out.println(n - ct);
            for(int i = 1; i <= n; i++) {
                if (!c[i]) {
                    out.print(i + " ");
                }
            }
            out.println();
        }
    }
}
