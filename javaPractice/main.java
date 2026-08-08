import java.util.HashMap;
import java..util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap <> ();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (numToIndex.containsKey(complement)) {
                return new int[] { numToIndex.get(complement), i };
            }

            // Add current number and index to map
            numToIndex.put(nums[i], i);
        }

        throw new IllegalArgumentException("No solution for two sum");
    }
}

/* public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
*/