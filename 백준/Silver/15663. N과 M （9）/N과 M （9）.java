import java.util.*;

public class Main {
	static StringBuffer sb = new StringBuffer();
	static int [] arr;
	static Set<String> set = new LinkedHashSet<String>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		arr = new int[N];

		for (int i = 0; i < N; i++) 
			arr[i] = sc.nextInt();

		Arrays.sort(arr);
		permutation(new int[M], 0, new boolean[N]);
		printResult();
		sc.close();
	}
	
	private static void printResult() {
		for (String num_str : set) {
			StringTokenizer st = new StringTokenizer(num_str, "[],");
			while (st.hasMoreTokens()) {
				sb.append(st.nextToken().trim());
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void permutation(int[] sel, int k, boolean [] v) {
		if (k == sel.length) {
			set.add(Arrays.toString(sel));
			return;
		}

        int past = -1;
		for (int i = 0; i < arr.length; i++) {
			if (!v[i]) {
                if (past == arr[i]) continue;
				v[i] = true;
                past = arr[i];
				sel[k] = arr[i];
				permutation(sel, k + 1, v);
				v[i] = false;
			}
		}
	}
}
