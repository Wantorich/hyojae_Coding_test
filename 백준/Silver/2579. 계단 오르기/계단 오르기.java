import java.util.*;

public class Main {
	static int X, N, scores[], memo[][];
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        scores = new int[301];
        for (int i = 1; i <= N; i++) scores[i] = sc.nextInt();
        
//        memo = new int[3][301];
//        memo[1][1] = scores[1];
//        memo[1][2] = scores[2];
//        memo[2][2] = scores[1] + scores[2];
//        dp(); // 현재 계단 단계와 연속해서 몇번올랐는지 여부
//        System.out.println(Math.max(memo[1][N], memo[2][N]));
        
        memo2 = new int[301];
        memo2[1] = scores[1];
        memo2[2] = scores[1] + scores[2];
        System.out.println(dp2(N));
        
        sc.close();
    }

	private static void dp() {
		for (int k = 3; k <= N; k++) {
			memo[1][k] = Math.max(memo[1][k-2], memo[2][k-2]) + scores[k];// 두칸 점프
			memo[2][k] = memo[1][k-1] + scores[k];
		}
	}
	
	static int memo2[];
	
	static int dp2(int k) {
		if (k == 0) return 0;
		if (memo2[k] != 0) return memo2[k];
		return memo2[k] = Math.max(dp2(k-2), dp2(k-3) + scores[k-1]) + scores[k];
	}
}

/*
1 -> 1
2 -> f(1) + 2, 2

n번째 계단을 밟을 수 있는 경우의 수는 바로 직전 계단을 밟고 오거나, 직전직전 계단을 밝고 왔거나
4번째 계단을 오른다고 하자. 2번째까지의 최댓값에서 점프 뛰거나, 3번째에서 올수있는 최대값에서 점프뛰거나
memo[n] = height[n] + Math.max(memo[n-2] + memo[n-1])
이걸로는 연속 3개 계단 밟는 것을 판단 불가능

계단 3개를 연속으론 오를 수 없다.
이차원 메모 테이블?
행에선 연속 몇번인지, 열에선 몇번째 계단인지, 값은 그 계단까지의 점수 합
*/