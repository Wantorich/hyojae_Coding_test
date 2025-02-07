import java.util.*;

class Node implements Comparable<Node> {
    int value;
    int classIdx;
    int pointer;

    public Node(int value, int classIdx, int pointer) {
        this.value = value;
        this.classIdx = classIdx;
        this.pointer = pointer;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.value, other.value);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] classes = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                classes[i][j] = sc.nextInt();
            }
            Arrays.sort(classes[i]);
        }
        
        PriorityQueue<Node> heap = new PriorityQueue<>();
        int currentMax = Integer.MIN_VALUE;
        
        for (int i = 0; i < N; i++) {
            int val = classes[i][0];
            heap.add(new Node(val, i, 0));
            currentMax = Math.max(currentMax, val);
        }
        
        int minRange = Integer.MAX_VALUE;
        
        while (true) {
            Node minNode = heap.poll();
            minRange = Math.min(minRange, currentMax - minNode.value);
            
            if (minNode.pointer == M - 1) break;
            
            int nextVal = classes[minNode.classIdx][minNode.pointer + 1];
            heap.add(new Node(nextVal, minNode.classIdx, minNode.pointer + 1));
            currentMax = Math.max(currentMax, nextVal);
        }
        
        System.out.println(minRange);
    }
}
