import java.util.*;

public class Solution {
	static char [] arr, sel;
	static boolean [] v;
	static boolean find;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int test_case = sc.nextInt();
		sc.nextLine();
		for (int t = 1; t <= test_case; t++) {
			arr = sc.nextLine().toCharArray();
			sel = new char[6];
			v = new boolean[6];
			find =false;
			permutation(0);
			System.out.printf("#%d %b\n", t, find);
		}
		
		sc.close();
	}

	private static void permutation(int k) {
		if (k == sel.length) {
			if (babygin(sel)) find = true;
			return;
		}
		
		if (find) return;
		
		
		for (int i = 0; i < arr.length; i++) {
			if (v[i]) continue;
			v[i] = true;
			sel[k] = arr[i];
			permutation(k+1);
			v[i] = false;
		}
	}

	private static boolean babygin(char[] array) {
		boolean cond1 = array[0] == array[1] && array[1] == array[2];
		boolean cond2 = array[1] - array[0] == array[2] - array[1];
		boolean cond3 = array[3] == array[4] && array[4] == array[5];
		boolean cond4 = array[4] - array[3] == array[5] - array[4];
		
		return (cond1 || cond2) && (cond3 || cond4);
	}
}
