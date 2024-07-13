import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int test_case = sc.nextInt();
		sc.nextLine();
		
		for (int t = 1; t <= test_case; t++) {
			int N = Integer.parseInt(sc.nextLine());
			int velocity = 0;
			int distance = 0;
			
			for (int i = 0; i < N; i++) {
				int command = sc.nextInt();
				if (command == 1) {
					int accel = sc.nextInt();
					velocity += accel;
				}
				else if (command == 2) {
					int d_accel = sc.nextInt();
					velocity -= d_accel;
				}
				sc.nextLine();
				
				if (velocity < 0) {
					velocity = 0;
				}
				
				distance += velocity;
			}
			
			System.out.printf("#%d %d", t, distance);
			System.out.println();
		}
		
		sc.close();
	}

}
