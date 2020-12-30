package codes;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements TreeNode interface.
 * 
 */
public class TreeNodeAbstract implements TreeNode {
  
  protected String nodeValue;
  private Map<String, TreeNode> children = new HashMap<String, TreeNode>();

  /**
   * Creates a TreeNode with no child.
   * @param nodeVal value of this node in String
   * @throws IllegalArgumentException if value is empty String
   */
  public TreeNodeAbstract(String nodeVal)
      throws IllegalArgumentException {
    if (nodeVal.equals("")) {
      throw new IllegalArgumentException("Value of node can not be empty!!");
    }
    this.nodeValue = nodeVal;
  }
  
  /**
   * Creates a tree node without the node value.
   */
  public TreeNodeAbstract() {
    
    this.nodeValue = "";
  }
  
  private boolean hasKey(String key) {
    
    return this.children.containsKey(key);
  }

  @Override
  public void addKey(String key, String code) {
    
    if (code.length() == 1) {
      this.children.put(code, new LeafNode(key));
      return;
    }
    
    if (! this.hasKey(String.valueOf(code.charAt(0)))) {
      this.children.put(String.valueOf(code.charAt(0)), new GenericTreeNode());
      
    }
    this.children.get(String.valueOf(code.charAt(0))).addKey(key, code.substring(1));
    return;

  }

  @Override
  public String getDecodedValue(String sequence)
      throws IllegalArgumentException {
    
    if (sequence.length() == 0 || ! this.children.containsKey(String.valueOf(sequence
        .charAt(0)))) {
      throw new IllegalArgumentException("Illegal sequence!!");
    }
    String message = this.children.get(String.valueOf(sequence.charAt(0)))
        .getDecodedValue(sequence.substring(1));
    String[] separatedString = message.split("\\+");
    int countSoFar = Integer.valueOf(separatedString[0]) + 1;
    return String.valueOf(countSoFar) + "+" + separatedString[1];
  }
  
  

}
