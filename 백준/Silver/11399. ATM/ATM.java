import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		int[] nums = Arrays.stream(sc.nextLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();
		
		Arrays.sort(nums);
		int[] sums = new int[N];
		sums[0] = nums[0];
		
		for (int i = 1; i < N; i++) {
			sums[i] = sums[i-1] + nums[i];
		}
		
		int answer = Arrays.stream(sums).sum();
		System.out.println(answer);
		sc.close();
	}
}
