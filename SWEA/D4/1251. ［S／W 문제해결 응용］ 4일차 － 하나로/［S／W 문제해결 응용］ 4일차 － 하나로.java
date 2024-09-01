import java.util.*;

class Island {
    int r, c, num;

    public Island(int r, int c, int num) {
        this.r = r;
        this.c = c;
        this.num = num;
    }
    
}

public class Solution {
    static int N, parents[];
    static double E;
    static Island[] islandArr;
    static List<Double[]> edgeList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        for (int t = 1; t <= tc; t++) {
            N = sc.nextInt();
            islandArr = new Island[N];
            edgeList = new ArrayList();
            
            int [] islandX = new int[N];
            int [] islandY = new int[N];
            
            for (int i = 0; i < N; i++) {
                islandX[i] = sc.nextInt();
            }
            
            for (int i = 0; i < N; i++) {
                islandY[i] = sc.nextInt();
            }
            
            for (int i = 0; i < N; i++) {
                islandArr[i] = new Island(islandX[i], islandY[i], i);
            }
            
            E = sc.nextDouble();
            
            // 각 island마다 다른 섬까지의 거리를 구하고 간선리스트에 저장함
            for (int i = 0; i < N; i++) {
                for (int j = i+1; j < N; j++) {
                    edgeList.add(new Double[] {(double) i, (double) j, getDistance(i, j)});
                }
            }
            
            
            
            Collections.sort(edgeList, (e1, e2) -> Double.compare(e1[2], e2[2])); // 간선 정렬
            
            makeSet();
            double sum = 0; int cnt = 0;
            
            for (int i = 0; i < edgeList.size(); i++) {
                Double [] edgeInfo = edgeList.get(i);
                double from = edgeInfo[0];
                double to = edgeInfo[1];
                
                if (union((int)from, (int)to)) {
                    sum += edgeInfo[2];
                    cnt++;
                }
                
                if (cnt == N-1) break;
            }
            
            System.out.println("#" + t + " " + Math.round(sum));
        }
        sc.close();
    }
    
    private static double getDistance(int n1, int n2) {
        Island island1 = islandArr[n1];
        Island island2 = islandArr[n2];
        double disX = Math.abs(island1.r - island2.r);
        double disY = Math.abs(island1.c - island2.c);
        
        return (disX*disX + disY*disY) * E;
    }
    
    private static void makeSet() {
        parents = new int[N]; // 정점만큼 만듬
        for (int i = 0; i < N; i++)
            parents[i] = i;
    }
    
    private static int find(int i) {
        if (i == parents[i]) return i;
        else return parents[i] = find(parents[i]);
    }
    
    private static boolean union(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        
        if (pi == pj) return false; // 부모 같음
        parents[pj] = pi; // j의 조상을 i의 조상으로 
        return true;
    }
}