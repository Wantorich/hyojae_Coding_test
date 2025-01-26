import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	br.readLine();
    	Map<Integer, Integer> map = new HashMap<>();
    	Arrays.stream(br.readLine().split(" "))
    		.mapToInt(Integer::parseInt)
    		.forEach(n -> map.put(n, map.getOrDefault(n, 0) + 1));

    	br.readLine();
    	String result = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.mapToObj(index -> String.valueOf(map.getOrDefault(index, 0)))
			.collect(Collectors.joining(" "));
    	
    	System.out.println(result);
    }
}
