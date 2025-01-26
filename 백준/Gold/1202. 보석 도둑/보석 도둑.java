import java.io.*;
import java.util.*;
import java.util.Map.Entry;

class Jewel {
	int price;
	int weight;
	
	Jewel(int price, int weight) {
		this.price = price;
		this.weight = weight;
	}
	
	int getPrice() {
		return this.price;
	}
}

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    	int N = Integer.parseInt(st.nextToken());
    	int K = Integer.parseInt(st.nextToken());
    	
    	List<Jewel> jewelList = new ArrayList<>();
    	int weight, price;
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine(), " ");
    		weight = Integer.parseInt(st.nextToken());
    		price = Integer.parseInt(st.nextToken());
    		jewelList.add(new Jewel(price, weight));
    	}
    	
    	jewelList.sort(Comparator.comparingInt(Jewel::getPrice).reversed());
    	
    	TreeMap<Integer, Integer> bagMap = new TreeMap<>();
    	for (int i = 0; i < K; i++) {
    		weight = Integer.parseInt(br.readLine());
    		bagMap.put(weight, bagMap.getOrDefault(weight, 0) + 1);
    	}
    	
    	long answer = 0;
    	for (int i = 0; i < N && bagMap.size() > 0; i++) {
    		Jewel jewel = jewelList.get(i);
    		Entry<Integer, Integer> ceilEntry = bagMap.ceilingEntry(jewel.weight);
    		if (ceilEntry != null) {
    			answer += jewel.getPrice();
    			bagMap.replace(ceilEntry.getKey(), ceilEntry.getValue() - 1);
    			if (ceilEntry.getValue() - 1 == 0)
    				bagMap.remove(ceilEntry.getKey());
    		}
    	}
    	System.out.println(answer);
    }
}
