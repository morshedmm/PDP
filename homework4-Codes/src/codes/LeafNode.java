package codes;

/**
 * Represents a leaf node in the tree.
 * 
 */
public class LeafNode extends TreeNodeAbstract {

  /**
   * Constructs a leaf node in the tree.
   * @param nodeVal value of the leaf node in String
   * @throws IllegalArgumentException if value of the node is empty
   */
  public LeafNode(String nodeVal) throws IllegalArgumentException {
    super(nodeVal);
  }
  
  @Override
  public String getDecodedValue(String sequence) {
    
    return "0" + "+" + this.nodeValue;
  }

}
