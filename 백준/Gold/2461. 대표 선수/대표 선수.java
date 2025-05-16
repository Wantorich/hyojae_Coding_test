import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	int[][] nums = new int[N][M];
    	for (int i = 0; i < N; i++) {
    		nums[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    		Arrays.sort(nums[i]);
    	}
    	
    	int[] pointers = new int[N];
    	TreeSet<int[]> ts = new TreeSet<>((a, b) -> Integer.compare(a[1], b[1]));
    	for (int i = 0; i < N; i++)
    		ts.add(new int[] {i, nums[i][0]});
    	
    	int answer = Integer.MAX_VALUE;
    	
    	while (true) {
    		answer = Math.min(answer, ts.last()[1] - ts.first()[1]);
			int index = ts.first()[0];
			if (++pointers[index] == M) 
				break;
			ts.remove(ts.first());
			ts.add(new int[] {index, nums[index][pointers[index]]});
    	}
    	
    	System.out.println(answer);
    }
}
