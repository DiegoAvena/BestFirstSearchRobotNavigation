/*

A node data structure for each node in the
grid

*/
class GridNode {

  private int nodeKey; //the key needed to find this node in a dictionary, just the order that this node appears at when reading the 2x2 grid from left to right, top to bottom

  /*

  predecessorNode stores the node that came immedietely before
  this node was looked at; this is what is used later to print the solution out,
  since if I look at every predecessor node starting at the goal node, then I can derive
  the path taken from initial the goal node

  */
  private GridNode predecessorNode;

  private char gridSymbol; //this is really the state description: a state is described by its symbol: i means initial state, g means goal state, + means obstacle, and . means empty state
  private int gridRow; //the row the node is at in the grid
  private int gridColumn; //the column the node is at in the grid

  private double nodeCost; //stores the cost placed on this node by the eval function

  public GridNode() {

  }

  public GridNode(int nodeKey, char gridSymbol, int gridRow, int gridColumn) {

    this.nodeKey = nodeKey;

    this.gridSymbol = gridSymbol;
    this.gridRow = gridRow;
    this.gridColumn = gridColumn;

  }

  //Mutator methods:
  public void setPredecessorNode(GridNode predecessorNode) {

    this.predecessorNode = predecessorNode;

  }

  public void setNodeCost(double nodeCost) {

    this.nodeCost = nodeCost;

  }

  public void setGridSymbol(char gridSymbol) {

    this.gridSymbol = gridSymbol;

  }

  //Accessor methods:
  public GridNode getPredeccessorNode() {

    return predecessorNode;

  }

  public double getNodeCost() {

    return nodeCost;

  }

  public char getGridSymbol() {

    return gridSymbol;

  }

  public int getGridRow() {

    return gridRow;

  }

  public int getGridColumn() {

    return gridColumn;

  }

  public int getNodeKey() {

    return nodeKey;

  }

}
