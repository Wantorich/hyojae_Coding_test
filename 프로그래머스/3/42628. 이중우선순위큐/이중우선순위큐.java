import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());
        StringTokenizer st;
        for (String operation : operations) {
            st = new StringTokenizer(operation, " ");
            switch (st.nextToken()) {
                case "I" -> {
                    int num = Integer.parseInt(st.nextToken());
                    minQueue.offer(num);
                    maxQueue.offer(num);
                }
                case "D" -> {
                    if (st.nextToken().equals("1")) {
                        // maxQueue del
                        if (!maxQueue.isEmpty()) {
                            int del = maxQueue.poll();
                            minQueue.remove(del);
                        }
                    } else {
                        // minQueue del
                        if (!minQueue.isEmpty()) {
                            int del = minQueue.poll();
                            maxQueue.remove(del);
                        }
                    }
                }
            }
        }
        if (!maxQueue.isEmpty()) {
            answer[0] = maxQueue.poll();
            answer[1] = minQueue.poll();
        }
        return answer;
    }
}