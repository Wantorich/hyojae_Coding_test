import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int N, M;
    static int[] A, B;
    static String[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer stA = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(stA.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        B = new int[M];
        StringTokenizer stB = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(stB.nextToken());
        }

        memo = new String[N][M];

        String[] result = common(0, 0).split(" ");
        List<String> answer = Arrays.stream(result)
                                    .filter(str -> str.length() > 0)
                                    .collect(Collectors.toList());

        System.out.println(answer.size());
        System.out.println(String.join(" ", answer));

        br.close();
    }

    private static String common(int i, int j) {
        if (i >= N || j >= M) return "";

        if (memo[i][j] != null)
            return memo[i][j];

        String result = null;

        if (A[i] == B[j]) {
            String curr = A[i] + "";
            String next = common(i + 1, j + 1);
            result = getLast(curr + " " + next, next);
        } else {
            result = getLast(common(i, j + 1), common(i + 1, j));
        }

        return memo[i][j] = result;
    }

    static String getLast(String s1, String s2) {
        if (s1.isBlank()) return s2;
        else if (s2.isBlank()) return s1;

        String[] split_s1 = s1.split(" ");
        String[] split_s2 = s2.split(" ");

        int minLen = Math.min(split_s1.length, split_s2.length);

        for (int i = 0; i < minLen; i++) {
            int a = Integer.parseInt(split_s1[i]);
            int b = Integer.parseInt(split_s2[i]);

            if (a < b) {
                return String.join(" ", split_s2);
            } else if (a > b) {
                return String.join(" ", split_s1);
            }
        }

        return split_s1.length > split_s2.length 
                ? String.join(" ", split_s1)
                : String.join(" ", split_s2);
    }
}
