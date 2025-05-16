import java.io.*;
import java.util.*;

class Star {
	List<Long> posList = new ArrayList<>();
	int num;
	
	Star (int num) {
		this.num = num;
	}
	
	void addPos(long pos) {
		posList.add(pos);
	}
}

public class Main {
	static int[] parents;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	List<Star> starList = new ArrayList<>();
    	parents = new int[N+1];
    	Arrays.fill(parents, -1);
    	
    	for (int i = 1; i <= N; i++) {
    		Star star = new Star(i);
    		String[] splits = br.readLine().split(" ");
    		for (String split : splits)
    			star.addPos(Integer.parseInt(split));
    		starList.add(star);
    	}
    	
    	List<long[]> edgeList = new ArrayList<>();
    	for (int i = 0; i < 3; i++) {
    		final int index = i;
    		starList.sort((a, b) -> Long.compare(a.posList.get(index), b.posList.get(index)));
    		for (int j = 0; j < starList.size() - 1; j++) {
    			edgeList.add(new long[] {starList.get(j).num, starList.get(j+1).num, starList.get(j+1).posList.get(index) - starList.get(j).posList.get(index)});
    		}
    	}
    	
    	edgeList.sort((a, b) -> Long.compare(a[2], b[2]));
    	
    	long answer = 0;
    	for (long[] edge : edgeList) {
    		if (union((int) edge[0], (int) edge[1])) {
    			answer += edge[2];
    		}
    	}
    	
    	System.out.println(answer);
    }
    
    static int find(int a) {
    	if (parents[a] == -1) 
    		return a;
    	return parents[a] = find(parents[a]);
    }
    
    static boolean union(int a, int b) {
    	int pa = find(a);
    	int pb = find(b);
    	
    	if (pa == pb) 
    		return false;
    	
    	parents[pa] = pb;
    	return true;
    }
}