import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		char [] charArr = sc.nextLine().trim().toCharArray();
		int LEN = charArr.length;
		
		int R = 1, C = LEN, cnt = 1, nr =R;
		
		while (R < C-1 && nr < C-1) {
			nr = R + cnt++;
			if (LEN % nr == 0) {
				R = nr;
				C = LEN / R;
				cnt = 1;
			}
		}
		
		char [][] grid = new char[R][C];
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				grid[j][i] = charArr[i*R+j];
			}
		}
		
		String result = "";
		for (int i = 0; i < R; i++) {
			result += String.valueOf(grid[i]);
		}
		
		System.out.println(result);
		
		sc.close();
	}

}
