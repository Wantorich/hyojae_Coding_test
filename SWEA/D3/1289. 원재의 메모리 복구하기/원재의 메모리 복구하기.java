import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();
		sc.nextLine();
		
		for (int t = 1; t <= test_case; t++) {
			String bits = sc.nextLine().trim();
			char [] bit_arr = new char[bits.length()];
			
			for (int i = 0; i < bit_arr.length; i++) {
				bit_arr[i] = '0';
			}
			
			int cnt = 0;
			
			for (int i = 0; i < bit_arr.length; i++) {
				if (bit_arr[i] != bits.charAt(i)) {
					char ow = bits.charAt(i);
					for (int j = i; j < bit_arr.length; j++) {
						bit_arr[j] = ow;
					}
					cnt++;
					if (bits.equals(new String(bit_arr))) {
//						System.out.println(new String(bit_arr));
						break;
					}
				}
			}
			
			System.out.printf("#%d %d", t, cnt);
			System.out.println();
		}
	}

}

/*
왼쪽서부터 1이 있으면 채워줘야해 이건 어쩔수없음
근데 한쪽에서 1을 채우면 나머지가 싹 덮어씌워짐

그럼 쓰고, 덮어쓰고, 확인하고 이 작업을 반복인가?

*/