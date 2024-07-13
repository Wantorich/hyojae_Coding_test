import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		sc.nextLine();
		
		int [] switchs = new int[N+1];
		for (int i = 1; i <= N; i++) {
			switchs[i] = sc.nextInt();
		}
		sc.nextLine();
		
		int T = Integer.parseInt(sc.nextLine().trim());
		
		int gender; int s_index; int str_pos; int end_pos;
		
		for (int i = 0; i < T; i++) {
			gender = sc.nextInt();
			s_index = sc.nextInt();
			sc.nextLine();
			
			if (gender == 1) {
				// Man
				for (int j = 1; j <= N; j++) {
					if (j % s_index == 0) {
						switchs[j] = 1 - switchs[j]; // 배수일때 스위치 값 바꿈
					}
				}
			}
			else {
				// Woman
				str_pos = end_pos = s_index;
				while (str_pos >= 1 && end_pos <= N) {
					if (switchs[str_pos] == switchs[end_pos]) {
						str_pos--;
						end_pos++;
					}
					else {
						break;
					}
				}
				for (int j = str_pos+1; j < end_pos; j++) {
					switchs[j] = 1 - switchs[j];
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.print(switchs[i] + " ");
            if (i % 20 == 0) System.out.println();
		}
		
		
		
		sc.close();
	}

}

/*

*/