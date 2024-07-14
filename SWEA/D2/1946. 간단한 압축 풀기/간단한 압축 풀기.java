import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int test_case = Integer.parseInt(sc.nextLine().trim());
		int p_cnt; String alpha; int quantity; int N;
		
		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			sc.nextLine();
			p_cnt = 0;
			System.out.println("#" + t);
			
			for (int i = 0; i < N; i++) {
				alpha = sc.next();
				quantity = sc.nextInt();
				if (i != N-1) sc.nextLine();
				
				for (int j = 0; j < quantity; j++, p_cnt++) {
					if (p_cnt != 0 && p_cnt % 10 == 0) {
						System.out.println();
					}
					System.out.print(alpha);
				}
			}
			System.out.println();
		}
		sc.close();
		
	}
	
	
}

/*
print cnt를 설정하고 10이 넘어가면 개행하고
입력 받으면서 프린트하면될거같은데

*/