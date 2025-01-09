package backtracking;

import java.util.HashSet;
import java.util.Set;

class WordSearch79 {

    private int ROWS, COLUMNS;
    private Set<String> path = new HashSet<>();

    public boolean dfs(char[][] board, String word, int rows, int columns, int charPosition){
        String coordinates = rows + "," + columns;
        if(charPosition == word.length()){
            return true;
        }
        if(rows < 0 || rows >= ROWS ||
                columns < 0 || columns >= COLUMNS ||
                word.charAt(charPosition) != board[rows][columns] ||
                path.contains(coordinates)){
            return false;
        }
        path.add(coordinates);
        boolean result = (dfs(board, word, rows + 1, columns, charPosition + 1) ||
                dfs(board, word, rows - 1, columns, charPosition + 1) ||
                dfs(board, word, rows, columns + 1, charPosition + 1) ||
                dfs(board, word, rows, columns - 1, charPosition + 1)
        );
        path.remove(coordinates);
        return result;
    }

    public boolean exist(char[][] board, String word) {
        ROWS = board.length;
        COLUMNS = board[0].length;

        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLUMNS; j++){
                if(dfs(board, word, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordSearch79 solution = new WordSearch79();

        char [][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";

        boolean result = solution.exist(board, word);
        System.out.println("Result: " + result);
    }
}
