import java.util.*;

public class Main {
	static int days[], pages[], N, M, result = Integer.MIN_VALUE;
	static int []memo;
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        
        days = new int[M];
        pages = new int[M];
        memo = new int[M+1];
        
        for (int i = 0; i < M; i++) {
        	days[i] = sc.nextInt();
        	pages[i] = sc.nextInt();
        }
        recur(0, 0, 0);
        System.out.println(result);
        
        
        sc.close();
    }

	private static void recur(int k, int day, int cnt) {
		if (day > N) return;
		
		if (k >= M) {
			result = Math.max(result, cnt);
			return;
		}
		
		recur(k+1, day+days[k], cnt + pages[k]);
		recur(k+1, day, cnt);
	}
}

