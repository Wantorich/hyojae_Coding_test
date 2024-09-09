import java.util.*;

class Reception {
	int cId, useTime, endTime;
	boolean isActivated;

	Reception(int endTime) {
		this.endTime = endTime;
	}
}

class Repair {
	int cId, useTime, endTime;
	boolean isActivated;

	Repair(int endTime) {
		this.endTime = endTime;
	}
}

public class Solution {
	static Reception[] receptions;
	static Repair[] repairs;
	static int K, targetReception, targetRepair;
	static Queue<Integer> arriveQ;
	static int[][] usages;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();

		for (int t = 1; t <= tc; t++) {
			arriveQ = new LinkedList<Integer>();
			receptions = new Reception[sc.nextInt()];
			repairs = new Repair[sc.nextInt()];
			K = sc.nextInt();
			usages = new int[K + 1][2]; // 0번은 reception, 1번은 repair
			targetReception = sc.nextInt() - 1;
			targetRepair = sc.nextInt() - 1;
			Queue<Integer> receptionWaitQ = new LinkedList<Integer>();
			Queue<Integer> repairWaitQ = new LinkedList<Integer>();

			for (int i = 0; i < receptions.length; i++)
				receptions[i] = new Reception(sc.nextInt());
			for (int i = 0; i < repairs.length; i++)
				repairs[i] = new Repair(sc.nextInt());
			for (int i = 0; i < K; i++)
				arriveQ.offer(sc.nextInt());

			// 이미 있는애들 먼저 이동시키긴 해야함

			int currTime = 0;
			int customerIdx = 1;
			int customerCnt = 0;
			int receptionCnt = 0, repairCnt = 0;
			while (customerCnt < K) {
				// repair 이동
				for (int i = 0; i < repairs.length; i++) {
					Repair r = repairs[i];
					if (r.isActivated && r.useTime++ == r.endTime) {
						// 밖으로 나감
						r.isActivated = false;
						r.useTime = 0;
						customerCnt++; // 볼일 끝난 손님 증가
						usages[r.cId][1] = i;
						repairCnt--;
					}
				}
				
				// repair 대기조 이동
				for (int i = 0; i < repairs.length - repairCnt && !repairWaitQ.isEmpty(); i++) {
					int nextCustomer = repairWaitQ.poll();
					offerCustomerToRepair(nextCustomer);
					repairCnt++;
					i--;
				}
				
				// reception 이동
				for (int i = 0; i < receptions.length; i++) {
					Reception r = receptions[i];
					if (r.isActivated && r.useTime++ == r.endTime) {
						r.isActivated = false;
						r.useTime = 0;
						if (repairCnt < repairs.length) {
							offerCustomerToRepair(r.cId);
							repairCnt++;
						}
						
						else {
							repairWaitQ.offer(r.cId); // repair 대기조로 이동
						}
						usages[r.cId][0] = i;
						receptionCnt--;
					}
				}
				
				// reception 대기조 이동
				for (int i = 0; i < receptions.length - receptionCnt && !receptionWaitQ.isEmpty(); i++) {
					int nextCustomer = receptionWaitQ.poll();
					offerCustomerToReception(nextCustomer);
					receptionCnt++;
					i--;
				}

				// 손님 이동
				while (!arriveQ.isEmpty() && arriveQ.peek() == currTime) {
					int nextCustomer = arriveQ.poll();
					// reception이 비었으면 넣고
					if (receptionCnt < receptions.length) {
						// 넣을 수 있으면
						offerCustomerToReception(customerIdx++);
						receptionCnt++;
					} else {
						receptionWaitQ.offer(customerIdx++);
					}
					// 다 찼으면 receptionWaitQ로 넣음
				}
				currTime++;
			}

//            System.out.println("=== 손님들이 어디를 방문했는지 ===");
//            for (int [] row : usages) {
//            	System.out.println(Arrays.toString(row));
//            }

			int result = 0;
			for (int i = 1; i < usages.length; i++) {
				if (usages[i][0] == targetReception && usages[i][1] == targetRepair) {
					result += i;
				}
			}
			result = result == 0 ? -1 : result;
			System.out.printf("#%d %d\n", t, result);
		}
		sc.close();
	}

	static boolean offerCustomerToReception(int cId) {
		for (int i = 0; i < receptions.length; i++) {
			for (Reception r : receptions) {
				if (r.isActivated)
					continue;
				r.isActivated = true;
				r.cId = cId;
				r.useTime = 1;
				return true; // 넣었으니 더 탐색할 필요 없음
			}
		}
		return false;
	}

	static boolean offerCustomerToRepair(int cId) {
		for (int i = 0; i < repairs.length; i++) {
			for (Repair r : repairs) {
				if (r.isActivated)
					continue;
				r.isActivated = true;
				r.cId = cId;
				r.useTime = 1;
				return true; // 넣었으니 더 탐색할 필요 없음
			}
		}
		return false;
	}
}

/*
 * 고객 도착시간 tk 중 가장 짧은 시간이 상담이 시작되는 시간
 * 
 * 
 */