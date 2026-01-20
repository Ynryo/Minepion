package fr.ynryo.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IA extends Player{
    private int difficulty;
    
    public IA() {
        super("Ordinateur", "redstone");
//        this(1);
    }
    
    public IA(int difficulty) {
        super("Ordinateur", "redstone");
        this.difficulty = difficulty;
    }
    
    public int getDifficulty() {
        return difficulty;
    }
    
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    
    public void move(int[][] board) {
        List<int[]> emptyPositions = new ArrayList<>();

        // search for empty cases
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    emptyPositions.add(new int[]{i, j});
                }
            }
        }

        if (emptyPositions.isEmpty()) {
            return;
        }

        Random random = new Random();
        int[] move = emptyPositions.get(random.nextInt(emptyPositions.size()));

        board[move[0]][move[1]] = 2;
    }
}