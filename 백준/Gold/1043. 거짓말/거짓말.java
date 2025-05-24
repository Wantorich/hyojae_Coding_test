import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(br.readLine(), " ");
    	int truthCnt = Integer.parseInt(st.nextToken());
    	Set<Integer> truthPeople = new HashSet<>();
    	for (int i = 0; i < truthCnt; i++) {
    		truthPeople.add(Integer.valueOf(st.nextToken()));
    	}
    	
    	int num;
    	List<Set<Integer>> partyPeople = new ArrayList<>();
    	
    	for (int i = 0; i < M; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		num = Integer.parseInt(st.nextToken());
    		Set<Integer> set = new HashSet<>(num);
    		for (int j = 0; j < num; j++) {
    			set.add(Integer.valueOf(st.nextToken()));
        	}
    		partyPeople.add(set);
    	}
    	
    	sort(partyPeople, truthPeople);
    	int answer = 0;
    	
    	for (int i = 0; i < partyPeople.size(); i++) {
    		Set<Integer> dummy = new HashSet<>(partyPeople.get(i));
    		dummy.retainAll(truthPeople);
    		if (!dummy.isEmpty()) {
    			truthPeople.addAll(partyPeople.get(i)); // 업데이트
    			partyPeople.remove(i--);
    			sort(partyPeople, truthPeople);
    		} else {
    			answer++;
    		}
    	}
    	
    	System.out.println(answer);
    }
    
    static void sort(List<Set<Integer>> partyPeople, Set<Integer> truthPeople) {
    	partyPeople.sort((s1, s2) -> {
    		Set<Integer> set2 = new HashSet<>(truthPeople);
    		Set<Integer> set1 = new HashSet<>(truthPeople);
    		set2.retainAll(s2);
    		set1.retainAll(s1);
    		int comp = Integer.compare(set2.size(), set1.size());
    		if (comp != 0) return comp;
    		return Integer.compare(s2.size(), s1.size());
    	});
    }
}