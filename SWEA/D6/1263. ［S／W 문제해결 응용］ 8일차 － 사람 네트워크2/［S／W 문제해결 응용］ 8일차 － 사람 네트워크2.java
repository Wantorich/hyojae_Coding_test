import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for (int t = 1; t <= tc; t++) {
        	int N = sc.nextInt();
        	int [][] matrix = new int[N][N];
        	for (int i = 0; i < N; i++)
        		for (int j = 0; j < N; j++) matrix[i][j] = sc.nextInt();
        	
        	int [][] disMatrix = new int[N][N];
        	
        	for (int i = 0; i < N; i++) {
        		Queue<Integer> q = new LinkedList<Integer>();
        		boolean [] visit = new boolean[N];
        		visit[i] = true;
        		
        		for (int j = 0; j < N; j++) {
        			if (i == j) continue;
        			if (matrix[i][j] == 1) {
        				q.offer(j);
        				visit[j] = true;
        			}
        		}
        		
        		int dis = 1;
        		while (!q.isEmpty()) {
        			int qSize = q.size();
        			for (int s = 0; s < qSize; s++) {
        				int curr = q.poll();
        				disMatrix[i][curr] = dis;
        				
        				for (int k = 0; k < N; k++) {
        					if (matrix[curr][k] == 0 || visit[k]) continue;
        					visit[k] = true;
        					q.offer(k);
        				}
        			}
        			dis++;
        		}
        	}
        	
        	int minDis = Integer.MAX_VALUE;
        	for (int [] row : disMatrix) {
        		minDis = Math.min(minDis, Arrays.stream(row).sum());
        	}
        	
        	System.out.printf("#%d %d\n", t, minDis);
        }
        sc.close();
    }
}