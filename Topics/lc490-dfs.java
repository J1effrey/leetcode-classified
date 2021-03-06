public class Solution {
    private static final int[] Y_DIRECTIONS = { 0, 0, 1, -1};
    private static final int[] X_DIRECTIONS = { 1, -1, 0, 0 };
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] startedHere = new boolean[maze.length][maze[0].length];
        return dfs(maze, startedHere, start, destination);
    }
    
    private boolean dfs(int[][] maze, boolean[][] startedHere, int[] start, int[] destination) {
        if (startedHere[start[0]][start[1]]) return false;
        if (Arrays.equals(start, destination)) return true;
        
        startedHere[start[0]][start[1]] = true;
        
        for (int i = 0; i < X_DIRECTIONS.length; i++) {
            int[] newStart = roll(maze, start[0], start[1], X_DIRECTIONS[i], Y_DIRECTIONS[i]);
            if (dfs(maze, startedHere, newStart, destination)) return true;
        }
        
        return false;
    }
    
    private int[] roll(int[][] maze, int row, int col, int rowInc, int colInc) {
        while (canRoll(maze, row + rowInc, col + colInc)) {
            row += rowInc;
            col += colInc;
        }
        
        return new int[]{row, col};
    }
    
    private boolean canRoll(int[][] maze, int row, int col) {
        if (row >= maze.length || row < 0 || col >= maze[0].length || col < 0) return false;
        return maze[row][col] != 1; // 1 is a wall
    }
}
