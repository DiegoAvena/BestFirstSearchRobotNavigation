import java.lang.Math;

/*

Finds the path to the goal state using an eval function that
returns the sum of the path cost so fat from initialNode to current node, and the
Euclidean distance from current node to goal node

*/
class SearchThree extends SearcherBase {

  public SearchThree() {

    searchTypeName = "Search strat 3";
    appendToFileWhenWritingSolution = true;

  }

  protected double evalFunction(GridNode nodeToConsider) {

    /*

      -g(N) = determinePathCost(nodeToConsider), the cost of the path so far from the initial node to N

      -h(N) =  Math.sqrt((Math.pow(nodeToConsider.getGridRow() - goalNode.getGridRow(), 2)) + (Math.pow(nodeToConsider.getGridColumn() - goalNode.getGridColumn(), 2)),
        the Euclidean Distance from N to the goal

      -f(N) = g(N) + h(N)

    */
    return determinePathCost(nodeToConsider) + Math.sqrt((Math.pow(nodeToConsider.getGridRow() - goalNode.getGridRow(), 2)) + (Math.pow(nodeToConsider.getGridColumn() - goalNode.getGridColumn(), 2)));

  }

}
