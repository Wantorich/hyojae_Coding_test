import java.util.*;
import java.io.*;

public class Main {
    static Boolean[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        memo = new Boolean[N][N];

        int[] nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            sb.append(isPalindrome(a, b, nums) ? 1 : 0).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static boolean isPalindrome(int a, int b, int[] nums) {
        if (a >= b) return true; // 길이 1 또는 빈 구간
        if (memo[a][b] != null) return memo[a][b]; // 이미 계산된 경우

        // 양 끝 숫자가 같고 내부 구간도 팰린드롬인지 확인
        return memo[a][b] = (nums[a] == nums[b]) && isPalindrome(a + 1, b - 1, nums);
    }
}
