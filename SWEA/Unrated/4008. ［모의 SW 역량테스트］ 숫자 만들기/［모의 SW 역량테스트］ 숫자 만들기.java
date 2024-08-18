import java.util.*;
import java.io.*;

public class Solution {
	static int N, max, min;
	static int [] nums;
	static char [] op = {'+', '-', '*', '/'};
	static char [] op_list;
	static Set<String> op_visit;
	static int visit_cnt, first_cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= test_case; t++) {
			N = sc.nextInt();
			nums = new int[N];
			op_list = new char[N-1];
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			op_visit = new HashSet<String>();
			visit_cnt = 0;
			first_cnt = 0;
			
			int idx = 0;
			for (int i = 0; i < 4; i++) {
				int cnt = sc.nextInt();
				for (int j = 0; j < cnt; j++) {
					op_list[idx++] = op[i];
				}
			}
			
			for (int i = 0; i < N; i++) nums[i] = sc.nextInt();
			
			permutation(0, new char[N-1], new boolean[N-1]);
			calculate();
			sb.append("#").append(t).append(" ").append(max-min).append("\n");
//			System.out.println("visit cnt : " + visit_cnt);
//			System.out.println("first cnt : " + first_cnt);
		}
		System.out.println(sb.toString());
		sc.close();
	}

	private static void permutation(int k, char[] sel, boolean[] v) {
		if (k == sel.length) {
			// 여기서 연산 하는 함수 호출
			String opStr = String.valueOf(sel);
//			op_visit.add(opStr);
			if (op_visit.add(opStr)) {
				first_cnt++;
			} else {
				visit_cnt++;
			}
			return;
		}
		
		// sort가 되면 전거를 저장해놓고 전게 나오면 continue
		// 넣은
		int past = -1;
		
		for (int i = 0; i < op_list.length; i++) {
			if (v[i]) continue;
			if (past == op_list[i]) continue;
			v[i] = true;
			past = op_list[i];
			sel[k] = op_list[i];
			permutation(k+1, sel, v);
			v[i] = false;
		}
		
	}

	private static void calculate() {
		for (String str : op_visit) {
			int temp = nums[0];
			
			for (int i = 1; i < nums.length; i++) {
				// nums는 i, op는 i-1
				int n = nums[i];
				char operation = str.charAt(i-1);
				switch(operation) {
				case '+' :
					temp += n; break;
				case '-' :
					temp -= n; break;
				case '*' :
					temp *= n; break;
				case '/' :
					temp /= n; break;
				}
			}
			
			int result = temp;
			
			max = Math.max(max, result);
			min = Math.min(min, result);
		}
		
		// 곱하기가 5개 있는데 
		// * * * * * 이 안에서도 순열이 돌잖아
		// 순열 구한거에서 중복한걸 빼줘야하네
		// 연산자의 순서가 똑같다면 결과도 똑같지, 근데 연산자가 2개이상이면 위 가능성이 무조건 있음
	}
}

