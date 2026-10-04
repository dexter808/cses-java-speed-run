
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
        Node right;
        Node left;
        Node (int n, Node left, Node right) {
            this.n = n;
            this.left = left;
            this.right = right;
        }
    }

    private static void remove(Node node) {
        Node l = node.left;
        Node r = node.right;

        node.left = node.right = null;

        l.right = r;
        r.left = l;
    }

    private static void solve() {
        int n = in.nextInt();
        Node head = new Node(1, null, null);
        Node n1 = head;
        for(int i = 2; i <= n; i++) {
            Node h2 = new Node(i, head, null);
            head.right = h2;
            head = h2;
        }
        n1.left = head;
        head.right = n1;

        head = n1.right;

        int t = n;
        Node next = null;
        while (t > 0) {
            t--;
            out.print(head.n + " ");
            next = head.right;
            remove(head);
            head = next.right;
        }
        out.println();
    }
}