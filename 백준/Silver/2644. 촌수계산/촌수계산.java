import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class Node {
	ArrayList<Node> siblings = new ArrayList<>();
	ArrayList<Node> childrens = new ArrayList<>();
	Node parent;
	int n_idx;
	
	Node(int idx) {
		this.n_idx = idx;
	}
}

public class Main {
	
    static boolean [] isvisited;
    static int N, result = -1, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        Node[] nodes = new Node[N+1];
        for (int i = 1; i < N+1; i++) {
        	nodes[i] = new Node(i);
        }
        isvisited = new boolean[N+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int str = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        
        int M = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	nodes[a].childrens.add(nodes[b]); // 자식으로 추가
        	nodes[b].parent = nodes[a];
        }
        
        dfs(nodes[str], nodes[end], 0);
        System.out.println(result);
    }
    
    static void dfs(Node s, Node e, int cnt) {
    	if (s == null || isvisited[s.n_idx]) return;
    	
    	isvisited[s.n_idx] = true;
    	
    	if (s.parent == e || s.childrens.contains(e)) {
    		result = cnt+1;
    		return;
    	}
    	
    	if (s.parent != null && s.parent.childrens.contains(e)) {
    		result = cnt+2;
    		return;
    	}
    	
		dfs(s.parent, e, cnt+1);
	
    	for (Node child : s.childrens) {
			dfs(child, e, cnt+1);
    	}
    }
}

