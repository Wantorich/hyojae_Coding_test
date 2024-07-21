
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		
		for (int t = 1; t <= test_case; t++) {
			int N = sc.nextInt();
			int range = N / 2;
			int total = 0;
			int val;
			sc.nextLine();
			
			for (int i = 0; i < N; i++) {
				String input_num = sc.nextLine();
				for (int j = 0; j < N; j++) {
					val = Character.getNumericValue(input_num.charAt(j));
					if (range <= j && j < N - range) {
						total += val;
					}
				}
				if (i < N / 2) {
					range--;
				} 
				else {
					range++;
				}
			}
			
			System.out.printf("#%d %d", t, total);
			System.out.println();
		}
		
		sc.close();
	}
	
}

/*
3 -> 1 0 1
5 -> 2 1 0 1 2
7 -> 3 2 1 0 1 2 3

크기 / 2
3 이하이거나 n-3 이상이면 더하지않는다


*/