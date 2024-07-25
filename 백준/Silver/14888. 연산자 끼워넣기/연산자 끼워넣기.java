import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static boolean [] v;
	static List <Character> op_arr;
	static int [] nums;
	static int max_result = Integer.MIN_VALUE, min_result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) 
			nums[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		op_arr = new ArrayList<Character>();
		char [] ops = {'+', '-', '*', '/'};
		for (int i = 0; i < 4; i++) {
			int op_num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < op_num; j++) {
				op_arr.add(ops[i]);
			}
		}
				
		v = new boolean[op_arr.size()];
		
		getPm(new int[op_arr.size()], 0);
		
		
		System.out.printf("%d\n%d", max_result, min_result);
	}
	
	static void calc(int [] ops, int [] nums) {
		int val = nums[0];
		for (int i = 0; i < ops.length; i++) {
			switch (op_arr.get(ops[i])) {
				case '+':
					val += nums[i+1];
					break;
				case '-':
					val -= nums[i+1];
					break;
				case '*':
					val *= nums[i+1];
					break;
				case '/':
					val /= nums[i+1];
			}
		}
		max_result = Math.max(val, max_result);
		min_result = Math.min(val, min_result);
	}
	
	static void getPm(int [] sel, int k) {
		if (k == sel.length) {
			calc(sel, nums);
			return; 
		}
		
		for (int i = 0; i < v.length; i++) {
			if (!v[i]) {
				v[i] = true;
				sel[k] = i;
				getPm(sel, k+1);
				v[i] = false;
			}
		}
	}
}

