import java.util.*;

public class Main {
    static class Edge {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 교차점의 개수
        int m = sc.nextInt(); // 골목길의 개수

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges.add(new Edge(u, v, w));
        }

        long[] dist = new long[n + 1];
        int[] prev = new int[n + 1];
        Arrays.fill(dist, Long.MIN_VALUE);
        dist[1] = 0;
        Arrays.fill(prev, -1);

        // 벨만-포드 알고리즘
        for (int i = 1; i <= n; i++) {
            for (Edge edge : edges) {
                if (dist[edge.from] != Long.MIN_VALUE && dist[edge.from] + edge.weight > dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                    prev[edge.to] = edge.from;

                    // n번째 반복에서 값이 갱신되면 음수 사이클 존재
                    if (i == n) {
                        if (hasPathToCycle(n, edges, edge.to)) {
                            System.out.println("-1");
                            return;
                        }
                    }
                }
            }
        }

        // 경로 추적
        if (dist[n] == Long.MIN_VALUE) {
            System.out.println("-1");
        } else {
            List<Integer> path = new ArrayList<>();
            for (int cur = n; cur != -1; cur = prev[cur]) {
                path.add(cur);
            }
            Collections.reverse(path);
            for (int p : path) {
                System.out.print(p + " ");
            }
        }
    }

    // 음수 사이클로 가는 경로가 있는지 확인
    private static boolean hasPathToCycle(int n, List<Edge> edges, int start) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == n) return true;

            visited[node] = true;
            for (Edge edge : edges) {
                if (edge.from == node && !visited[edge.to]) {
                    queue.add(edge.to);
                }
            }
        }

        return false;
    }
}
