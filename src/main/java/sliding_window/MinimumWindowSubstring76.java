package sliding_window;

import java.util.HashMap;
import java.util.Map;

class MinimumWindowSubstring76 {

    public String minWindow(String s, String t) {

        if(s.length() < t.length()){
            return "";
        }

        Map<Character, Integer> remainingChars = new HashMap();
        for (int i = 0; i < t.length(); i++) {
            char currentChar = t.charAt(i);
            if (remainingChars.containsKey(currentChar)) {
                remainingChars.put(currentChar, remainingChars.get(currentChar) + 1);
            } else {
                remainingChars.put(currentChar, 1);
            }
        }

        Map<Character, Integer> allChars = new HashMap<>(remainingChars);

        System.out.println(remainingChars.values());

        int right = 0;
        int left = 0;
        int min = 100000000;
        int minRight = 0;
        int minLeft = 0;
        boolean started = false;
        while(right < s.length()){
            char currentCharLeft = s.charAt(left);
            System.out.println("Left: " + left + "\nRight: " + right);
            System.out.println("Current Char Left: " + currentCharLeft);
            System.out.println("------------------ \n  ALLKEYS: " + allChars.keySet() + "\nALLVALUES: " + allChars.values());
            if (allChars.containsKey(currentCharLeft)){
                started = true;
                while(!remainingChars.isEmpty()){
                    System.out.println("------------------ \n  KEYS: " + remainingChars.keySet() + "\nVALUES: " + remainingChars.values());
                    char currentCharRight = s.charAt(right);
                    int count = remainingChars.getOrDefault(currentCharRight, 0);
                    System.out.println("\n  KEY: " + currentCharRight + "\nVALUE: " + count);
                    if (count == 1) {
                        remainingChars.remove(currentCharRight);
                    } else if (count > 1) {
                        remainingChars.put(currentCharRight, count - 1);
                    }
                    right++;
                    if(right == s.length()){
                        // ovde treba da nastavi sa levom stranom dok ne pokupi ono sto mu treba
                        right--;
                        if((right - left) < min){
                            minRight = right;
                            minLeft = left;
                        }
                        return s.substring(minLeft, minRight + 1);
                    }
                }
                right--;
                if((right - left) < min){
                    min = right - left;
                    minRight = right;
                    minLeft = left;
                }
                remainingChars.put(currentCharLeft, 1);
            }
            if(!started)
                right++;

            left++;
            System.out.println("------------------");
        }

        return s.substring(minLeft, minRight + 1);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring76 solution = new MinimumWindowSubstring76();

        String s = "ADOBECODEBANC";
        String t = "ABC";

        String result = solution.minWindow(s, t);
        System.out.println("Result: " + result);
    }
}
