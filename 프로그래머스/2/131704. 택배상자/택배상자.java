import java.util.*;
import java.util.stream.IntStream;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> tempSt = new Stack<Integer>();
        int [] nums = new int[order.length+1];
        for (int i = 1; i <= order.length; i++) {
            nums[i] = i;
        }
        
        int from = 0;
        int to = nums.length;
        
        for (int i = 0; i < order.length; i++) {
            int box = order[i];
            // 큐에 있는지 스택에 있는지 모름, 택배상자가
            
            int idx = Arrays.binarySearch(nums, from, to, box); 
            if (idx > 0) {
                // nums에 숫자가 있다면 나올때까지 다 스택에 박음
                // idx에 box가 있음, nums의 box 인덱스
                for (int j = from; j < idx; j++) {
                    tempSt.push(nums[j]);
                    // nums[j] = 0; // 0으로 초기화
                }
                // nums[idx] = 0; // box도 뺐으니까 0으로 초기화
                // nums 배열을 업데이트해줘야함
                from = idx+1; // 뽑은 박스 다음부터 탐색
                answer++;
                continue;
            }
            
            // Q에 없고 box에 있을때
            if (tempSt.peek() == box) {
                tempSt.pop();
                answer++;
                continue;
            }
            
            // 두군데다 박스가 없는경우, 즉 이미 버린경우
            break;
        }
        return answer;
    }
}

/*
아니지 준비 배열은 1부터 order.length까지임
앞에서부터 보면서 못넣으면 stack에 넣음
계속 반복
찾으면 answer 1 증가
즉 nums배열과 stack의 top에서 현재 넣을 수 있는게 있는지 확인해야함
stack이 비었다면 당연히 nums에서 찾아야하고
contain을 이용해서 둘중하나에서 찾음
*/