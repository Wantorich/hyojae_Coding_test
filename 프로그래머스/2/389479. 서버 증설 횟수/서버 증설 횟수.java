import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] servers = new int[players.length];
        for (int i = 0; i < servers.length; i++) {
            int demand = players[i] / m;
            if (demand > servers[i]) {
                int added = demand - servers[i];
                for (int j = i; j < i + k && j < servers.length; j++) {
                    servers[j] += added;
                }
                answer += added;
            }
        }
        return answer;
    }
}


/*
k에 따라서 서버의 지속시간을 관리, 즉 현재 시간에 서버가 몇대 떠있는지 관리해야함
m은 서버를 증설할지의 기준점. 0 ~ m미만까지는 증설 필요없음 
즉 몫이 1이상이어야함, 아니 그냥 몫임. 필요 서버의 수가
그럼 결국 서버 유지만 관리하면됌, 총 배열 원소개수는 24
서버 추가할떄 미리 추가해주면됌
*/