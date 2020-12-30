package codes;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents a Tree for decoding the message sent using the dictionary.
 * 
 */
public class Tree {
  
  private TreeNode root;
  
  /**
   * Creates a tree for decoding a message.
   * @param keyCode dictionary used for decoding
   * @throws IllegalArgumentException if dictionary provided is empty
   */
  public Tree(Map<String, String> keyCode)
      throws IllegalArgumentException {
    if (keyCode.isEmpty()) {
      throw new IllegalArgumentException("can not create tree with empty code");
    }
    
    this.root = new GenericTreeNode();
    
    for (Entry<String, String> entry : keyCode.entrySet()) {
      String key = entry.getKey();
      String value = (String) entry.getValue();
      if (value.length() == 0) {
        throw new IllegalArgumentException("value of key can not be empty!!");
      }
      this.root.addKey(key, value);
       
    }
    
  }
  
  /**
   * Decodes a message using the tree built.
   * @param inputSequence he message to decode in String
   * @return decoded message in String
   * @throws IllegalArgumentException if input message is empty
   */
  public String decode(String inputSequence)
      throws IllegalArgumentException {
    if (inputSequence.length() == 0) {
      throw new IllegalArgumentException("Input sequence can not be empty!!");
    }
    
    StringBuilder decodedMessage = new StringBuilder();
    
    int sequenceDecoded = 0;
    
    while (sequenceDecoded != inputSequence.length()) {
      
      String[] returnedMessage = root.getDecodedValue(inputSequence.substring(sequenceDecoded))
          .split("\\+");
      sequenceDecoded += Integer.valueOf(returnedMessage[0]);
      decodedMessage.append(returnedMessage[1]);
    }
    
    return decodedMessage.toString();
    
  }

}
