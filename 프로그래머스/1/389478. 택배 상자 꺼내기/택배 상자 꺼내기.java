import java.util.*;

class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        ArrayDeque<Integer>[] stacks = new ArrayDeque[w];
        for (int i = 0; i < stacks.length; i++)
            stacks[i] = new ArrayDeque<>();
        
        int p = 1;
        boolean stage = true;
        int idx = 0;
        while (p <= n) {
            for (int i = p; i < p + w && i <= n; i++) {
                stacks[idx].push(i);
                if (i != p + w - 1) {
                    idx = stage ? idx + 1 : idx - 1;
                }
            }
            p += w;
            stage = !stage;
        }
        
        for (ArrayDeque<Integer> stack : stacks) {
            int cnt = 0;
            while (!stack.isEmpty() && stack.peek() != num) {
                stack.pop();
                cnt++;
            }
            if (!stack.isEmpty()) {
                answer = cnt + 1;
                break;
            }
        }
        return answer;
    }
}

/*
스택을 여러개 쓴다?
n은 1부터 n까지, 이중 포문
스택 인덱스를 +1 , -1로 관리

*/