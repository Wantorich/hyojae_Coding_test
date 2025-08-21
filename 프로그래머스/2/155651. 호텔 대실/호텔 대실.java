import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(book_time, (a, b) -> parsing(a[0]) - parsing(b[0]));
        for (String[] bt : book_time) {
            // 대체할게 있는지 체크
            boolean flag = false;
            for (int i = 0; i < list.size(); i++) {
                int endTime = list.get(i);
                if (endTime + 10 <= parsing(bt[0])) {
                    list.set(i, parsing(bt[1]));
                    flag = true;
                    break;
                }
            }
            
            if (!flag) {
                list.add(parsing(bt[1]));
            }
        }    
        return list.size();
    }
    
    int parsing(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}