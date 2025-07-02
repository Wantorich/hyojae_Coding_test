import java.util.*;
import java.io.*;

public class Main {
	static int parent[], headCount[], candySum[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] candies = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		// Union-find로 관리
		// 숫자 적은놈으로 합치자
		parent = new int[N];
		Arrays.fill(parent, -1);

		// 합쳐진 아이들 인원수 관리, 1로 초기
		headCount = new int[N];
		Arrays.fill(headCount, 1);
		candySum = candies.clone();

		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			from = Integer.parseInt(st.nextToken()) - 1;
			to = Integer.parseInt(st.nextToken()) - 1;
			if (find(from) != find(to)) {
				union(from, to);
			}
		}
		
		// 이 정보를 바탕으로 DP 수행
//		System.out.println(Arrays.toString(parent));
//		System.out.println(Arrays.toString(headCount));
//		System.out.println(Arrays.toString(candySum));

		// DP knapsack인데 K가 최대 3000임 즉 2999까지 허용 
		// col은 어디까지 해야할까 배열의 길이? 배열의 인덱스 범위? 
		List<int[]> info = new ArrayList<>();
		info.add(new int[] {0, 0});
		for (int i = 0; i < N; i++) {
			if (headCount[i] == 0)
				continue;
			// 인원수와 사탕 합으로 저장 
			info.add(new int[] {headCount[i], candySum[i]});
		}
		
		// DP 테이블 정의 
		int[][] dp = new int[K][info.size()];
		for (int i = 1; i < K; i++) {
			for (int j = 1; j < info.size(); j++) {
				// 기본적으로 바로 윗행의 값을 상속받음 + 왼쪽값도
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				if (info.get(j)[0] <= i) {
					dp[i][j] = Math.max(dp[i][j],
							dp[i-info.get(j)[0]][j-1] + info.get(j)[1]);
				}
			}
		}
		
//		for (int[] rows : dp) {
//			System.out.println(Arrays.toString(rows));
//		}
		System.out.println(dp[K-1][info.size()-1]);
	}

	private static void union(int a, int b) {
		// a, b중 더 작은 곳으로 합치고, headCount도 이동?
		int pa = find(a);
		int pb = find(b);
		
		int min = Math.min(pa, pb);
		int max = Math.max(pa, pb);
		
		// 큰놈이 작은놈쪽으로 부모 향
		parent[max] = min;
		
		// 사탕처리 
		candySum[min] += candySum[max];
		candySum[max] = 0;
		
		// 인원수 처리 
		headCount[min] += headCount[max];
		headCount[max] = 0;
	}

	private static int find(int num) {
		if (parent[num] == -1) {
			return num;
		}
		return parent[num] = find(parent[num]);
	}
}

/*
 * 
 * 친구들끼리 연결되어있으니 집단이 한 뭉텅이라고 봐도 무방 안들키면서 최대를 뺏어야함 결국 선택의 문제, DP 내가 몇명한테 뺏었는데, 그
 * 때의 최댓값을 구하는 문제
 * 
 * union-find로 해야하나? 근데 아이 숫자 정보도 관리를 해야함
 * 
 * 결과적으로 사탕 개수 / 아이 명수의 정보로 관리하는게 DP 짜기가 좋아보이는데
 * 
 */