import java.util.*;

public class Solution {
	static int N, M, parents[];
	
	static void makeSet() {
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i; // 자신의 부모를 자신으로 함
		}
	}
	
	static int findSet(int a) {
		if (parents[a] == a) return a; // 자신이 자신의 부모라면 루트노드이고 집합의 대표자가 됌
		return parents[a] = findSet(parents[a]); // path compression
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false; // 두 집합의 대표자가 같으면 이미 같은 집합이므로 합집합 연산 불가
		// aRoot에 bRoot를 흡수 : 두 집합 합치기
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= tc; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			parents = new int[N+1];
			makeSet();
			
			for (int i = 0; i < M; i++) {
				// union할 값들 받아옴
				switch(sc.nextInt()) {
				case 0:
					// union
					union(sc.nextInt(), sc.nextInt());
					break;
				case 1:
					// find
					// ture면 1, false면 0
					int value = findSet(sc.nextInt()) == findSet(sc.nextInt()) ? 1 : 0;
					sb.append(value);
				}
			}
			
			System.out.printf("#%d %s\n", t, sb.toString());
            sb.setLength(0);
		}
		sc.close();
	}
}
