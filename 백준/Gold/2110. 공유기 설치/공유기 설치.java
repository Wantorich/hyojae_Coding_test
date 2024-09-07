import java.util.*;

public class Main {
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        TreeSet<Integer> ts = new TreeSet<Integer>();
        for (int i = 0; i < N; i++) ts.add(sc.nextInt());
        
        int left = 1, right = ts.last() - ts.first(), mid = 0;
        int result = 0;
        O:while (left <= right) {
        	mid = (left + right) / 2;
        	
        	int home = ts.first();
        	for (int i = 1; i < M; i++) {
        		if (ts.ceiling(home + mid) == null) {
        			// 현재 길이가 너무 김
        			right = mid - 1;
        			continue O;
        		}
        		home = ts.ceiling(home + mid);
        	}
        	
    		left = mid + 1;
    		result = Math.max(result, mid);
        }
        
        System.out.println(result);
        sc.close();
    }
}
