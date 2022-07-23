# 8-puzzle-Solver
Program to solve the [8-puzzle](http://en.wikipedia.org/wiki/Fifteen_puzzle) problem (and its natural generalizations) using the A* search algorithm.

# Programming Assignment :

># The problem. 
The 8-puzzle is a sliding puzzle that is played on a 3-by-3 grid with 8 square tiles labeled 1 through 8, plus a blank square. The goal is to rearrange the tiles so that they are in row-major order, using as few moves as possible. You are permitted to slide tiles either horizontally or vertically into the blank square. The following diagram shows a sequence of moves from an initial board (left) to the goal board (right).
![](https://www.cs.princeton.edu/courses/archive/spr18/cos226/assignments/8puzzle/4moves.png)

### Board data type. 
To begin, create a data type that models an n-by-n board with sliding tiles. Implement an immutable data type Board
Constructor.  You may assume that the constructor receives an n-by-n array containing a permutation of the n2 integers between 0 and n2 − 1, where 0 represents the blank square. You may also assume that 2 ≤ n ≤ 32,768.

### String representation.  
The toString() method returns a string composed of n + 1 lines. The first line contains the board size n; the remaining n lines contains the n-by-n grid of tiles in row-major order, using 0 to designate the blank square.

![](https://www.cs.princeton.edu/courses/archive/spr18/cos226/assignments/8puzzle/string-representation.png)

### The Manhattan distance 
The Manhattan distancebetween a board and the goal board is the sum of the Manhattan distances (sum of the vertical and horizontal distance) from the tiles to their goal positions.
