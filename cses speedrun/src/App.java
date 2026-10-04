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
 
    private static class Node {
        int n;
        Node left;
        Node right;
 
        Node(int n1, Node left, Node right) {
            n = n1;
            this.left = left;
            this.right = right;
        }
    }
 
    private static void solve() {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
 
        int p1 = 0;
        int p2 = 0;
        HashMap<Integer, Integer> m = new HashMap<>();
        long ans = 0;
 
        while (p2 < n) {
            while (m.containsKey(a[p2]) && m.get(a[p2]) > 0) {
                m.put(a[p1], m.get(a[p1]) - 1);
                p1++;
            }
            if(m.containsKey(a[p2])) {
                m.put(a[p2], m.get(a[p2]) + 1);
            } else {
                m.put(a[p2], 1);
            }
            ans += p2 - p1 + 1;
            p2++;
        }
        out.println(ans);
    }
}
