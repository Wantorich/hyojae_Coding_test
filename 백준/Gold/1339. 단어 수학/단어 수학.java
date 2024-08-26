import java.util.*;

public class Main {
	static int N;
    
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.nextLine();
		
		String[] alphaArr = new String[N];
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		Map<Character, Integer> numberMap = new HashMap<Character, Integer>();
		
		for (int i = 0; i < N; i++) {
			String input = sc.nextLine();
			alphaArr[i] = input;
			for (int j = 0; j < input.length(); j++) {
				char alpha = input.charAt(j);
				int pos = input.length() - j - 1;
				if (map.get(alpha) == null) {
					// 맵에 존재하지 않으면 넣어줌
					map.put(alpha, (int) Math.pow(10, pos));
				} else {
					// 이미 존재할경우, 이번 알파벳이 더 선두위치면 교체해주고, cnt는 1 증가시킴
					int prev = map.get(alpha);
					map.put(alpha, prev + (int) Math.pow(10, pos));
				}
			}
		}
		
		List<Character> keySet = new ArrayList<Character>(map.keySet());
		keySet.sort((k1, k2) -> Integer.compare(map.get(k2), map.get(k1)));
		
		int number = 9;
		for (Character key : keySet) {
			numberMap.put(key, number--);
//			System.out.println("key : " + key + ", value : " + map.get(key));
		}
		
		// 각 알파벳에 숫자 할당 높은 수부터
		for (Character key : numberMap.keySet()) {
//			System.out.println("key : " + key + ", value : " + numberMap.get(key));
		}
		
		int sum = 0;
		for (int i = 0; i < alphaArr.length; i++) {
			String alphaStr = alphaArr[i];
			for (int j = 0; j < alphaStr.length(); j++) {
				char key = alphaStr.charAt(j);
				sum += numberMap.get(key) * (int) Math.pow(10, alphaStr.length() - j - 1);
			}
		}
		
		System.out.println(sum);
		
		sc.close();
	}
}

// map의 키는 알파벳, value는 가장 선두에 있는 위치와 빈도수
// 가장 선두가 첫번째 비교, 빈도수가 두번째 비교
