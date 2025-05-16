import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int K = Integer.parseInt(st.nextToken());
    	final int MAX_LEN = 1000000;
    	
    	int[] imos = new int[MAX_LEN+10];
    	
    	int start, end;
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		start = Integer.parseInt(st.nextToken());
    		end = Integer.parseInt(st.nextToken());
    		imos[start+1]++;
    		imos[end+1]--;
    	}
    	
    	int sum = 0;
    	for (int i = 0; i <= MAX_LEN; i++) {
    		sum += imos[i];
    		imos[i] = sum;
    	}
    	
    	int left = 0, right = 1;
    	int answer = 0;
    	
    	while (right <= MAX_LEN) {
    		if (answer + imos[right] < K) {
    			answer += imos[right++];
    		} else if (answer + imos[right] > K) {
    			answer -= imos[++left];
    		} else {
    			System.out.printf("%d %d", left, right);
    			return;
    		}
    	}
    	System.out.printf("%d %d", 0, 0);
    }
}
