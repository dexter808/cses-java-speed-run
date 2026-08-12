
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
        int n = 8;
        char[][] ar = new char[n][n];

        for(int i = 0; i < n; i++) {
            ar[i] = in.nextLine().toCharArray();
        }

        boolean c[] = new boolean[n];
        boolean od[] = new boolean[2 * n - 1]; // obtuse diagonal; 
        boolean ad[] = new boolean[2 * n - 1]; // acute diagonal;

        int ans = 0;
        for(int k = 0; k < n; k++) {
            ans += dfs(0,k,n,c,od,ad,ar);
        }

        out.println(ans);
    }
    private static int dfs(int i, int j, int n, boolean[] c, boolean[] ad, boolean[] od, char[][] ar) {
        int ans = 0;
        // check if current combo possible
        if (ar[i][j] == '.' && !c[j] && !ad[i+j] && !od[i-j+(n-1)]) {
            if(i == n - 1) {
                return 1;
            }
            ad[i + j] = c[j] = od[(n-1) + i - j] = true;
            for(int k = 0; k < n; k++) {
                ans += dfs(i+1,k,n,c,ad,od,ar);
            }
            ad[i + j] = c[j] = od[(n-1) + i - j] = false;
        }
        return ans;
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