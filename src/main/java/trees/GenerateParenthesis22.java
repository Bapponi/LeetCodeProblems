package trees;

import java.util.ArrayList;
import java.util.List;

class GenerateParenthesis22 {

    private void backtrack(int openCount, int closedCount, int n, List<String> res, StringBuilder stack) {
        if(openCount == n && openCount == closedCount) {
            res.add(stack.toString());
            return;
        }

        if(closedCount < openCount) {
            stack.append(')');
            backtrack(openCount, closedCount + 1, n, res, stack);
            stack.deleteCharAt(stack.length() - 1);
        }
        if(openCount < n) {
            stack.append('(');
            backtrack(openCount + 1, closedCount, n, res, stack);
            stack.deleteCharAt(stack.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder stack = new StringBuilder();
        backtrack(0, 0, n, res, stack);
        return res;
    }

    public static void main(String[] args) {
        GenerateParenthesis22 solution = new GenerateParenthesis22();

        List<String> result = solution.generateParenthesis(3);
        System.out.println("Result: " + result);
    }
}
