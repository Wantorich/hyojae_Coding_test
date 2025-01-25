import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	static int answer, N;
	static int[] memo;
	
    public static void main(String[] args) throws IOException {
    	Scanner sc = new Scanner(System.in);
    	String input = sc.nextLine();
    	N = input.length();
    	answer = N;
    	
    	boolean[][] isPalindrom = new boolean[N][N];
    	List<Integer>[] list = new ArrayList[N];
    	for (int i = 0; i < list.length; i++)
    		list[i] = new ArrayList<>();
    	
    	for (int i = 0; i < N-1; i++) {
            isPalindrom[i][i] = true;
            if (input.charAt(i) == input.charAt(i+1)) {
            	isPalindrom[i][i+1] = true;
            	list[i].add(i+1);
            }
        }
    	isPalindrom[N-1][N-1] = true;

        for (int len = 3; len <= N; len++) {
            for (int start = 0; start <= N - len; start++) {
                int end = start + len - 1;
                if (input.charAt(start) == input.charAt(end) && isPalindrom[start + 1][end - 1]) {
                	isPalindrom[start][end] = true;
                	list[start].add(end);
                }
            }
        }
        
        memo = new int[N];
        Arrays.fill(memo, N);
        
        recursive(N-1, isPalindrom);
        System.out.println(memo[N-1]);
    	
    	sc.close();
    }
    
    private static int recursive(int end, boolean[][] isPalindrom) {
    	if (end < 0)
    		return 0;
    	
    	if (memo[end] != N)
    		return memo[end];
    	
    	for (int i = 0; i < N; i++) {
    		if (isPalindrom[i][end]) {
    			memo[end] = Math.min(memo[end], recursive(i-1, isPalindrom) + 1);
    		}
    	}
    	
    	return memo[end];
	}
}
