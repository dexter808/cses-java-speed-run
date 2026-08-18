
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
    private static void solve() {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] c = in.nextIntArray(n);

        Arrays.sort(c);

        int p1 = 0;
        int p2 = n - 1;
        int ans = 0;

        while (p1 <= p2) {
            ans++;
            if(c[p1]+c[p2] <= m) {
                p1++;
                p2--;
            } else {
                p2--;
            }
        }

        out.println(ans);
    }
}