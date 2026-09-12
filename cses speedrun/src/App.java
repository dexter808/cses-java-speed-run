
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
     * 4 2 1 5 3 -> 3
     * 4 1 2 5 3 -> 2
     * 3 1 2 5 4 -> 
     * 3 2 1 5 4
     */

    private static void solve() {
        char[] s = in.nextLine().toCharArray();
        int n = s.length;

        int[] fr = new int[26];

        for(int i = 0; i < n; i++) {
            fr[s[i] - 'A']++;
        }

        char[] ans = new char[n];

        for(int i = 0 ; i < n; i++) {
            ans[i] = '\u0000';
        }

        for(int i = 0 ; i < n; i++) {
            if (ans[i] != '\u0000') {
                continue;
            }
            int lc = (i == 0)?-1:(int)(ans[i - 1] - 'A');
            int cl = n - i;
            int fc = -1;
            int hf = 0;
            for(int j = 0; j < 26; j++) {
                if(lc == j) {
                    continue;
                }
                if(fr[j] > (cl + 1) / 2) {
                    System.out.println(-1);
                    return;
                }
                if(fc == -1 && fr[j] > 0) {
                    fc = j;
                }
                if(fr[hf] < fr[j]) {
                    hf = j;
                }
            }
            int cc = fc;
            if(cl % 2 == 1 && fr[hf] == ((cl/2) + 1)) {
                cc = hf;
            }
            ans[i] = (char)(cc + 'A');
            fr[cc]--;
        }
        System.out.println(ans);
    }
}