import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String pattern = sc.nextLine();
        
        int[] pi = new int[pattern.length()];
        int j = 0;
        for (int i = 1; i < pi.length; i++) {
        	// 일치하지 않는경우
        	while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
        		j = pi[j-1];
        	if (pattern.charAt(i) == pattern.charAt(j)) {
        		pi[i] = ++j;
        	} 
        }
        
        int cnt = 0;
        List<Integer> idxList = new ArrayList<Integer>();
        j = 0;
        for (int i = 0; i < text.length(); i++) {
        	// 일치하지 않는경우
        	while (j > 0 && text.charAt(i) != pattern.charAt(j))
        		j = pi[j-1];
        	if (text.charAt(i) == pattern.charAt(j)) {
        		if (++j == pattern.length()) {
        			cnt++;
        			j = pi[j-1];
        			idxList.add(i - pattern.length() + 1 + 1);
        		}
        	} 
        }
        
        System.out.println(cnt);
        for (Integer idx : idxList) System.out.print(idx + " ");
        
        sc.close();
    }
}