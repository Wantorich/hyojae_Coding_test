import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        int max = 0;
        List<String[]> list1 = new ArrayList<>(); // X 없는거
        List<String[]> list2 = new ArrayList<>(); // X 있는거
        for (String exp : expressions) {
            String[] split = exp.split(" ");
            if (split[split.length-1].equals("X")) {
                list2.add(split);
            } else {
                list1.add(split);
            }
            for (int i = 0; i < split.length; i++) {
                if (i % 2 == 0 && !split[i].equals("X")) {
                    int num = Integer.parseInt(split[i]);
                    while (num > 0) {
                        int remain = num % 10;
                        max = Math.max(max, remain);
                        num /= 10;
                    }
                }
            }
        }
        // System.out.println(max+1);
        // max+1부터 9까지 완성된 수식에 넣어보면서 후보군을 찾아야함 
        List<Integer> list3 = new ArrayList<>();
        
        for (int i = max+1; i <= 9; i++) {
            // list1을 뒤지면서 해당 진법으로 모두 성공하는지를 확인해야함 
            boolean flag = true;
            for (String str[] : list1) {
                if (!isCorrect(str, i)) {
                    flag = false;
                }
            }
            if (flag) {
                list3.add(i);
            }
        }
        
        // System.out.println(list3.size());
        // for (int n : list3) {
        //     System.out.print(n + " ");
        // }
        
        Map<String[], Set<Integer>> map = new HashMap<>();
        for (int radix : list3) {
            for (String[] str : list2) {
                int value = solve(str, radix);
                map.putIfAbsent(str, new HashSet<>());
                map.get(str).add(value);
            }
        }
        
        for (int i = 0; i < list2.size(); i++) {
            String[] str = list2.get(i);
            Set<Integer> set = map.get(str);
            if (set.size() > 1) {
                str[str.length-1] = "?";
            } else if (set.size() == 1) {
                Iterator<Integer> it = set.iterator();
                str[str.length-1] = String.valueOf(it.next());
            }
        }
        
        String[] answer = new String[list2.size()];
        for (int i = 0; i < list2.size(); i++) {
            String[] str = list2.get(i);
            answer[i] = String.join(" ", str[0], str[1], str[2], str[3], str[4]);
        }
        return answer;
    }
    
    int solve(String[] exp, int radix) {
        int a = Integer.parseInt(exp[0], radix);
        int b = Integer.parseInt(exp[2], radix);
        
        if (exp[1].equals("+")) {
            return Integer.parseInt(Integer.toString(a + b, radix));
        } else {
            return Integer.parseInt(Integer.toString(a - b, radix));
        }
    }
    
    boolean isCorrect(String[] exp, int radix) {
        int a = Integer.parseInt(exp[0], radix);
        int b = Integer.parseInt(exp[2], radix);
        int c = Integer.parseInt(exp[4], radix);
        
        if (exp[1].equals("+")) {
            return a + b == c;
        } else {
            return a - b == c;
        }
    }
}

/*
진법 후보는 숫자들중 가장 큰수 + 1부터 시작함
완전한 수식이 있으면 진법을 알아낼수있다? 아닐수도 2-1 = 1은 못알아냄 
*/