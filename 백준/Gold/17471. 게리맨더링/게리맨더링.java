import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.io.*;

public class Main {
	static int [][] grid;
	static boolean [] v;
	static int [] people;
	static int N, cnt;
	private static int result = Integer.MAX_VALUE;
	static boolean searchEnd = false;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		grid = new int [N+1][N+1];
		v = new boolean[N+1];
		people = new int[N+1];
		
		String [] lines = br.readLine().split(" ");
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(lines[i-1]);
		}
		
		
		for (int i = 1; i <= N; i++) {
			lines = br.readLine().split(" ");
			for (int j = 1; j <= Integer.parseInt(lines[0]); j++) {
				int idx = Integer.parseInt(lines[j]);
				grid[i][idx] = 1;
			}
		}
		
		for (int [] row : grid) {
//			System.out.println(Arrays.toString(row));
		}
		
//		System.out.println("--- powerSet ---");
		powerSet(1, new boolean[N+1]);
		result = result == Integer.MAX_VALUE ? -1 : result;
		System.out.println(result);
	}

	private static void powerSet(int k, boolean [] sel) {
		if (searchEnd) return;
		
		if (k == sel.length) {
			List<Integer> a_team = new ArrayList<>();
			List<Integer> b_team = new ArrayList<>();
			for (int i = 1; i < sel.length; i++) {
				if (sel[i]) {
//					System.out.print(i + " ");
					a_team.add(i);
				} else {
					b_team.add(i);
				}
			}
//			System.out.println();

			// 팀이 0명이거나 전부 가져온 경우, 즉 최소 1명도 없는 경우
			if (a_team.size() == 0 || a_team.size() == N) return;
			
			cnt = 1;
			v = new boolean[N+1];
			v[a_team.get(0)] = true;
			dfs(a_team, a_team.get(0));
//			System.out.print("a team list : ");
//			for (int a : a_team) {
//				System.out.print(a + " ");
//			}
//			System.out.println();
//			System.out.println("a_team size : " + a_team.size() + ", a_cnt : " + cnt);
			if (cnt != a_team.size()) return;
			
			v = new boolean[N+1];
			v[b_team.get(0)] = true;
//			System.out.print("b team list : ");
//			for (int b : b_team) {
//				System.out.print(b + " ");
//			}
//			System.out.println();
			cnt = 1;
			dfs(b_team, b_team.get(0));
//			System.out.println("b_team size : " + b_team.size() + ", b_cnt : " + cnt);
//			System.out.println();
			if (cnt != b_team.size()) return;
			
//			System.out.println("팀은 다 맞아요");
			
			// 팀의 인구수의 차이 구하기
			int a_sum = 0;
			for (int a_idx : a_team) {
				a_sum += people[a_idx];
			}
			
			int b_sum = 0;
			for (int b_idx : b_team) {
				b_sum += people[b_idx];
			}
			
			int diff = Math.abs(a_sum - b_sum);
			result = Math.min(result, diff);
			if (result == 0) searchEnd = true;
			return;
		}
		
		sel[k] = true;
		powerSet(k+1, sel);
		
		sel[k] = false;
		powerSet(k+1, sel);
		
	}
	
	
	private static void dfs(List<Integer> team, int idx) {
		for (int i = 1; i <= N; i++) {
			if (grid[idx][i] == 1 && !v[i] && team.contains(i)) {
				v[i] = true;
				cnt++;
				dfs(team, i);
			}
		}
	}


	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
}
