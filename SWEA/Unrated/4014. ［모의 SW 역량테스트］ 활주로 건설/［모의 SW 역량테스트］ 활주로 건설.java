import java.util.*;
import java.io.*;

public class Solution {
	static int N, X;
	static int [][] grid;
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			X = sc.nextInt();
			grid = new int [N][N];
			int result = 0;
			
			for (int i = 0; i < N*N; i++) 
				grid[i/N][i%N] = sc.nextInt();
			
			for (int i = 0; i < N; i++) {
				int [] temp = new int[N];
				for (int j = 0; j < N; j++)
					temp[j] = grid[j][i];
				
				if (canBuild(grid[i])) {
//					System.out.println("다리 건설, 행 : " + i);
					result++;
				}
				
				if (canBuild(temp)) {
//					System.out.println("다리 건설, 열 : " + i);
					result++;
				}
				
			}
			
			System.out.printf("#%d %d\n", t, result);
		}
		sc.close();
	}
	private static boolean canBuild(int[] arr) {
		int curr = arr[0], curr_cnt = 1;
		
		for (int i = 1; i < arr.length; i++) {
			int val = arr[i];
			if (curr == val) {
				curr_cnt++;
			} else if (curr < val) {
				// 높은 경우에는 활주로를 건설가능한지 따져봐야함
				// 높이 차이가 1인지
				if (val - curr > 1 || curr_cnt < X) return false; 
				// 높이차이가 1보다 크면 못건설함, 활주로 길이도 고려
				
				// 낮은 지대의 길이가 활주로 건설가능한 길이보다 긴가?
				// 활주로 건설 후 현재값 초기화
				curr = val;
				curr_cnt = 1;
			} else if (curr > val) {
				// 높은지대에서 낮은 지대로 가는 경우
				// 낮은지대에서 연속적으로 활주로 건설가능한 길이가 있는지 확인
                if (curr - val > 1) return false;
                
				int cnt = 1, j = i+1;
				while (j < N && arr[j] == val) {
					cnt++;
					if (cnt == X) break;
					j++;
				}
				
				// 활주로 길이가 충분했다면 활주로 마지막에서 나왔을 것이고
				// 건설가능한 활주로가 없었다면 j = N-1 이거나 다른 높이의 배열 위치
				
				if (cnt < X) return false; // 길이 부족
				
				// 여기있다는건 활주로를 건설했다는 소리고 활주로의 끝에 있다는거
				// 포인터를 활주로 다음 값으로 옮기는데
				// 만약 여기서 활주로의 마지막 값과 높이가 다른 값이 나와버리면 바로 false임
				// 더 낮은값이 나온다
				curr = arr[j];
				curr_cnt = 0;
				i = j;
			}
		}
		return true;
	}
}

// 활주로 건설 가능 경우의 수는
// 행 또는 열의 모든 숫자가 똑같을때
// 행 또는 열의 모든 숫자의 높이 차이가 1만큼만 차이나고
// 연속적으로 나열되어있을때
/*
활주로의 길이는 2 이상이므로 퐁당퐁당은 활주로를 건설할 수 있는 경우의 수가 없음
N이 최대 20이니까 20*20 즉 400개의 행 또는 열의 경우를 따져봐야함
즉 최소 활주로의 길이만큼의 연속된 숫자가 존재하고 (m 작은값, M 큰값)
m ~ M ~ m // m ~ M이나  // M ~ m // M ~ m ~ M ~ m 퐁당퐁당도 가능은 함
오르막길과 내리막길이 순차적으로 오면 가능이야 
활주로의 길이는 전체가 똑같아야하기때문에 
배열을 읽다가 나보다 큰 원소를 만나면 그 이전까지 활주로가 건설가능한지를 따져봐야함, 안되면 불가능
나보다 작은 원소를 만나면 일단 차이가 1이어야하고, 작은 원소부터 최소 W만큼의 원소가 존재하는지 봐야함
최대 최소 차이가 2이상나도  활주로만 연결가능하면 가능이네
*/