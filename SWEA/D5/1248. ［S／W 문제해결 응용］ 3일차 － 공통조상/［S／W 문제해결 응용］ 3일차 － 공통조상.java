import java.util.*;

class Node {
	int num, rank;
	Node left, right, parent;
	
	Node (int num) {
		this.num = num;
		if (num == 1) this.rank = 1;
	}
	
	void addNode (Node child) {
		if (left == null) left = child;
		else right = child;
	}
}

public class Solution {
	static int V, E, target1, target2;
	static Node[] nodes;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for (int t = 1; t <= tc; t++) {
			V = sc.nextInt();
			E = sc.nextInt();
			target1 = sc.nextInt();
			target2 = sc.nextInt();
			
			nodes = new Node[V+1];
			for (int i = 0; i < E; i++) {
				int parent = sc.nextInt();
				int child = sc.nextInt();
				
				if (nodes[parent] == null) {
					nodes[parent] = new Node(parent);
				}
				if (nodes[child] == null) {
					nodes[child] = new Node(child);
				}
				
				nodes[parent].addNode(nodes[child]);
				nodes[child].parent = nodes[parent];
			}
			
			updateRank(nodes[1]);
			
			// rank가 같은 부모노드 찾기
			Node p1 = nodes[target1];
			Node p2 = nodes[target2];
			
			while (p1.num != p2.num) {
				if (p1.rank == p2.rank) {
					p1 = p1.parent;
				} else if (p1.rank < p2.rank) {
					while (p1.rank != p2.rank) p2 = p2.parent;
				} else {
					while (p1.rank != p2.rank) p1 = p1.parent;
				}
			}
			
			Node ancestor = p1;
			System.out.printf("#%d %d %d\n", t, ancestor.num, treversal(ancestor));
			// 개수 찾기
		}
		
		sc.close();
	}
	
	static void updateRank(Node root) {
		if (root == null) return;
		
		if (root.left != null) {
			root.left.rank = root.rank + 1;
			updateRank(root.left);
		}
		if (root.right != null) {
			root.right.rank = root.rank + 1;
			updateRank(root.right);
		}
	}
	
	static int treversal(Node n) {
		if (n == null) return 0;
		return treversal(n.left) + treversal(n.right) + 1;
	}

}
