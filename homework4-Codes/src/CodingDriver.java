

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import codes.CodingUtility;



/**
 * Driver class for Codes.
 * 
 */
public class CodingDriver {

  /**
   * Main function in the driver class.
   * @param args command line arguments
   * @throws IOException if input file doesn't exist
   */
  public static void main(String[] args) throws IOException {
    
    CodingUtility myCoding = new CodingUtility();
    
    if (Integer.parseInt(args[0]) == 1) {
      System.out.println("Reading from command line, will print to file ouput.txt");
      String myInputMessage = args[1];
      String mySymbols = args[2];
      FileWriter writeToFile = new FileWriter("output.txt");
      String outputCode = myCoding.doHuffmanEncoding(myInputMessage, mySymbols);
      
      
      for (int idx = 0; idx < outputCode.length(); idx++) {
        writeToFile.write(outputCode.charAt(idx)); 
      }
        
      writeToFile.close();
      
    }
    
    if (Integer.parseInt(args[0]) == 2) {
      
      int character = 0;
      boolean beforeNewLine = true;
      
      StringBuilder encodedMessage = new StringBuilder();
      StringBuilder dictionary = new StringBuilder();
      
      FileReader readFromFile = null; 
      try { 
        readFromFile = new FileReader("inputfile"); 
      } catch (FileNotFoundException fe) {
        System.out.println("File not found"); 
      }
      
      while ((character = readFromFile.read()) != -1) {
        if (String.valueOf((char) character).equals("\n")) {
          beforeNewLine = false;
          continue;
        }
        if (beforeNewLine) {
          encodedMessage.append((char) character);
        } else {
          dictionary.append((char) character);
        }
        
      }
      System.out.println((encodedMessage.toString()));
      System.out.println(dictionary.toString());
      System.out.println(myCoding.doDecode(encodedMessage.toString(), dictionary.toString()));
    }
    
    if (Integer.parseInt(args[0]) == 3) {
      System.out.println("Give the input message to encode:");
      Scanner myScanner = new Scanner(System.in);
      String inputString = myScanner.nextLine();
      System.out.println("Print the symbol->code dictionary, for example a 10 b 01:");
      String inputDictionary = myScanner.nextLine();
      myScanner.close();
      System.out.println("Encoded output is:");
      System.out.println(myCoding.doEncode(inputString, inputDictionary));
    }
    
    if (Integer.parseInt(args[0]) == 4) {
      int character = 0;
      boolean beforeNewLine = true;
      
      StringBuilder inputMessage = new StringBuilder();
      StringBuilder dictionary = new StringBuilder();
      
      FileReader readFromFile = null; 
      try { 
        readFromFile = new FileReader("inputmsgfile"); 
      } catch (FileNotFoundException fe) { 
        System.out.println("File not found"); 
      }
      
      while ((character = readFromFile.read()) != -1) {
        if (String.valueOf((char) character).equals("\n")) {
          beforeNewLine = false;
          continue;
        }
        if (beforeNewLine) {
          inputMessage.append((char) character);
        } else {
          dictionary.append((char) character);
        }
        
      }
      
      String encodedMsg = myCoding.doEncodeToHex(inputMessage.toString(),
          dictionary.toString());
      FileWriter writeToFile = new FileWriter("outputmsgfile.txt");
      
      for (int idx = 0; idx < encodedMsg.length(); idx++) {
        writeToFile.write(encodedMsg.charAt(idx)); 
      }
        
      writeToFile.close();
      
    }

  }

}
