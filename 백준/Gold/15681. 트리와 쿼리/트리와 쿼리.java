import java.io.*;
import java.util.*;

public class Main {
    static Node[] nodes;
    static int[] nodeCnts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        nodes = new Node[N + 1];
        for (int i = 0; i <= N; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nodes[from].connect.add(nodes[to]);
            nodes[to].connect.add(nodes[from]);
        }

        // make Tree
        makeTree(nodes[R], new Node(-1));

        // subtree node count
        nodeCnts = new int[N + 1];
        countNode(nodes[R]);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int index = Integer.parseInt(br.readLine());
            sb.append(nodeCnts[index]).append("\n");
        }

        System.out.print(sb.toString());
    }

    private static int countNode(Node curr) {
        int cnt = 1; // 자신은 기본 포함
        for (Node child : curr.children) {
            cnt += countNode(child);
        }
        return nodeCnts[curr.num] = cnt;
    }

    private static void makeTree(Node curr, Node parent) {
        for (Node child : curr.connect) {
            if (child.num == parent.num)
                continue;

            curr.children.add(child);
            child.parent = curr;

            // recursive
            makeTree(child, curr);
        }
    }
}

class Node {
    int num;
    Node parent;
    Set<Node> children = new HashSet<>();
    Set<Node> connect = new HashSet<>();

    Node(int num) {
        this.num = num;
    }
}
