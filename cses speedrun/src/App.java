
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * Out of any 2 movies we will always choose the movie that ends first
     */
    private static void solve() {
        int n = in.nextInt();
        long[] a = new long[n];

        for(int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }

        Arrays.sort(a);

        
        long[] ps = new long[n];
        ps[0] = a[0];
        
        for(int i = 1; i < n ; i++) {
            ps[i] = a[i] + ps[i - 1];
        }
        
        long l = 0;
        long r = a[n - 1];

        while (l <= r) {
            long mid = (l + r) / 2;
            long c_mid = calculate_cost(n, ps, mid, a);
            long c_l = calculate_cost(n, ps, mid - 1, a);
            long c_r = calculate_cost(n, ps, mid + 1, a);

            if(c_l >= c_mid && c_mid <= c_r) {
                out.println(c_mid);
                return;
            } else if (c_l >= c_mid) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
    }
    
    private static long calculate_cost(int n, long[] ps, long mid, long[] a) {
        int i_mid = floorBinarySearch(a, mid);
        long c_mid = 0;
        if(i_mid >= 0) {
            c_mid += mid*(i_mid + 1) - ps[i_mid];
            c_mid += ps[n - 1] - ps[i_mid] - (n - 1 - i_mid)*mid;
        } else {
            c_mid += ps[n - 1] - (n - 1 - i_mid)*mid;
        }
        return c_mid;
    }

    static int floorBinarySearch(long[] a, long t) {
        int l = 0;
        int r = a.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (a[mid] <= t) {
                l = l + 1;
            } else {
                r = r - 1;
            }
        }
        return r;
    }
}