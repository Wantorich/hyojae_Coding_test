import java.util.*;

class Solution
{
    static List<Integer>[] adjList;
    static int result, N, M;
    static boolean[] visit;
    
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(int test_case = 1; test_case <= tc; test_case++)
        {
            N = sc.nextInt();
            M = sc.nextInt();
            adjList = new ArrayList[N+1];
            visit = new boolean[N+1];
            for (int i = 0; i < adjList.length; i++)
                adjList[i] = new ArrayList<>();
            result = 0;
            
            for (int i = 0; i < M; i++) {
                int from = sc.nextInt() - 1;
                int to = sc.nextInt() - 1;
                adjList[from].add(to);
                adjList[to].add(from);
            }
            
            for (int i = 0; i < N; i++) {
               Arrays.fill(visit, false);
               visit[i] = true;
               dfs(i, 1); 
            }
            
            System.out.printf("#%d %d\n", test_case, result);
        }
        sc.close();
    }
    
    static void dfs(int v, int cnt) {
      
        for (Integer next : adjList[v]) {
            if (visit[next]) continue;
            visit[next] = true;
            dfs(next, cnt+1);
            visit[next] = false;
        }
        
        result = Math.max(result, cnt);
    }
}
