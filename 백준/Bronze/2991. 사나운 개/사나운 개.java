import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		int D = sc.nextInt();
		
		int [] people = new int[3];
		for (int i = 0; i < 3; i++) 
			people[i] = sc.nextInt();

		int time1 = A+B;
		int time2 = C+D;
		
		for (int person : people) {
			int remain1 = person % time1;
			int remain2 = person % time2;
			int cnt = 0;
			
			if (remain1 <= A && remain1 != 0) cnt++;
			if (remain2 <= C && remain2 != 0) cnt++;
			System.out.println(cnt);
		}
		
		sc.close();
	}

}
