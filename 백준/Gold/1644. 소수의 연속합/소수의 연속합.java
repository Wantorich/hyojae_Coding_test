import java.io.*;
import java.util.*;

public class Main {
	static boolean[] memo = new boolean[100001];
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	List<Integer> primeList = new ArrayList<>();
    	boolean[] isPrime = new boolean[N+1];
    	Arrays.fill(isPrime, true);
    	
    	for (int i = 2; i <= (int) Math.sqrt(N); i++) {
    		if (!isPrime[i]) continue;
    		for (int j = i * i; j <= N; j += i) {
    			isPrime[j] = false;
    		}
    	}
    	
    	for (int i = 2; i <= N; i++)
    		if (isPrime[i])
    			primeList.add(i);
    	
    	primeList.add(0);
    	
    	int left = 0, right = 0;
    	long answer = 0, sum = 0;
    	
    	while (right < primeList.size()) {
    		if (sum < N) {
    			sum += primeList.get(right++);
    		} else if (sum >= N) {
    			if (sum == N) {
    				answer++;
    			}
    			sum -= primeList.get(left++);
    		} 
    	}
    	
    	System.out.println(answer);
    }
}