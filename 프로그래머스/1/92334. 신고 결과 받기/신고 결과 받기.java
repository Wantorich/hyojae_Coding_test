import java.util.*;
import java.util.Map.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Integer> reportCntMap = new HashMap<>();
        Map<String, Set<String>> reportMap = new HashMap<>();
        int[] answer = new int[id_list.length];
        List<String> idList = Arrays.asList(id_list);
        
        for (String id : id_list) {
            reportCntMap.put(id, 0);
            reportMap.put(id, new HashSet<>());
        }
        
        StringTokenizer st = null;
        for (int i = 0; i < report.length; i++) {
            st = new StringTokenizer(report[i], " ");
            String reporter = st.nextToken();
            String reported = st.nextToken();
            
            Set<String> reportSet = reportMap.get(reported);
            if (!reportSet.contains(reporter)) {
                reportSet.add(reporter);
                reportCntMap.compute(reported, (key, value) -> value+1);
            }
        }
        
        int targetIdx = 0;
        for (Entry<String, Integer> entry : reportCntMap.entrySet()) {
            if (entry.getValue() < k) continue;
            
            for (String reporter : reportMap.get(entry.getKey())) {
                targetIdx = idList.indexOf(reporter);
                answer[targetIdx]++;
            }
        }
        return answer;
    }
}