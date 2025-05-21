import java.util.*;

class Solution {
    static final char OPEN = '(';
    static final char CLOSE = ')';
    static int answer = 0;
    static int MAX_OPEN;
    public int solution(int n) {
        MAX_OPEN = n;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        dfs(n, 0, stack);
        return answer;
    }
    
    static void dfs(int remain, int curr, ArrayDeque<Character> stack) {
        if (remain == 0 && curr == MAX_OPEN && stack.isEmpty()) {
            answer++;
            return;
        }
        
        // 결국 내가 하는 선택은 여는 브라켓 혹은 닫는 브라켓을 추가하는거임
        // 여는 브라켓
        if (curr < MAX_OPEN) {
            ArrayDeque<Character> st = new ArrayDeque<>(stack);
            st.push(OPEN);
            dfs(remain, curr+1, st);
        }
        
        // 닫는 브라켓
        if (!stack.isEmpty()) {
            if (stack.peek() == OPEN) {
                stack.pop();
                dfs(remain-1, curr, stack);
            } 
        }
    }
}

/*
1 -> () 
2 -> (()), ()()

백트래킹
((
()
스택으로 쓰고 () -> n 하나 줄이고

스택 비면 무조건 (

그 이외는 ( 나 ) 둘다 가능한거아님?

근데 ( 이것도 무작정 넣으면 안되는게 넣을때마다 count 늘리고
n까지만 넣어야함

count 다 차면 무조건 ) 만 넣어야해
*/