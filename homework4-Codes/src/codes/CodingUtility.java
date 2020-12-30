package codes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Represents a coding Utility class used to encode and decode a message. 
 * 
 */
public class CodingUtility {
  
  
  
  /**
   * Encodes a message using key code.
   * @param message needed to encode in String
   * @param keyCode key code in String used to encode
   * @return encoded message
   * @throws IllegalArgumentException if message or key code is empty
   */
  public String doEncode(String message, String keyCode)
      throws IllegalArgumentException {
    if (message.length() == 0 || keyCode.length() == 0 || ! this.ifPrefix(convertToMap(keyCode))) {
      throw new IllegalArgumentException("Illegal input parameters!!");
    }
    
    StringBuilder encodedMessage = new StringBuilder();
    Map<String, String> keyMap = new HashMap<String, String>();
    keyMap = convertToMap(keyCode);
    
    for (int idx = 0; idx < message.length(); idx++) {
      encodedMessage.append(keyMap.get(String.valueOf(message.charAt(idx))));
    }
    return encodedMessage.toString();
    
  }
  
  /**
   * Encodes a message using key code to represent encoding in hexadecimal number.
   * @param message needed to encode in String
   * @param keyCode key code in String used to encode
   * @return encoded message in String
   * @throws IllegalArgumentException if message or key code is empty
   */
  public String doEncodeToHex(String message, String keyCode)
      throws IllegalArgumentException {
    if (ifNotBinary(keyCode)) {
      throw new IllegalArgumentException("code should be binary to convert to Hex");
    } 
    
    String encodedMessage = doEncode(message, keyCode);
    return binaryToHex(encodedMessage);
    
  }
  
  /**
   * Decodes a message from given key code.
   * @param encodedMessage Message in String
   * @param keyCode key code in String used to decode
   * @return decoded message in String
   * @throws IllegalArgumentException if encoded message or keycode is empty
   */
  public String doDecode(String encodedMessage, String keyCode)
      throws IllegalArgumentException {
    if (encodedMessage.length() == 0 || keyCode.length() == 0 
        || ! this.ifPrefix(convertToMap(keyCode))) {
      throw new IllegalArgumentException("Illegal input parameters!!");
    }
    
    Map<String, String> keyMap = new HashMap<String, String>();
    keyMap = convertToMap(keyCode);
    Tree decodingTree = new Tree(keyMap);
    return decodingTree.decode(encodedMessage);
  }
  
  /**
   * Used to get key code dictionary to encode a message using Huffman encoding.
   * @param message used to do Huffman encoding in String
   * @param symbol symbols used to encode in String
   * @return a String to represent Huffman encoding
   * @throws IllegalArgumentException if message or symbol is empty
   */
  public String doHuffmanEncoding(String message, String symbol) 
      throws IllegalArgumentException {
    if (message.length() == 0 || symbol.length() == 0) {
      throw new IllegalArgumentException("Illegal input parameters!!");
    }
    
    HuffmanEncoding encoder = new HuffmanEncoding(message, symbol);
    return encoder.toString();
  }
  
  private Map<String, String> convertToMap(String keyCode) {
    
    Map<String, String> keyMap = new HashMap<String, String>();
    int idx = 0;
    
    while (idx < keyCode.length()) {
      final String key = String.valueOf(keyCode.charAt(idx));
      idx++;
      StringBuilder value = new StringBuilder();
      idx++;
      while (idx < keyCode.length() && ! String.valueOf(keyCode.charAt(idx)).equals(" ")) {
        value.append(keyCode.charAt(idx));
        idx++;
      }
      keyMap.put(key, value.toString());
      idx++;
    }
    return keyMap;
    
  }
  
  private String binaryToHex(String binary) {
    
    StringBuilder hexCode = new StringBuilder();
    
    Map<String, String> binToHex = new HashMap<String, String>();
    binToHex.put("0000", "0");
    binToHex.put("0001", "1");
    binToHex.put("0010", "2");
    binToHex.put("0011", "3");
    binToHex.put("0100", "4");
    binToHex.put("0101", "5");
    binToHex.put("0110", "6");
    binToHex.put("0111", "7");
    binToHex.put("1000", "8");
    binToHex.put("1001", "9");
    binToHex.put("1010", "A");
    binToHex.put("1011", "B");
    binToHex.put("1100", "C");
    binToHex.put("1101", "D");
    binToHex.put("1110", "E");
    binToHex.put("1111", "F");
    
    StringBuilder finalBinary = new StringBuilder(); 
    if (binary.length() % 4 != 0) {
      int zerosToAdd = 4 - binary.length() % 4;
      while (zerosToAdd != 0) {
        finalBinary.append("0");
        zerosToAdd--;
      }
    }
    
    int idx = 0;
    while (idx < binary.length()) {
      
      finalBinary.append(String.valueOf(binary.charAt(idx)));
      idx++;
    }
    idx = 0;
    while (idx < finalBinary.toString().length()) {
      StringBuilder curString = new StringBuilder();
      int jdx = 0;
      while (jdx < 4) {
        curString.append(String.valueOf(finalBinary.toString().charAt(idx + jdx)));
        jdx++;
      }
      hexCode.append(binToHex.get(curString.toString()));
      idx += 4;
    }
    return hexCode.toString();
  }
  
  private boolean ifNotBinary(String keyCode) {
    Map<String, String> keyMap = new HashMap<String, String>();
    keyMap = convertToMap(keyCode);
    for (Entry<String, String> entry : keyMap.entrySet()) {
      String myStr = entry.getValue();
      int idx = 0;
      while (idx < myStr.length()) {
        if (! String.valueOf(myStr.charAt(idx)).equals("0") 
            && ! String.valueOf(myStr.charAt(idx)).equals("1")) {
          return true;
        }
        idx++;
      }
      
    }
    return false;
  }
  
  private boolean ifPrefix(Map<String, String> keyMap) {
    
    for (Entry<String, String> entry : keyMap.entrySet()) {
      String myStr = entry.getValue();
      StringBuilder appendedStr = new StringBuilder();
      for (int idx = 0; idx < myStr.length() - 1; idx++) {
        appendedStr.append(myStr.charAt(idx));
        for (Entry<String, String> entry2 : keyMap.entrySet()) {
          if (entry2.getValue().equals(appendedStr.toString())) {
            return false;
          }
        }
      }
    }
    return true;
  }
  

}
