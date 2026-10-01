
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
        int t = 1; 
        // t = in.nextInt(); 

        while (t-- > 0) {
            solve();
        }

        // Essential: Flush out the remaining stream before exiting
        out.flush();
    }

    /**
     * 4 2 1 5 3
     * 4 1 2 5 3
     * 3 1 2 5 4
     * 3 2 1 5 4
     */
    // static class Node {
    //     int num;
    //     int index;
    //     Node prev;
    //     Node next;
    // }
    private static void solve() {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = in.nextIntArray(n);
        int[] numToIndex = new int[n + 1];
        int[] startNums = new int[n + 1];
        int ans = n;

        for(int i = 0; i < n; i++) {
            numToIndex[a[i]] = i + 1;
            startNums[a[i]] = 1;
        }
        for(int i = 2; i <= n; i++) {
            if (numToIndex[i - 1] < numToIndex[i]) {
                startNums[i] = 0;
                ans--;
            }
        }

        while(m > 0) {
            m--;

            int x = in.nextInt();
            int y = in.nextInt();

            if (x > y) {
                int t = y;
                y = x;
                x = t;
            }

            int cx = a[x - 1];
            int cy = a[y - 1];

            a[x - 1] = cy;
            a[y - 1] = cx;

            numToIndex[cy] =  x;
            numToIndex[cx] =  y;

            // going back can promote cy to become a leader index(cy - 1) > x
            if (cy > 1 && x < numToIndex[cy - 1] && startNums[cy] == 0) {
                startNums[cy] = 1;
                ans++;
            }
            // going back can remove the cy+1 from leader if x < index(cy+1)
            if (cy < n && x < numToIndex[cy + 1] && startNums[cy + 1] == 1) {
                startNums[cy + 1] = 0;
                ans--;
            }

            // going forward can remove cx from leader ship
            if (cx > 1 && startNums[cx] == 1 && y > numToIndex[cx - 1]) {
                startNums[cx] = 0;
                ans--;
            }
            // going forward can make cx + 1 a leader 
            if (cx < n && y > numToIndex[cx + 1] && startNums[cx + 1] == 0) {
                startNums[cx + 1] = 1;
                ans++;
            }

            out.println(ans);
        }
    }
    // Time Complexity -> O(n + m)
    // Space Complexity -> O(n)
}