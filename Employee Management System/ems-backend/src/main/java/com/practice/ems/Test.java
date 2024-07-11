package com.practice.ems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Test {

    /*

        Title - Longest substring without repeating characters

        Statement - Given a string s, find the length of the longest substring without repeating characters.

        Example 1:
        Input: s = "abcabcbb"
        Output: 3
        Explanation: The answer is "abc", with the length of 3.

        Example 2:
        Input: s = "bbbbb"
        Output: 1
        Explanation: The answer is "b", with the length of 1.

        Example 3:
        Input: s = "pwwkew"
        Output: 3
        Explanation: The answer is "wke", with the length of 3.

        Input: s = "afdeabtapq"
        Output: 6
        Explanation: The answer is "fdeabt", with the length of 6.

     */

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int left = 0, right = 0;
        HashSet<Character> set = new HashSet<>();

        while (right < n) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
                maxLength = Math.max(maxLength, right - left);
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }

        return maxLength;
    }

    /*

        Title - Score of a string

        Problem - You are given a string s. The score of a string is defined as the sum of the absolute difference between
                  the ASCII values of adjacent characters.
                  Return the score of s.

        Example 1:
        Input: s = "hello"
        Output: 13
        Explanation:
        The ASCII values of the characters in s are: 'h' = 104, 'e' = 101, 'l' = 108, 'o' = 111. So,
        the score of s would be |104 - 101| + |101 - 108| + |108 - 108| + |108 - 111| = 3 + 7 + 0 + 3 = 13.

        Example 2:
        Input: s = "zaz"
        Output: 50
        Explanation:
        The ASCII values of the characters in s are: 'z' = 122, 'a' = 97. So,
        the score of s would be |122 - 97| + |97 - 122| = 25 + 25 = 50.

     */

    public static int calculateScore(String s) {
        int score = 0;

        for (int i = 1; i < s.length(); i++) {
            score += Math.abs(s.charAt(i) - s.charAt(i - 1));
        }

        return score;
    }

    /*

        Title - Valid Word

        Problem -   A word is considered valid if:
                    It contains a minimum of 3 characters.
                    It contains only digits (0-9), and English letters (uppercase and lowercase).
                    It includes at least one vowel.
                    It includes at least one consonant.
                    You are given a string word.
                    Return true if word is valid, otherwise, return false.
                    Notes:
                    'a', 'e', 'i', 'o', 'u', and their uppercases are vowels.
                    A consonant is an English letter that is not a vowel.

        Example 1:
        Input: word = "234Adas"
        Output: true
        Explanation:
        This word satisfies the conditions.

        Example 2:
        Input: word = "b3"
        Output: false
        Explanation:
        The length of this word is fewer than 3, and does not have a vowel.

        Example 3:
        Input: word = "a3$e"
        Output: false
        Explanation:
        This word contains a '$' character and does not have a consonant.

        Example 4:
        Input: word = "123@157Ab0"
        Output: false
        Explanation:
        This word contains a '@' character and does not have a consonant.

        Example 5:
        Input: word = "a3B"
        Output: true
        Explanation:
        This word satisfies the conditions.

     */

    public static boolean isValid(String s) {
        if (s.length() < 3) {
            return false;
        }
        boolean hasVowel = false;
        boolean hasConsonant = false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
            if (isVowel(c)) {
                hasVowel = true;
            } else if (Character.isLetter(c)) {
                hasConsonant = true;
            }
        }
        return hasVowel && hasConsonant;
    }

    private static boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    /*
        New Questions
     */

    /*
        Title - Group Anagrams

        Given an array of strings strs, group the anagrams together. You can return the answer in any order.

        An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
        typically using all the original letters exactly once.

        Example 1:
        Input: strs = ["eat","tea","tan","ate","nat","bat"]
        Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

        Example 2:
        Input: strs = [""]
        Output: [[""]]

        Example 3:
        Input: strs = ["a"]
        Output: [["a"]]
     */

    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }
            map.get(sorted).add(s);
        }

        return new ArrayList<>(map.values());
    }

    /*
        Title - Maximum sub array

        Description - Given an integer array nums, find the subarray with the largest sum, and return its sum.

        Example 1:
        Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
        Output: 6
        Explanation: The subarray [4,-1,2,1] has the largest sum 6.

        Example 2:
        Input: nums = [1]
        Output: 1
        Explanation: The subarray [1] has the largest sum 1.

        Example 3:
        Input: nums = [5,4,-1,7,8]
        Output: 23
        Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.

        Example 4:
        Input: nums = [1, 2, 3, 4, 5]
        Output: 15
        Explanation: The subarray [1, 2, 3, 4, 5] has the largest sum 15.

        Example 5:
        Input: nums = [-10, 2, 3, 4, 5, 50]
        Output: 64
        Explanation: The subarray [2, 3, 4, 5, 50] has the largest sum 64.
     */

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }


    public static void main(String[] args) {

//        String s1 = "pwwkew";
//        int result1 = lengthOfLongestSubstring(s1);
//        System.out.println("The length of the longest substring without repeating characters is: " + result1);
//
//        String s2 = "zaz";
//        int result2 = calculateScore(s2);
//        System.out.println("The score of the string \"" + s2 + "\" is: " + result2);
//
//        String s3 = "a3B";
//        System.out.println("Is \"" + s3 + "\" valid? " + isValid(s3));
//
//        String[] s4 = {"eat", "tea", "tan", "ate", "nat", "bat"};
//        List<List<String>> result = groupAnagrams(s4);
//        for (List<String> group : result) {
//            System.out.println(group);
//        }

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("The maximum subarray sum is: " + maxSubArray(nums));
    }
}