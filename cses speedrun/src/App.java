
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

    static FastReader in = new FastReader();
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    public static void main(String[] args) throws IOException {
        // Read number of test cases (use 1 if the problem doesn't specify 't')
        // int t = in.nextInt(); 
        int t = 1; 

        while (t-- > 0) {
            solve();
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
    private static void solve() {
        int n = in.nextInt();

        int[][] ans = new int[n][n];
        HashSet<Integer>[] r = new HashSet[n];
        HashSet<Integer>[] c = new HashSet[n];
        for(int i = 0; i < n; i++) {
            ans[i][0] = ans[0][i] = i;
            r[i] = new HashSet<>();
            r[i].add(i);
            c[i] = new HashSet<>();
            c[i].add(i);
        }
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < n; j++) {
                for(int k = 0; k < 2*n; k++) {
                    if (!r[i].contains(k) && !c[j].contains(k)) {
                        r[i].add(k);
                        c[j].add(k);
                        ans[i][j] = k;
                        break;
                    }
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

}

/**
 * 000
 * 001
 * 011
 * 010
 * 110
 * 111
 * 101
 * 100
 */