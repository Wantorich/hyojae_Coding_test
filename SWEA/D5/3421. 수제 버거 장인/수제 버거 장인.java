import java.util.*;
import java.io.*;

public class Solution {
	static int N, M, result;
	static int [][] duplicate;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		
		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			duplicate = new int[N+1][N+1];
			result = N + 1; // 공집합 + 1개씩 뽑는 경우의 수
			
			for (int i = 0; i < M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				duplicate[a][b] = 1;
				duplicate[b][a] = 1;
			}
			
			// 조합을 여러개 만들까
			for (int i = 2; i <= N; i++) {
				comb(0, 1, new int[i]);
			}
			System.out.printf("#%d %d\n", t, result);
		}
		sc.close();
	}

	private static void comb(int cnt, int start, int[] sel) {
		if (cnt == sel.length) {
			// 같이 넣으면 안되는 재료 있는지 확인
			result++;
			return;
		}
		
		O:for (int i = start; i <= N; i++) {
			// 중복 체크
			// cnt 전까지 i와 같이 들어가면 안되는 친구가 들어가 있나 체크
			for (int j = 0; j < cnt; j++) {
				if (duplicate[i][sel[j]] == 1) 
					// 같이 들어가면 안됌
					continue O;
			}
			sel[cnt] = i;
			comb(cnt+1, i+1, sel);
		}
	}
}

