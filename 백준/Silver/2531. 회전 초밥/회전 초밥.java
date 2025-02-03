import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	int[] sushis = new int[info[0] + info[2] - 1];
    	
    	for (int i = 0; i < info[0]; i++) 
    		sushis[i] = Integer.parseInt(br.readLine());
    	
    	for (int i = 0; i < info[2] - 1; i++) 
    		sushis[i + info[0]] = sushis[i];
    	
    	int[] counts = new int[info[1]+1];
    	int left = 0, right = 0;
    	int answer = 0, count = 0;
    	
    	while (right < sushis.length) {
    		if (++counts[sushis[right++]] == 1) {
    			count++;
    		}
    		
    		if (right - left == info[2]) {
    			if (counts[info[3]] == 0) 
    				answer = Math.max(answer, count + 1);
    			answer = Math.max(answer, count);
    			
    			if (--counts[sushis[left++]] == 0) 
    				count--;
    		}
    	}
    	System.out.println(answer);
    }
}
