import java.util.*;

public class Solution {
    static long N;
    static long result;

public static void main(String[] args)  {
    Scanner sc = new Scanner(System.in);
    int test_case = sc.nextInt();
    StringBuilder sb = new StringBuilder();

    for (int t = 1; t <= test_case; t++) {
        N = sc.nextLong();
        result = Long.MAX_VALUE;
        
        Stack<long[]> q = new Stack<long[]>();
        q.push(new long[] {N, 0});
        
        while (!q.isEmpty()) {
            long [] info = q.pop();
            long val = info[0];
            long cnt = info[1];
            
            if (val == 2) {
                result = cnt;
                break;
            }
            
            long sqrt = (long) Math.sqrt(val);
            
            
            
            if (sqrt * sqrt == val) {
                // sqrt가 제곱근이라면
                q.push(new long[] {sqrt, cnt+1});
                continue;
            }
            
            // 제곱근이 아니다 그럼 다음 제곱근을 넣자
            long nextVal = (sqrt+1) * (sqrt+1);
            q.push(new long[] {nextVal, cnt + nextVal - val});
        }
        sb.append("#").append(t).append(" ").append(result).append("\n");
    }
    System.out.println(sb.toString());
    sc.close();
}
}