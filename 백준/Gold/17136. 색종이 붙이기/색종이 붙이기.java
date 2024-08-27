import java.util.*;

public class Main {
    static int grid[][] = new int[10][10], result = Integer.MAX_VALUE;
    static int [] quantity = new int[6];
    static List<int[]> paperList = new ArrayList();
    static boolean [][] visit = new boolean[10][10];
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 100; i++) {
            int val = sc.nextInt();
            grid[i/10][i%10] = val;
            if (val == 1) paperList.add(new int[] {i/10, i%10});
        }
        Arrays.fill(quantity, 5); // 색종이 갯수 초기화
        
        attach(0, 0);
        result = result == Integer.MAX_VALUE ? -1 : result;
        System.out.println(result);
        
        sc.close();
    }
    
    private static boolean isAllAttached() {
    	for (int [] pos : paperList) {
    		if (!visit[pos[0]][pos[1]]) return false;
    	}
    	return true;
    }

    private static void attach(int k, int cnt) {
    	if (cnt >= result) return;
    	
        if (k == paperList.size()) {
            // 다 붙힘
        	if (isAllAttached()) 
        		result = Math.min(result, cnt);
            return;
        }
        
        int [] pos = paperList.get(k);
        
        int r = pos[0];
        int c = pos[1];
        
        if (visit[r][c]) {
            // 이미 색종이 붙혔으면
            attach(k+1, cnt);
        }
        
        else {
            // 색종이 붙여야함
            // 색종이 큰거부터 붙여봄
            for (int size = 5; size >= 1; size--) {
            	if (quantity[size] == 0) {
            		// 색종이를 붙여야하는데 1칸짜리도 다 쓰면 이 방법은 아니므로 return
            		if (size == 1) return; 
            		continue;
            	}
            	
                if (canAttach(r, c, size)) {
                    paint(r, c, size);
                    quantity[size]--; // 색종이 한개 빼줌
                    attach(k+1, cnt+1);
                    erase(r, c, size); // 칠한거 지워줌
                    quantity[size]++; // 색종이 다시 더해줌
                }
            }
        }
    }

    private static void erase(int r, int c, int size) {
    	for (int i = r; i < r+size; i++) {
            for (int j = c; j < c + size; j++) {
                visit[i][j] = false;
            }
        }
	}

	private static void paint(int r, int c, int size) {
        // 배열을 1 -> 0으로 바꾼다
        // 방문배열로 관리한다
		for (int i = r; i < r+size; i++) {
            for (int j = c; j < c + size; j++) {
                visit[i][j] = true;
            }
        }
    }

    private static boolean canAttach(int r, int c, int size) {
        // r,c부터 가로 세로로 size만큼의 색종이만큼 1이 있는지
        // 그리고 색종이 사이즈가 존재하는지
        for (int i = r; i < r+size; i++) {
            for (int j = c; j < c + size; j++) {
            	// 범위를 벗어나거나, 0이거나 이미 색종이가 붙혀져있거나(덮어쓰는건 안됌)
                if (!inRange(i,j) || grid[i][j] == 0 || visit[i][j]) return false;
            }
        }
        return true;
    }
    
    private static boolean inRange(int r, int c) {
    	return r >= 0 && r < 10 && c >= 0 && c < 10;
    }
}