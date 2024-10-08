class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int putIdx = deliveries.length-1;
        int getIdx = pickups.length-1;
        int putBox = 0; int getBox = 0;
        
        // 초기 포인터 설정
        while (putIdx >= 0 && deliveries[putIdx] == 0) putIdx--;
        while (getIdx >= 0 && pickups[getIdx] == 0) getIdx--;
        
        while (putIdx >= 0 || getIdx >= 0) {
            answer += Math.max(putIdx, getIdx) + 1;
            
            // 택배 배달하기
            while (putIdx >= 0 && putBox < cap) {
                if (deliveries[putIdx] + putBox > cap) {
                    deliveries[putIdx] -= cap - putBox;
                    putBox = cap;
                    break;
                } else {
                    putBox += deliveries[putIdx];
                    deliveries[putIdx] = 0;
                }
                putIdx--;
            }
            
            // 택배 수거하기
            while (getIdx >= 0 && getBox < cap) {
                if (pickups[getIdx] + getBox > cap) {
                    pickups[getIdx] -= cap - getBox;
                    getBox = cap;
                    break;
                } else {
                    getBox += pickups[getIdx];
                    pickups[getIdx] = 0;
                }
                getIdx--;
            }
            
            while (putIdx >= 0 && deliveries[putIdx] == 0) putIdx--;
            while (getIdx >= 0 && pickups[getIdx] == 0) getIdx--;
            putBox = 0;
            getBox = 0;
        }
        
        return answer * 2;
    }
}