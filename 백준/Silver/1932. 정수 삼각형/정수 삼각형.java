import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[][] treeList = new int[N + 1][N+1];
		int[][] sumList = new int[N + 1][N+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				treeList[i][j] = sc.nextInt();
				if (j == 0) {
					sumList[i][j] = sumList[i-1][j] + treeList[i][j];
				} else if (j == i) {
					sumList[i][j] = sumList[i-1][j-1] + treeList[i][j];
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				if (j == 0 || j == i) continue;
				sumList[i][j] = Math.max(sumList[i-1][j-1], sumList[i-1][j]) + treeList[i][j];
			}
		}
		
		System.out.println(Arrays.stream(sumList[N]).max().getAsInt());
		sc.close();
	}
}