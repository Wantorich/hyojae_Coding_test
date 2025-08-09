import java.util.*;
import java.awt.Point;

class Robot {
    Point pos;
    int index;
    int[] route;
    boolean isActive;
    
    Robot (int[] pos, int[] route) {
        this.pos = new Point(pos[0], pos[1]);
        this.route = route;
        isActive = true;
    }
    
    Point move(int[][] points) {
        if (!isActive) return null;
        
        Point next = new Point(points[route[index]-1][0], points[route[index]-1][1]);

        if (pos.x != next.x) {
            this.pos.x = next.x > this.pos.x ? this.pos.x + 1 : this.pos.x - 1;
        } else if (next.y != this.pos.y){
            this.pos.y = next.y > this.pos.y ? this.pos.y + 1 : this.pos.y - 1;
        }
        
        if (this.pos.equals(next)) {
            if (index == route.length - 1) {
                isActive = false; 
            }
            index++;
        }
        
        return this.pos;
    }
}

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        List<Robot> robotList = new ArrayList<>();
        for (int[] route : routes) {
            int[] startP = points[route[0]-1];
            robotList.add(new Robot(startP, route));
        }
        
        Map<Point, Integer> map = new HashMap<>();
        boolean flag = true;
        while (flag) {
            flag = false;
            for (Robot robot : robotList) {
                if (!robot.isActive) continue;
                flag = true;
                Point point = robot.move(points);
                Integer value = map.get(point);
                if (value == null) {
                    map.put(point, 1);
                } else if (value == 1) {
                    map.put(point, 2);
                    answer++;
                } else if (value > 1) {
                    map.put(point, value + 1);
                }
            }
            map.clear();
        }
        return answer;
    }
}

/*
전형적인 구현 문제, 먼저 로봇을 전부 이동시키고
문제는 한번에 다 처리를 해줘야된다는거임 
Map에 포인트로 저장한다 
이동이야 r먼저 이동하고 c 이동하면 되는거고 각 로봇마다 활동중인지 판단하는거 있어야하고
*/