import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int len = 8;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] wheels = new char[5][len];

		for (int i = 1; i <= 4; i++) {
			wheels[i] = br.readLine().toCharArray();
		}

		int K = Integer.parseInt(br.readLine());
		int[] select = new int[K];
		int[] dir = new int[K];

		for (int i = 0; i < K; i++) {
			String[] line = br.readLine().split(" ");
			select[i] = Integer.parseInt(line[0]);
			dir[i] = Integer.parseInt(line[1]);
		}

		// 중요한 인덱스는 0(12시방향), 2(3시방향), 6(9시방향)
		// dir이 1이면 시계방향, -1이면 반시계

		for (int i = 0; i < select.length; i++) {
			int idx = select[i];
			List<int []> turnList = new ArrayList<int []>();
			turnList.add(new int[] {idx, dir[i]});
			
			int cnt = 1;
			for (int j = idx; j < 4; j++, cnt++) {
				if (wheels[j][2] != wheels[j+1][6]) {
					// 다음 것도 바꿈
					turnList.add(new int[] {j+1, (int) Math.pow(-1, cnt) * dir[i]});
				}
				else {
					break;
				}
			}
			
			cnt = 1;
			for (int j = idx; j > 1; j--, cnt++) {
				if (wheels[j][6] != wheels[j-1][2]) {
					// 다음 것도 바꿈
					turnList.add(new int[] {j-1, (int) Math.pow(-1, cnt) * dir[i]});
				}
				else {
					break;
				}
			}
			
			
			for (int k = 0; k < turnList.size(); k++) {
//			System.out.println("turnList : " + turnList.get(k)[0]);
				changeWheel(wheels[turnList.get(k)[0]], turnList.get(k)[1]);
			}
			
//			for (int k = 1; k <= 4; k++) {
//				System.out.println(Arrays.toString(wheels[k]));
//			}
		}
		
		
		int sum = 0;
		for (int i = 1; i <= 4; i++) {
//			System.out.println(Arrays.toString(wheels[i]));
			sum += wheels[i][0] == '1' ? (int) Math.pow(2, i-1) : 0;
		}
		
		
		System.out.println(sum);
	}
	
	static void changeWheel(char [] wheel, int dir) {
//		System.out.println("-- 휠 바꾸기 --");
//		System.out.println(Arrays.toString(wheel));
		StringBuilder sb = new StringBuilder();
		char [] newChars;
		switch (dir) {
			case 1: // 끝의 문자가 맨 앞으로옴
				newChars = sb.append(wheel).insert(0, wheel[len - 1]).deleteCharAt(len).toString().toCharArray();
				System.arraycopy(newChars, 0, wheel, 0, len);
				break;
			case -1:
				newChars = sb.append(wheel).insert(len, wheel[0]).deleteCharAt(0).toString().toCharArray();
				System.arraycopy(newChars, 0, wheel, 0, len);
				break;
		}
//		System.out.println(Arrays.toString(wheel));
//		System.out.println("-- 휠 바꾸기 끝--");
	}

}