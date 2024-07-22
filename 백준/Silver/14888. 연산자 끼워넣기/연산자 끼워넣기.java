import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static boolean [] v;
	static ArrayList<Character> op_arr;
	static char [][] op_pm;
	static int op_lens = 0, idx = 0;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int [] nums = new int[N];
		for (int i = 0; i < N; i++) 
			nums[i] = sc.nextInt();
		
		op_arr = new ArrayList<Character>();
		char [] ops = {'+', '-', '*', '/'};
		for (int i = 0; i < 4; i++) {
			int op_num = sc.nextInt();
			for (int j = 0; j < op_num; j++) {
				op_arr.add(ops[i]);
			}
			op_lens += op_num;
		}
		
		op_pm = new char[factorial(op_lens)][op_lens];
		v = new boolean[op_lens];
		
		getPm(new char[op_lens], 0);
		
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, result;
		for (int i = 0; i < factorial(op_lens); i++) {
			result = nums[0];
			for (int j = 0; j < op_lens; j++) {
				int or = nums[j+1];
				switch (op_pm[i][j]) {
					case '+':
						result += or;
						break;
					case '-':
						result -= or;
						break;
					case '*':
						result *= or;
						break;
					case '/':
						result /= or;
				}
			}
			max = Math.max(max, result);
			min = Math.min(min, result);
		}
		
		System.out.printf("%d\n%d", max, min);
	}
	
	static int factorial(int n) {
		return n == 1 ? 1 : n * factorial(n-1);
	}
	
	static void getPm(char [] arr, int k) {
		if (k == op_lens) {
			System.arraycopy(arr, 0, op_pm[idx++], 0, arr.length);
			return; 
		}
		
		for (int i = 0; i < v.length; i++) {
			if (!v[i]) {
				v[i] = true;
				arr[k] = op_arr.get(i);
				getPm(arr, k+1);
				v[i] = false;
			}
		}
	}
}

