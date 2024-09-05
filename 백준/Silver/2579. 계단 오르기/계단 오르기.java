import java.util.*;

public class Main {
	static int X, N, scores[], memo[][];
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        scores = new int[301];
        for (int i = 1; i <= N; i++) scores[i] = sc.nextInt();
        memo = new int[3][301];
        memo[1][1] = scores[1];
        memo[1][2] = scores[2];
        memo[2][2] = scores[1] + scores[2];
        dp(); // 현재 계단 단계와 연속해서 몇번올랐는지 여부
        System.out.println(Math.max(memo[1][N], memo[2][N]));
        sc.close();
    }

	private static void dp() {
		for (int k = 3; k <= N; k++) {
			memo[1][k] = Math.max(memo[1][k-2], memo[2][k-2]) + scores[k];// 두칸 점프
			memo[2][k] = memo[1][k-1] + scores[k];
		}
	}
}