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
        int x = in.nextInt();
        int n = in.nextInt();
 
        // ArrayList<Integer> a = new ArrayList<>();
        int[] a = in.nextIntArray(n);
        
        int[] b = new int[n + 2];
        for (int i = 0; i < n; i++) {
            b[i] = a[i];
        }
        b[n] = 0;
        b[n + 1] = x;
 
 
        Map<Integer, Node> m = new HashMap<>();
        
        Arrays.sort(b);
 
        int l = b[1];
        for(int e: b) {
            m.put(e, new Node(e, null, null));
        }
 
        for(int i = 0; i < n + 2; i++) {
            if (i > 0) {
                l = Math.max(l, b[i] - b[i - 1]);
                m.get(b[i]).left = m.get(b[i - 1]);
            }
            if (i < n + 1) {
                m.get(b[i]).right = m.get(b[i + 1]);
            }
        }// 0 - 3 - 6 - 8
 
        int[] ans = new int[n];
 
        for(int i = n - 1; i >= 0; i--) {
            ans[i] = l;
            
            int t = a[i];
            Node node = m.get(t);
            Node le = node.left;
            Node ri = node.right;
 
            le.right = ri;
            ri.left = le;
 
            l = Math.max(l, ri.n - le.n);
        }
 
        for(int e: ans) {
            out.print(e + " ");
        }
        out.println();
    }
}