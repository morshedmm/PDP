package codes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Generates Huffman encoding from given message and symbol.
 * 
 */
public class HuffmanEncoding {
  
  private Map<String, String> keyCode;
  
  /**
   * Constructs Huffman encoding object.
   * @param message used to implement the encoding in String
   * @param symbol allowed symbols in String
   * @throws IllegalArgumentException if message or encoding is empty
   */
  public HuffmanEncoding(String message, String symbol)
      throws IllegalArgumentException {
    if (message.length() == 0 || symbol.length() == 0) {
      throw new IllegalArgumentException("Illegal argument!!");
    }
    
    keyCode = new HashMap<String, String>();
    
    List<CharacterElement> characterList = this.parseMessage(message);
    
    // Added all characters to map.
    this.addChars(characterList);
    
    // Sorted characters to put them in priority queue.
    Collections.sort(characterList);
    
    // Created the priority queue using the linked list.
    LinkedList<CharacterElement> linkList = new LinkedList<>(characterList);
    
    this.buildEncoding(linkList, symbol);
    
    
  }
  
  private void buildEncoding(LinkedList<CharacterElement> linkList, String symbol) {
    
    int numOfElementsProcessed = symbol.length();
    
    while (linkList.size() != 1) {
      
      int count = 0;
      
      List<CharacterElement> charList = new ArrayList<CharacterElement>();
      
      while (count < numOfElementsProcessed) {
        charList.add(linkList.removeFirst());
        count++;
        if (linkList.size() == 0) {
          break;
        }
      }
      
      // Updating each key
      this.updateSymbols(charList, symbol);
      
      StringBuilder newStr = new StringBuilder();
      int totalValue = 0;
      
      for (CharacterElement eachElem: charList) {
        String[] myStr = eachElem.toString().split("\\+");
        newStr.append(myStr[0]);
        totalValue += Integer.valueOf(myStr[1]);
      }
      // Adding new charSet to queue.
      CharacterElement charCombined = new CharacterElement(newStr.toString(), totalValue);
      
      int placeToAdd = this.findIndex(linkList, charCombined);
      
      linkList.add(placeToAdd, charCombined);
      
    }
    
  }
  
  private int findIndex(LinkedList<CharacterElement> linkList, CharacterElement charCombined) {
    
    if (linkList.size() == 0) {
      return 0;
    }
    
    int counter = 0;
    ListIterator<CharacterElement> listIter = linkList.listIterator(0);
    
    while (listIter.hasNext()) {
      
      CharacterElement eachElem = (CharacterElement) listIter.next();
      if (charCombined.compareTo(eachElem) == -1) {
        return counter;
      }
      counter++;
    }
    return counter;
  }
  
  private void updateSymbols(List<CharacterElement> charList, String symbol) {
    
    int id = 0;
    for (CharacterElement eachElem: charList) {
      String[] myStr = eachElem.toString().split("\\+");
      for (int idx = 0; idx < myStr[0].length(); idx++) {
        this.keyCode.put(String.valueOf(myStr[0].charAt(idx)), 
            String.valueOf(symbol.charAt(id)) + this.keyCode.get(String.valueOf(myStr[0]
                .charAt(idx))));
      }
      id++;
    }
    return;
  }
  
  private void addChars(List<CharacterElement> characterList) {
    
    for (CharacterElement eachChar : characterList) {
      
      String[] myStr = eachChar.toString().split("\\+");
      this.keyCode.put(myStr[0], "");
    }
    return;
  }
  
  private List<CharacterElement> parseMessage(String message) {
    
    List<CharacterElement> myList = new ArrayList<CharacterElement>();
    Map<String, Integer> myMap = new HashMap<String, Integer>();
    
    int idx = 0;
    
    while (idx < message.length()) {
      
      StringBuilder curStr = new StringBuilder();
      
      curStr.append(String.valueOf(message.charAt(idx)));
      if (message.charAt(idx) == '\\' && idx != message.length() - 1 
          && message.charAt(idx) == 'n') {
        curStr.append(String.valueOf(message.charAt(idx + 1)));
        idx++;
      } 
      
      if (! myMap.containsKey(curStr.toString())) {
        myMap.put(curStr.toString(), 1);
      } else {
        myMap.put(curStr.toString(), 1 + myMap.get(curStr.toString()));
      }
      idx++;
    }
    
    for (Entry<String, Integer> entry : myMap.entrySet()) {
      String key = entry.getKey();
      int value = entry.getValue();
      myList.add(new CharacterElement(key, value));
    }
    
    return myList;
    
  }
  
  @Override
  public String toString() {
    StringBuilder myStr = new StringBuilder();
    for (Entry<String, String> entry : this.keyCode.entrySet()) {
      myStr.append(entry.getKey());
      myStr.append(":");
      myStr.append(entry.getValue());
      myStr.append("\n");
    }
    return myStr.toString();
  }

}
