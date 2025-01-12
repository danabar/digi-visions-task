package digi.visions.task.one;

import java.util.Stack;

/**
 * Given a string, we need to reverse substrings between each pair of parentheses, then
 * printing the original with reversed substrings
 */
public class ReverseParentheses {
    public static String reverseParentheses(String s) {
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(i); // Push index of '('
            } else if (chars[i] == ')') {
                int start = stack.pop(); // Pop the matching '('
                reverse(chars, start + 1, i - 1); // Reverse the substring
            }
        }

        // Build the final result, including parentheses
        StringBuilder result = new StringBuilder();
        for (char c : chars) {
            result.append(c);
        }

        return result.toString();
    }

    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverseParentheses("abd(jnb)asdf")); // Output: abd(bnj)asdf
        System.out.println(reverseParentheses("abdjnbasdf"));    // Output: abdjnbasdf
        System.out.println(reverseParentheses("dd(df)a(ghhh)")); // Output: dd(fd)a(hhhg)
    }
}