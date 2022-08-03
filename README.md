# 8-puzzle-Solver
Program to solve the [8-puzzle](http://en.wikipedia.org/wiki/Fifteen_puzzle) problem (and its natural generalizations) using the A* search algorithm.

# Programming Assignment :

># The problem. 
The 8-puzzle is a sliding puzzle that is played on a 3-by-3 grid with 8 square tiles labeled 1 through 8, plus a blank square. The goal is to rearrange the tiles so that they are in row-major order, using as few moves as possible. You are permitted to slide tiles either horizontally or vertically into the blank square. The following diagram shows a sequence of moves from an initial board (left) to the goal board (right).

![](https://www.cs.princeton.edu/courses/archive/spr18/cos226/assignments/8puzzle/4moves.png)

### Board data type. 
To begin, create a data type that models an n-by-n board with sliding tiles. Implement an immutable data type Board
**Constructor**.  You may assume that the constructor receives an n-by-n array containing a permutation of the n2 integers between 0 and n2 − 1, where 0 represents the blank square. You may also assume that 2 ≤ n ≤ 32,768.

### String representation.  
The **toString()** method returns a string composed of n + 1 lines. The first line contains the board size n; the remaining n lines contains the n-by-n grid of tiles in row-major order, using 0 to designate the blank square.

![](https://www.cs.princeton.edu/courses/archive/spr18/cos226/assignments/8puzzle/string-representation.png)

### The Manhattan distance 
**The Manhattan distance** between a board and the goal board is the sum of the Manhattan distances (sum of the vertical and horizontal distance) from the tiles to their goal positions.
### Performance requirements.  
In the worst case, your implementation must support **size()**  in constant time;

**the constructor, manhattan(), isGoal(), equals(), toString()** in time proportional to n² (or better); 


### A* search. 
Now, we describe a solution to the 8-puzzle problem that illustrates a general artificial intelligence methodology known as the [A* search algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm). We define a search node of the game to be a board, the number of moves made to reach the board, and the previous search node. First, insert the initial search node (the initial board, 0 moves, and a null previous search node) into a priority queue. Then, delete from the priority queue the search node with the minimum priority, and insert onto the priority queue all neighboring search nodes (those that can be reached in one move from the dequeued search node). Repeat this procedure until the search node dequeued corresponds to the goal board.

The efficacy of this approach hinges on the choice of priority function for a search node. We consider two priority functions:

The Manhattan priority function is the Manhattan distance of a board plus the number of moves made so far to get to the search node.
To solve the puzzle from a given search node on the priority queue, the total number of moves we need to make (including those already made) is at least its priority, using Manhattan priority function. Why? Consequently, when the goal board is dequeued, we have discovered not only a sequence of moves from the initial board to the goal board, but one that makes the fewest moves.

### Game tree. 
One way to view the computation is as a game tree, where each search node is a node in the game tree and the children of a node correspond to its neighboring search nodes. The root of the game tree is the initial search node; the internal nodes have already been processed; the leaf nodes are maintained in a priority queue; at each step, the A* algorithm removes the node with the smallest priority from the priority queue and processes it (by adding its children to both the game tree and the priority queue).

For example, the following diagram illustrates the game tree after each of the first three steps of running the A* search algorithm on a 3-by-3 puzzle using the Manhattan priority function.
![](https://www.cs.princeton.edu/courses/archive/spr18/cos226/assignments/8puzzle/game-tree.png)

### Solver data type. 
In this part, you will implement A* search to solve n-by-n slider puzzles. Create an immutable data type Solver with the following API:

public class Solver {
    public Solver(Board initial)             // find a solution to the initial board (using the A* algorithm)
    public int moves()                       // min number of moves to solve initial board
    public Iterable<Board> listSolution()        // sequence of boards in a shortest solution
    public static void main(String[] args)   // test client (see below) 
}
### Test client.
Your test client should take the name of an input file as a command-line argument and print the minimum number of moves to solve the puzzle and a corresponding solution. The input file contains the board size n, followed by the n-by-n grid of tiles, using 0 to designate the blank square.

java Solver puzzle04.txt
Number of moves = 4
    
    
 0  1  3 
 4  2  5 
 7  8  6 


 1  0  3 
 4  2  5 
 7  8  6 

    
 1  2  3 
 4  0  5 
 7  8  6 

    
 1  2  3 
 4  5  0 
 7  8  6 

    
 1  2  3 
 4  5  6 
 7  8  0

    
    #TO-DO 
    -general refactoring of old my old code to an more readable source code
    -make a new version in c++ to learn that language
