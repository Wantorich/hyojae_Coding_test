import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			int[] heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int N = heights[0];
			
			if (N == 0) break;
			ArrayDeque<Integer> stack = new ArrayDeque<>();
			int[] L = new int[N+1];
			int[] R = new int[N+1];
			
			Arrays.fill(L, 0);
			Arrays.fill(R, N+1);
			
			stack.push(1);
			
			for (int i = 2; i <= N; i++) {
				int curr = heights[i];
				
				if (curr > heights[stack.peek()]) {
					L[i] = stack.peek();
				} else if (curr == heights[stack.peek()]) {
					if (L[stack.peek()] != -1)
						L[i] = L[stack.peek()];
				} else {
					// 오른쪽 하방이 정해짐
					while (!stack.isEmpty() && curr < heights[stack.peek()]) {
						R[stack.pop()] = i;
					}
					if (!stack.isEmpty()) {
						if (curr > heights[stack.peek()]) {
							L[i] = stack.peek();
						} else if (curr == heights[stack.peek()]) {
							L[i] = L[stack.peek()];
						}
					}
				}
				stack.push(i);
			}

			long maxArea = 0;
			// 넓이 계산
			for (int i = 1; i <= N; i++) {
				long area = (long) (R[i] - L[i] - 1) * heights[i];
				maxArea = Math.max(maxArea, area);
			}
			sb.append(maxArea).append("\n");
		}
		System.out.println(sb.toString());
	}

}
