import java.util.*;
import java.io.*;

public class Solution {
	static int [][] magnets = new int[5][8];
	static int [][] commands;
	static int K, top = 0, right = 2, left = 6;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int test_case = Integer.parseInt(br.readLine());

		for (int t = 1; t <= test_case; t++) {
			K = Integer.parseInt(br.readLine());
			commands = new int[K][2];
			for (int i = 1; i <= 4; i++) {
				String [] inputs = br.readLine().split(" ");
				for (int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(inputs[j]);
				}
			}
			
			// K번의 자석 회전
			for (int i = 0; i < K; i++) {
				String [] inputs = br.readLine().split(" ");
				commands[i][0] = Integer.parseInt(inputs[0]);
				commands[i][1] = Integer.parseInt(inputs[1]);
			}
			
//			for (int i = 1; i < 5; i++) {
//				System.out.println(Arrays.toString(magnets[i]));
//			}
			
			simulation();
			// 자석들의 top을 확인해서 결과 출력
			int result = 0;
			for (int i = 1; i < 5; i++) {
				result += magnets[i][top] == 1 ? Math.pow(2, i-1) : 0;
			}
			System.out.printf("#%d %d\n", t, result);
		}
	}
	
	


	private static void simulation() {
		// 자석 회선 시뮬레이션
		for (int k = 0; k < K; k++) {
			List<int[]> rotate = new ArrayList<int[]>();
			int magnet = commands[k][0];
			int dir = commands[k][1];
			rotate.add(new int[] {magnet, dir});
			
			// 양쪽 자석 회전해야하는지 체크
			// 오른쪽 자석 체크
			for (int i = magnet+1; i <= 4; i++) {
				if (magnets[i-1][right] != magnets[i][left]) {
					dir *= -1; // 회전방향 전거랑 다르게 바꿔줌
					rotate.add(new int[] {i, dir});
				}
				else {
					// 일치하지 않는 경우
					break;
				}
			}
			
			dir = commands[k][1]; // 방향 초기화
			// 왼쪽 자석 체크
			for (int i = magnet-1; i > 0; i--) {
				if (magnets[i+1][left] != magnets[i][right]) {
					dir *= -1;
					rotate.add(new int[] {i, dir});
				}
				else {
					break;
				}
			}
			
			// 자석 회전 시키기
			for (int [] curr : rotate) {
//				System.out.println(Arrays.toString(curr));
				int [] tmp = new int[8];
				switch (curr[1]) {
					case 1:
						// 시계방향 회전, 배열 맨끝 원소가 첫자리에오고 한자리씩 밀림
						System.arraycopy(magnets[curr[0]], 0, tmp, 1, tmp.length-1);
						tmp[0] = magnets[curr[0]][7];
						magnets[curr[0]] = tmp;
						break;
					case -1:
						// 반시계방향 회전, 첫번째 원소가 마지막으로 감
						System.arraycopy(magnets[curr[0]], 1, tmp, 0, tmp.length-1);
						tmp[7] = magnets[curr[0]][0];
						magnets[curr[0]] = tmp;
						break;
				}
			}
			
//			System.out.println("----------------------");
//			for (int i = 1; i < 5; i++) {
//				System.out.println(Arrays.toString(magnets[i]));
//			}
//			System.out.println("----------------------");
		}
	}
}