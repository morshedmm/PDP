package codes;

/**
 * Represents a tree Node.
 * 
 */
public interface TreeNode {
    
  /**
   * Adds the key with the value to expand the tree.
   * @param key to be added
   * @param code key in String
   */
  public void addKey(String key, String code);
  
  /**
   * Gives the node from the key.
   * @param sequence a String representing sequence
   * @return a TreeNode
   */
  public String getDecodedValue(String sequence);
  
  
}
