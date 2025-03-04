package arrays;

/* Utility class providing custom string manipulation functions */
public class StringUtils {

    /**
     * Reverses a string.
     *
     * @param str the string to reverse
     * @return the reversed string
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }

        char[] characters = str.toCharArray();
        int left = 0;
        int right = characters.length - 1;

        while (left < right) {
            char temp = characters[left];
            characters[left] = characters[right];
            characters[right] = temp;

            left++;
            right--;
        }

        return new String(characters);
    }

    /**
     * Helper method to reverse a portion of a character array.
     *
     * @param chars the character array
     * @param start the starting index
     * @param end the ending index
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * Extracts a substring from the original string.
     *
     * @param str the original string
     * @param start the starting index (inclusive)
     * @param end the ending index (exclusive)
     * @return the extracted substring
     * @throws IndexOutOfBoundsException if indices are invalid
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static String substring(String str, int start, int end) {
        if (str == null)
            return null;

        if (start < 0 || end > str.length() || start > end)
            throw new IndexOutOfBoundsException("Invalid substring indices: start=" + start + ", end=" + end + ", length=" + str.length());

        char[] result = new char[end-start];
        for (int idx=0; idx<result.length; idx++)
            result[idx] = str.charAt(start + idx);

        return new String(result);
    }

    /**
     * Checks if two strings are anagrams of each other.
     *
     * @param str1 the first string
     * @param str2 the second string
     * @return true if the strings are anagrams, false otherwise
     * Time Complexity: O(n)
     * Space Complexity: O(1) - constant space for character counts (max 256)
     */
    public static boolean isAnagram(String str1, String str2) {
        if (str1 == null || str2 == null)
            return str1 == str2;

        if (str1.length() != str2.length())
            return false;

        int[] charCount = new int[256];

        for (char c: str1.toCharArray())
            charCount[c]++;

        for (char c: str2.toCharArray())
            if (--charCount[c] < 0)
                return false;

        return true;
    }

    /**
     * Rotates an array of characters by k positions to the right.
     *
     * @param chars the character array to rotate
     * @param k the number of positions to rotate
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */

    /*

        The Core Concept
        When we rotate an array to the right by k positions, we're essentially performing two operations:

        Taking the last k elements and moving them to the front
        Shifting the first (n-k) elements to the right

        We can think of our array as consisting of two segments: A (first n-k elements) and B (last k elements). After rotation, we want the array to be BA instead of AB.
        Visualizing with an Example
        Consider the array [1, 2, 3, 4, 5] and k = 2.
        We can divide this into:

        A = [1, 2, 3] (first n-k elements)
        B = [4, 5] (last k elements)
     */
    public static void rotateArray(char[] chars, int k) {
        if (chars == null || chars.length <= 1 || k % chars.length == 0)
            return; // no rotation needed

        int n = chars.length;
        k = k % n;  // normalize k to handle k > n

        reverse(chars, 0, n-1);
        reverse(chars, 0, k-1);
        reverse(chars, k, n-1);
    }

    /**
     * Finds pairs of indices in an array where the elements sum to a target value.
     *
     * @param nums the array of integers
     * @param target the target sum
     * @return a 2D array where each row contains the indices of a pair that sums to target
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int[][] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2){
            return new int[0][2];
        }

        DynamicArray<int[]> resultPairs = new DynamicArray<>();

        java.util.Map<Integer, Integer> numberIdxMap = new java.util.HashMap<>();
        for (int idx=0; idx<nums.length; idx++) {
            int complement = target-nums[idx];

            if (numberIdxMap.containsKey(complement)) {
                int[] pair = new int[] {numberIdxMap.get(complement), idx};
                resultPairs.add(pair);
            }

            numberIdxMap.put(nums[idx], idx);
        }

        int[][] result = new int[resultPairs.size()][2];
        for (int idx=0; idx<resultPairs.size(); idx++)
            result[idx] = resultPairs.get(idx);

        return result;
    }
}
