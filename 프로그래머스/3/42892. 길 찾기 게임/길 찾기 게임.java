import java.util.*;

class Node {
    int x, y, idx, num;
    Node left, right;
    
    Node(int x, int y, int num) {
        this.x = x;
        this.y = y;
        this.num = num;
    }
    
    int getX() {
        return x;
    }
    
    int getY() {
        return y;
    }
}

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        Node[] nodes = new Node[nodeinfo.length];
        TreeMap<Integer, List<Node>> nodeMap = new TreeMap<>(); // 키는 y
        for (int i = 0; i < nodeinfo.length; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            nodes[i] = new Node(x, y, i+1);
            nodeMap.putIfAbsent(y, new ArrayList<>());
            nodeMap.get(y).add(nodes[i]);
        }
        Arrays.sort(nodes, Comparator.comparingInt(Node::getX));
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].idx = i; // 인덱스 삽입
            // System.out.println("x : " + node.x + ", y : " + node.y);
        }
        
        Node root = nodeMap.lastEntry().getValue().get(0);
        // System.out.println("x : " + root.x + " rootY : " + root.y);
        // 재귀 돌림, node 완성하기위해
        recursive(root, -1, nodes.length, nodeMap);
        
        List<Integer> temp = new ArrayList<>();
        preOrder(root, temp);
        answer[0] = temp.stream().mapToInt(Integer::intValue).toArray();
        temp.clear();
        postOrder(root, temp);
        answer[1] = temp.stream().mapToInt(Integer::intValue).toArray();
        
        return answer;
    }
    
    static void preOrder(Node node, List<Integer> list) {
        // 전위 순회 나, 왼, 오
        if (node == null) return;
        
        list.add(node.num);
        preOrder(node.left, list);
        preOrder(node.right, list);
    }
    
    static void postOrder(Node node, List<Integer> list) {
        // 전위 순회 나, 왼, 오
        if (node == null) return;
        
        postOrder(node.left, list);
        postOrder(node.right, list);
        list.add(node.num);
    }
    
    static void recursive(Node curr, int left, int right, TreeMap<Integer, List<Node>> nodeMap) {
        int height = curr.y;
        if (nodeMap.lowerKey(height) == null)
            return;
        List<Node> nodes = nodeMap.lowerEntry(height).getValue();
        for (Node node : nodes) {
            if (node.idx < curr.idx && node.idx > left) {
                // 왼쪽으론 자기보다 작고 left보다 커야함
                curr.left = node;
                // System.out.println("left Node : " + node.x + ":" + node.y);
                recursive(node, left, curr.idx, nodeMap);
            }
            if (node.idx > curr.idx && node.idx < right) {
                // 오른쪽으론 자기보다 크고 right보다 작아야함
                curr.right = node;
                // System.out.println("right Node : " + node.x + ":" + node.y);
                recursive(node, curr.idx, right, nodeMap);
            }
        }
    }
}

/*
y좌표가 가장 큰것이 루트네
중위순회는 구할수있음
그럼 이걸 가지고 전위와 후위를 구할수가있나?
6 9 4 1 5 8 7 2 3

treeset이나 map으로 y축 기준 정렬을 하고
루트부터 시작해서
이진트리니까 내 밑에 최대 2개임
각각 빨대 뽑고 재귀
근데 왼쪽자식과 오른쪽 자식의 유효범위가 존재해야함

*/