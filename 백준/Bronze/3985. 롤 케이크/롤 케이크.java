import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int L = sc.nextInt();
		int N = sc.nextInt();
		int s, e, max_diff = 0, max_earn = 0;
		int r1 = 1, r2 = 1;
		int [] arr = new int[L+1];
		
		for (int i = 1; i <= N; i++) {
			s = sc.nextInt();
			e = sc.nextInt();
			
			r1 = e-s+1 > max_diff ? i : r1;
			max_diff = Math.max(max_diff, e-s+1);
			
			int earn = 0;
			for (int j = s; j <= e; j++) {
				if (arr[j] != 0) continue;
				
				arr[j] = i;
				earn++;
			}
			
			r2 = earn > max_earn ? i : r2;
			max_earn = Math.max(max_earn, earn);
		}
		
		System.out.printf("%d%n%d", r1, r2);
		sc.close();
	}

}
