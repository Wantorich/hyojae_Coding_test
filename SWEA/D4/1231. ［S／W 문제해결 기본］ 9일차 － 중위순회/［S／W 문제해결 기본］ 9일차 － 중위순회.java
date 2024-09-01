import java.util.*;

class Node {
	String str;
	int left, right;
	
	Node (String str) {
		this.str = str;
	}
}

public class Solution {
	static Node[] nodes;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		int tc = sc.nextInt();
		
		for (int t = 1; t <= 10; t++) {
			int N = sc.nextInt();
			nodes = new Node[N+1];
			
			sc.nextLine();
			for (int i = 0; i < N; i++) {
				String[] input = sc.nextLine().trim().split(" ");
				int index = Integer.parseInt(input[0]);
				nodes[index] = new Node(input[1]);
				if (input.length >= 3) {
					nodes[index].left = Integer.parseInt(input[2]);
					if (input.length == 4) {
						nodes[index].right = Integer.parseInt(input[3]);
					}
				}
				
			}
			sb.setLength(0);
			inorderTreversal(nodes[1]);
			System.out.printf("#%d %s\n", t, sb.toString());
		}
		sc.close();
	}

	private static void inorderTreversal(Node node) {
		if (node == null) return;
		
		Node leftNode = nodes[node.left];
		Node rightNode = nodes[node.right];
		
		inorderTreversal(leftNode);
		sb.append(node.str);
		inorderTreversal(rightNode);
	}

}
