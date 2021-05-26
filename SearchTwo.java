import java.lang.Math;

/*

Finds the path from initial state to goal state using an
eval function that returns the Manhattan distance to the goal

*/
class SearchTwo extends SearcherBase {

  public SearchTwo() {

    searchTypeName = "Search strat 2";
    appendToFileWhenWritingSolution = true;

  }

  protected double evalFunction(GridNode nodeToConsider) {

    //Manhattan distance to the goal:
    return (Math.abs(nodeToConsider.getGridRow() - goalNode.getGridRow())) + (Math.abs(nodeToConsider.getGridColumn() - goalNode.getGridColumn()));

  }

}
