import java.util.*;
import java.util.stream.*;

class Solution {
    static int idx;
    public int[] solution(int[] fees, String[] records) {
        TreeMap<String, Map<String, List<String>>> recordMap = new TreeMap<>();
          
        Arrays.stream(records)
            .map(record -> record.split(" ")[1])
            .distinct()
            .forEach(carNum -> {
                Map<String, List<String>> timeMap = new HashMap<>();
                timeMap.put("IN", new ArrayList());
                timeMap.put("OUT", new ArrayList());
                recordMap.put(carNum, timeMap);
            });
            
        int[] answer = new int[recordMap.size()];
        Arrays.stream(records)
            .map(record -> record.split(" "))
            .forEach(info -> recordMap.get(info[1]).get(info[2]).add(info[0]));
        
        for (Map<String, List<String>> tmap : recordMap.values()) {
            List<String> inList = tmap.get("IN");
            List<String> outList = tmap.get("OUT");
            
            if (inList.size() == outList.size() + 1) 
                outList.add("23:59");
            
            int inSum = inList.stream().mapToInt(time -> timeToNum(time)).sum();
            int outSum = outList.stream().mapToInt(time -> timeToNum(time)).sum();
            int parkingTime = outSum - inSum;
            
            int fee = fees[1];
            if (parkingTime > fees[0]) {
                double addtionalFee = Math.ceil(1.0 * (parkingTime - fees[0]) / fees[2]) * fees[3];
                fee += (int) addtionalFee;
            }
            
            answer[idx++] = fee;
        }
        return answer;
    }
    
    int timeToNum(String time) {
        int min = Integer.parseInt(time.substring(0, 2)) * 60;
        int sec = Integer.parseInt(time.substring(3));
        return min + sec;
    }
}