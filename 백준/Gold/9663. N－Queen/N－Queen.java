import java.util.Scanner;

public class Main {
	static int N, col[], ans;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new int[N+1];
		
		setQueens(1);
		System.out.println(ans);
		sc.close();
	}
	
	
	static void setQueens(int rowNo) {
		if (!isAvailable(rowNo-1)) return;  
			
		if (rowNo > N) {
			// 무조건 답
			ans++;
			return;
		}
		
		
		for (int c = 1; c <= N; c++) {
			col[rowNo] = c;
			setQueens(rowNo+1);
		}
	}


	private static boolean isAvailable(int rowNo) {
		for (int k = 1; k < rowNo; k++) {
			if (col[rowNo] == col[k] || rowNo-k == Math.abs(col[k] - col[rowNo])) return false;
		}
		return true;
	}
}
