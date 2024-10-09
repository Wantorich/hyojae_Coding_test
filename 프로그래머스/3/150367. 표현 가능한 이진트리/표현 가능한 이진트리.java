import java.util.*;

class Solution {
    static boolean flag;
    public int[] solution(long[] numbers) {
        int [] answer = new int[numbers.length];
        int idx = 0;
        
        // long test = 10002405000L;
        // System.out.println("num : " + test + ", binary : " + Long.toBinaryString(test));
        // System.out.println("len : " + Long.toBinaryString(test).length());
        // System.out.println("final binary : " + binary);
        
        for (long number : numbers) {
            String binary = Long.toBinaryString(number);
            int bitNum = (int) (Math.ceil(Math.log(number) / Math.log(2)));
            // binary = bitNum % 2 == 0 ? "0" + binary : binary;
            // System.out.println("log : " + (int) (Math.ceil(Math.log(number) / Math.log(2))));
            // System.out.println("num : " + number + ", binary : " + Long.toBinaryString(number));
            // System.out.println("final binary : " + binary);
            
            int len = binary.length();

            for (int i = 0; i <= 5; i++) {
                int lb = 1 << i;
                int ub = 1 << i+1;

                if (lb <= len && len < ub) {
                    len = ub - 1;
                    break;
                }
            }

            String fullBinary = String.format("%" + len + "s", binary).replace(' ', '0');
            // System.out.println(fullBinary);
            // System.out.println(fullBinary.length());
            
            flag = true;
            int start = fullBinary.length() / 2;
            dfs(start, fullBinary, (start+1) / 2);
            answer[idx++] = flag ? 1 : 0;
        }
        return answer;
    }
    
    static void dfs(int curr, String binary, int depth) {
        if (depth == 0) return;
        
        if (binary.charAt(curr) == '0') {
            if (binary.charAt(curr-depth) == '1' || binary.charAt(curr+depth) == '1') {
                flag = false;
                return;
            }
        } 
        dfs(curr-depth, binary, depth/2);    
        dfs(curr+depth, binary, depth/2);    
    }
}