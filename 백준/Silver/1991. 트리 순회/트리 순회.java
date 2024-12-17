import java.util.*;

public class Main {
	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.nextLine();
		Map<Character, Node> tree = new HashMap<>();
		for (char alpha = 'A'; alpha <= 'Z'; alpha++) 
			tree.put(alpha, new Node(alpha));
		
		for (int i = 0; i < N; i++) {
			char parent = sc.next().charAt(0);
			String left = sc.next();
			String right = sc.next();
			
			if (!left.equals("."))
				tree.get(parent).left = tree.get(left.charAt(0));
			if (!right.equals("."))
			tree.get(parent).right = tree.get(right.charAt(0));
		}
		
		preorder(tree, tree.get('A'));
		System.out.println(sb.toString());
		sb.setLength(0);
		inorder(tree, tree.get('A'));
		System.out.println(sb.toString());
		sb.setLength(0);
		postorder(tree, tree.get('A'));
		System.out.println(sb.toString());
		sb.setLength(0);
		sc.close();
	}

	private static void postorder(Map<Character, Node> tree, Node curr) {
		if (curr.left != null) 
			postorder(tree, curr.left);
		if (curr.right != null) 
			postorder(tree, curr.right);
		sb.append(curr.value);
	}

	private static void inorder(Map<Character, Node> tree, Node curr) {
		if (curr.left != null) 
			inorder(tree, curr.left);
		sb.append(curr.value);
		if (curr.right != null) 
			inorder(tree, curr.right);
	}

	private static void preorder(Map<Character, Node> tree, Node curr) {
		sb.append(curr.value);
		if (curr.left != null) 
			preorder(tree, curr.left);
		if (curr.right != null) 
			preorder(tree, curr.right);
	}
}

class Node {
	char value;
	Node left, right;
	
	Node(char value) {
		this.value = value;
	}
}
