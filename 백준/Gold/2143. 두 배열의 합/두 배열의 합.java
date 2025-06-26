import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	long T = Long.parseLong(br.readLine());
    	int N = Integer.parseInt(br.readLine());
    	int[]a = new int[N];
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	for (int i = 0; i < N; i++) {
    		a[i] = Integer.parseInt(st.nextToken());
    	}
    	int M = Integer.parseInt(br.readLine());
    	int[] b = new int[M];
    	st = new StringTokenizer(br.readLine(), " ");
    	for (int i = 0; i < M; i++) {
    		b[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	Map<Long, Integer> cntMap = new HashMap<>();
    	long result = 0;
    	// A를 순회하면서 T - A의 부배열의 합의 값을 맵에 저장 (개수를)
    	long tmp, key;
    	for (int i = 0; i < a.length; i++) {
    		tmp = 0;
    		for (int j = i; j < a.length; j++) {
    			tmp += a[j];
    			key = T - tmp;
    			cntMap.computeIfAbsent(key, v -> 0);
    			cntMap.compute(key, (k, v) -> v+1);
    		}
    	}
    	
    	// B를 순회하면서 Map에 저장되있는지 확인 확인한만큼 result 증가
    	for (int i = 0; i < b.length; i++) {
    		tmp = 0;
    		for (int j = i; j < b.length; j++) {
    			tmp += b[j];
    			key = tmp;
    			Integer cnt = cntMap.get(key);
    			if (cnt != null) {
    				result += cntMap.get(key);
    			}
    		}
    	}
    	
    	System.out.println(result);
    }
}

/*

*/