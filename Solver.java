import java.io.*;
import java.util.*;

public class Solver{
    private PriorityQueue<SearchNode>  priorityq;
    private PriorityQueue<SearchNode> pqtwin;
    private int n;
    private Board initial;
    private Board goal;
    private SearchNode fine;

    private class SearchNode implements Comparable<SearchNode>{
        private Board board;
        private int m,priority;
        private SearchNode previousN;

        public SearchNode(Board board, int m, SearchNode previousN){
            this.board = board;
            this.m = m;
            priority = m + board.manhattan();
            this.previousN = previousN;
        }

        public int compareTo(SearchNode that)
            return (this.priority - that.priority);
    }
    
    public Solver(Board initial){        
        this.initial = initial;
        n = initial.dimension();
        priorityq = new PriorityQueue<SearchNode>();
        pqtwin = new PriorityQueue<SearchNode>();

        int[][] cubes = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            cubes[i][j] = i+j+1;
        cubes[n-1][n-1] = 0;
        goal = new Board(cubes);

        SearchNode minN;
        SearchNode minNTwin;
        priorityq.add(new SearchNode(initial, 0, null));
        pqtwin.add(new SearchNode(initial.twin(), 0, null));
        while(!priorityq.peek().board.equals(goal) && !pqtwin.peek().board.equals(goal)) {
            minN = priorityq.peek();
            minNTwin = pqtwin.peek();
            priorityq.poll();  
            pqtwin.poll();
            for (Board neighbor: minN.board.neighbors()){
                if (minN.m == 0) 
                    priorityq.add(new SearchNode(neighbor, minN.m+1, minN));
                else if (!neighbor.equals(minN.previousN.board)) 
                    priorityq.add(new SearchNode(neighbor, minN.m+1, minN));
            }
            
            for (Board neighbor: minNTwin.board.neighbors()) {
                if (minNTwin.m == 0) 
                    pqtwin.add(new SearchNode(neighbor, minNTwin.m+1, minNTwin));
                else if (!neighbor.equals(minNTwin.previousN.board)) 
                    pqtwin.add(new SearchNode(neighbor, minNTwin.m+1, minNTwin));
            }
        }
    }
   
    public int moves() {return priorityq.peek().m;}

    public Iterable<Board> listSolution() {
        LinkedList<Board> stackSolution = new LinkedList<Board>();      
        SearchNode curr = priorityq.peek();
        while (curr.previousN!=null) {
            stackSolution.addFirst(curr.board);
            curr = curr.previousN;
        }
        stackSolution.addFirst(initial);
        return stackSolution;
    }

    public static void main(String[] args) {
        String nomefile = args[0];
        try {      
            FileReader file = new FileReader(nomefile);
            Scanner scan = new Scanner(file);           
            int n = scan.nextInt();
            int[][] cubes = new int[n][n];
            for (int i = 0; i < n; i++)    
                for (int j = 0; j < n; j++){
                    cubes[i][j] = scan.nextInt();
                }
            Board initial= new Board(cubes);
            Solver solving = new Solver(initial);
            System.out.println("Number of moves = " + solving.moves());
            for (Board board : solving.listSolution())
                System.out.println(board.toString());
             file.close();         
        } 
        catch (Exception e) {}
        }
    }