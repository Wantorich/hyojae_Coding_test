import java.util.*;

class Clerk {
    Clerk boss;
    String name;
    int payment;
    
    Clerk(String name) {
        this.name = name;
    }
}

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        Map<String, Clerk> clerkMap = new HashMap<>();
        Clerk center = new Clerk("center");
        for (int i = 0; i < enroll.length; i++) {
            Clerk clerk = new Clerk(enroll[i]);
            clerk.boss = clerkMap.getOrDefault(referral[i], center);
            clerkMap.put(enroll[i], clerk);
        }
        
        for (int i = 0; i < seller.length; i++) {
            Clerk clerk = clerkMap.get(seller[i]);
            int earning = amount[i] * 100;
            distribute(clerk, earning);
        }
        
        for (int i = 0; i < enroll.length; i++) {
            Clerk clerk = clerkMap.get(enroll[i]);
            answer[i] = clerk.payment;
        }
        
        System.out.println(Arrays.toString(answer));
        
        return answer;
    }
    
    static void distribute(Clerk clerk, int amount) {
        if (clerk.name.equals("center")) return;
        if (amount / 10 == 0) {
            clerk.payment += amount;    
            return;
        }
        int serveMoney = amount / 10;
        int myMoney = amount - serveMoney;
        clerk.payment += myMoney;
        distribute(clerk.boss, serveMoney);
    } 
}