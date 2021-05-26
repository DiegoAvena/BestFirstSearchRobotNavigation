# BestFirstSearchRobotNavigation
Finds the path a robot can take from a starting position to a final position on a 2D, NxN grid.

## PROGRAMMED BY:
  Name: Diego Avena
  email: avena@chapman.edu
  StudentID: 2299333
  Project Name: Best-First Robot Navigation
  
## DESCRIPTION:

-Finds the path a robot can take from a starting position to a final position on a 2D, NxN grid.
-The NxN grid is supplied via an input file (see Input file formatting rules)
-The grid has both free and obstacle grids
-Grids marked with + are obstacle grids
-Grids marked with . are free grids
-A grid marked with i is the starting grid/position of the robot
-A grid marked with g is the goal node/final position the robot wants to reach
-The robot can only move left, right, up, and down, and can only move by 1 grid each step.
-Each move has a step cost of 1

## INPUT FILE FORMATTING RULES:

Create a text file and preferebly place it in the same
directory of this README file.

Organize the input file in the following way:

line 1: X, An integer number representing the grid size
lines 2 to (currentLine + X): The grid, only acceptable characters are . + i g

## COMPILING INSTRUCTIONS:

With makefile:
  make all

 Without makeFile:
  javac *java

## RUNNING INSTRUCTIONS:

  With makefile
    2 Options:

      Option 1: type make run
        Runs program without any file given through command line, this will cause
              program to prompt user to enter the name of a file, or ask if they want to exit

       Option 2: type make run F=(fileToReadFrom)
        allows you to run the program, and feed in the name of a file to read from through the command line

   Without makefile:
      2 Options:

         Option 1: type java Simulation
             Runs program without any file given through command line, this will cause
              program to prompt user to enter the name of a file, or ask if they want to exit

        Option 2: type java Simulation (fileToReadFrom)
            allows you to run the program,
            and feed in the name of
            a file to read from through the command line

## PROGRAM OUTPUT:
  The program will perform 4 versions of best first search on the
  input file given, and output the results in the form of the grid with
  the path drawn on it to both to the console and a
  file named Solutions.txt. Before and solution grids are printed for each
  version of best first search, along with path cost at the bottom of each solution
  grid. The path found the g is marked on the grid by o. If no solution is found, then for
  each best first search version, the program will state this instead of showing a solution
  grid.

