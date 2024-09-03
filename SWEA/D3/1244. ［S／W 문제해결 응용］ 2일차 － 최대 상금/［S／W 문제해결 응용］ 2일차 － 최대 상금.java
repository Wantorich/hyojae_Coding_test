import java.util.*;

public class Solution {
	static int N, K, result, L; 
	static char nums[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for (int t = 1; t <= tc; t++) {
			N = sc.nextInt();
			K = sc.nextInt();
			result = Integer.MIN_VALUE; // 초기값을 기본값으로
			
			nums = String.valueOf(N).toCharArray();
			// N의 자리수중에 2자리를 고름
			// 인자는 현재 교환횟수
            
            if (K > nums.length) {
                if (nums.length % 2 != K % 2) {
                    K = nums.length-1;
                } else {
                    K = nums.length;
                }
            }
            
			calculate(0, 0);
			System.out.printf("#%d %d\n", t, result);
		}
		sc.close();
	}

	// 가지치기
	// 바꿔줄게 없는 경우도 있겠네 그래서 틀린듯
	// 이미 내림차순으로 정렬된 경우에는 뭘 바꿔줘야함?
	
	private static void calculate(int cnt, int idx) {
		if (cnt == K) {
			int numVal = Integer.parseInt(new String(nums));
			result = Math.max(result, numVal);
			// 교환 다함 최대값 갱신
			return;
		}
		
		// 2개 뽑아서 스왑하고 cnt+1 증가시키고 다시 원상복구
		// n^2만큼의 시간복잡도 -> 최대 36을 * K(10) = 360
		for (int i = idx; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				swap(i, j);
				calculate(cnt+1, i);
				swap(i, j);
			}
		}
	}

	private static void swap(int i, int j) {
		char temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
