class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        int totalLen = getTime(video_len);
        int currTime = getTime(pos);
        int strTime = getTime(op_start);
        int endTime = getTime(op_end);
        
        if (strTime <= currTime && currTime <= endTime)
            currTime = endTime;
        
        for (String command : commands) {
            switch (command) {
                case "prev" :
                    currTime = Math.max(0, currTime - 10);
                    break;
                case "next" :
                    currTime = Math.min(totalLen, currTime + 10);
                    break;
                
            }
            if (strTime <= currTime && currTime <= endTime)
                currTime = endTime;
        }
        
        int min = currTime / 60;
        int sec = currTime % 60;
        
        if (min < 10)
            answer += "0";
        answer += String.valueOf(min);
        answer += ":";
        if (sec < 10)
            answer += "0";
        answer += String.valueOf(sec);
        return answer;
    }
    
    static int getTime(String time) {
        int min = Integer.parseInt(time.split(":")[0]) * 60;
        int sec = Integer.parseInt(time.split(":")[1]);
        return min + sec;
    }
}