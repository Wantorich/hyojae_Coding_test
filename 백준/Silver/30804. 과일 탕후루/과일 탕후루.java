import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int[] fruits = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	int[] counts = new int[10];
    	
    	int left = 0, right = 0;
    	int answer = 1, key;
    	int currCnt = 0;
    	
    	while (right < fruits.length) {
    		
    		if (currCnt < 2) {
    			if (counts[fruits[right++]]++ == 0)
    				currCnt++;
    		} else {
    			if (counts[fruits[right]] > 0) {
    				counts[fruits[right]]++;
    				right++;
    			} else {
    				answer = Math.max(answer, right - left);
    				if (--counts[fruits[left++]] == 0)
    					currCnt--;
    			}
    		}
    	}
    	answer = Math.max(answer, right - left);
    	System.out.println(answer);
    }
}