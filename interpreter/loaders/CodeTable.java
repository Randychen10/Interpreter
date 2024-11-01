/**
 * Code table of byte codes in language X
 * @key name of a specific byte code
 * @value name of the class that the key belongs to.
 */
package interpreter.loaders;

import java.util.HashMap;
import java.util.Map;

public final class CodeTable {
   private static Map<String, String> table = new HashMap<>();

   private CodeTable() {
      // do nothing
   }

   /**
    * fill code table with class name mappings
    */
   public static void init() {
      table.put("HALT", "HaltCode");
      table.put("VERBOSE", "VerboseCode");
      table.put("POP", "PopCode");
      table.put("FALSEBRANCH", "FalseBranchCode");
      table.put("GOTO", "GoToCode");
      table.put("STORE", "StoreCode");
      table.put("LOAD", "LoadCode");
      table.put("LIT", "LitCode");
      table.put("ARGS", "ArgsCode");
      table.put("CALL", "CallCode");
      table.put("RETURN", "ReturnCode");
      table.put("BOP", "BopCode");
      table.put("READ", "ReadCode");
      table.put("WRITE", "WriteCode");
      table.put("LABEL", "LabelCode");
   }

   /**
    * Returns the ByteCode class name for a given token.
    * 
    * @param token bytecode to map. For example, HALT --> HaltCode
    * @return class name of bytecode
    */
   public static String getClassName(String token) {
      return table.get(token);
   }
}
