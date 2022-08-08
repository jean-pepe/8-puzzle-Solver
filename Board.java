import java.util.*;

public class Board
{
    private final int boardDimension;
    private final char[] cube;
    private int voidPosition;
   
    private int row(int p) { return (int) Math.ceil((double)p/(double)boardDimension); }
   
    private int column(int p) {
        if (p % boardDimension == 0) 
            return boardDimension;
        return p % boardDimension;
    }

    public Board(int[][] cubes) {
        boardDimension = cubes.length;
        this.cube = new char[boardDimension*boardDimension];
        int k = 0 ;
        for (int i = 0; i < boardDimension; i++){
            for (int j = 0; j < boardDimension; j++) {
                this.cube[k] = (char) cubes[i][j];
                if (cubes[i][j] == 0) voidPosition = k;
                k ++;
            }
        }
    }

    public int dimension() { return boardDimension; }

    public int manhattan() {
        int manhattan = 0;
        for (int k = 0; k < boardDimension*boardDimension; k++) {
            if (cube[k] == 0) continue;
            int rowdifference = Math.abs(row(cube[k]) - row(k+1));
            int columndiff = Math.abs(column(cube[k]) - column(k+1));
            manhattan = manhattan + rowdifference + columndiff;
        }
        return manhattan;
    }

    public boolean isGoal() {
        for (int k = 0 ; k < boardDimension * boardDimension - 2; k++)
            if (cube[k] > cube[k + 1]) return false;
        return true;
    }
    public boolean equals(Object t) 
    {
        if (t == this) return true;
        if (t == null) return false;
        if (t.getClass() != this.getClass()) return false;
        Board that = (Board) t;
        if (!Arrays.equals(this.cube, that.cube)) return false;
        return true;
    }
    
    public Board twin() {
        boolean swapsucc = false;
        char[] clone = cube.clone();
        int k = 0;
        do { k=(int)(Math.random()*(boardDimension*boardDimension)); } 
        while (cube[k] == 0);

        while (swapsucc == false) {
            int choice = (int) (Math.random()*4);
            switch(choice) {
                case 0 :
                    if(row(k+1) == 1) swapsucc = false;
                    else if(clone[k-boardDimension] == 0) swapsucc = false;
                    else {
                        swapUp(clone,k);
                        swapsucc = true;
                    }
                    break;
                case 1 : //swap down
                    if(row(k+1) == boardDimension) swapsucc = false;
                    else if(clone[k+boardDimension] == 0) swapsucc = false;
                    else {
                        swapDown(clone,k);
                        swapsucc = true;
                    }
                    break;
                case 2 : //swap left
                    if(column(k+1) == 1) swapsucc = false;
                    else if (clone[k-1] == 0) swapsucc = false;
                    else {
                        swapLeft(clone,k);
                        swapsucc = true;
                    }
                    break;
                case 3 : //swap right
                    if(column(k+1) == boardDimension) swapsucc = false;
                    else if(clone[k+1] == 0) swapsucc = false;
                    else {
                        swapRight(clone,k);
                        swapsucc = true;
                    }
                    break;
            }
        }
        Board cloneBoard = new Board(matrix(clone));
        return cloneBoard;
    }

    public Iterable<Board> neighbors(){
        Stack<Board> stackNeighbors = new Stack<Board>();
        char[] neighbor;
        if(row(voidPosition+1) != 1) {
            neighbor = cube.clone();
            swapUp(neighbor,voidPosition);
            Board neighborBoard = new Board(matrix(neighbor));
            stackNeighbors.push(neighborBoard);
        }
        if(row(voidPosition+1) != boardDimension) {
            neighbor = cube.clone();
            swapDown(neighbor,voidPosition);
            Board neighborBoard = new Board(matrix(neighbor));
            stackNeighbors.push(neighborBoard);
        }
        if(column(voidPosition+1) != 1) {
            neighbor = cube.clone();
            swapLeft(neighbor,voidPosition);
            Board neighborBoard = new Board(matrix(neighbor));
            stackNeighbors.push(neighborBoard);
        }
        if(column(voidPosition+1) != boardDimension) {
            neighbor = cube.clone();
            swapRight(neighbor,voidPosition);
            Board neighborBoard = new Board(matrix(neighbor));
            stackNeighbors.push(neighborBoard);
        }
        return stackNeighbors;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < (boardDimension*boardDimension); i++)
                s.append(String.format("%2d ", (int)cube[i]));
        return s.toString();
    }
   
    private void swapUp(char[] y, int k){
        char temp = y[k];
        y[k] = y[k - boardDimension];
        y[k - boardDimension] = temp;
    }
    private void swapDown(char[] y, int k){
        char temp = y[k];
        y [k]= y[k + boardDimension];
        y[k + boardDimension] = temp;
    }
    private void swapLeft(char[] y, int k){
        char temp = y[k];
        y[k] = y[k - 1];
        y[k - 1] = temp;
    }
    private void swapRight(char[] y, int k){
        char temp = y[k];
        y[k] = y[k + 1];
        y[k + 1] = temp;
    }

    private int[][] matrix(char[] y) 
    {
        int k = 0 ;
        int[][] cubes = new int[boardDimension][boardDimension];
        for (int i = 0; i < boardDimension; i++)
            for (int j = 0; j < boardDimension; j++) {
            cubes[i][j] = y[k];
            k++;
        }
        return cubes;
    }    
}