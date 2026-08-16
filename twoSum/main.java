import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class main {
    
    /**
     * Find two numbers in the array that add up to the target sum.
     * Returns the indices of the two numbers.
     * 
     * @param nums Array of integers
     * @param target The target sum
     * @return An array containing the indices of the two numbers that add up to target
     */
    public static int[] twoSum(int[] nums, int target) {
        // HashMap to store value and its index
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            // Calculate the complement needed to reach target
            int complement = target - nums[i];
            
            // Check if complement exists in map
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            
            // Store current number and its index
            map.put(nums[i], i);
        }
        
        // No solution found
        return new int[] {};
    }
    
    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = { 2, 7, 11, 15 };
        int target1 = 9;
        int[] result1 = twoSum(nums1, target1);
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Output: " + Arrays.toString(result1));
        System.out.println();
        
        // Test case 2
        int[] nums2 = { 3, 2, 4 };
        int target2 = 6;
        int[] result2 = twoSum(nums2, target2);
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + Arrays.toString(result2));
        System.out.println();
        
        // Test case 3
        int[] nums3 = { 3, 3 };
        int target3 = 6;
        int[] result3 = twoSum(nums3, target3);
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", target = " + target3);
        System.out.println("Output: " + Arrays.toString(result3));
    }
}
