import java.util.*;

public class Main {
	static int N, max_weight, remote_node;
	static boolean v[];
	static ArrayList<ArrayList<int[]>> adj = new ArrayList<ArrayList<int[]>>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		for (int i = 0; i <= N; i++) {
			adj.add(new ArrayList<int[]>());
			// 출발점이 1부터 12까지의 arrList가 생성됌
		}
		
		
		for (int i = 1; i < N; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int w = sc.nextInt();
			adj.get(a).add(new int[] {b, w});
			adj.get(b).add(new int[] {a, w});
		}
		
		v = new boolean[N+1];
		max_weight = Integer.MIN_VALUE;
		v[1] = true;
		getRemoteNode(1, 0);
		
		Arrays.fill(v, false);
		max_weight = Integer.MIN_VALUE;
		v[remote_node] = true;
		getRemoteNode(remote_node, 0);
		
		System.out.println(max_weight);
		sc.close();
	}

	private static void getRemoteNode(int node, int weight) {
		if (weight > max_weight) {
			max_weight = weight;
			remote_node = node;
		}
		
		for (int[] node_info : adj.get(node)) {
			int currNode = node_info[0];
			int currWeight = node_info[1];
			
			if (v[currNode]) continue;
			
			v[currNode] = true;
			getRemoteNode(currNode, weight + currWeight);
		}
	}
}
