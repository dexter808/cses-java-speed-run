
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
        int t = in.nextInt(); 
        // int t = 1; 

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
        int a = in.nextInt();
        int b = in.nextInt();

        if (a + b > n || ((a == 0 || b == 0) && (a > 0 || b > 0))) {
            System.out.println("NO");
            return;
        } 
        System.out.println("YES");

        // Play all neutral cards
        int d = n - (a+b); // from (d+1)..n will be equal by both

        // 
        int pt = a + b;
        int wd = Math.min(a,b);
        int a1 = 0;
        int b1 = 0;
        if (a > b) {
            a1 += wd;
        } else {
            b1 += wd;
        }
        List<Integer> ac = new ArrayList<>();
        List<Integer> bc = new ArrayList<>();
        
        for(int i = n - d + 1; i <= n; i++) {
            ac.add(i);
            bc.add(i);
        }

        if (d != n) {
            for(int i = 1; i <= n - d; i++) {
                ac.add((a1++ % (n - d)) + 1);
                bc.add((b1++ % (n - d)) + 1);
            }
        }

        for(int i: ac) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        for(int i: bc) {
            System.out.print(i + " ");
        }
        System.out.println();
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