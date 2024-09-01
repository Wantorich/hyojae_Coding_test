import java.util.*;

class Solution {
    static List<String> answer = new ArrayList();
    static int size;
    static boolean visit[], find;
    static List<String[]> ticketList = new ArrayList();
    
    public String[] solution(String[][] tickets) {
       size = tickets.length + 1;
       visit = new boolean[tickets.length];
        
       for (String [] ticket : tickets) {
           ticketList.add(ticket);
       }
        
       // dfs
       dfs("ICN",  new ArrayList<String>(Arrays.asList("ICN")));

       for (String str : answer) {
           System.out.print(str + " ");
       }

       return answer.toArray(new String[0]);
    }
    
    static void dfs(String key, List<String> list) {
        if (find) return;
        
        if (list.size() == size) {
            // 모든 여행 경로 돌았음
            answer.addAll(list);
            find = true;
            return;
        }
        
        List<String[]> reserveList = new ArrayList();
        
        for (int i = 0; i < ticketList.size(); i++) {
            if (visit[i]) continue;
            
            String [] info = ticketList.get(i);
            if (!info[0].equals(key)) continue;
            
            String to = info[1];
            reserveList.add(new String[]{to, String.valueOf(i)});
            // 원소의 0번째는 from, 1번째는 to 이게 맞아야함
        }
        
        Collections.sort(reserveList, (str1, str2) -> str1[0].compareTo(str2[0]));
        
        for (String[] info : reserveList) {
            String next = info[0];
            int idx = Integer.parseInt(info[1]);
            
            visit[idx] = true;
            list.add(next);
            dfs(next, list);
            visit[idx] = false;
            list.remove(list.size()-1);
        }
    }
}

