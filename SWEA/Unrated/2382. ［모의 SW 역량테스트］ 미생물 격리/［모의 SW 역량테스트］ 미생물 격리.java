import java.util.*;

class Cell {
	int r, c, dir, num;
	int [] dr = {0, -1, 1, 0, 0};
	int [] dc = {0, 0, 0, -1, 1};

	public Cell(int r, int c, int num, int dir) {
		super();
		this.r = r;
		this.c = c;
		this.dir = dir;
		this.num = num;
	}

	@Override
	public String toString() {
		return "Cell [r=" + r + ", c=" + c + ", dir=" + dir + ", num=" + num + "]";
	}
	
	public void move() {
		r += dr[dir];
		c += dc[dir];
	}
	
	public boolean isEnd(int N) {
		return r == 0 || r == N-1 || c == 0 || c == N-1;
	}
	
	public void switchDir() {
		dir = dir < 3 ? 3 - dir : 7 - dir;
		num /= 2;
	}
	
	public Cell mergeCell(List<Cell> cList) {
		int newDir = this.dir, max_num = this.num, sum_num = this.num;
		for (Cell cell : cList) {
			sum_num += cell.num;
			if (cell.num > max_num) {
				max_num = cell.num;
				newDir = cell.dir;
			}
		}
		Cell cell = new Cell(this.r, this.c, sum_num, newDir);
		return cell;
	}
}

public class Solution {
	static int N, M, K;
	static List<Cell> cellList;
	static Cell[] cellArr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for (int t = 1; t <= tc; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			cellList = new LinkedList<>();
			cellArr = new Cell[K];
			
			for (int i = 0; i < K; i++) {
				cellArr[i] = new Cell(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			}
			
			for (int i = 0; i < M; i++) {
				// move
				for (int j = 0; j < cellArr.length; j++) {
					Cell cell = cellArr[j];
					if (cell == null) continue;
					if (cell.num == 0) cellArr[j] = null;
					cell.move();
					if (cell.isEnd(N)) {
						cell.switchDir();
					}
				}
				
				List<Cell> tmp = new ArrayList<>();
				for (int j = 0; j < cellArr.length-1; j++) {
					Cell curr = cellArr[j];
					if (curr == null) continue;
					
					for (int l = j+1; l < cellArr.length; l++) {
						Cell next = cellArr[l];
						if (next == null) continue;
						
						if (curr.r == next.r && curr.c == next.c) {
							tmp.add(next);
							cellArr[l] = null;
						}
					}
					// 합병
					if (tmp.size() > 0) {
						Cell mergeCell = curr.mergeCell(tmp);
						cellArr[j] = mergeCell;
						tmp.clear();
					}
				}
			}
			int sum = Arrays.stream(cellArr).filter(cell -> cell != null).mapToInt(cell -> cell.num).sum();
			System.out.printf("#%d %d\n", t, sum);
			
		}
		sc.close();
	}
}