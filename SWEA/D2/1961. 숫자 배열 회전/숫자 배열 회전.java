import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N;
		int [][] grid; int [][] grid_90; int [][] grid_180; int [][] grid_270; 
		int test_case = sc.nextInt();
		sc.nextLine();
		
		for (int T = 1; T <= test_case; T++) {
			N = sc.nextInt();
			if (sc.hasNextLine()) sc.nextLine();
			
			grid = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					grid[i][j] = sc.nextInt();
				}
				if (sc.hasNextLine()) sc.nextLine();
			}
			
			grid_90 = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					grid_90[j][N-1-i] = grid[i][j];
				}
			}
			
			grid_180 = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					grid_180[j][N-1-i] = grid_90[i][j];
				}
			}
			
			grid_270 = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					grid_270[j][N-1-i] = grid_180[i][j];
				}
			}
			
			System.out.println("#" + T);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(grid_90[i][j]);
				}
				System.out.print(" ");
				for (int j = 0; j < N; j++) {
					System.out.print(grid_180[i][j]);
				}
				System.out.print(" ");
				for (int j = 0; j < N; j++) {
					System.out.print(grid_270[i][j]);
				}
				System.out.println();
			}
		}
	}
	
}

/*
 

*/