import java.util.*;

public class Solution {
	static int D, M, TM, Y, result;
	static List<Integer> useList;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int test_case = sc.nextInt();
		for (int t = 1; t <= test_case; t++) {
			D = sc.nextInt();
			M = sc.nextInt();
			TM = sc.nextInt();
			Y = sc.nextInt();
			result = Y; // 1년치 사면 어차피 무조건 다 이용 가능
			useList = new ArrayList<Integer>();
			
			for (int i = 0; i < 12; i++) {
				useList.add(sc.nextInt());
			}
			useList.add(0);
			
			// 현재 달 인덱스, 쓴 돈
			buyTicket(0, 0, useList.get(0));
			System.out.printf("#%d %d\n", t, result);
		}
		sc.close();
	}
	
	private static void buyTicket(int idx, int spend, int remain) {
		if (idx == useList.size()-1) {
			result = Math.min(result, spend);
			return;
		}
		
		if (spend > result) 
			return;
		
		// 이용계획이 0일치여도 이건 장기기때문에 구매 가능
		if (idx < 10) {
			// 3달치동안 적어도 한달은 이용계획이 있어야함
			boolean shouldUse = false;
			for (int i = idx; i < idx+3; i++) {
				if (useList.get(i) > 0) {
					shouldUse = true;
					break;
				}
			}
			
			if (shouldUse)
				buyTicket(idx+3, spend+TM, useList.get(idx+3));
		}
		
		
		// 0일치 에도 3달치를 살수가 있구나
		// 1일치 사는 경우, 1달치, 3달치, 12달치
		if (remain <= 0) {
			// 현재 달에 이용계획 없으면, 다음달로 넘어감
			// 이용권을 사서 0보다 작거나 같으면 이미 산거니까 넘어감
			buyTicket(idx+1, spend, useList.get(idx+1));
			
			// 여기서 일년치랑 3달치 사는 경우는 고려해야함
			// 하루치랑 한달치는 살 필요 없음
			
		} else {
			// 한달치 / 하루치 나눈 금액이 분수령임
			// 이 금액보다 remain이 큰 경우는 한달치로 결제하는게 이득이고
			// 낮은 경우에는 하루로 결제하는게 이득임
			
			if (remain > M / D) {
				buyTicket(idx+1, spend+M, useList.get(idx+1)); // 한달치
			} else {
				buyTicket(idx+1, spend+D*remain, useList.get(idx+1)); // 하루치 삼
			}
			
			
		}
	}
	
	
}
