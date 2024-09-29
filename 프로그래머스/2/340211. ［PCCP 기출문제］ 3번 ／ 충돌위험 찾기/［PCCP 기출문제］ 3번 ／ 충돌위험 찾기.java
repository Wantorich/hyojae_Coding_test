import java.util.*;

class Robot {
    int r, c;
    boolean status = true;
    Queue<int[]> routeQ;
    
    Robot(int r, int c) {
        this.r = r;
        this.c = c;
        routeQ = new LinkedList<int[]>();
    }
    
    void addRoute(int[] route) {
        routeQ.offer(new int[]{route[0], route[1]});
    }
    
    void move(int[][] map) {
        if (routeQ.isEmpty()) {
            map[r][c]--;
            status = false;
            return;
        }
        
        int [] dest = routeQ.peek();
        map[r][c]--;
        
        if (r < dest[0]) r++;
        else if (r > dest[0]) r--;
        else if (c < dest[1]) c++;
        else if (c > dest[1]) c--;
        
        if (r == dest[0] && c == dest[1]) {
            routeQ.poll();
        }
        
        map[r][c]++;
    }
}

class Solution {
    static int map[][];
    static int SIZE = 102;
    
    public int solution(int[][] points, int[][] routes) {
        map = new int[SIZE][SIZE];
        int answer = 0;
        
        List<Robot> robots = new ArrayList();
        for (int i = 0; i < routes.length; i++) {
            int [] route = routes[i];
            int pointIdx = route[0];
            int sr = points[pointIdx-1][0];
            int sc = points[pointIdx-1][1];
            map[sr][sc]++;
            Robot robot = new Robot(sr,sc);
            for (int j = 1; j < route.length; j++) {
                pointIdx = route[j];
                robot.addRoute(points[pointIdx-1]);
            }
            robots.add(robot);
        }
        
        // 충돌한 놈 있나 확인하기
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] >= 2) answer++;
            }
        }
        
        while (robots.size() > 0) {
            for (int i = 0; i < robots.size(); i++) {
                if (!robots.get(i).status) {
                    robots.remove(i--);
                    continue;
                }
                robots.get(i).move(map);
            }
            
            // 충돌한 놈 있나 확인하기
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (map[i][j] >= 2) answer++;
                }
            }
        }
        
        return answer;
    }
}