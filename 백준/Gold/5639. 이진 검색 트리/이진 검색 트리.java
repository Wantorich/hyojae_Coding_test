import java.util.*;
import java.util.stream.Collectors;

public class Main {
	static StringBuilder sb;
	static int treeSize;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Integer> preOrder = new ArrayList<>();
		sb = new StringBuilder();
		String line;
		
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			if (line.isEmpty()) break;
			preOrder.add(Integer.parseInt(line));
		}
		
		TreeSet<Integer> ts = new TreeSet<>();
		ts.addAll(preOrder);
		List<Integer> inOrder = ts.stream().collect(Collectors.toList());
		treeSize = inOrder.size();
		
		printPostOrder(preOrder, inOrder);
		System.out.println(sb.toString());
		sc.close();
	}

	private static void printPostOrder(List<Integer> preOrder, List<Integer> inOrder) {
		if (preOrder.isEmpty()) return;
		
		int root = preOrder.get(0);
		int treeSize = preOrder.size();
		
		int rootIdx = Collections.binarySearch(inOrder, root);
		int L = rootIdx;
		
		// left subTree
		printPostOrder(preOrder.subList(1, 1+L), inOrder.subList(0, L));
		// right subTree
		printPostOrder(preOrder.subList(L+1, treeSize), inOrder.subList(L+1, treeSize));
		// curr Node
		sb.append(root).append("\n");
	}
}
