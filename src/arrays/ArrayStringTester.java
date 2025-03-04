package arrays;

/**
 * Test class demonstrating the usage of DynamicArray and StringUtils.
 */
public class ArrayStringTester {

    public static void main(String[] args) {
        testDynamicArray();
        testStringUtils();
        solveSampleProblems();
    }

    /**
     * Tests the DynamicArray implementation.
     */
    private static void testDynamicArray() {
        System.out.println("===== DYNAMIC ARRAY TESTS =====");

        // Create a dynamic array
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        System.out.println("Initial capacity: " + dynamicArray.capacity());
        System.out.println("Initial size: " + dynamicArray.size());

        // Add elements
        System.out.println("\nAdding elements...");
        for (int i = 0; i < 15; i++) {
            dynamicArray.add(i * 10);
        }
        System.out.println("After adding 15 elements:");
        System.out.println("Size: " + dynamicArray.size());
        System.out.println("Capacity: " + dynamicArray.capacity());
        System.out.println("Contents: " + dynamicArray);

        // Access elements
        System.out.println("\nAccessing elements:");
        System.out.println("Element at index 5: " + dynamicArray.get(5));

        // Modify element
        System.out.println("\nModifying element at index 7...");
        int oldValue = dynamicArray.set(7, 777);
        System.out.println("Old value: " + oldValue);
        System.out.println("New contents: " + dynamicArray);

        // Insert element
        System.out.println("\nInserting element at index 3...");
        dynamicArray.add(3, 333);
        System.out.println("After insertion:");
        System.out.println("Size: " + dynamicArray.size());
        System.out.println("Contents: " + dynamicArray);

        // Remove element
        System.out.println("\nRemoving element at index 8...");
        int removedValue = dynamicArray.remove(8);
        System.out.println("Removed value: " + removedValue);
        System.out.println("After removal:");
        System.out.println("Size: " + dynamicArray.size());
        System.out.println("Contents: " + dynamicArray);

        // Test with string elements
        System.out.println("\nTesting with String elements:");
        DynamicArray<String> stringArray = new DynamicArray<>();
        stringArray.add("Java");
        stringArray.add("Python");
        stringArray.add("C++");
        System.out.println("String array: " + stringArray);
    }

    /**
     * Tests the StringUtils implementation.
     */
    private static void testStringUtils() {
        System.out.println("\n===== STRING UTILS TESTS =====");

        // Test string reversal
        String original = "Hello, World!";
        String reversed = StringUtils.reverse(original);
        System.out.println("Original string: " + original);
        System.out.println("Reversed string: " + reversed);

        // Test substring
        String subString = StringUtils.substring(original, 7, 12);
        System.out.println("\nSubstring(7, 12): " + subString);

        // Test anagram check
        String str1 = "listen";
        String str2 = "silent";
        String str3 = "hello";
        System.out.println("\nTesting anagrams:");
        System.out.println(str1 + " and " + str2 + " are anagrams: " + StringUtils.isAnagram(str1, str2));
        System.out.println(str1 + " and " + str3 + " are anagrams: " + StringUtils.isAnagram(str1, str3));

        // Test array rotation
        System.out.println("\nTesting array rotation:");
        char[] charArray = original.toCharArray();
        System.out.println("Original array: " + new String(charArray));
        StringUtils.rotateArray(charArray, 4);
        System.out.println("After rotation by 4: " + new String(charArray));
    }

    /**
     * Solves the sample problems mentioned in the requirements.
     */
    private static void solveSampleProblems() {
        System.out.println("\n===== SAMPLE PROBLEMS =====");

        // Two Sum
        System.out.println("Two Sum Problem:");
        int[] nums = {2, 7, 11, 15, 3, 6, 8, 14};
        int target = 9;
        int[][] pairs = StringUtils.twoSum(nums, target);

        System.out.println("Input array: " + arrayToString(nums));
        System.out.println("Target: " + target);
        System.out.println("Pairs with sum " + target + ":");
        for (int[] pair : pairs) {
            System.out.println("Indices: [" + pair[0] + ", " + pair[1] + "], " +
                    "Values: [" + nums[pair[0]] + ", " + nums[pair[1]] + "]");
        }

        // Valid Anagram
        System.out.println("\nValid Anagram Problem:");
        String s1 = "anagram";
        String s2 = "nagaram";
        System.out.println("String 1: " + s1);
        System.out.println("String 2: " + s2);
        System.out.println("Are they anagrams? " + StringUtils.isAnagram(s1, s2));

        // Rotate Array
        System.out.println("\nRotate Array Problem:");
        char[] chars = "abcdefg".toCharArray();
        System.out.println("Original array: " + new String(chars));
        StringUtils.rotateArray(chars, 3);
        System.out.println("After rotation by 3: " + new String(chars));
    }

    /**
     * Helper method to convert an array to string for display.
     */
    private static String arrayToString(int[] array) {
        if (array == null) return "null";
        if (array.length == 0) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
