import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] buildings = new int[N];
		for (int i = 0; i < N; i++)
			buildings[i] = Integer.parseInt(br.readLine());
		
		ArrayDeque<int[]> stack = new ArrayDeque<>();
		long[] sights = new long[N];
		
		// 스택 탑보다 크면 대체하고
		// 작으면 그냥 넣음
		
		for (int i = N-1; i >= 0; i--) {
			int height = buildings[i];
			
			while (!stack.isEmpty()) {
				if (height <= stack.peek()[0]) {
					break;
				} else {
					stack.pop();
				}
			}
			
			if (stack.isEmpty()) {
				sights[i] = N-1 - i;
			} else {
				int index = stack.peek()[1];
				sights[i] = index - i - 1;
			}
			stack.push(new int[] {height, i});
		}
		
		long answer = Arrays.stream(sights).sum();
		System.out.println(answer);
	}
}
