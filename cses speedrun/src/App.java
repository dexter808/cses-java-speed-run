
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
        // Sort + DSU path complression
        private static void solve() {
            int n = in.nextInt();
            int m = in.nextInt();
    
            int[] t = in.nextIntArray(n);
            int[] ct = in.nextIntArray(m);
            int[] gt = new int[n];
    
            // GOTO array
            for(int i = 0; i < n; i++) {
                gt[i]=-2;
            }
    
            Arrays.sort(t);
    
            
            for(int i = 0; i < m; i ++) {
                int c = ct[i];
                int l = 0;
                int r = n - 1;
                while (l <= r) {
                    int mid = (l + r) / 2;
                    if (t[mid] <= c) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                int ans = getIndex(gt, r);
                if (ans < 0) {
                    out.println(-1);
                }
                else {
                    out.println(t[ans]);
                }
            }
        }
    
        static int getIndex(int[] gt, int i) {
            if( i <  0) {
                return i;
            }
            if (gt[i] == -1) {
                return -1;
            }
            if(gt[i] == -2) {
                gt[i] = i - 1;
                return i;
            }
            gt[i] = getIndex(gt, gt[i]);
            return gt[i];
        }
}