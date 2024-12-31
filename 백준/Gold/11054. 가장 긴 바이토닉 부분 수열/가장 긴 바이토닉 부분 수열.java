import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		int[] nums = Arrays.stream(sc.nextLine().split(" "))
				.mapToInt(Integer::parseInt).toArray();
		
		int[] lis = new int[N];
		int[] lds = new int[N];
		
		Arrays.fill(lis, 1);
		Arrays.fill(lds, 1);
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i]) {
					lis[i] = Math.max(lis[i], lis[j] + 1);
				} 
			}
		}
		
		for (int i = N-1; i >= 0; i--) {
			for (int j = N-1; j > i; j--) {
				if (nums[j] < nums[i]) {
					lds[i] = Math.max(lds[i], lds[j] + 1);
				}
			}
		}
		
		int result = 0;
		for (int i = 0; i < N; i++) {
			result = Math.max(result, lis[i] + lds[i] - 1);
		}
		System.out.println(result);
	}
}
