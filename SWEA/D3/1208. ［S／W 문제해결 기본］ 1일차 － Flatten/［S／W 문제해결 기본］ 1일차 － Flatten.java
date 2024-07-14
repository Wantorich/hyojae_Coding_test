
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int LEN = 100;
		
		for (int t = 1; t <= 10; t++) {
			int [] heights = new int [LEN];
			int dump = sc.nextInt();
			for (int i = 0; i < LEN; i++) {
				heights[i] = sc.nextInt();
				for (int j = i; j > 0; j--) {
					if (heights[j] < heights[j-1]) {
						int temp = heights[j];
						heights[j] = heights[j-1];
						heights[j-1] = temp;
					}
				}
			}
			
			while (dump > 0) {
				heights[LEN-1] -= 1;
				heights[0] += 1;
				dump--;
				
				int i = LEN-1;
				while (heights[i] < heights[i-1]) {
					int temp = heights[i];
					heights[i] = heights[i-1];
					heights[i-1] = temp;
					i--;
				}
				
				int j = 0;
				while (heights[j] > heights[j+1]) {
					int temp = heights[j];
					heights[j] = heights[j+1];
					heights[j+1] = temp;
					j++;
				}
			}
			
			System.out.printf("#%d %d", t, heights[LEN-1] - heights[0]);
			System.out.println();
		}
	}
	
}

/*
배열에서 max인것을 찾아서 2번쨰로 큰것이 될때까지 빼는게 덤프
옮기는건 제일 작은거에 옮기고, 매번 판단?
정렬을 하면 제일 큰거에서 제일 작은거에 옮기면
제일 작은거 2개가 같아지겠지
그럼 그걸 다시 2번째로 작은거까지 또 옮기고

*/