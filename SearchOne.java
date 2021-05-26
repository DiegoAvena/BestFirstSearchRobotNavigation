import java.lang.Math;
import java.util.Map;

/*

Finds a path to the goal node
using the Euclidean distance to
the goal node

*/
class SearchOne extends SearcherBase {

  public SearchOne() {

    searchTypeName = "Search Strat 1";
    appendToFileWhenWritingSolution = false; //so that old solutions potentially in this solution file get deleted

  }

  protected double evalFunction(GridNode nodeToConsider) {

    //Euclidean distance from N to the goal
    return Math.sqrt((Math.pow(nodeToConsider.getGridRow() - goalNode.getGridRow(), 2)) + (Math.pow(nodeToConsider.getGridColumn() - goalNode.getGridColumn(), 2)));

  }

}
