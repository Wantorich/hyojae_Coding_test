import java.util.*;

class Person {
	int r, c, num, entrance, distance;

	Person(int r, int c, int num) {
		super();
		this.r = r;
		this.c = c;
		this.num = num;
	}

	@Override
	public String toString() {
		return String.format("Person [r=%s, c=%s, num=%s, entrance=%s, distance=%s]", r, c, num, entrance, distance);
	}
}

class Stair {
	int r, c, height;
	List<Person> useList = new ArrayList<Person>();
	
	Stair(int r, int c, int height) {
		super();
		this.r = r;
		this.c = c;
		this.height = height;
	}
}

public class Solution {
    static int N, result;
    static List<Person> pList = new ArrayList<Person>();
    static Stair[] stairs = new Stair[2];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        for (int t = 1; t <= tc; t++) {
            N = sc.nextInt();
            pList.clear();
            int pCnt = 1;
            int sCnt = 0;
            result = Integer.MAX_VALUE;
            
            for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
            		int val = sc.nextInt();
            		if (val == 1) { // person
            			pList.add(new Person(i, j, pCnt++));
            		} else if (val > 1) { // stair
            			stairs[sCnt++] = new Stair(i, j, val);
            		}
            	}
            }
            
            // 부분조합으로 경우의 수 찾긴데, 최소 1명은 있어야되는?
            subset(new boolean[pList.size()], 0);
            System.out.printf("#%d %d\n", t, result);
        }
        sc.close();
    }
    
    static int getDistance(Person p, Stair s) {
    	return Math.abs(p.r - s.r) + Math.abs(p.c - s.c);
    }

	private static void subset(boolean[] sel, int k) {
		if (k == sel.length) { // 다 뽑음
			// true면 0번, false면 1번
			for (int i = 0; i < sel.length; i++) {
				Person p = pList.get(i);
				p.entrance = sel[i] ? 0 : 1;
				// 거리 업데이트
				p.distance = getDistance(p, stairs[p.entrance]);
			}
			
			simulation();
			return;
		}
		
		sel[k] = true;
		subset(sel, k+1);

		sel[k] = false;
		subset(sel, k+1);
	}
	
	

	private static void simulation() {
		// pList에서 0보다 큰 수중 최솟값을 찾아 모든 리스트들을 그 값으로 빼줌
		// stair에서 useList에 존재하는 값이 있으면 update해주고 나갈사람 나가게함
		// 그럼 0이 되는 값이 생기고 0인 값은 해당 계단의 리스트에 넣어줌
		// 모든값이 0 이하가 되면 useList를 비울때가지 반복해서 끝냄
		int outCnt = 0; int time = 0; 
		O:while (outCnt < pList.size()) {
			// minDis 찾음
			int minVal = Integer.MAX_VALUE;
			for (Person p : pList) {
				if (p.distance <= 0) continue; // 이미 계단 들어간건 제외
				minVal = Math.min(minVal, p.distance);
			}

			// minval이 업데이트 안됐다면 계단에 다 들어갔다는거
			if (minVal == Integer.MAX_VALUE) {
				while (true) {
					outCnt += updateStairInfo(1); // 1초씩 계단 상황 업데이트해줌
					time++;
					if (outCnt == pList.size()) break O;
				}
			}
			
			// stair가서 useList 값 업데이트
			outCnt += updateStairInfo(minVal); // 빠져나간 사람만큼 아웃카운트 증가
			
			// person들 거리 좁히고 0인 놈들은 계단에 입장
			for (Person p : pList) {
				// 양수인 놈들만 거리 빼줌, 왜냐면 0 이하놈들은 이미 계단에 들어갔으니까
				if (p.distance <= 0) continue;
				p.distance -= minVal;
				if (p.distance <= 0) {
					int stairNum = p.entrance;
					stairs[stairNum].useList.add(p);
				}
			}
			time += minVal;
		}
		
		result = Math.min(result, time);
	}

	private static int updateStairInfo(int time) {
		int removeCnt = 0;
		for (int i = 0; i < stairs.length; i++) {
			if (stairs[i].useList.size() == 0) continue;
			
			for (int j = 0; j < Math.min(stairs[i].useList.size(), 3); j++) {
				Person usePerson = stairs[i].useList.get(j);
				usePerson.distance -= time; 
				// 계단의 높이보다 높다면, 즉 계단을 빠져나갔는지 체크
				if (Math.abs(usePerson.distance) > stairs[i].height) {
					stairs[i].useList.remove(j--);
					removeCnt++;
				}
			}
		}
		return removeCnt;
	}
}