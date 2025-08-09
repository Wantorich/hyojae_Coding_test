class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        int currTime = parseTime(pos);
        int opStart = parseTime(op_start);
        int opEnd = parseTime(op_end);
        int endTime = parseTime(video_len);
        for (String command : commands) {
            // 오프닝 건너뛰기인지 확인 
            if (opStart <= currTime && currTime <= opEnd) {
                currTime = opEnd;
            }
            
            if (command.equals("next")) {
                currTime = Math.min(endTime, currTime + 10);    
            } else if (command.equals("prev")) {
                currTime = Math.max(0, currTime - 10);    
            }
        }
        
        // 오프닝 건너뛰기인지 확인 
        if (opStart <= currTime && currTime <= opEnd) {
            currTime = opEnd;
        }
        return parseTime(currTime);
    }
    
    int parseTime(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int min = Integer.parseInt(split[1]);
        return hour * 60 + min;
    }
    
    String parseTime(int time) {
        int hour = time / 60;
        int min = time % 60;
        return String.format("%02d:%02d", hour, min);
    }
}