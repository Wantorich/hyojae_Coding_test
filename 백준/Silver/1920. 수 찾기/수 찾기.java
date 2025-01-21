import java.util.*;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	sc.nextLine();
    	int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	Arrays.sort(nums);
    	int M = sc.nextInt();
    	sc.nextLine();
    	StringBuilder sb = new StringBuilder();
    	int[] querys = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    	for (int query : querys) {
    		if (Arrays.binarySearch(nums, query) < 0)
    			sb.append(0);
    		else 
    			sb.append(1);
    		sb.append("\n");
    	}
    	System.out.println(sb.toString());
    	sc.close();
    }
}
