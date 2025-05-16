import java.util.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] info, int n, int m) {
        Arrays.sort(info, (arr1, arr2) -> Double.compare((double) arr2[0] / arr2[1],
                                                         (double) arr1[0] / arr1[1]));
        // for (int [] row : info) {
        //     System.out.println(Arrays.toString(row));
        // }
        dfs(0, 0, 0, info, n, m);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    static void dfs(int idx, int a, int b, int[][] info, int n, int m) {
        if (answer <= a) {
            return;
        }
        
        if (idx == info.length) {
            answer = Math.min(answer, a);
            return;
        }
        
        if (info[idx][1] + b < m) {
            dfs(idx+1, a, b + info[idx][1], info, n, m);
        }
        
        if (info[idx][0] + a < n) {
            dfs(idx+1, a + info[idx][0], b, info, n, m);
        }
    }
}

/*
물건을 훔쳐야한다면 당연히 흔적을 더 적게 남기는 도둑으로 훔치는게 맞음
근데 그러면서 A도둑이 최소의 흔적이 남아야함
즉 B도둑에게 최대의 일감을 몰아줘야함
2^40 = 2 ^ 10 ^ 4 = 1000^ 4 = 10^12
최대라 그리디적인 느낌인데
B가 훔칠수 있는 경우의 수가 여러가지다.
백트래킹을 하는데 정렬을 해서 앞에서부터 B가 다 가져가는 경우를 첫번쨰로 보고
이후에 가지치기를 하면 많이 줄일 수 잇나?
결국 배열의 처음부터 끝까지는 돌아야하고
그럼 배열의 인덱스와 현재 A, B의 흔적이 중요하네

결국 제한된 M안에서 B가 훔칠수 잇는 MAX를 찾아내야하는데.. 가 아님
A의 min을 찾아야하니까
전체 A의 총량과 B의 총량을 구하고
하나씩 보면서 A에 넣고 작은것부터..

남은거 B에 짬떄려도 되는지? 안되는지? 판단

즉 A 흔적 작은순으로 정렬하고.. 하나씩 넣으면서 되는지 안되는지 판단
[1, 1], [2, 3]
가지가 2개 생기는건 똑같음.

1 1 2 2 2 3

1, 2, 3 3개밖에 없다.

2는 1 2개
3는 1 3개

*/