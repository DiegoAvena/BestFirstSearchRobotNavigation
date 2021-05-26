import java.util.*;

abstract class SearcherBase extends FileIOManager {

  protected String searchTypeName; //for differentiating between the different search methods

  protected int numberOfNodesInSearchTree; //keeps track of how many nodes are in the current search tree
  private int pathCost; //the cost of the path

  /*

  theFringe is the set of unexplored nodes found as a result of
  expanding a node, uses hash map for faster lookups of next node to go to,
  an improved version would use a priority queue instead though, but I
  am unable to use the predefined priority queue because this queue does not
  base priority off of the cost of the node (and comparator would not do
  what I need in terms of comparison).

  (A custom made priority queue will need to be made)

  */
  protected HashMap<Integer, GridNode> theFringe;
  protected HashMap<Integer, GridNode> exploredNodes; //the set of explored nodes, uses hash map for faster lookups of nodes that have been explored already

  protected GridNode[][] theGrid; //stores ALL of the grid nodes created from the text file
  protected int gridSize; //since grid is a square, a size of 2 means 2x2, 3 means 3x3, etc.

  protected GridNode goalNode; //the node to reach
  protected GridNode initialNode; //the node the robot starts at
  protected GridNode currentNode; //the node the robot currently is at

  //Constructor
  public SearcherBase() {

    theFringe = new HashMap<Integer, GridNode>();
    exploredNodes = new HashMap<Integer, GridNode>();
    numberOfNodesInSearchTree = 0;

    theGrid = null;
    pathCost = 0;

  }

  /*

  Creates all of the search nodes,
  initializes the grid array with the text file,
  returns false if text file does not exist or was
  formatted incorrectly

  */
  public boolean Initialize(String fileName) {

    if (openFile(fileName)) {

      if (theGrid == null) {

        try {

          if (readInNextCharacter()) {

            String letterRepOfGridSize = Character.toString(character);
            readInNextCharacter();
            while (character != '\n') {

              letterRepOfGridSize += Character.toString(character);
              readInNextCharacter();

            }

            gridSize = Integer.parseInt(letterRepOfGridSize);
            theGrid = new GridNode[gridSize][gridSize];

          }
          else {

            //file was empty:
            System.out.println("Error: the file has no contents");
            return false;

          }

        }
        catch (Exception e) {

          //file was not set up properly:
          System.out.println("Error: The first line in the file must be an integer for the grid size");

        }

      }

    }
    else {

      //failed to find the file with the grid
      System.out.println("Error: Could not find the given file");
      return false;

    }

    //Now create all of the nodes and add them to the array
    int keyForNodeInDictionary = 1;

    for(int row = 0; row < gridSize; row++) {

      for (int column = 0; column < gridSize; column++) {

        readInNextCharacter();

        if (character != '\n') {

          theGrid[row][column] = new GridNode(keyForNodeInDictionary, character, row, column);

          if (character == 'g') {

            //this is the goal state:
            goalNode =   theGrid[row][column];

          }

          if (character == 'i') {

            //this is the initial state:
            initialNode = theGrid[row][column];

          }

          keyForNodeInDictionary++; //each node recieves a unique key, so that they can be looked up later during search

        }

      }

      readInNextCharacter();

    }

    //Prints the grid onto the console
    System.out.println("Graph before solution: ");
    for(int row = 0; row < gridSize; row++) {

      for (int column = 0; column < gridSize; column++) {

        System.out.print(theGrid[row][column].getGridSymbol());

      }

      System.out.println();

    }

    //initialization was successful by this point
    currentNode = initialNode;
    return true;

  }

  //the function that the search will use to determine which paths to take
  protected abstract double evalFunction(GridNode nodeToConsider);

  //determines the path cost of a given path
  protected int determinePathCost(GridNode nodeToStartCalculatingPathCostFrom) {

    int stepCost = 0;
    GridNode node = nodeToStartCalculatingPathCostFrom;

    while (node.getNodeKey() != initialNode.getNodeKey()) {

      stepCost++;
      node = node.getPredeccessorNode();

    }

    return stepCost;

  }

  //finds the next node on the path that takes robot closer to the goal node
  protected GridNode findNextNode() {

    GridNode newNodeToGoTo = currentNode;

    theFringe.remove(currentNode.getNodeKey());
    exploredNodes.put(currentNode.getNodeKey(), currentNode);
    numberOfNodesInSearchTree++;

    //Expand the current node to add unexplored nodes to the fringe:

    //check top neighbor:
    if (currentNode.getGridRow() - 1 >= 0) {

      //the current node has a top neighbor:
      if (exploredNodes.containsKey(theGrid[currentNode.getGridRow() - 1][currentNode.getGridColumn()].getNodeKey()) == false) {

        //this neighbor has not been explored yet
        if (theGrid[currentNode.getGridRow() - 1][currentNode.getGridColumn()].getGridSymbol() != '+') {

          //this neighbor has not been explored yet and is empty, add to the fringe:
          theGrid[currentNode.getGridRow() - 1][currentNode.getGridColumn()].setPredecessorNode(currentNode);
          theFringe.put(theGrid[currentNode.getGridRow() - 1][currentNode.getGridColumn()].getNodeKey(), theGrid[currentNode.getGridRow() - 1][currentNode.getGridColumn()]);
          theGrid[currentNode.getGridRow() - 1][currentNode.getGridColumn()].setNodeCost(evalFunction(theGrid[currentNode.getGridRow() - 1][currentNode.getGridColumn()]));

        }

      }

    }

    //check bottom neighbor:
    if (currentNode.getGridRow() + 1 < gridSize) {

      //the current node has a left neighbor:
      if (exploredNodes.containsKey(theGrid[currentNode.getGridRow() + 1][currentNode.getGridColumn()].getNodeKey()) == false) {

        //this neighbor has not been explored yet
        if (theGrid[currentNode.getGridRow() + 1][currentNode.getGridColumn()].getGridSymbol() != '+') {

          //this neighbor has not been explored yet and is empty, add to the fringe:
          theGrid[currentNode.getGridRow() + 1][currentNode.getGridColumn()].setPredecessorNode(currentNode);
          theFringe.put(theGrid[currentNode.getGridRow() + 1][currentNode.getGridColumn()].getNodeKey(), theGrid[currentNode.getGridRow() + 1][currentNode.getGridColumn()]);
          theGrid[currentNode.getGridRow() + 1][currentNode.getGridColumn()].setNodeCost(evalFunction(theGrid[currentNode.getGridRow() + 1][currentNode.getGridColumn()]));

        }

      }

    }

    //check right:
    if (currentNode.getGridColumn() + 1 < gridSize) {

      //the current node has a right neighbor:
      if (exploredNodes.containsKey(theGrid[currentNode.getGridRow()][currentNode.getGridColumn() + 1].getNodeKey()) == false) {

        //this neighbor has not been explored yet
        if (theGrid[currentNode.getGridRow()][currentNode.getGridColumn() + 1].getGridSymbol() != '+') {

          //this neighbor has not been explored yet and is empty, add to the fringe:
          theGrid[currentNode.getGridRow()][currentNode.getGridColumn() + 1].setPredecessorNode(currentNode);
          theFringe.put(theGrid[currentNode.getGridRow()][currentNode.getGridColumn() + 1].getNodeKey(), theGrid[currentNode.getGridRow()][currentNode.getGridColumn() + 1]);
          theGrid[currentNode.getGridRow()][currentNode.getGridColumn() + 1].setNodeCost(evalFunction(theGrid[currentNode.getGridRow()][currentNode.getGridColumn() + 1]));

        }

      }

    }

    //Check left:
    if (currentNode.getGridColumn() - 1 >= 0) {

      //the current node has a left neighbor:
      if (exploredNodes.containsKey(theGrid[currentNode.getGridRow()][currentNode.getGridColumn() - 1].getNodeKey()) == false) {

        //this neighbor has not been explored yet
        if (theGrid[currentNode.getGridRow()][currentNode.getGridColumn() - 1].getGridSymbol() != '+') {

          //this neighbor has not been explored yet and is empty, add to the fringe:
          theGrid[currentNode.getGridRow()][currentNode.getGridColumn() - 1].setPredecessorNode(currentNode);
          theFringe.put(theGrid[currentNode.getGridRow()][currentNode.getGridColumn() - 1].getNodeKey(), theGrid[currentNode.getGridRow()][currentNode.getGridColumn() - 1]);
          theGrid[currentNode.getGridRow()][currentNode.getGridColumn() - 1].setNodeCost(evalFunction(theGrid[currentNode.getGridRow()][currentNode.getGridColumn() - 1]));

        }

      }

    }

    //Set to the new node (which is the 1 with the smallest cost in the fringe):
    double currentSmallestCost = -5;

    for (Map.Entry<Integer, GridNode> entry : theFringe.entrySet()) {

      if (currentSmallestCost < 0) {

        //just pick any node to start with
        currentSmallestCost = entry.getValue().getNodeCost();
        newNodeToGoTo = entry.getValue();

      }
      else if (currentSmallestCost > entry.getValue().getNodeCost()){

        //this node in the fringe is cheaper, set this as the potential new node to go to
        currentSmallestCost = entry.getValue().getNodeCost();
        newNodeToGoTo = entry.getValue();

      }

    }

    return newNodeToGoTo;

  }

  /*

  Prints results to the console, and also writes the results
  to a file called Solutions.txt

  */
  private void reportResults() {

    String results = "\n" + searchTypeName + "\nGrid before solution: \n";

    for (int row = 0; row < gridSize; row++) {

      for (int column = 0; column < gridSize; column++) {

        results += theGrid[row][column].getGridSymbol();

      }

      results += "\n";

    }


    pathCost = determinePathCost(goalNode);

    //current node is the goal node, do not want to change the goal nodes symbol to O, so go to its predecessor:
    GridNode node = goalNode.getPredeccessorNode();

    //Draws the path onto the grid:
    while (node.getNodeKey() != initialNode.getNodeKey()) {


      theGrid[node.getGridRow()][node.getGridColumn()].setGridSymbol('O');
      node = node.getPredeccessorNode();

    }

    System.out.println("Solution: ");

    //Write solution to file and print on the console:
    results += "Solution Grid: \n";
    for(int row = 0; row < gridSize; row++) {

      for (int column = 0; column < gridSize; column++) {

        results += theGrid[row][column].getGridSymbol();
        System.out.print(theGrid[row][column].getGridSymbol());

      }

      results += "\n";
      System.out.println();

    }

    results += ("Path Cost: " +pathCost+"\n");
    results += ("Number of nodes in search tree: "+numberOfNodesInSearchTree+"\n");

    //Write the results to a file
    writeMessageToFile("Solutions.txt", results);

    System.out.println("Path cost using " +searchTypeName+" is: "+ pathCost);
    System.out.println("Number of nodes in search tree: "+numberOfNodesInSearchTree);

  }

  //Launches the search algorithm
  public void conductSearch() {

    exploredNodes.put(initialNode.getNodeKey(), initialNode);
    currentNode = initialNode;

    while (true) {

      currentNode = findNextNode();

      if (currentNode.getNodeKey() ==  goalNode.getNodeKey()) {

        //solution found
        reportResults();
        break;

      }

      if (theFringe.isEmpty()) {

        //no solution found
        System.out.println("No solution found");
        break;

      }

    }

  }


}
