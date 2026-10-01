
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
    static int salt = 1;
    private static void solve() {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);

        HashMap<Integer, Integer> m = new HashMap<>(); // frequency of elements inside the set
        TreeSet<Integer> s = new TreeSet<>(); // Elements in order

        for(int e: a) {
            m.put(e, 0);
        }

        for(int e: a) {
            Integer g = s.higher(e);
            if (g == null) {
                m.put(e, m.get(e) + 1);
                s.add(e);
            } else {
                m.put(g, m.get(g) - 1);
                m.put(e, m.get(e) + 1);
                s.add(e);
                if(m.get(g) == 0) {
                    s.remove(g);
                }
            }
        }

        int ans = 0;
        for(int e: s) {
            ans += m.get(e);
        }

        System.out.println(ans);
    }
    // T.C. = O(N)
    // S.C = O(N)
}