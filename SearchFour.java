/*

Finds the path from initial state to goal state using an
eval function that returns the sum of the path cost with the
Manhattan distance from current node to goal node

*/
class SearchFour extends SearcherBase {

  public SearchFour() {

    searchTypeName = "Search strat 4";
    appendToFileWhenWritingSolution = true;

  }

  protected double evalFunction(GridNode nodeToConsider) {

    /*

    -g(N) = determinePathCost(nodeToConsider), the cost of the path so far from the initial node to N

    -h(N) = (Math.abs(nodeToConsider.getGridRow() - goalNode.getGridRow())) + (Math.abs(nodeToConsider.getGridColumn() - goalNode.getGridColumn())),
      the Manhattan distance to the goal

    -f(N) = g(N) + h(N)

    */
    return determinePathCost(nodeToConsider) + ((Math.abs(nodeToConsider.getGridRow() - goalNode.getGridRow())) + (Math.abs(nodeToConsider.getGridColumn() - goalNode.getGridColumn())));

  }


}
