import java.util.*;
import java.util.Map.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        // Arrays.sort(A);
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int num : B) {
            tm.putIfAbsent(num, 0);
            tm.compute(num, (k, v) -> v+1);
        }
        int b;
        for (int a : A) {
            if (tm.higherEntry(a) != null) {
                // 이길 수 있으면
                int key = tm.higherKey(a);
                tm.compute(key, (k, v) -> v-1);
                if (tm.get(key) == 0) {
                    tm.remove(key);
                }
                answer++;
            } else {
                // 못이기면 가장 낮은거 버림
                tm.pollFirstEntry();
            }
        }
        return answer;
    }
}

/*

B의 원소를 순서있이 모두 나열하는 경우의 수가 완탐 (100000)!
원소 합은 Long타입으로 해야하네

이길떄마다 1점이니까 핵심은 최대한 적은 차이로 이기는게 목적
B를 treeset에 넣고

근데 내가 지는 판도 있을거임. 그럴땐 어떻게해야할까
질떈 가장 낮은 카드를 버려야함

즉 큰게 있으면 내고 없으면 가장 낮은거 버림

A를 정렬을 해야할까?

*/