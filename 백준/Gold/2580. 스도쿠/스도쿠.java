import java.util.*;
import java.io.*;

public class Main {
	static int size = 9;
	static int [][] grid = new int[size][size];
	static List<Integer> list = new ArrayList<Integer>();
	static boolean complete = false;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int idx = -1;
		while (++idx < size*size) {
			grid[idx/size][idx%size] = sc.nextInt();
			if (grid[idx/size][idx%size] == 0) {
				list.add(idx);
			}
		}
		
		fullSearch(list, new int[list.size()], 0);
		
		for (int [] row : grid) {
			for (int val : row) {
				System.out.print(val + " ");
			}
			System.out.println();
		}
		sc.close();
	}

	private static void fullSearch(List<Integer> list, int [] sel, int k) {
		if (complete) return;
		
		if (sel.length == k) {
			// 스도쿠인지 판단
			if (isSudoku(grid)) {
//				System.out.println("끝");
//				System.out.println(Arrays.toString(sel));
				complete = true;
				for (int i = 0; i < sel.length; i++) {
					int pos = list.get(i);
					grid[pos/size][pos%size] = sel[i];
				}
				return;
			}
			else {
//				System.out.println("--- 뭐지 ? ---");
//				System.out.println(Arrays.toString(sel));
			}
			return;
		}
		
		boolean [] visit = repeatRemove(list.get(k));
		for (int i = 1; i <= 9; i++) {
			// 가로 세로 격자 판단해서 있으면 안넣음
			if (visit[i]) continue; // 가지 자르기, 백트래킹
			
			sel[k] = i;
			int pos = list.get(k);
			grid[pos/size][pos%size] = i;
//			System.out.println("k : " + k  + ", r : " + pos/size + ", c : " + pos%size + " = " + i);
			fullSearch(list, sel, k+1);
			if (complete) break;
			grid[pos/size][pos%size] = 0;
		}
		
	}
	
	private static boolean[] repeatRemove(Integer pos) {
		int r = pos / size;
		int c = pos % size;
		
		boolean[] v = new boolean[10];
		
		for (int i = 0; i < size; i++) {
			if (i == c) continue;
			v[grid[r][i]] = true;
		}
		
		for (int i = 0; i < size; i++) {
			v[grid[i][c]] = true;
		}
		
//		System.out.println("pos : " + pos + ", r : " + r + ", c : " + c + Arrays.toString(v));
		// r / 3 ~ 3개, c / 3 ~ 3개
		r = r % 3 == 1 ? r-1 : r % 3 == 2 ? r - 2 : r;
		c = c % 3 == 1 ? c-1 : c % 3 == 2 ? c - 2 : c;
		
		for (int i = r; i < r+3; i++) {
			for (int j = c; j < c+3; j++) {
				v[grid[i][j]] = true;
			}
		}
		
		return v;
	}

	private static boolean isSudoku(int[][] map) {
		for (int i = 0; i < size; i++) {
			boolean [] check = new boolean[size+1];
			int cnt = 0;
			for (int j = 0; j < size; j++) {
				if (!check[grid[i][j]]) {
					check[grid[i][j]] = true;
					cnt += 1;
				}
			}
			if (cnt != size) return false;
		}
		
		for (int i = 0; i < size; i++) {
			boolean [] check = new boolean[size+1];
			int cnt = 0;
			for (int j = 0; j < size; j++) {
				if (!check[grid[j][i]]) {
					check[grid[j][i]] = true;
					cnt += 1;
				}
			}
			if (cnt != size) return false;
		}
		
		for (int i = 0; i < size; i+=3) {
			for (int j = 0; j < size; j+=3) {
				boolean [] check = new boolean[size+1];
				int cnt = 0;
				for (int k = i; k < i+3; k++) {
					for (int l = j; l < j+3; l++) {
						if (!check[grid[k][l]]) {
							check[grid[k][l]] = true;
							cnt += 1;
						}
					}
				}
				if (cnt != size) return false;
			}
		}
		
		return true;
	}
}
