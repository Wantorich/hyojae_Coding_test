import java.util.*;

class Homework implements Comparable<Homework>{
    String name;
    int strTime, duration;
    
    Homework (String name, String time, String duration) {
        this.name = name;
        this.duration = Integer.parseInt(duration);
        this.strTime = Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
    }
    
    @Override
    public int compareTo(Homework h) {
        return Integer.compare(this.strTime, h.strTime);
    }
}

class Solution {
    public String[] solution(String[][] plans) {
        List<String> finishList = new ArrayList<String>();
        List<Homework> hwList = new ArrayList<Homework>();
        for (String [] plan : plans) hwList.add(new Homework(plan[0], plan[1], plan[2]));
        Collections.sort(hwList);
        
        ArrayDeque<Homework> pq = new ArrayDeque<Homework>(hwList);
        
        // 시작시간 제일 적은것을 기준으로 초기화, 오프셋만 남김
        int minTime = pq.peek().strTime;
        for (Homework h : pq) h.strTime -= minTime;
        
        // 임시 스택
        Stack<Homework> tempStack = new Stack<Homework>();
        
        int time = 0;
        while (!pq.isEmpty()) {
            Homework curr = pq.poll();
            time = curr.strTime;
            // System.out.println("curr duration : " + curr.duration);
            
            if (pq.isEmpty()) {
                // 뽑은게 마지막거 일때
                // time += curr.duration;
                finishList.add(curr.name);
                continue;
            }
            
            // 현재 숙제의 종료시간과 다음 대기 숙제의 시작시간을 비교
            if (time + curr.duration <= pq.peek().strTime) {
                time += curr.duration;
                finishList.add(curr.name);
                
                // 대기스택과 다음 Q의 시간을 비교
                if (time == pq.peek().strTime) continue; // 바로 다음거 뽑음
                
                // 대기 스택에서 넣을게 있는지 확인
                if (!tempStack.isEmpty()) {
                    Homework next = tempStack.pop();
                    next.strTime = time;
                    pq.offerFirst(next);
                }
                continue;
            } else {
                // 다 못마치고 교체해야할때
                // 일단 할수있는데까지 함
                int diff = pq.peek().strTime - time;
                curr.duration -= diff;
                tempStack.push(curr);
                time += diff;
            }
        }
        
        // stack에는 아직 남아있을 수있음, 순서대로 뽑아서 더함
        while (!tempStack.isEmpty()) finishList.add(tempStack.pop().name);
        
        return finishList.toArray(new String[0]);
    }
}