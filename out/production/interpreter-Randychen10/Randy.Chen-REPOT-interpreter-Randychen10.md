

# Grading Report for P1



## Student Name: 

Randy.Chen


## Commit Count: 

16



## Git Diff Since Base Commit: 


<details>
    <summary>Git Diff</summary>

~~~bash
diff --git a/.idea/.gitignore b/.idea/.gitignore
new file mode 100644
index 0000000..13566b8
--- /dev/null
+++ b/.idea/.gitignore
@@ -0,0 +1,8 @@
+# Default ignored files
+/shelf/
+/workspace.xml
+# Editor-based HTTP Client requests
+/httpRequests/
+# Datasource local storage ignored files
+/dataSources/
+/dataSources.local.xml
diff --git a/.idea/material_theme_project_new.xml b/.idea/material_theme_project_new.xml
new file mode 100644
index 0000000..77c3fe6
--- /dev/null
+++ b/.idea/material_theme_project_new.xml
@@ -0,0 +1,12 @@
+<?xml version="1.0" encoding="UTF-8"?>
+<project version="4">
+  <component name="MaterialThemeProjectNewConfig">
+    <option name="metadata">
+      <MTProjectMetadataState>
+        <option name="migrated" value="true" />
+        <option name="pristineConfig" value="false" />
+        <option name="userId" value="-3ba6b3b0:190224b351c:-7ffe" />
+      </MTProjectMetadataState>
+    </option>
+  </component>
+</project>
\ No newline at end of file
diff --git a/.idea/misc.xml b/.idea/misc.xml
new file mode 100644
index 0000000..4444b22
--- /dev/null
+++ b/.idea/misc.xml
@@ -0,0 +1,6 @@
+<?xml version="1.0" encoding="UTF-8"?>
+<project version="4">
+  <component name="ProjectRootManager" version="2" languageLevel="JDK_22" default="true" project-jdk-name="openjdk-22" project-jdk-type="JavaSDK">
+    <output url="file://$PROJECT_DIR$/out" />
+  </component>
+</project>
\ No newline at end of file
diff --git a/.idea/modules.xml b/.idea/modules.xml
new file mode 100644
index 0000000..d8aa394
--- /dev/null
+++ b/.idea/modules.xml
@@ -0,0 +1,8 @@
+<?xml version="1.0" encoding="UTF-8"?>
+<project version="4">
+  <component name="ProjectModuleManager">
+    <modules>
+      <module fileurl="file://$PROJECT_DIR$/interpreter-Randychen10.iml" filepath="$PROJECT_DIR$/interpreter-Randychen10.iml" />
+    </modules>
+  </component>
+</project>
\ No newline at end of file
diff --git a/.idea/vcs.xml b/.idea/vcs.xml
new file mode 100644
index 0000000..35eb1dd
--- /dev/null
+++ b/.idea/vcs.xml
@@ -0,0 +1,6 @@
+<?xml version="1.0" encoding="UTF-8"?>
+<project version="4">
+  <component name="VcsDirectoryMappings">
+    <mapping directory="" vcs="Git" />
+  </component>
+</project>
\ No newline at end of file
diff --git a/README.md b/README.md
index 2b9a292..c58bd7d 100644
--- a/README.md
+++ b/README.md
@@ -1,7 +1,7 @@
 # CSC 413 - Project Two - The Interpreter
 
-## Student Name  : Name here
+## Student Name  : Randy Chen
 
-## Student ID    : ID here
+## Student ID    : 922525848
 
-## Student Email : Email here
+## Student Email : rchen10@sfsu.edu
diff --git a/documentation/Chen_Randy.docx b/documentation/Chen_Randy.docx
new file mode 100644
index 0000000..5a3df99
Binary files /dev/null and b/documentation/Chen_Randy.docx differ
diff --git a/documentation/Chen_Randy.pdf b/documentation/Chen_Randy.pdf
new file mode 100644
index 0000000..8ee9ab7
Binary files /dev/null and b/documentation/Chen_Randy.pdf differ
diff --git a/documentation/Interpreter UML.png b/documentation/Interpreter UML.png
new file mode 100644
index 0000000..7417886
Binary files /dev/null and b/documentation/Interpreter UML.png differ
diff --git a/interpreter-Randychen10.iml b/interpreter-Randychen10.iml
new file mode 100644
index 0000000..b107a2d
--- /dev/null
+++ b/interpreter-Randychen10.iml
@@ -0,0 +1,11 @@
+<?xml version="1.0" encoding="UTF-8"?>
+<module type="JAVA_MODULE" version="4">
+  <component name="NewModuleRootManager" inherit-compiler-output="true">
+    <exclude-output />
+    <content url="file://$MODULE_DIR$">
+      <sourceFolder url="file://$MODULE_DIR$" isTestSource="false" />
+    </content>
+    <orderEntry type="inheritedJdk" />
+    <orderEntry type="sourceFolder" forTests="false" />
+  </component>
+</module>
\ No newline at end of file
diff --git a/interpreter/bytecodes/ArgsCode.java b/interpreter/bytecodes/ArgsCode.java
new file mode 100644
index 0000000..78e6406
--- /dev/null
+++ b/interpreter/bytecodes/ArgsCode.java
@@ -0,0 +1,26 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+
+import java.util.List;
+
+public class ArgsCode extends ByteCode {
+    private int numArgs;
+
+    @Override
+    public void init(List<String> args) {
+        if(!args.isEmpty()) {
+            numArgs = Integer.parseInt(args.get(0));
+        }
+    }
+
+    @Override
+    public void execute(VirtualMachine vm) {
+        vm.newFrame(numArgs);
+    }
+
+    @Override
+    public String toString() {
+        return "ARGS " + numArgs;
+    }
+}
diff --git a/interpreter/bytecodes/BopCode.java b/interpreter/bytecodes/BopCode.java
new file mode 100644
index 0000000..2f61fe5
--- /dev/null
+++ b/interpreter/bytecodes/BopCode.java
@@ -0,0 +1,74 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+
+import java.util.List;
+
+public class BopCode extends ByteCode {
+    private String operator;  // The binary operator (e.g., +, -, *, /, etc.)
+
+    /**
+     * Initialize the BopCode with the binary operator.
+     *
+     * @param args The list of arguments passed to this bytecode. Expects the operator as the first argument.
+     */
+    @Override
+    public void init(List<String> args) {
+        // Check if an operator is provided and initialize
+        if (args.size() > 0) {
+            this.operator = args.get(0);  // Assuming the operator is the first argument
+        } else {
+            throw new IllegalArgumentException("Operator argument is missing.");
+        }
+    }
+
+    /**
+     * Execute the BopCode operation by performing the binary operation
+     * on the top two elements of the runtime stack.
+     *
+     * @param vm The virtual machine instance on which this bytecode is executed.
+     */
+    @Override
+    public void execute(VirtualMachine vm) {
+        // Pop two operands from the runtime stack
+        int rightOperand = vm.popRunStack();
+        int leftOperand = vm.popRunStack();
+        int result = 0;
+
+        // Perform the binary operation based on the operator
+        switch (operator) {
+            case "+" -> result = leftOperand + rightOperand;
+            case "-" -> result = leftOperand - rightOperand;
+            case "*" -> result = leftOperand * rightOperand;
+            case "/" -> {
+                if (rightOperand == 0) {
+                    throw new ArithmeticException("Division by zero");
+                }
+                result = leftOperand / rightOperand;
+            }
+            case "==" -> result = (leftOperand == rightOperand) ? 1 : 0;
+            case "!=" -> result = (leftOperand != rightOperand) ? 1 : 0;
+            case "<" -> result = (leftOperand < rightOperand) ? 1 : 0;
+            case ">" -> result = (leftOperand > rightOperand) ? 1 : 0;
+            case "<=" -> result = (leftOperand <= rightOperand) ? 1 : 0;
+            case ">=" -> result = (leftOperand >= rightOperand) ? 1 : 0;
+            case "&" -> result = (leftOperand != 0 && rightOperand != 0) ? 1 : 0;  // Logical AND
+            case "|" -> result = (leftOperand != 0 || rightOperand != 0) ? 1 : 0;  // Logical OR
+            default -> throw new RuntimeException("Unknown operator: " + operator);
+        }
+
+        // Push the result of the operation back onto the stack
+        vm.push(result);
+    }
+
+    /**
+     * This is for verbose mode output where it shows the bytecode being executed.
+     * It returns a string representation of the operation for the VERBOSE mode.
+     *
+     * @return The string format of the bytecode operation.
+     */
+    @Override
+    public String toString() {
+        return "BOP " + operator;
+    }
+}
diff --git a/interpreter/bytecodes/ByteCode.java b/interpreter/bytecodes/ByteCode.java
new file mode 100644
index 0000000..f988860
--- /dev/null
+++ b/interpreter/bytecodes/ByteCode.java
@@ -0,0 +1,11 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+
+import java.util.List;
+
+public abstract class ByteCode {
+    public abstract void init(List<String> args);
+    public abstract void execute(VirtualMachine vm);
+    public abstract String toString();
+}
diff --git a/interpreter/bytecodes/CallCode.java b/interpreter/bytecodes/CallCode.java
new file mode 100644
index 0000000..a92206f
--- /dev/null
+++ b/interpreter/bytecodes/CallCode.java
@@ -0,0 +1,33 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+import java.util.List;
+
+public class CallCode extends ByteCode {
+    private String label;
+    private int labelAddress;
+
+    @Override
+    public void init(List<String> args) {
+        label = args.get(0);
+    }
+
+    public String getLabel() {
+        return label;
+    }
+
+    public void setTargetAddress(int address) {
+        this.labelAddress = address;
+    }
+
+    @Override
+    public void execute(VirtualMachine vm) {
+        vm.pushReturnAddress(vm.getProgramCounter());
+        vm.setProgramCounter(labelAddress);
+    }
+
+    @Override
+    public String toString() {
+        return "CALL " + label + " -> " + labelAddress;
+    }
+}
diff --git a/interpreter/bytecodes/FalseBranchCode.java b/interpreter/bytecodes/FalseBranchCode.java
new file mode 100644
index 0000000..14d867f
--- /dev/null
+++ b/interpreter/bytecodes/FalseBranchCode.java
@@ -0,0 +1,37 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+import java.util.List;
+
+public class FalseBranchCode extends ByteCode {
+
+    private String label;
+    private int labelAddress;
+
+    @Override
+    public void init(List<String> args) {
+        this.label = args.get(0);
+    }
+
+    public String getLabel() {
+        return label;
+    }
+
+    public void setTargetAddress(int address) {
+        this.labelAddress = address;
+    }
+
+    @Override
+    public void execute(VirtualMachine vm) {
+        int value = vm.popRunStack();
+
+        if (value == 0) {
+            vm.setProgramCounter(labelAddress);
+        }
+    }
+
+    @Override
+    public String toString() {
+        return "FALSEBRANCH " + label + " -> " + labelAddress;
+    }
+}
diff --git a/interpreter/bytecodes/GoToCode.java b/interpreter/bytecodes/GoToCode.java
new file mode 100644
index 0000000..606d192
--- /dev/null
+++ b/interpreter/bytecodes/GoToCode.java
@@ -0,0 +1,33 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+
+import java.util.List;
+
+public class GoToCode extends ByteCode {
+    private String label;
+    private int labelAddress;
+
+    @Override
+    public void init(List<String> args) {
+        this.label = args.getFirst();
+    }
+
+    public String getLabel() {
+        return label;
+    }
+
+    public void setTargetAddress(int address) {
+        this.labelAddress = address;
+    }
+
+    @Override
+    public void execute(VirtualMachine vm) {
+        vm.setProgramCounter(labelAddress);
+    }
+
+    @Override
+    public String toString() {
+        return "GOTO " + label + " -> " + labelAddress;
+    }
+}
diff --git a/interpreter/bytecodes/HaltCode.java b/interpreter/bytecodes/HaltCode.java
new file mode 100644
index 0000000..14fa757
--- /dev/null
+++ b/interpreter/bytecodes/HaltCode.java
@@ -0,0 +1,22 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+
+import java.util.List;
+
+public class HaltCode extends ByteCode {
+    @Override
+    public void init(List<String> args) {
+
+    }
+
+    @Override
+    public void execute(VirtualMachine vm) {
+        vm.haltExecution();
+    }
+
+    @Override
+    public String toString() {
+        return "";
+    }
+}
diff --git a/interpreter/bytecodes/LabelCode.java b/interpreter/bytecodes/LabelCode.java
new file mode 100644
index 0000000..b56e994
--- /dev/null
+++ b/interpreter/bytecodes/LabelCode.java
@@ -0,0 +1,31 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+import java.util.List;
+
+public class LabelCode extends ByteCode {
+    private String label;
+
+    @Override
+    public void init(List<String> args) {
+        if (!args.isEmpty()) {
+            this.label = args.getFirst();
+        } else {
+            throw new RuntimeException("LABEL missing argument");
+        }
+    }
+
+    public String getLabel() {
+        return label;
+    }
+
+    @Override
+    public void execute(VirtualMachine vm) {
+        // No functionality for execution, Label is only used for marking a position
+    }
+
+    @Override
+    public String toString() {
+        return "LABEL " + label;
+    }
+}
diff --git a/interpreter/bytecodes/LitCode.java b/interpreter/bytecodes/LitCode.java
new file mode 100644
index 0000000..4d66642
--- /dev/null
+++ b/interpreter/bytecodes/LitCode.java
@@ -0,0 +1,43 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+
+import java.util.List;
+
+public class LitCode extends ByteCode {
+    private int value;      // The literal value (an integer)
+    private String identifier;  // Optional identifier (like a variable name)
+
+    @Override
+    public void init(List<String> args) {
+        // The first argument is the literal value, which is an integer
+        value = Integer.parseInt(args.get(0));  // Parse the literal value
+
+        // The second argument, if present, is the identifier (a string)
+        if (args.size() > 1) {
+            identifier = args.get(1);  // This is the identifier (e.g., "x")
+        } else {
+            identifier = null;  // No identifier provided
+        }
+    }
+
+    @Override
+    public void execute(VirtualMachine vm) {
+        vm.pushRunStack(value);  // Push the literal value onto the runtime stack
+
+        if (identifier != null) {
+            // If there's an identifier, you may need to associate it with the value
+            // depending on your implementation, but for now, we just handle the literal.
+        }
+    }
+
+    @Override
+    public String toString() {
+        // If an identifier is present, include it in the verbose output
+        if (identifier != null) {
+            return "LIT " + value + " " + identifier + " <load " + identifier + ">";
+        } else {
+            return "LIT " + value;
+        }
+    }
+}
diff --git a/interpreter/bytecodes/LoadCode.java b/interpreter/bytecodes/LoadCode.java
new file mode 100644
index 0000000..59528c2
--- /dev/null
+++ b/interpreter/bytecodes/LoadCode.java
@@ -0,0 +1,35 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+
+import java.util.List;
+
+public class LoadCode extends ByteCode {
+    private String identifier;
+    private int offset;
+
+    @Override
+    public void init(List<String> args) {
+        offset = Integer.parseInt(args.get(0));
+
+        if (args.size() > 1) {
+            identifier = args.get(1);
+        } else {
+            identifier = null;
+        }
+    }
+
+    @Override
+    public void execute(VirtualMachine vm) {
+        vm.load(offset);
+    }
+
+    @Override
+    public String toString() {
+        if (identifier != null) {
+            return "LOAD " + offset + " " + identifier + " <load " + identifier + ">";
+        } else {
+            return "LOAD " + offset;
+        }
+    }
+}
diff --git a/interpreter/bytecodes/PopCode.java b/interpreter/bytecodes/PopCode.java
new file mode 100644
index 0000000..8dc52dc
--- /dev/null
+++ b/interpreter/bytecodes/PopCode.java
@@ -0,0 +1,24 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+
+import java.util.List;
+
+public class PopCode extends ByteCode {
+    private int nums;
+
+    @Override
+    public void init(List<String> args) {
+        nums = Integer.parseInt(args.get(0));
+    }
+
+    @Override
+    public void execute(VirtualMachine vm) {
+        vm.popRunStack();
+    }
+
+    @Override
+    public String toString() {
+        return "POP " + nums;
+    }
+}
diff --git a/interpreter/bytecodes/ReadCode.java b/interpreter/bytecodes/ReadCode.java
new file mode 100644
index 0000000..787f0c4
--- /dev/null
+++ b/interpreter/bytecodes/ReadCode.java
@@ -0,0 +1,38 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+import java.util.Scanner;
+import java.util.List;
+
+public class ReadCode extends ByteCode {
+
+    @Override
+    public void init(List<String> args) {
+        // No arguments needed for Read bytecode
+    }
+
+    @Override
+    public void execute(VirtualMachine vm) {
+        Scanner scanner = new Scanner(System.in);
+        int inputValue = 0;
+        boolean validInput = false;
+
+        while (!validInput) {
+            System.out.print("Please enter an integer: ");
+            if (scanner.hasNextInt()) {
+                inputValue = scanner.nextInt();
+                validInput = true;
+            } else {
+                System.out.println("Invalid input. Please try again.");
+                scanner.next();
+            }
+        }
+
+        vm.push(inputValue);
+    }
+
+    @Override
+    public String toString() {
+        return "READ";
+    }
+}
diff --git a/interpreter/bytecodes/ReturnCode.java b/interpreter/bytecodes/ReturnCode.java
new file mode 100644
index 0000000..cd8da9b
--- /dev/null
+++ b/interpreter/bytecodes/ReturnCode.java
@@ -0,0 +1,37 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+
+import java.util.List;
+
+public class ReturnCode extends ByteCode {
+    private String label;
+
+    @Override
+    public void init(List<String> args) {
+        if (!args.isEmpty()) {
+            label = args.getFirst();
+        } else {
+            label = null;
+        }
+    }
+
+    @Override
+    public void execute(VirtualMachine vm) {
+        int value = vm.peek();
+        vm.popFrame();
+        vm.push(value);
+
+        int address = vm.popReturnAddress();
+        vm.setProgramCounter(address);
+    }
+
+    @Override
+    public String toString() {
+        if(label == null){
+            return "RETURN ";
+        }else {
+            return "RETURN " + label + " EXIT " + label;
+        }
+    }
+}
diff --git a/interpreter/bytecodes/StoreCode.java b/interpreter/bytecodes/StoreCode.java
new file mode 100644
index 0000000..90f27e0
--- /dev/null
+++ b/interpreter/bytecodes/StoreCode.java
@@ -0,0 +1,38 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+import java.util.List;
+
+public class StoreCode extends ByteCode {
+    private int offset;  // The offset in the runtime stack (integer)
+    private String identifier;  // Optional identifier (variable name)
+
+    @Override
+    public void init(List<String> args) {
+        // Parse the first argument as an integer (offset)
+        offset = Integer.parseInt(args.get(0));  // This is the offset in the runtime stack
+
+        // The second argument, if present, is the identifier (a string)
+        if (args.size() > 1) {
+            identifier = args.get(1);  // This is the variable name (e.g., "x")
+        } else {
+            identifier = null;  // No identifier provided
+        }
+    }
+
+    @Override
+    public void execute(VirtualMachine vm) {
+        // Store the value at the given offset
+        vm.store(offset);
+    }
+
+    @Override
+    public String toString() {
+        // If an identifier is present, include it in the output
+        if (identifier != null) {
+            return "STORE " + offset + " " + identifier + " <store " + identifier + ">";
+        } else {
+            return "STORE " + offset;
+        }
+    }
+}
diff --git a/interpreter/bytecodes/VerboseCode.java b/interpreter/bytecodes/VerboseCode.java
new file mode 100644
index 0000000..132816d
--- /dev/null
+++ b/interpreter/bytecodes/VerboseCode.java
@@ -0,0 +1,32 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+
+import java.util.List;
+
+public class VerboseCode extends ByteCode{
+    private Boolean mode;
+    private String value;
+
+    public void init(List<String> args) {
+        this.value = args.getFirst();
+    }
+
+    public void execute(VirtualMachine vm) {
+        if (value.equals("ON")){
+            this.mode = true;
+        }
+
+        if (value.equals("OFF")){
+            this.mode = false;
+        }
+
+        vm.setVerboseMode(mode);
+    }
+
+    public String toString(){
+        String val = "VERBOSE " + value;
+
+        return val;
+    }
+}
diff --git a/interpreter/bytecodes/WriteCode.java b/interpreter/bytecodes/WriteCode.java
new file mode 100644
index 0000000..9f6d3d1
--- /dev/null
+++ b/interpreter/bytecodes/WriteCode.java
@@ -0,0 +1,24 @@
+package interpreter.bytecodes;
+
+import interpreter.virtualmachine.VirtualMachine;
+import java.util.List;
+
+public class WriteCode extends ByteCode {
+
+    @Override
+    public void init(List<String> args) {
+        // No arguments needed for Write bytecode
+    }
+
+    @Override
+    public void execute(VirtualMachine vm) {
+        int topValue = vm.peek();
+
+        System.out.println(topValue);
+    }
+
+    @Override
+    public String toString() {
+        return "WRITE";
+    }
+}
diff --git a/interpreter/loaders/ByteCodeLoader.java b/interpreter/loaders/ByteCodeLoader.java
index add318f..990b07f 100644
--- a/interpreter/loaders/ByteCodeLoader.java
+++ b/interpreter/loaders/ByteCodeLoader.java
@@ -1,26 +1,57 @@
 package interpreter.loaders;
 
-import interpreter.loaders.Program;
+import interpreter.bytecodes.ByteCode;
+
+import java.io.*;
+import java.lang.reflect.InvocationTargetException;
+import java.util.Arrays;
 
 public final class ByteCodeLoader {
     private String codSourceFileName;
-    
+
     /**
      * Constructs ByteCodeLoader object given a COD source code
      * file name
+     *
      * @param fileName name of .cod File to load.
      */
-    public ByteCodeLoader(String fileName){
+    public ByteCodeLoader(String fileName) {
         this.codSourceFileName = fileName;
     }
-    
+
     /**
      * Loads a program from a .cod file.
+     *
      * @return a constructed Program Object.
-     * @throws InvalidProgramException thrown when 
-     * loadCodes fails.
+     * @throws InvalidProgramException thrown when
+     *                                 loadCodes fails.
      */
     public Program loadCodes() throws InvalidProgramException {
-       return null;
+        final Program program = new Program(); // Create a new Program instance to hold the ByteCodes
+
+        // Try-with-resources to automatically close the BufferedReader after use
+        try (BufferedReader reader = new BufferedReader(new FileReader(this.codSourceFileName))) {
+
+            // Read each line of the file while there is data available
+            while (reader.ready()) {
+                String[] items = reader.readLine().split("\\s+"); // Split the line into parts using whitespace as the delimiter
+                String className = CodeTable.getClassName(items[0]); // Get the corresponding class name for the ByteCode
+
+                // Dynamically load the ByteCode class and create an instance of it
+                ByteCode bc = (ByteCode) Class.forName("interpreter.bytecodes." + className)
+                        .getDeclaredConstructor().newInstance();
+
+                // Initialize the ByteCode with its arguments (skipping the first item which is the ByteCode name)
+                bc.init(Arrays.asList(items).subList(1, items.length));
+
+                // Add the instantiated and initialized ByteCode to the program
+                program.addCode(bc);
+            }
+        } catch (IOException | ClassNotFoundException | InvocationTargetException | InstantiationException
+                 | IllegalAccessException | NoSuchMethodException ex) {
+            // Catch any exceptions related to file I/O or reflection, and wrap them in an InvalidProgramException
+            throw new InvalidProgramException(ex);
+        }
+        return program; // Return the constructed program containing all ByteCodes
     }
 }
diff --git a/interpreter/loaders/CodeTable.java b/interpreter/loaders/CodeTable.java
index 4b2d000..312e48c 100644
--- a/interpreter/loaders/CodeTable.java
+++ b/interpreter/loaders/CodeTable.java
@@ -5,7 +5,11 @@
  */
 package interpreter.loaders;
 
+import java.util.HashMap;
+import java.util.Map;
+
 public final class CodeTable {
+   private static Map<String, String> table = new HashMap<>();
 
    private CodeTable() {
       // do nothing
@@ -15,7 +19,21 @@ public final class CodeTable {
     * fill code table with class name mappings
     */
    public static void init() {
-
+      table.put("HALT", "HaltCode");
+      table.put("VERBOSE", "VerboseCode");
+      table.put("POP", "PopCode");
+      table.put("FALSEBRANCH", "FalseBranchCode");
+      table.put("GOTO", "GoToCode");
+      table.put("STORE", "StoreCode");
+      table.put("LOAD", "LoadCode");
+      table.put("LIT", "LitCode");
+      table.put("ARGS", "ArgsCode");
+      table.put("CALL", "CallCode");
+      table.put("RETURN", "ReturnCode");
+      table.put("BOP", "BopCode");
+      table.put("READ", "ReadCode");
+      table.put("WRITE", "WriteCode");
+      table.put("LABEL", "LabelCode");
    }
 
    /**
@@ -25,7 +43,6 @@ public final class CodeTable {
     * @return class name of bytecode
     */
    public static String getClassName(String token) {
-      return null;
+      return table.get(token);
    }
-
 }
diff --git a/interpreter/loaders/Program.java b/interpreter/loaders/Program.java
index ad09e34..fb358a4 100644
--- a/interpreter/loaders/Program.java
+++ b/interpreter/loaders/Program.java
@@ -1,7 +1,16 @@
 package interpreter.loaders;
 
+import interpreter.bytecodes.ByteCode;
+import interpreter.bytecodes.LabelCode;
+import interpreter.bytecodes.FalseBranchCode;
+import interpreter.bytecodes.GoToCode;
+import interpreter.bytecodes.CallCode;
+
 import java.util.ArrayList;
 import java.util.List;
+import java.util.Map;
+import java.util.HashMap;
+
 public class Program {
 
     private List<ByteCode> program;
@@ -11,7 +20,7 @@ public class Program {
      * ArrayList
      */
     public Program() {
-
+        program = new ArrayList<>();
     }
 
     /**
@@ -19,7 +28,7 @@ public class Program {
      * @return size of program
      */
     public int getSize() {
-        return 0;
+        return this.program.size();
     }
 
     /**
@@ -28,7 +37,7 @@ public class Program {
      * @return a bytecode.
      */
     public ByteCode getCode(int programCounter) {
-        return null;
+        return this.program.get(programCounter);
     }
 
     /**
@@ -36,7 +45,7 @@ public class Program {
      * @param c bytecode to be added
      */
     public void addCode(ByteCode c) {
-
+        this.program.add(c);
     }
 
     /**
@@ -49,6 +58,61 @@ public class Program {
      * **** METHOD SIGNATURE CANNOT BE CHANGED *****
      */
     public void resolveAddress() {
+        Map<String, Integer> labelMap = new HashMap<>();
+
+        // First pass: map labels to their addresses
+        for (int i = 0; i < this.program.size(); i++) {
+            ByteCode code = this.program.get(i);
+            if (code instanceof LabelCode) {
+                String label = ((LabelCode) code).getLabel();
+                labelMap.put(label, i);  // Store the label and its address
+            }
+        }
+
+
+        // Iterate through all the ByteCodes in the program list
+        for (int i = 0; i < this.program.size(); i++) {
+            ByteCode code = this.program.get(i); // Retrieve each ByteCode from the program list
+
+            // Check if the ByteCode is an instance of GoToCode
+            if (code instanceof GoToCode) {
+                GoToCode gotoCode = (GoToCode) code;
+                String label = gotoCode.getLabel(); // Get the label associated with the GoToCode
+                Integer targetAddress = labelMap.get(label); // Look up the address corresponding to the label
+
+                // If the label is found in the labelMap, set the target address in GoToCode
+                if (targetAddress != null) {
+                    gotoCode.setTargetAddress(targetAddress);  // Set the resolved address
+                } else {
+                    throw new RuntimeException("Label not found: " + label); // Throw an error if the label is not found
+                }
+
+                // Check if the ByteCode is an instance of CallCode
+            } else if (code instanceof CallCode) {
+                CallCode callCode = (CallCode) code;
+                String label = callCode.getLabel(); // Get the label associated with the CallCode
+                Integer targetAddress = labelMap.get(label); // Look up the address corresponding to the label
+
+                // If the label is found, set the target address in CallCode
+                if (targetAddress != null) {
+                    callCode.setTargetAddress(targetAddress);  // Set the resolved address
+                } else {
+                    throw new RuntimeException("Label not found: " + label); // Throw an error if the label is not found
+                }
+
+                // Check if the ByteCode is an instance of FalseBranchCode
+            } else if (code instanceof FalseBranchCode) {
+                FalseBranchCode falseBranchCode = (FalseBranchCode) code;
+                String label = falseBranchCode.getLabel(); // Get the label associated with the FalseBranchCode
+                Integer targetAddress = labelMap.get(label); // Look up the address corresponding to the label
 
+                // If the label is found, set the target address in FalseBranchCode
+                if (targetAddress != null) {
+                    falseBranchCode.setTargetAddress(targetAddress);  // Set the resolved address
+                } else {
+                    throw new RuntimeException("Label not found: " + label); // Throw an error if the label is not found
+                }
+            }
+        }
     }
-}   
\ No newline at end of file
+}
\ No newline at end of file
diff --git a/interpreter/virtualmachine/RunTimeStack.java b/interpreter/virtualmachine/RunTimeStack.java
index 3289bc0..a9a834d 100644
--- a/interpreter/virtualmachine/RunTimeStack.java
+++ b/interpreter/virtualmachine/RunTimeStack.java
@@ -17,4 +17,116 @@ class RunTimeStack {
         framePointer.add(0);
     }
 
+    public String verboseDisplay(){
+        /**
+         * Used for displaying the current state of the runTimeStack .
+         * It will print portions of the stack based on respective
+         * frame markers .
+         * Example [1 ,2 ,3] [4 ,5 ,6] [7 ,8]
+         * Frame pointers would be 0 ,3 ,6
+         */
+
+        StringBuilder result = new StringBuilder();
+        int previousFrame = 0;
+
+        for (int i = 1; i < framePointer.size(); i++) {
+            int currentFrame = framePointer.get(i);
+            result.append(runTimeStack.subList(previousFrame, currentFrame)).append(" ");
+            previousFrame = currentFrame;
+        }
+
+        result.append(runTimeStack.subList(previousFrame, runTimeStack.size()));
+        return result.toString();
+    }
+
+    public int peek(){
+        /**
+         * returns the top of the runtime stack , but does not remove
+         * @return copy of the top of the stack .
+         */
+
+        // Get the amount of items from the stack
+        return this.runTimeStack.get(this.runTimeStack.size() - 1);
+    }
+
+    public int push(int value){
+        /**
+         * push the value i to the top of the stack .
+         * @param i value to be pushed .
+         * @return value pushed
+         */
+
+        // Adds an item to the stack
+        this.runTimeStack.add(value);
+        return value;
+    }
+
+    public int pop(){
+        /**
+         * removes to the top of the runtime stack .
+         * @return the value popped .
+         */
+
+        // Removes one item from the stack
+        return this.runTimeStack.remove(this.runTimeStack.size() - 1);
+    }
+
+    public int store(int offsetInFrame){
+        /**
+         * Takes the top item of the run time stack , and stores
+         * it into a offset starting from the current frame .
+         * @param offsetInFrame number of slots above current frame
+         marker
+         * @return the item just stored
+         */
+
+        // Stores an item into a offset in the current frame
+        int location = framePointer.peek() + offsetInFrame;
+        int value = runTimeStack.remove(runTimeStack.size() - 1);
+        runTimeStack.set(location, value);
+        return value;
+    }
+
+    public int load(int offsetInFrame){
+        /**
+         * Takes a value from the run time stack that is at offset
+         * from the current frame marker and pushes it onto the top of
+         * the stack .
+         * @param offsetInFrame number of slots above current frame
+         marker
+         * @return item just loaded into the offset
+         */
+
+        int value = this.runTimeStack.get(framePointer.peek() + offsetInFrame);
+        return push(value);
+    }
+
+    public void newFrameAt(int offsetFromTopOfRunStack){
+        /**
+         * create a new frame pointer at the index offset slots down
+         * from the top of the runtime stack .
+         * @param offsetFromTopOfRunStack slots down from the top of
+         the runtime stack
+         */
+
+        framePointer.push(runTimeStack.size() - offsetFromTopOfRunStack);
+    }
+
+    public void popFrame() {
+        /**
+         * pop the current frame off the runtime stack . Also removes
+         * the frame pointer value from the FramePointer Stack .
+         */
+
+        int start = framePointer.pop();
+
+        while (runTimeStack.size() > start) {
+            this.pop();
+        }
+    }
+
+    public int size(){
+        // Returns the size of the stack.
+        return this.runTimeStack.size();
+    }
 }
diff --git a/interpreter/virtualmachine/VirtualMachine.java b/interpreter/virtualmachine/VirtualMachine.java
index 5d7be36..495bf8c 100644
--- a/interpreter/virtualmachine/VirtualMachine.java
+++ b/interpreter/virtualmachine/VirtualMachine.java
@@ -1,6 +1,7 @@
 package interpreter.virtualmachine;
 
 import java.util.Stack;
+import interpreter.bytecodes.ByteCode;
 import interpreter.loaders.Program;
 
 public class VirtualMachine {
@@ -10,11 +11,98 @@ public class VirtualMachine {
     private Program        program;
     private int            programCounter;
     private boolean        isRunning;
+    private boolean        verboseMode;
 
     public VirtualMachine(Program program) {
         this.program = program;
         this.runTimeStack = new RunTimeStack();
         this.returnAddress = new Stack<>();
         this.programCounter = 0;
+        this.verboseMode = true;
+        this.isRunning = true;
+    }
+
+    // Executes the program once program is started
+    public void executeProgram() {
+        isRunning = true;
+
+        while (isRunning) {
+            ByteCode code = program.getCode(programCounter);
+            code.execute(this);
+            if (verboseMode) {
+                System.out.println(code);
+                System.out.println(runTimeStack.verboseDisplay());
+            }
+            programCounter++;
+        }
+    }
+
+    // Turns verbose mode on or off
+    public void setVerboseMode(Boolean mode) {
+        this.verboseMode = mode;
+    }
+
+    // Stops the program when HaltCode is called
+    public void haltExecution() {
+        this.isRunning = false;
+    }
+
+    //
+    public void setProgramCounter(int address) {
+        this.programCounter = address;
+    }
+
+    public int getProgramCounter() {
+        return this.programCounter;
+    }
+
+    // Calls the store method from the RTS
+    public int store(int offset) {
+        return this.runTimeStack.store(offset);
+    }
+
+    // Calls the load method from the RTS
+    public int load(int offset) {
+        return this.runTimeStack.load(offset);
+    }
+
+    // Calls the push method from the RTS
+    public void push(int value) {
+        this.runTimeStack.push(value);
+    }
+
+    // Pushes the return address
+    public void pushReturnAddress(int address) {
+        this.returnAddress.push(address);
+    }
+
+    // Pops the return address
+    public int popReturnAddress() {
+        return returnAddress.pop();
+    }
+
+    // Calls the peek method from the RTS
+    public int peek() {
+       return this.runTimeStack.peek();
+    }
+
+    // Calls the popFrame method from the RTS
+    public void popFrame() {
+        this.runTimeStack.popFrame();
+    }
+
+    // Calls the pop method from the RTS
+    public int popRunStack() {
+        return this.runTimeStack.pop();
+    }
+
+    // Calls the push method from the RTS
+    public void pushRunStack(int value) {
+        this.runTimeStack.push(value);
+    }
+
+    // Calls newFrame method from the RTS
+    public void newFrame(int numArgs) {
+        this.runTimeStack.newFrameAt(numArgs);
     }
 }
diff --git a/out/production/interpreter-Randychen10/.idea/.gitignore b/out/production/interpreter-Randychen10/.idea/.gitignore
new file mode 100644
index 0000000..13566b8
--- /dev/null
+++ b/out/production/interpreter-Randychen10/.idea/.gitignore
@@ -0,0 +1,8 @@
+# Default ignored files
+/shelf/
+/workspace.xml
+# Editor-based HTTP Client requests
+/httpRequests/
+# Datasource local storage ignored files
+/dataSources/
+/dataSources.local.xml
diff --git a/out/production/interpreter-Randychen10/.idea/material_theme_project_new.xml b/out/production/interpreter-Randychen10/.idea/material_theme_project_new.xml
new file mode 100644
index 0000000..77c3fe6
--- /dev/null
+++ b/out/production/interpreter-Randychen10/.idea/material_theme_project_new.xml
@@ -0,0 +1,12 @@
+<?xml version="1.0" encoding="UTF-8"?>
+<project version="4">
+  <component name="MaterialThemeProjectNewConfig">
+    <option name="metadata">
+      <MTProjectMetadataState>
+        <option name="migrated" value="true" />
+        <option name="pristineConfig" value="false" />
+        <option name="userId" value="-3ba6b3b0:190224b351c:-7ffe" />
+      </MTProjectMetadataState>
+    </option>
+  </component>
+</project>
\ No newline at end of file
diff --git a/out/production/interpreter-Randychen10/.idea/misc.xml b/out/production/interpreter-Randychen10/.idea/misc.xml
new file mode 100644
index 0000000..4444b22
--- /dev/null
+++ b/out/production/interpreter-Randychen10/.idea/misc.xml
@@ -0,0 +1,6 @@
+<?xml version="1.0" encoding="UTF-8"?>
+<project version="4">
+  <component name="ProjectRootManager" version="2" languageLevel="JDK_22" default="true" project-jdk-name="openjdk-22" project-jdk-type="JavaSDK">
+    <output url="file://$PROJECT_DIR$/out" />
+  </component>
+</project>
\ No newline at end of file
diff --git a/out/production/interpreter-Randychen10/.idea/modules.xml b/out/production/interpreter-Randychen10/.idea/modules.xml
new file mode 100644
index 0000000..d8aa394
--- /dev/null
+++ b/out/production/interpreter-Randychen10/.idea/modules.xml
@@ -0,0 +1,8 @@
+<?xml version="1.0" encoding="UTF-8"?>
+<project version="4">
+  <component name="ProjectModuleManager">
+    <modules>
+      <module fileurl="file://$PROJECT_DIR$/interpreter-Randychen10.iml" filepath="$PROJECT_DIR$/interpreter-Randychen10.iml" />
+    </modules>
+  </component>
+</project>
\ No newline at end of file
diff --git a/out/production/interpreter-Randychen10/.idea/vcs.xml b/out/production/interpreter-Randychen10/.idea/vcs.xml
new file mode 100644
index 0000000..35eb1dd
--- /dev/null
+++ b/out/production/interpreter-Randychen10/.idea/vcs.xml
@@ -0,0 +1,6 @@
+<?xml version="1.0" encoding="UTF-8"?>
+<project version="4">
+  <component name="VcsDirectoryMappings">
+    <mapping directory="" vcs="Git" />
+  </component>
+</project>
\ No newline at end of file
diff --git a/out/production/interpreter-Randychen10/LICENSE b/out/production/interpreter-Randychen10/LICENSE
new file mode 100644
index 0000000..baa976f
--- /dev/null
+++ b/out/production/interpreter-Randychen10/LICENSE
@@ -0,0 +1,21 @@
+MIT License
+
+Copyright (c) 2017 CSC-413-SFSU-01
+
+Permission is hereby granted, free of charge, to any person obtaining a copy
+of this software and associated documentation files (the "Software"), to deal
+in the Software without restriction, including without limitation the rights
+to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
+copies of the Software, and to permit persons to whom the Software is
+furnished to do so, subject to the following conditions:
+
+The above copyright notice and this permission notice shall be included in all
+copies or substantial portions of the Software.
+
+THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
+IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
+FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
+AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
+LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
+OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
+SOFTWARE.
diff --git a/out/production/interpreter-Randychen10/README.md b/out/production/interpreter-Randychen10/README.md
new file mode 100644
index 0000000..0014a8d
--- /dev/null
+++ b/out/production/interpreter-Randychen10/README.md
@@ -0,0 +1,7 @@
+# CSC 413 - Project Two - The Interpreter
+
+## Student Name  : Randy Chen
+
+## Student ID    : 922525848
+
+## Student Email : rchen10@sfsu.edu
diff --git a/out/production/interpreter-Randychen10/documentation/Chen_Randy.docx b/out/production/interpreter-Randychen10/documentation/Chen_Randy.docx
new file mode 100644
index 0000000..5a3df99
Binary files /dev/null and b/out/production/interpreter-Randychen10/documentation/Chen_Randy.docx differ
diff --git a/out/production/interpreter-Randychen10/documentation/Chen_Randy.pdf b/out/production/interpreter-Randychen10/documentation/Chen_Randy.pdf
new file mode 100644
index 0000000..8ee9ab7
Binary files /dev/null and b/out/production/interpreter-Randychen10/documentation/Chen_Randy.pdf differ
diff --git a/out/production/interpreter-Randychen10/documentation/Documentation Guidelines.pdf b/out/production/interpreter-Randychen10/documentation/Documentation Guidelines.pdf
new file mode 100644
index 0000000..0790547
Binary files /dev/null and b/out/production/interpreter-Randychen10/documentation/Documentation Guidelines.pdf differ
diff --git a/out/production/interpreter-Randychen10/documentation/Interpreter UML.png b/out/production/interpreter-Randychen10/documentation/Interpreter UML.png
new file mode 100644
index 0000000..7417886
Binary files /dev/null and b/out/production/interpreter-Randychen10/documentation/Interpreter UML.png differ
diff --git a/documentation/lastname_firstname.docx b/out/production/interpreter-Randychen10/documentation/lastname_firstname.docx
similarity index 100%
rename from documentation/lastname_firstname.docx
rename to out/production/interpreter-Randychen10/documentation/lastname_firstname.docx
diff --git a/out/production/interpreter-Randychen10/factorial.verbose.cod b/out/production/interpreter-Randychen10/factorial.verbose.cod
new file mode 100644
index 0000000..28d55a4
--- /dev/null
+++ b/out/production/interpreter-Randychen10/factorial.verbose.cod
@@ -0,0 +1,43 @@
+VERBOSE ON
+GOTO start<<1>>
+LABEL Read
+READ
+RETURN
+LABEL Write
+LOAD 0 dummyFormal
+WRITE
+RETURN
+LABEL start<<1>>
+GOTO continue<<3>>
+LABEL factorial<<2>>
+LOAD 0 n
+LIT 2
+BOP <
+FALSEBRANCH else<<4>>
+LIT 1
+RETURN factorial<<2>>
+POP 0
+GOTO continue<<5>>
+LABEL else<<4>>
+LOAD 0 n
+LOAD 0 n
+LIT 1
+BOP -
+ARGS 1
+CALL factorial<<2>>
+BOP *
+RETURN factorial<<2>>
+POP 0
+LABEL continue<<5>>
+POP 0
+LIT 0    GRATIS-RETURN-VALUE
+RETURN factorial<<2>>
+LABEL continue<<3>>
+ARGS 0
+CALL Read
+ARGS 1
+CALL factorial<<2>>
+ARGS 1
+CALL Write
+POP 3
+HALT
\ No newline at end of file
diff --git a/out/production/interpreter-Randychen10/factorial.x b/out/production/interpreter-Randychen10/factorial.x
new file mode 100644
index 0000000..71a6d05
--- /dev/null
+++ b/out/production/interpreter-Randychen10/factorial.x
@@ -0,0 +1,9 @@
+program {
+  int factorial(int n) {
+      if (n < 2) then 
+         { return 1 }
+      else 
+         {return n*factorial(n-1) }
+  }
+  write(factorial(read()))
+}
diff --git a/out/production/interpreter-Randychen10/factorial.x.cod b/out/production/interpreter-Randychen10/factorial.x.cod
new file mode 100644
index 0000000..d185d62
--- /dev/null
+++ b/out/production/interpreter-Randychen10/factorial.x.cod
@@ -0,0 +1,42 @@
+GOTO start<<1>>
+LABEL Read
+READ
+RETURN
+LABEL Write
+LOAD 0 dummyFormal
+WRITE
+RETURN
+LABEL start<<1>>
+GOTO continue<<3>>
+LABEL factorial<<2>>
+LOAD 0 n
+LIT 2
+BOP <
+FALSEBRANCH else<<4>>
+LIT 1
+RETURN factorial<<2>>
+POP 0
+GOTO continue<<5>>
+LABEL else<<4>>
+LOAD 0 n
+LOAD 0 n
+LIT 1
+BOP -
+ARGS 1
+CALL factorial<<2>>
+BOP *
+RETURN factorial<<2>>
+POP 0
+LABEL continue<<5>>
+POP 0
+LIT 0    GRATIS-RETURN-VALUE
+RETURN factorial<<2>>
+LABEL continue<<3>>
+ARGS 0
+CALL Read
+ARGS 1
+CALL factorial<<2>>
+ARGS 1
+CALL Write
+POP 3
+HALT
\ No newline at end of file
diff --git a/out/production/interpreter-Randychen10/fib.x b/out/production/interpreter-Randychen10/fib.x
new file mode 100644
index 0000000..345a3e9
--- /dev/null
+++ b/out/production/interpreter-Randychen10/fib.x
@@ -0,0 +1,18 @@
+ program { int x
+   int fib(int n) { 
+       if (n <= 1) then
+          { return 1 }
+       else
+           { if (n == 2) then
+                { return 1 }
+             else
+                { return fib(n-2) + fib(n-1) }
+           }
+   }
+   int k    x = 5
+   k = write(fib(read()))
+   { int x
+     x = 7
+     x = 8
+   }
+ }
diff --git a/out/production/interpreter-Randychen10/fib.x.cod b/out/production/interpreter-Randychen10/fib.x.cod
new file mode 100644
index 0000000..79c0b86
--- /dev/null
+++ b/out/production/interpreter-Randychen10/fib.x.cod
@@ -0,0 +1,68 @@
+GOTO start<<1>>
+LABEL Read
+READ
+RETURN
+LABEL Write
+LOAD 0 dummyFormal
+WRITE
+RETURN
+LABEL start<<1>>
+LIT 0 x
+GOTO continue<<3>>
+LABEL fib<<2>>
+LOAD 0 n
+LIT 1
+BOP <=
+FALSEBRANCH else<<4>>
+LIT 1
+RETURN fib<<2>>
+POP 0
+GOTO continue<<5>>
+LABEL else<<4>>
+LOAD 0 n
+LIT 2
+BOP ==
+FALSEBRANCH else<<6>>
+LIT 1
+RETURN fib<<2>>
+POP 0
+GOTO continue<<7>>
+LABEL else<<6>>
+LOAD 0 n
+LIT 2
+BOP -
+ARGS 1
+CALL fib<<2>>
+LOAD 0 n
+LIT 1
+BOP -
+ARGS 1
+CALL fib<<2>>
+BOP +
+RETURN fib<<2>>
+POP 0
+LABEL continue<<7>>
+POP 0
+LABEL continue<<5>>
+POP 0
+LIT 0    GRATIS-RETURN-VALUE
+RETURN fib<<2>>
+LABEL continue<<3>>
+LIT 0 k
+LIT 5
+STORE 0 x
+ARGS 0
+CALL Read
+ARGS 1
+CALL fib<<2>>
+ARGS 1
+CALL Write
+STORE 1 k
+LIT 0 x
+LIT 7
+STORE 2 x
+LIT 8
+STORE 2 x
+POP 1
+POP 2
+HALT
\ No newline at end of file
diff --git a/out/production/interpreter-Randychen10/functionArgsTest.cod b/out/production/interpreter-Randychen10/functionArgsTest.cod
new file mode 100644
index 0000000..819eb0e
--- /dev/null
+++ b/out/production/interpreter-Randychen10/functionArgsTest.cod
@@ -0,0 +1,60 @@
+GOTO CONTINUE<<1>>
+LABEL CONTINUE<<2>>
+GOTO CONTINUE<<3>>
+LABEL CONTINUE<<4>>
+LIT 0
+LIT 1
+ARGS 2
+CALL doublePrint<<1>>
+POP 1
+LIT 0
+LIT 1
+LIT 2
+ARGS 3
+CALL triplePrint<<1>>
+POP 1
+LIT 0
+LIT 1
+LIT 2
+LIT 3
+ARGS 4
+CALL quadruplePrint<<1>>
+POP 66
+HALT
+LABEL CONTINUE<<1>>
+GOTO CONTINUE<<2>>
+LABEL CONTINUE<<3>>
+GOTO CONTINUE<<4>>
+LABEL doublePrint<<1>>
+LOAD 0
+WRITE
+POP 1
+LOAD 1
+WRITE
+POP 1
+RETURN doublePrint<<1>>
+LABEL triplePrint<<1>>
+LOAD 0
+WRITE
+POP 1
+LOAD 1
+WRITE
+POP 1
+LOAD 2
+WRITE
+POP 2
+RETURN triplePrint<<1>>
+LABEL quadruplePrint<<1>>
+LOAD 0
+WRITE
+POP 1
+LOAD 1
+WRITE
+POP 1
+LOAD 2
+WRITE
+POP 1
+LOAD 3
+WRITE
+POP 1
+RETURN quadruplePrint<<1>>
\ No newline at end of file
diff --git a/out/production/interpreter-Randychen10/interpreter-Randychen10.iml b/out/production/interpreter-Randychen10/interpreter-Randychen10.iml
new file mode 100644
index 0000000..b107a2d
--- /dev/null
+++ b/out/production/interpreter-Randychen10/interpreter-Randychen10.iml
@@ -0,0 +1,11 @@
+<?xml version="1.0" encoding="UTF-8"?>
+<module type="JAVA_MODULE" version="4">
+  <component name="NewModuleRootManager" inherit-compiler-output="true">
+    <exclude-output />
+    <content url="file://$MODULE_DIR$">
+      <sourceFolder url="file://$MODULE_DIR$" isTestSource="false" />
+    </content>
+    <orderEntry type="inheritedJdk" />
+    <orderEntry type="sourceFolder" forTests="false" />
+  </component>
+</module>
\ No newline at end of file
diff --git a/out/production/interpreter-Randychen10/interpreter/Interpreter.class b/out/production/interpreter-Randychen10/interpreter/Interpreter.class
new file mode 100644
index 0000000..e9c8327
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/Interpreter.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/.gitignore b/out/production/interpreter-Randychen10/interpreter/bytecodes/.gitignore
new file mode 100644
index 0000000..e69de29
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/ArgsCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/ArgsCode.class
new file mode 100644
index 0000000..efa7b14
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/ArgsCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/BopCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/BopCode.class
new file mode 100644
index 0000000..94f52c8
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/BopCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/ByteCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/ByteCode.class
new file mode 100644
index 0000000..5f3e9c3
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/ByteCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/CallCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/CallCode.class
new file mode 100644
index 0000000..118f5a9
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/CallCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/FalseBranchCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/FalseBranchCode.class
new file mode 100644
index 0000000..b1b0ffb
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/FalseBranchCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/GoToCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/GoToCode.class
new file mode 100644
index 0000000..be46972
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/GoToCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/HaltCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/HaltCode.class
new file mode 100644
index 0000000..f75f8fc
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/HaltCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/LabelCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/LabelCode.class
new file mode 100644
index 0000000..5c89eda
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/LabelCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/LitCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/LitCode.class
new file mode 100644
index 0000000..8dcade7
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/LitCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/LoadCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/LoadCode.class
new file mode 100644
index 0000000..201c96d
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/LoadCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/PopCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/PopCode.class
new file mode 100644
index 0000000..dc8a274
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/PopCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/ReadCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/ReadCode.class
new file mode 100644
index 0000000..08dec2c
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/ReadCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/ReturnCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/ReturnCode.class
new file mode 100644
index 0000000..16ce525
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/ReturnCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/StoreCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/StoreCode.class
new file mode 100644
index 0000000..d24be21
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/StoreCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/VerboseCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/VerboseCode.class
new file mode 100644
index 0000000..fd7e78f
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/VerboseCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/bytecodes/WriteCode.class b/out/production/interpreter-Randychen10/interpreter/bytecodes/WriteCode.class
new file mode 100644
index 0000000..fae901c
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/bytecodes/WriteCode.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/loaders/ByteCodeLoader.class b/out/production/interpreter-Randychen10/interpreter/loaders/ByteCodeLoader.class
new file mode 100644
index 0000000..39f79fc
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/loaders/ByteCodeLoader.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/loaders/CodeTable.class b/out/production/interpreter-Randychen10/interpreter/loaders/CodeTable.class
new file mode 100644
index 0000000..e19d9e4
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/loaders/CodeTable.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/loaders/InvalidProgramException.class b/out/production/interpreter-Randychen10/interpreter/loaders/InvalidProgramException.class
new file mode 100644
index 0000000..7c72a42
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/loaders/InvalidProgramException.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/loaders/Program.class b/out/production/interpreter-Randychen10/interpreter/loaders/Program.class
new file mode 100644
index 0000000..5b4684d
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/loaders/Program.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/virtualmachine/RunTimeStack.class b/out/production/interpreter-Randychen10/interpreter/virtualmachine/RunTimeStack.class
new file mode 100644
index 0000000..d42e0ca
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/virtualmachine/RunTimeStack.class differ
diff --git a/out/production/interpreter-Randychen10/interpreter/virtualmachine/VirtualMachine.class b/out/production/interpreter-Randychen10/interpreter/virtualmachine/VirtualMachine.class
new file mode 100644
index 0000000..1f3a086
Binary files /dev/null and b/out/production/interpreter-Randychen10/interpreter/virtualmachine/VirtualMachine.class differ
diff --git a/out/production/interpreter-Randychen10/theinterpreter.pdf b/out/production/interpreter-Randychen10/theinterpreter.pdf
new file mode 100644
index 0000000..dd3b7d6
Binary files /dev/null and b/out/production/interpreter-Randychen10/theinterpreter.pdf differ

~~~

</details>




## Compiling Source Code Results: 



~~~bash

~~~
    


## Code Review


<details>
    <summary>./interpreter/Interpreter.java</summary>

~~~java
package interpreter;

import interpreter.loaders.ByteCodeLoader;
import interpreter.loaders.CodeTable;
import interpreter.loaders.InvalidProgramException;
import interpreter.loaders.Program;
import interpreter.virtualmachine.VirtualMachine;

/**
 * <pre>
 *     Interpreter class runs the interpreter:
 *     1. Perform all initializations
 *     2. Load the ByteCodes from file
 *     3. Run the virtual machine
 * 
 *     THIS FILE CANNOT BE MODIFIED. DO NOT
 *     LET ANY EXCEPTIONS REACH THE
 * 
 * </pre>
 */
public class Interpreter {

    private ByteCodeLoader byteCodeLoader;

    public Interpreter(String codeFile) {
        byteCodeLoader = new ByteCodeLoader(codeFile);
    }

    void run() {
        CodeTable.init();
        Program program = null;
        try{
            program = byteCodeLoader.loadCodes();
        } catch(InvalidProgramException ex){
            System.out.println(ex);
            ex.printStackTrace();
            System.exit(-2);
        }
        program.resolveAddress();
        VirtualMachine virtualMachine = new VirtualMachine(program);
        virtualMachine.executeProgram();
    }

    public static void main(String args[]) {

        if (args.length == 0) {
            System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
            System.exit(1);
        }
        (new Interpreter(args[0])).run();
    }
}
~~~

</details>



<details>
    <summary>./interpreter/virtualmachine/VirtualMachine.java</summary>

~~~java
package interpreter.virtualmachine;

import java.util.Stack;
import interpreter.bytecodes.ByteCode;
import interpreter.loaders.Program;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;
    private boolean        verboseMode;

    public VirtualMachine(Program program) {
        this.program = program;
        this.runTimeStack = new RunTimeStack();
        this.returnAddress = new Stack<>();
        this.programCounter = 0;
        this.verboseMode = true;
        this.isRunning = true;
    }

    // Executes the program once program is started
    public void executeProgram() {
        isRunning = true;

        while (isRunning) {
            ByteCode code = program.getCode(programCounter);
            code.execute(this);
            if (verboseMode) {
                System.out.println(code);
                System.out.println(runTimeStack.verboseDisplay());
            }
            programCounter++;
        }
    }

    // Turns verbose mode on or off
    public void setVerboseMode(Boolean mode) {
        this.verboseMode = mode;
    }

    // Stops the program when HaltCode is called
    public void haltExecution() {
        this.isRunning = false;
    }

    //
    public void setProgramCounter(int address) {
        this.programCounter = address;
    }

    public int getProgramCounter() {
        return this.programCounter;
    }

    // Calls the store method from the RTS
    public int store(int offset) {
        return this.runTimeStack.store(offset);
    }

    // Calls the load method from the RTS
    public int load(int offset) {
        return this.runTimeStack.load(offset);
    }

    // Calls the push method from the RTS
    public void push(int value) {
        this.runTimeStack.push(value);
    }

    // Pushes the return address
    public void pushReturnAddress(int address) {
        this.returnAddress.push(address);
    }

    // Pops the return address
    public int popReturnAddress() {
        return returnAddress.pop();
    }

    // Calls the peek method from the RTS
    public int peek() {
       return this.runTimeStack.peek();
    }

    // Calls the popFrame method from the RTS
    public void popFrame() {
        this.runTimeStack.popFrame();
    }

    // Calls the pop method from the RTS
    public int popRunStack() {
        return this.runTimeStack.pop();
    }

    // Calls the push method from the RTS
    public void pushRunStack(int value) {
        this.runTimeStack.push(value);
    }

    // Calls newFrame method from the RTS
    public void newFrame(int numArgs) {
        this.runTimeStack.newFrameAt(numArgs);
    }
}

~~~

</details>



<details>
    <summary>./interpreter/virtualmachine/RunTimeStack.java</summary>

~~~java
package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class RunTimeStack {

    private List<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial frame pointer value, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    public String verboseDisplay(){
        /**
         * Used for displaying the current state of the runTimeStack .
         * It will print portions of the stack based on respective
         * frame markers .
         * Example [1 ,2 ,3] [4 ,5 ,6] [7 ,8]
         * Frame pointers would be 0 ,3 ,6
         */

        StringBuilder result = new StringBuilder();
        int previousFrame = 0;

        for (int i = 1; i < framePointer.size(); i++) {
            int currentFrame = framePointer.get(i);
            result.append(runTimeStack.subList(previousFrame, currentFrame)).append(" ");
            previousFrame = currentFrame;
        }

        result.append(runTimeStack.subList(previousFrame, runTimeStack.size()));
        return result.toString();
    }

    public int peek(){
        /**
         * returns the top of the runtime stack , but does not remove
         * @return copy of the top of the stack .
         */

        // Get the amount of items from the stack
        return this.runTimeStack.get(this.runTimeStack.size() - 1);
    }

    public int push(int value){
        /**
         * push the value i to the top of the stack .
         * @param i value to be pushed .
         * @return value pushed
         */

        // Adds an item to the stack
        this.runTimeStack.add(value);
        return value;
    }

    public int pop(){
        /**
         * removes to the top of the runtime stack .
         * @return the value popped .
         */

        // Removes one item from the stack
        return this.runTimeStack.remove(this.runTimeStack.size() - 1);
    }

    public int store(int offsetInFrame){
        /**
         * Takes the top item of the run time stack , and stores
         * it into a offset starting from the current frame .
         * @param offsetInFrame number of slots above current frame
         marker
         * @return the item just stored
         */

        // Stores an item into a offset in the current frame
        int location = framePointer.peek() + offsetInFrame;
        int value = runTimeStack.remove(runTimeStack.size() - 1);
        runTimeStack.set(location, value);
        return value;
    }

    public int load(int offsetInFrame){
        /**
         * Takes a value from the run time stack that is at offset
         * from the current frame marker and pushes it onto the top of
         * the stack .
         * @param offsetInFrame number of slots above current frame
         marker
         * @return item just loaded into the offset
         */

        int value = this.runTimeStack.get(framePointer.peek() + offsetInFrame);
        return push(value);
    }

    public void newFrameAt(int offsetFromTopOfRunStack){
        /**
         * create a new frame pointer at the index offset slots down
         * from the top of the runtime stack .
         * @param offsetFromTopOfRunStack slots down from the top of
         the runtime stack
         */

        framePointer.push(runTimeStack.size() - offsetFromTopOfRunStack);
    }

    public void popFrame() {
        /**
         * pop the current frame off the runtime stack . Also removes
         * the frame pointer value from the FramePointer Stack .
         */

        int start = framePointer.pop();

        while (runTimeStack.size() > start) {
            this.pop();
        }
    }

    public int size(){
        // Returns the size of the stack.
        return this.runTimeStack.size();
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/ByteCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public abstract class ByteCode {
    public abstract void init(List<String> args);
    public abstract void execute(VirtualMachine vm);
    public abstract String toString();
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/HaltCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class HaltCode extends ByteCode {
    @Override
    public void init(List<String> args) {

    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.haltExecution();
    }

    @Override
    public String toString() {
        return "";
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/LoadCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class LoadCode extends ByteCode {
    private String identifier;
    private int offset;

    @Override
    public void init(List<String> args) {
        offset = Integer.parseInt(args.get(0));

        if (args.size() > 1) {
            identifier = args.get(1);
        } else {
            identifier = null;
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.load(offset);
    }

    @Override
    public String toString() {
        if (identifier != null) {
            return "LOAD " + offset + " " + identifier + " <load " + identifier + ">";
        } else {
            return "LOAD " + offset;
        }
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/VerboseCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class VerboseCode extends ByteCode{
    private Boolean mode;
    private String value;

    public void init(List<String> args) {
        this.value = args.getFirst();
    }

    public void execute(VirtualMachine vm) {
        if (value.equals("ON")){
            this.mode = true;
        }

        if (value.equals("OFF")){
            this.mode = false;
        }

        vm.setVerboseMode(mode);
    }

    public String toString(){
        String val = "VERBOSE " + value;

        return val;
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/ReturnCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class ReturnCode extends ByteCode {
    private String label;

    @Override
    public void init(List<String> args) {
        if (!args.isEmpty()) {
            label = args.getFirst();
        } else {
            label = null;
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        int value = vm.peek();
        vm.popFrame();
        vm.push(value);

        int address = vm.popReturnAddress();
        vm.setProgramCounter(address);
    }

    @Override
    public String toString() {
        if(label == null){
            return "RETURN ";
        }else {
            return "RETURN " + label + " EXIT " + label;
        }
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/LitCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class LitCode extends ByteCode {
    private int value;      // The literal value (an integer)
    private String identifier;  // Optional identifier (like a variable name)

    @Override
    public void init(List<String> args) {
        // The first argument is the literal value, which is an integer
        value = Integer.parseInt(args.get(0));  // Parse the literal value

        // The second argument, if present, is the identifier (a string)
        if (args.size() > 1) {
            identifier = args.get(1);  // This is the identifier (e.g., "x")
        } else {
            identifier = null;  // No identifier provided
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushRunStack(value);  // Push the literal value onto the runtime stack

        if (identifier != null) {
            // If there's an identifier, you may need to associate it with the value
            // depending on your implementation, but for now, we just handle the literal.
        }
    }

    @Override
    public String toString() {
        // If an identifier is present, include it in the verbose output
        if (identifier != null) {
            return "LIT " + value + " " + identifier + " <load " + identifier + ">";
        } else {
            return "LIT " + value;
        }
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/FalseBranchCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;
import java.util.List;

public class FalseBranchCode extends ByteCode {

    private String label;
    private int labelAddress;

    @Override
    public void init(List<String> args) {
        this.label = args.get(0);
    }

    public String getLabel() {
        return label;
    }

    public void setTargetAddress(int address) {
        this.labelAddress = address;
    }

    @Override
    public void execute(VirtualMachine vm) {
        int value = vm.popRunStack();

        if (value == 0) {
            vm.setProgramCounter(labelAddress);
        }
    }

    @Override
    public String toString() {
        return "FALSEBRANCH " + label + " -> " + labelAddress;
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/ArgsCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class ArgsCode extends ByteCode {
    private int numArgs;

    @Override
    public void init(List<String> args) {
        if(!args.isEmpty()) {
            numArgs = Integer.parseInt(args.get(0));
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.newFrame(numArgs);
    }

    @Override
    public String toString() {
        return "ARGS " + numArgs;
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/StoreCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;
import java.util.List;

public class StoreCode extends ByteCode {
    private int offset;  // The offset in the runtime stack (integer)
    private String identifier;  // Optional identifier (variable name)

    @Override
    public void init(List<String> args) {
        // Parse the first argument as an integer (offset)
        offset = Integer.parseInt(args.get(0));  // This is the offset in the runtime stack

        // The second argument, if present, is the identifier (a string)
        if (args.size() > 1) {
            identifier = args.get(1);  // This is the variable name (e.g., "x")
        } else {
            identifier = null;  // No identifier provided
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        // Store the value at the given offset
        vm.store(offset);
    }

    @Override
    public String toString() {
        // If an identifier is present, include it in the output
        if (identifier != null) {
            return "STORE " + offset + " " + identifier + " <store " + identifier + ">";
        } else {
            return "STORE " + offset;
        }
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/LabelCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;
import java.util.List;

public class LabelCode extends ByteCode {
    private String label;

    @Override
    public void init(List<String> args) {
        if (!args.isEmpty()) {
            this.label = args.getFirst();
        } else {
            throw new RuntimeException("LABEL missing argument");
        }
    }

    public String getLabel() {
        return label;
    }

    @Override
    public void execute(VirtualMachine vm) {
        // No functionality for execution, Label is only used for marking a position
    }

    @Override
    public String toString() {
        return "LABEL " + label;
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/ReadCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;
import java.util.Scanner;
import java.util.List;

public class ReadCode extends ByteCode {

    @Override
    public void init(List<String> args) {
        // No arguments needed for Read bytecode
    }

    @Override
    public void execute(VirtualMachine vm) {
        Scanner scanner = new Scanner(System.in);
        int inputValue = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Please enter an integer: ");
            if (scanner.hasNextInt()) {
                inputValue = scanner.nextInt();
                validInput = true;
            } else {
                System.out.println("Invalid input. Please try again.");
                scanner.next();
            }
        }

        vm.push(inputValue);
    }

    @Override
    public String toString() {
        return "READ";
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/PopCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class PopCode extends ByteCode {
    private int nums;

    @Override
    public void init(List<String> args) {
        nums = Integer.parseInt(args.get(0));
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.popRunStack();
    }

    @Override
    public String toString() {
        return "POP " + nums;
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/GoToCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class GoToCode extends ByteCode {
    private String label;
    private int labelAddress;

    @Override
    public void init(List<String> args) {
        this.label = args.getFirst();
    }

    public String getLabel() {
        return label;
    }

    public void setTargetAddress(int address) {
        this.labelAddress = address;
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setProgramCounter(labelAddress);
    }

    @Override
    public String toString() {
        return "GOTO " + label + " -> " + labelAddress;
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/CallCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;
import java.util.List;

public class CallCode extends ByteCode {
    private String label;
    private int labelAddress;

    @Override
    public void init(List<String> args) {
        label = args.get(0);
    }

    public String getLabel() {
        return label;
    }

    public void setTargetAddress(int address) {
        this.labelAddress = address;
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushReturnAddress(vm.getProgramCounter());
        vm.setProgramCounter(labelAddress);
    }

    @Override
    public String toString() {
        return "CALL " + label + " -> " + labelAddress;
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/WriteCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;
import java.util.List;

public class WriteCode extends ByteCode {

    @Override
    public void init(List<String> args) {
        // No arguments needed for Write bytecode
    }

    @Override
    public void execute(VirtualMachine vm) {
        int topValue = vm.peek();

        System.out.println(topValue);
    }

    @Override
    public String toString() {
        return "WRITE";
    }
}

~~~

</details>



<details>
    <summary>./interpreter/bytecodes/BopCode.java</summary>

~~~java
package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class BopCode extends ByteCode {
    private String operator;  // The binary operator (e.g., +, -, *, /, etc.)

    /**
     * Initialize the BopCode with the binary operator.
     *
     * @param args The list of arguments passed to this bytecode. Expects the operator as the first argument.
     */
    @Override
    public void init(List<String> args) {
        // Check if an operator is provided and initialize
        if (args.size() > 0) {
            this.operator = args.get(0);  // Assuming the operator is the first argument
        } else {
            throw new IllegalArgumentException("Operator argument is missing.");
        }
    }

    /**
     * Execute the BopCode operation by performing the binary operation
     * on the top two elements of the runtime stack.
     *
     * @param vm The virtual machine instance on which this bytecode is executed.
     */
    @Override
    public void execute(VirtualMachine vm) {
        // Pop two operands from the runtime stack
        int rightOperand = vm.popRunStack();
        int leftOperand = vm.popRunStack();
        int result = 0;

        // Perform the binary operation based on the operator
        switch (operator) {
            case "+" -> result = leftOperand + rightOperand;
            case "-" -> result = leftOperand - rightOperand;
            case "*" -> result = leftOperand * rightOperand;
            case "/" -> {
                if (rightOperand == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = leftOperand / rightOperand;
            }
            case "==" -> result = (leftOperand == rightOperand) ? 1 : 0;
            case "!=" -> result = (leftOperand != rightOperand) ? 1 : 0;
            case "<" -> result = (leftOperand < rightOperand) ? 1 : 0;
            case ">" -> result = (leftOperand > rightOperand) ? 1 : 0;
            case "<=" -> result = (leftOperand <= rightOperand) ? 1 : 0;
            case ">=" -> result = (leftOperand >= rightOperand) ? 1 : 0;
            case "&" -> result = (leftOperand != 0 && rightOperand != 0) ? 1 : 0;  // Logical AND
            case "|" -> result = (leftOperand != 0 || rightOperand != 0) ? 1 : 0;  // Logical OR
            default -> throw new RuntimeException("Unknown operator: " + operator);
        }

        // Push the result of the operation back onto the stack
        vm.push(result);
    }

    /**
     * This is for verbose mode output where it shows the bytecode being executed.
     * It returns a string representation of the operation for the VERBOSE mode.
     *
     * @return The string format of the bytecode operation.
     */
    @Override
    public String toString() {
        return "BOP " + operator;
    }
}

~~~

</details>



<details>
    <summary>./interpreter/loaders/ByteCodeLoader.java</summary>

~~~java
package interpreter.loaders;

import interpreter.bytecodes.ByteCode;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class ByteCodeLoader {
    private String codSourceFileName;

    /**
     * Constructs ByteCodeLoader object given a COD source code
     * file name
     *
     * @param fileName name of .cod File to load.
     */
    public ByteCodeLoader(String fileName) {
        this.codSourceFileName = fileName;
    }

    /**
     * Loads a program from a .cod file.
     *
     * @return a constructed Program Object.
     * @throws InvalidProgramException thrown when
     *                                 loadCodes fails.
     */
    public Program loadCodes() throws InvalidProgramException {
        final Program program = new Program(); // Create a new Program instance to hold the ByteCodes

        // Try-with-resources to automatically close the BufferedReader after use
        try (BufferedReader reader = new BufferedReader(new FileReader(this.codSourceFileName))) {

            // Read each line of the file while there is data available
            while (reader.ready()) {
                String[] items = reader.readLine().split("\\s+"); // Split the line into parts using whitespace as the delimiter
                String className = CodeTable.getClassName(items[0]); // Get the corresponding class name for the ByteCode

                // Dynamically load the ByteCode class and create an instance of it
                ByteCode bc = (ByteCode) Class.forName("interpreter.bytecodes." + className)
                        .getDeclaredConstructor().newInstance();

                // Initialize the ByteCode with its arguments (skipping the first item which is the ByteCode name)
                bc.init(Arrays.asList(items).subList(1, items.length));

                // Add the instantiated and initialized ByteCode to the program
                program.addCode(bc);
            }
        } catch (IOException | ClassNotFoundException | InvocationTargetException | InstantiationException
                 | IllegalAccessException | NoSuchMethodException ex) {
            // Catch any exceptions related to file I/O or reflection, and wrap them in an InvalidProgramException
            throw new InvalidProgramException(ex);
        }
        return program; // Return the constructed program containing all ByteCodes
    }
}

~~~

</details>



<details>
    <summary>./interpreter/loaders/CodeTable.java</summary>

~~~java
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

~~~

</details>



<details>
    <summary>./interpreter/loaders/InvalidProgramException.java</summary>

~~~java
package interpreter.loaders;

/**
 * Exception for when loadCode fails.
 * This exception is used to bubble up all
 * exceptions that can be thrown by loadCodes.
 * 
 * DO NOT ADD ANY ADDITIONAL Constructors.
 */
public class InvalidProgramException extends Exception {
    public InvalidProgramException(Throwable ex) {
        super(ex);
    }

    public InvalidProgramException(Throwable ex, String message){
        super(message, ex);
    }

    public InvalidProgramException(String message){
        super(message);
    }
}

~~~

</details>



<details>
    <summary>./interpreter/loaders/Program.java</summary>

~~~java
package interpreter.loaders;

import interpreter.bytecodes.ByteCode;
import interpreter.bytecodes.LabelCode;
import interpreter.bytecodes.FalseBranchCode;
import interpreter.bytecodes.GoToCode;
import interpreter.bytecodes.CallCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Program {

    private List<ByteCode> program;

    /**
     * Instantiates a program object using an
     * ArrayList
     */
    public Program() {
        program = new ArrayList<>();
    }

    /**
     * Gets the size of the current program.
     * @return size of program
     */
    public int getSize() {
        return this.program.size();
    }

    /**
     * Grabs an instance of a bytecode at an index.
     * @param programCounter index of bytecode to get.
     * @return a bytecode.
     */
    public ByteCode getCode(int programCounter) {
        return this.program.get(programCounter);
    }

    /**
     * Adds a bytecode instance to the Program List.
     * @param c bytecode to be added
     */
    public void addCode(ByteCode c) {
        this.program.add(c);
    }

    /**
     * Makes multiple passes through the program ArrayList
     * resolving addrss for Goto,Call and FalseBranch
     * bytecodes. These bytecodes can only jump to Label
     * codes that have a matching label value.
     * HINT: make note of what type of data-structure
     * ByteCodes are stored in.
     * **** METHOD SIGNATURE CANNOT BE CHANGED *****
     */
    public void resolveAddress() {
        Map<String, Integer> labelMap = new HashMap<>();

        // First pass: map labels to their addresses
        for (int i = 0; i < this.program.size(); i++) {
            ByteCode code = this.program.get(i);
            if (code instanceof LabelCode) {
                String label = ((LabelCode) code).getLabel();
                labelMap.put(label, i);  // Store the label and its address
            }
        }


        // Iterate through all the ByteCodes in the program list
        for (int i = 0; i < this.program.size(); i++) {
            ByteCode code = this.program.get(i); // Retrieve each ByteCode from the program list

            // Check if the ByteCode is an instance of GoToCode
            if (code instanceof GoToCode) {
                GoToCode gotoCode = (GoToCode) code;
                String label = gotoCode.getLabel(); // Get the label associated with the GoToCode
                Integer targetAddress = labelMap.get(label); // Look up the address corresponding to the label

                // If the label is found in the labelMap, set the target address in GoToCode
                if (targetAddress != null) {
                    gotoCode.setTargetAddress(targetAddress);  // Set the resolved address
                } else {
                    throw new RuntimeException("Label not found: " + label); // Throw an error if the label is not found
                }

                // Check if the ByteCode is an instance of CallCode
            } else if (code instanceof CallCode) {
                CallCode callCode = (CallCode) code;
                String label = callCode.getLabel(); // Get the label associated with the CallCode
                Integer targetAddress = labelMap.get(label); // Look up the address corresponding to the label

                // If the label is found, set the target address in CallCode
                if (targetAddress != null) {
                    callCode.setTargetAddress(targetAddress);  // Set the resolved address
                } else {
                    throw new RuntimeException("Label not found: " + label); // Throw an error if the label is not found
                }

                // Check if the ByteCode is an instance of FalseBranchCode
            } else if (code instanceof FalseBranchCode) {
                FalseBranchCode falseBranchCode = (FalseBranchCode) code;
                String label = falseBranchCode.getLabel(); // Get the label associated with the FalseBranchCode
                Integer targetAddress = labelMap.get(label); // Look up the address corresponding to the label

                // If the label is found, set the target address in FalseBranchCode
                if (targetAddress != null) {
                    falseBranchCode.setTargetAddress(targetAddress);  // Set the resolved address
                } else {
                    throw new RuntimeException("Label not found: " + label); // Throw an error if the label is not found
                }
            }
        }
    }
}
~~~

</details>




## Factorial Verbose -- Input : 6



~~~bash
VERBOSE ON
[]
GOTO start<<1>> -> 9
[]
GOTO continue<<3>> -> 34
[]
ARGS 0
[] []
CALL Read -> 2
[] []
Please enter an integer: READ
[] [6]
RETURN 
[6]
ARGS 1
[] [6]
CALL factorial<<2>> -> 11
[] [6]
LOAD 0 n <load n>
[] [6, 6]
LIT 2
[] [6, 6, 2]
BOP <
[] [6, 0]
FALSEBRANCH else<<4>> -> 20
[] [6]
LOAD 0 n <load n>
[] [6, 6]
LOAD 0 n <load n>
[] [6, 6, 6]
LIT 1
[] [6, 6, 6, 1]
BOP -
[] [6, 6, 5]
ARGS 1
[] [6, 6] [5]
CALL factorial<<2>> -> 11
[] [6, 6] [5]
LOAD 0 n <load n>
[] [6, 6] [5, 5]
LIT 2
[] [6, 6] [5, 5, 2]
BOP <
[] [6, 6] [5, 0]
FALSEBRANCH else<<4>> -> 20
[] [6, 6] [5]
LOAD 0 n <load n>
[] [6, 6] [5, 5]
LOAD 0 n <load n>
[] [6, 6] [5, 5, 5]
LIT 1
[] [6, 6] [5, 5, 5, 1]
BOP -
[] [6, 6] [5, 5, 4]
ARGS 1
[] [6, 6] [5, 5] [4]
CALL factorial<<2>> -> 11
[] [6, 6] [5, 5] [4]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4]
LIT 2
[] [6, 6] [5, 5] [4, 4, 2]
BOP <
[] [6, 6] [5, 5] [4, 0]
FALSEBRANCH else<<4>> -> 20
[] [6, 6] [5, 5] [4]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4, 4]
LIT 1
[] [6, 6] [5, 5] [4, 4, 4, 1]
BOP -
[] [6, 6] [5, 5] [4, 4, 3]
ARGS 1
[] [6, 6] [5, 5] [4, 4] [3]
CALL factorial<<2>> -> 11
[] [6, 6] [5, 5] [4, 4] [3]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4] [3, 3]
LIT 2
[] [6, 6] [5, 5] [4, 4] [3, 3, 2]
BOP <
[] [6, 6] [5, 5] [4, 4] [3, 0]
FALSEBRANCH else<<4>> -> 20
[] [6, 6] [5, 5] [4, 4] [3]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4] [3, 3]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4] [3, 3, 3]
LIT 1
[] [6, 6] [5, 5] [4, 4] [3, 3, 3, 1]
BOP -
[] [6, 6] [5, 5] [4, 4] [3, 3, 2]
ARGS 1
[] [6, 6] [5, 5] [4, 4] [3, 3] [2]
CALL factorial<<2>> -> 11
[] [6, 6] [5, 5] [4, 4] [3, 3] [2]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2]
LIT 2
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2, 2]
BOP <
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 0]
FALSEBRANCH else<<4>> -> 20
[] [6, 6] [5, 5] [4, 4] [3, 3] [2]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2, 2]
LIT 1
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2, 2, 1]
BOP -
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2, 1]
ARGS 1
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2] [1]
CALL factorial<<2>> -> 11
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2] [1]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2] [1, 1]
LIT 2
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2] [1, 1, 2]
BOP <
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2] [1, 1]
FALSEBRANCH else<<4>> -> 20
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2] [1]
LIT 1
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2] [1, 1]
RETURN factorial<<2>> EXIT factorial<<2>>
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2, 1]
BOP *
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2]
RETURN factorial<<2>> EXIT factorial<<2>>
[] [6, 6] [5, 5] [4, 4] [3, 3, 2]
BOP *
[] [6, 6] [5, 5] [4, 4] [3, 6]
RETURN factorial<<2>> EXIT factorial<<2>>
[] [6, 6] [5, 5] [4, 4, 6]
BOP *
[] [6, 6] [5, 5] [4, 24]
RETURN factorial<<2>> EXIT factorial<<2>>
[] [6, 6] [5, 5, 24]
BOP *
[] [6, 6] [5, 120]
RETURN factorial<<2>> EXIT factorial<<2>>
[] [6, 6, 120]
BOP *
[] [6, 720]
RETURN factorial<<2>> EXIT factorial<<2>>
[720]
ARGS 1
[] [720]
CALL Write -> 5
[] [720]
LOAD 0 dummyFormal <load dummyFormal>
[] [720, 720]
720
WRITE
[] [720, 720]
RETURN 
[720]
POP 3
[]

[]

~~~
    


## Factorial -- Input : 6



~~~bash
GOTO start<<1>> -> 8
[]
GOTO continue<<3>> -> 33
[]
ARGS 0
[] []
CALL Read -> 1
[] []
Please enter an integer: READ
[] [6]
RETURN 
[6]
ARGS 1
[] [6]
CALL factorial<<2>> -> 10
[] [6]
LOAD 0 n <load n>
[] [6, 6]
LIT 2
[] [6, 6, 2]
BOP <
[] [6, 0]
FALSEBRANCH else<<4>> -> 19
[] [6]
LOAD 0 n <load n>
[] [6, 6]
LOAD 0 n <load n>
[] [6, 6, 6]
LIT 1
[] [6, 6, 6, 1]
BOP -
[] [6, 6, 5]
ARGS 1
[] [6, 6] [5]
CALL factorial<<2>> -> 10
[] [6, 6] [5]
LOAD 0 n <load n>
[] [6, 6] [5, 5]
LIT 2
[] [6, 6] [5, 5, 2]
BOP <
[] [6, 6] [5, 0]
FALSEBRANCH else<<4>> -> 19
[] [6, 6] [5]
LOAD 0 n <load n>
[] [6, 6] [5, 5]
LOAD 0 n <load n>
[] [6, 6] [5, 5, 5]
LIT 1
[] [6, 6] [5, 5, 5, 1]
BOP -
[] [6, 6] [5, 5, 4]
ARGS 1
[] [6, 6] [5, 5] [4]
CALL factorial<<2>> -> 10
[] [6, 6] [5, 5] [4]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4]
LIT 2
[] [6, 6] [5, 5] [4, 4, 2]
BOP <
[] [6, 6] [5, 5] [4, 0]
FALSEBRANCH else<<4>> -> 19
[] [6, 6] [5, 5] [4]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4, 4]
LIT 1
[] [6, 6] [5, 5] [4, 4, 4, 1]
BOP -
[] [6, 6] [5, 5] [4, 4, 3]
ARGS 1
[] [6, 6] [5, 5] [4, 4] [3]
CALL factorial<<2>> -> 10
[] [6, 6] [5, 5] [4, 4] [3]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4] [3, 3]
LIT 2
[] [6, 6] [5, 5] [4, 4] [3, 3, 2]
BOP <
[] [6, 6] [5, 5] [4, 4] [3, 0]
FALSEBRANCH else<<4>> -> 19
[] [6, 6] [5, 5] [4, 4] [3]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4] [3, 3]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4] [3, 3, 3]
LIT 1
[] [6, 6] [5, 5] [4, 4] [3, 3, 3, 1]
BOP -
[] [6, 6] [5, 5] [4, 4] [3, 3, 2]
ARGS 1
[] [6, 6] [5, 5] [4, 4] [3, 3] [2]
CALL factorial<<2>> -> 10
[] [6, 6] [5, 5] [4, 4] [3, 3] [2]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2]
LIT 2
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2, 2]
BOP <
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 0]
FALSEBRANCH else<<4>> -> 19
[] [6, 6] [5, 5] [4, 4] [3, 3] [2]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2, 2]
LIT 1
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2, 2, 1]
BOP -
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2, 1]
ARGS 1
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2] [1]
CALL factorial<<2>> -> 10
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2] [1]
LOAD 0 n <load n>
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2] [1, 1]
LIT 2
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2] [1, 1, 2]
BOP <
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2] [1, 1]
FALSEBRANCH else<<4>> -> 19
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2] [1]
LIT 1
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2] [1, 1]
RETURN factorial<<2>> EXIT factorial<<2>>
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2, 1]
BOP *
[] [6, 6] [5, 5] [4, 4] [3, 3] [2, 2]
RETURN factorial<<2>> EXIT factorial<<2>>
[] [6, 6] [5, 5] [4, 4] [3, 3, 2]
BOP *
[] [6, 6] [5, 5] [4, 4] [3, 6]
RETURN factorial<<2>> EXIT factorial<<2>>
[] [6, 6] [5, 5] [4, 4, 6]
BOP *
[] [6, 6] [5, 5] [4, 24]
RETURN factorial<<2>> EXIT factorial<<2>>
[] [6, 6] [5, 5, 24]
BOP *
[] [6, 6] [5, 120]
RETURN factorial<<2>> EXIT factorial<<2>>
[] [6, 6, 120]
BOP *
[] [6, 720]
RETURN factorial<<2>> EXIT factorial<<2>>
[720]
ARGS 1
[] [720]
CALL Write -> 4
[] [720]
LOAD 0 dummyFormal <load dummyFormal>
[] [720, 720]
720
WRITE
[] [720, 720]
RETURN 
[720]
POP 3
[]

[]

~~~
    


## Fib Verbose -- Input : 6



~~~bash
VERBOSE ON
[]
GOTO start<<1>> -> 9
[]
LIT 0 x <load x>
[0]
GOTO continue<<3>> -> 50
[0]
LIT 0 k <load k>
[0, 0]
LIT 5
[0, 0, 5]
STORE 0 x <store x>
[5, 0]
ARGS 0
[5, 0] []
CALL Read -> 2
[5, 0] []
Please enter an integer: READ
[5, 0] [6]
RETURN 
[5, 0, 6]
ARGS 1
[5, 0] [6]
CALL fib<<2>> -> 12
[5, 0] [6]
LOAD 0 n <load n>
[5, 0] [6, 6]
LIT 1
[5, 0] [6, 6, 1]
BOP <=
[5, 0] [6, 0]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6]
LOAD 0 n <load n>
[5, 0] [6, 6]
LIT 2
[5, 0] [6, 6, 2]
BOP ==
[5, 0] [6, 0]
FALSEBRANCH else<<6>> -> 30
[5, 0] [6]
LOAD 0 n <load n>
[5, 0] [6, 6]
LIT 2
[5, 0] [6, 6, 2]
BOP -
[5, 0] [6, 4]
ARGS 1
[5, 0] [6] [4]
CALL fib<<2>> -> 12
[5, 0] [6] [4]
LOAD 0 n <load n>
[5, 0] [6] [4, 4]
LIT 1
[5, 0] [6] [4, 4, 1]
BOP <=
[5, 0] [6] [4, 0]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6] [4]
LOAD 0 n <load n>
[5, 0] [6] [4, 4]
LIT 2
[5, 0] [6] [4, 4, 2]
BOP ==
[5, 0] [6] [4, 0]
FALSEBRANCH else<<6>> -> 30
[5, 0] [6] [4]
LOAD 0 n <load n>
[5, 0] [6] [4, 4]
LIT 2
[5, 0] [6] [4, 4, 2]
BOP -
[5, 0] [6] [4, 2]
ARGS 1
[5, 0] [6] [4] [2]
CALL fib<<2>> -> 12
[5, 0] [6] [4] [2]
LOAD 0 n <load n>
[5, 0] [6] [4] [2, 2]
LIT 1
[5, 0] [6] [4] [2, 2, 1]
BOP <=
[5, 0] [6] [4] [2, 0]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6] [4] [2]
LOAD 0 n <load n>
[5, 0] [6] [4] [2, 2]
LIT 2
[5, 0] [6] [4] [2, 2, 2]
BOP ==
[5, 0] [6] [4] [2, 1]
FALSEBRANCH else<<6>> -> 30
[5, 0] [6] [4] [2]
LIT 1
[5, 0] [6] [4] [2, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6] [4, 1]
LOAD 0 n <load n>
[5, 0] [6] [4, 1, 4]
LIT 1
[5, 0] [6] [4, 1, 4, 1]
BOP -
[5, 0] [6] [4, 1, 3]
ARGS 1
[5, 0] [6] [4, 1] [3]
CALL fib<<2>> -> 12
[5, 0] [6] [4, 1] [3]
LOAD 0 n <load n>
[5, 0] [6] [4, 1] [3, 3]
LIT 1
[5, 0] [6] [4, 1] [3, 3, 1]
BOP <=
[5, 0] [6] [4, 1] [3, 0]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6] [4, 1] [3]
LOAD 0 n <load n>
[5, 0] [6] [4, 1] [3, 3]
LIT 2
[5, 0] [6] [4, 1] [3, 3, 2]
BOP ==
[5, 0] [6] [4, 1] [3, 0]
FALSEBRANCH else<<6>> -> 30
[5, 0] [6] [4, 1] [3]
LOAD 0 n <load n>
[5, 0] [6] [4, 1] [3, 3]
LIT 2
[5, 0] [6] [4, 1] [3, 3, 2]
BOP -
[5, 0] [6] [4, 1] [3, 1]
ARGS 1
[5, 0] [6] [4, 1] [3] [1]
CALL fib<<2>> -> 12
[5, 0] [6] [4, 1] [3] [1]
LOAD 0 n <load n>
[5, 0] [6] [4, 1] [3] [1, 1]
LIT 1
[5, 0] [6] [4, 1] [3] [1, 1, 1]
BOP <=
[5, 0] [6] [4, 1] [3] [1, 1]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6] [4, 1] [3] [1]
LIT 1
[5, 0] [6] [4, 1] [3] [1, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6] [4, 1] [3, 1]
LOAD 0 n <load n>
[5, 0] [6] [4, 1] [3, 1, 3]
LIT 1
[5, 0] [6] [4, 1] [3, 1, 3, 1]
BOP -
[5, 0] [6] [4, 1] [3, 1, 2]
ARGS 1
[5, 0] [6] [4, 1] [3, 1] [2]
CALL fib<<2>> -> 12
[5, 0] [6] [4, 1] [3, 1] [2]
LOAD 0 n <load n>
[5, 0] [6] [4, 1] [3, 1] [2, 2]
LIT 1
[5, 0] [6] [4, 1] [3, 1] [2, 2, 1]
BOP <=
[5, 0] [6] [4, 1] [3, 1] [2, 0]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6] [4, 1] [3, 1] [2]
LOAD 0 n <load n>
[5, 0] [6] [4, 1] [3, 1] [2, 2]
LIT 2
[5, 0] [6] [4, 1] [3, 1] [2, 2, 2]
BOP ==
[5, 0] [6] [4, 1] [3, 1] [2, 1]
FALSEBRANCH else<<6>> -> 30
[5, 0] [6] [4, 1] [3, 1] [2]
LIT 1
[5, 0] [6] [4, 1] [3, 1] [2, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6] [4, 1] [3, 1, 1]
BOP +
[5, 0] [6] [4, 1] [3, 2]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6] [4, 1, 2]
BOP +
[5, 0] [6] [4, 3]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3]
LOAD 0 n <load n>
[5, 0] [6, 3, 6]
LIT 1
[5, 0] [6, 3, 6, 1]
BOP -
[5, 0] [6, 3, 5]
ARGS 1
[5, 0] [6, 3] [5]
CALL fib<<2>> -> 12
[5, 0] [6, 3] [5]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 5]
LIT 1
[5, 0] [6, 3] [5, 5, 1]
BOP <=
[5, 0] [6, 3] [5, 0]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6, 3] [5]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 5]
LIT 2
[5, 0] [6, 3] [5, 5, 2]
BOP ==
[5, 0] [6, 3] [5, 0]
FALSEBRANCH else<<6>> -> 30
[5, 0] [6, 3] [5]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 5]
LIT 2
[5, 0] [6, 3] [5, 5, 2]
BOP -
[5, 0] [6, 3] [5, 3]
ARGS 1
[5, 0] [6, 3] [5] [3]
CALL fib<<2>> -> 12
[5, 0] [6, 3] [5] [3]
LOAD 0 n <load n>
[5, 0] [6, 3] [5] [3, 3]
LIT 1
[5, 0] [6, 3] [5] [3, 3, 1]
BOP <=
[5, 0] [6, 3] [5] [3, 0]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6, 3] [5] [3]
LOAD 0 n <load n>
[5, 0] [6, 3] [5] [3, 3]
LIT 2
[5, 0] [6, 3] [5] [3, 3, 2]
BOP ==
[5, 0] [6, 3] [5] [3, 0]
FALSEBRANCH else<<6>> -> 30
[5, 0] [6, 3] [5] [3]
LOAD 0 n <load n>
[5, 0] [6, 3] [5] [3, 3]
LIT 2
[5, 0] [6, 3] [5] [3, 3, 2]
BOP -
[5, 0] [6, 3] [5] [3, 1]
ARGS 1
[5, 0] [6, 3] [5] [3] [1]
CALL fib<<2>> -> 12
[5, 0] [6, 3] [5] [3] [1]
LOAD 0 n <load n>
[5, 0] [6, 3] [5] [3] [1, 1]
LIT 1
[5, 0] [6, 3] [5] [3] [1, 1, 1]
BOP <=
[5, 0] [6, 3] [5] [3] [1, 1]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6, 3] [5] [3] [1]
LIT 1
[5, 0] [6, 3] [5] [3] [1, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5] [3, 1]
LOAD 0 n <load n>
[5, 0] [6, 3] [5] [3, 1, 3]
LIT 1
[5, 0] [6, 3] [5] [3, 1, 3, 1]
BOP -
[5, 0] [6, 3] [5] [3, 1, 2]
ARGS 1
[5, 0] [6, 3] [5] [3, 1] [2]
CALL fib<<2>> -> 12
[5, 0] [6, 3] [5] [3, 1] [2]
LOAD 0 n <load n>
[5, 0] [6, 3] [5] [3, 1] [2, 2]
LIT 1
[5, 0] [6, 3] [5] [3, 1] [2, 2, 1]
BOP <=
[5, 0] [6, 3] [5] [3, 1] [2, 0]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6, 3] [5] [3, 1] [2]
LOAD 0 n <load n>
[5, 0] [6, 3] [5] [3, 1] [2, 2]
LIT 2
[5, 0] [6, 3] [5] [3, 1] [2, 2, 2]
BOP ==
[5, 0] [6, 3] [5] [3, 1] [2, 1]
FALSEBRANCH else<<6>> -> 30
[5, 0] [6, 3] [5] [3, 1] [2]
LIT 1
[5, 0] [6, 3] [5] [3, 1] [2, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5] [3, 1, 1]
BOP +
[5, 0] [6, 3] [5] [3, 2]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5, 2]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2, 5]
LIT 1
[5, 0] [6, 3] [5, 2, 5, 1]
BOP -
[5, 0] [6, 3] [5, 2, 4]
ARGS 1
[5, 0] [6, 3] [5, 2] [4]
CALL fib<<2>> -> 12
[5, 0] [6, 3] [5, 2] [4]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 4]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 4, 1]
BOP <=
[5, 0] [6, 3] [5, 2] [4, 0]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6, 3] [5, 2] [4]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 4]
LIT 2
[5, 0] [6, 3] [5, 2] [4, 4, 2]
BOP ==
[5, 0] [6, 3] [5, 2] [4, 0]
FALSEBRANCH else<<6>> -> 30
[5, 0] [6, 3] [5, 2] [4]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 4]
LIT 2
[5, 0] [6, 3] [5, 2] [4, 4, 2]
BOP -
[5, 0] [6, 3] [5, 2] [4, 2]
ARGS 1
[5, 0] [6, 3] [5, 2] [4] [2]
CALL fib<<2>> -> 12
[5, 0] [6, 3] [5, 2] [4] [2]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4] [2, 2]
LIT 1
[5, 0] [6, 3] [5, 2] [4] [2, 2, 1]
BOP <=
[5, 0] [6, 3] [5, 2] [4] [2, 0]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6, 3] [5, 2] [4] [2]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4] [2, 2]
LIT 2
[5, 0] [6, 3] [5, 2] [4] [2, 2, 2]
BOP ==
[5, 0] [6, 3] [5, 2] [4] [2, 1]
FALSEBRANCH else<<6>> -> 30
[5, 0] [6, 3] [5, 2] [4] [2]
LIT 1
[5, 0] [6, 3] [5, 2] [4] [2, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5, 2] [4, 1]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1, 4]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 1, 4, 1]
BOP -
[5, 0] [6, 3] [5, 2] [4, 1, 3]
ARGS 1
[5, 0] [6, 3] [5, 2] [4, 1] [3]
CALL fib<<2>> -> 12
[5, 0] [6, 3] [5, 2] [4, 1] [3]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 3]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 1] [3, 3, 1]
BOP <=
[5, 0] [6, 3] [5, 2] [4, 1] [3, 0]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6, 3] [5, 2] [4, 1] [3]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 3]
LIT 2
[5, 0] [6, 3] [5, 2] [4, 1] [3, 3, 2]
BOP ==
[5, 0] [6, 3] [5, 2] [4, 1] [3, 0]
FALSEBRANCH else<<6>> -> 30
[5, 0] [6, 3] [5, 2] [4, 1] [3]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 3]
LIT 2
[5, 0] [6, 3] [5, 2] [4, 1] [3, 3, 2]
BOP -
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1]
ARGS 1
[5, 0] [6, 3] [5, 2] [4, 1] [3] [1]
CALL fib<<2>> -> 12
[5, 0] [6, 3] [5, 2] [4, 1] [3] [1]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1] [3] [1, 1]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 1] [3] [1, 1, 1]
BOP <=
[5, 0] [6, 3] [5, 2] [4, 1] [3] [1, 1]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6, 3] [5, 2] [4, 1] [3] [1]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 1] [3] [1, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1, 3]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1, 3, 1]
BOP -
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1, 2]
ARGS 1
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2]
CALL fib<<2>> -> 12
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2, 2]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2, 2, 1]
BOP <=
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2, 0]
FALSEBRANCH else<<4>> -> 21
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2, 2]
LIT 2
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2, 2, 2]
BOP ==
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2, 1]
FALSEBRANCH else<<6>> -> 30
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1, 1]
BOP +
[5, 0] [6, 3] [5, 2] [4, 1] [3, 2]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5, 2] [4, 1, 2]
BOP +
[5, 0] [6, 3] [5, 2] [4, 3]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5, 2, 3]
BOP +
[5, 0] [6, 3] [5, 5]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3, 5]
BOP +
[5, 0] [6, 8]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0, 8]
ARGS 1
[5, 0] [8]
CALL Write -> 5
[5, 0] [8]
LOAD 0 dummyFormal <load dummyFormal>
[5, 0] [8, 8]
8
WRITE
[5, 0] [8, 8]
RETURN 
[5, 0, 8]
STORE 1 k <store k>
[5, 8]
LIT 0 x <load x>
[5, 8, 0]
LIT 7
[5, 8, 0, 7]
STORE 2 x <store x>
[5, 8, 7]
LIT 8
[5, 8, 7, 8]
STORE 2 x <store x>
[5, 8, 8]
POP 1
[5, 8]
POP 2
[5]

[5]

~~~
    


## Fib -- Input : 6



~~~bash
GOTO start<<1>> -> 8
[]
LIT 0 x <load x>
[0]
GOTO continue<<3>> -> 49
[0]
LIT 0 k <load k>
[0, 0]
LIT 5
[0, 0, 5]
STORE 0 x <store x>
[5, 0]
ARGS 0
[5, 0] []
CALL Read -> 1
[5, 0] []
Please enter an integer: READ
[5, 0] [6]
RETURN 
[5, 0, 6]
ARGS 1
[5, 0] [6]
CALL fib<<2>> -> 11
[5, 0] [6]
LOAD 0 n <load n>
[5, 0] [6, 6]
LIT 1
[5, 0] [6, 6, 1]
BOP <=
[5, 0] [6, 0]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6]
LOAD 0 n <load n>
[5, 0] [6, 6]
LIT 2
[5, 0] [6, 6, 2]
BOP ==
[5, 0] [6, 0]
FALSEBRANCH else<<6>> -> 29
[5, 0] [6]
LOAD 0 n <load n>
[5, 0] [6, 6]
LIT 2
[5, 0] [6, 6, 2]
BOP -
[5, 0] [6, 4]
ARGS 1
[5, 0] [6] [4]
CALL fib<<2>> -> 11
[5, 0] [6] [4]
LOAD 0 n <load n>
[5, 0] [6] [4, 4]
LIT 1
[5, 0] [6] [4, 4, 1]
BOP <=
[5, 0] [6] [4, 0]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6] [4]
LOAD 0 n <load n>
[5, 0] [6] [4, 4]
LIT 2
[5, 0] [6] [4, 4, 2]
BOP ==
[5, 0] [6] [4, 0]
FALSEBRANCH else<<6>> -> 29
[5, 0] [6] [4]
LOAD 0 n <load n>
[5, 0] [6] [4, 4]
LIT 2
[5, 0] [6] [4, 4, 2]
BOP -
[5, 0] [6] [4, 2]
ARGS 1
[5, 0] [6] [4] [2]
CALL fib<<2>> -> 11
[5, 0] [6] [4] [2]
LOAD 0 n <load n>
[5, 0] [6] [4] [2, 2]
LIT 1
[5, 0] [6] [4] [2, 2, 1]
BOP <=
[5, 0] [6] [4] [2, 0]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6] [4] [2]
LOAD 0 n <load n>
[5, 0] [6] [4] [2, 2]
LIT 2
[5, 0] [6] [4] [2, 2, 2]
BOP ==
[5, 0] [6] [4] [2, 1]
FALSEBRANCH else<<6>> -> 29
[5, 0] [6] [4] [2]
LIT 1
[5, 0] [6] [4] [2, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6] [4, 1]
LOAD 0 n <load n>
[5, 0] [6] [4, 1, 4]
LIT 1
[5, 0] [6] [4, 1, 4, 1]
BOP -
[5, 0] [6] [4, 1, 3]
ARGS 1
[5, 0] [6] [4, 1] [3]
CALL fib<<2>> -> 11
[5, 0] [6] [4, 1] [3]
LOAD 0 n <load n>
[5, 0] [6] [4, 1] [3, 3]
LIT 1
[5, 0] [6] [4, 1] [3, 3, 1]
BOP <=
[5, 0] [6] [4, 1] [3, 0]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6] [4, 1] [3]
LOAD 0 n <load n>
[5, 0] [6] [4, 1] [3, 3]
LIT 2
[5, 0] [6] [4, 1] [3, 3, 2]
BOP ==
[5, 0] [6] [4, 1] [3, 0]
FALSEBRANCH else<<6>> -> 29
[5, 0] [6] [4, 1] [3]
LOAD 0 n <load n>
[5, 0] [6] [4, 1] [3, 3]
LIT 2
[5, 0] [6] [4, 1] [3, 3, 2]
BOP -
[5, 0] [6] [4, 1] [3, 1]
ARGS 1
[5, 0] [6] [4, 1] [3] [1]
CALL fib<<2>> -> 11
[5, 0] [6] [4, 1] [3] [1]
LOAD 0 n <load n>
[5, 0] [6] [4, 1] [3] [1, 1]
LIT 1
[5, 0] [6] [4, 1] [3] [1, 1, 1]
BOP <=
[5, 0] [6] [4, 1] [3] [1, 1]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6] [4, 1] [3] [1]
LIT 1
[5, 0] [6] [4, 1] [3] [1, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6] [4, 1] [3, 1]
LOAD 0 n <load n>
[5, 0] [6] [4, 1] [3, 1, 3]
LIT 1
[5, 0] [6] [4, 1] [3, 1, 3, 1]
BOP -
[5, 0] [6] [4, 1] [3, 1, 2]
ARGS 1
[5, 0] [6] [4, 1] [3, 1] [2]
CALL fib<<2>> -> 11
[5, 0] [6] [4, 1] [3, 1] [2]
LOAD 0 n <load n>
[5, 0] [6] [4, 1] [3, 1] [2, 2]
LIT 1
[5, 0] [6] [4, 1] [3, 1] [2, 2, 1]
BOP <=
[5, 0] [6] [4, 1] [3, 1] [2, 0]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6] [4, 1] [3, 1] [2]
LOAD 0 n <load n>
[5, 0] [6] [4, 1] [3, 1] [2, 2]
LIT 2
[5, 0] [6] [4, 1] [3, 1] [2, 2, 2]
BOP ==
[5, 0] [6] [4, 1] [3, 1] [2, 1]
FALSEBRANCH else<<6>> -> 29
[5, 0] [6] [4, 1] [3, 1] [2]
LIT 1
[5, 0] [6] [4, 1] [3, 1] [2, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6] [4, 1] [3, 1, 1]
BOP +
[5, 0] [6] [4, 1] [3, 2]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6] [4, 1, 2]
BOP +
[5, 0] [6] [4, 3]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3]
LOAD 0 n <load n>
[5, 0] [6, 3, 6]
LIT 1
[5, 0] [6, 3, 6, 1]
BOP -
[5, 0] [6, 3, 5]
ARGS 1
[5, 0] [6, 3] [5]
CALL fib<<2>> -> 11
[5, 0] [6, 3] [5]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 5]
LIT 1
[5, 0] [6, 3] [5, 5, 1]
BOP <=
[5, 0] [6, 3] [5, 0]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6, 3] [5]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 5]
LIT 2
[5, 0] [6, 3] [5, 5, 2]
BOP ==
[5, 0] [6, 3] [5, 0]
FALSEBRANCH else<<6>> -> 29
[5, 0] [6, 3] [5]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 5]
LIT 2
[5, 0] [6, 3] [5, 5, 2]
BOP -
[5, 0] [6, 3] [5, 3]
ARGS 1
[5, 0] [6, 3] [5] [3]
CALL fib<<2>> -> 11
[5, 0] [6, 3] [5] [3]
LOAD 0 n <load n>
[5, 0] [6, 3] [5] [3, 3]
LIT 1
[5, 0] [6, 3] [5] [3, 3, 1]
BOP <=
[5, 0] [6, 3] [5] [3, 0]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6, 3] [5] [3]
LOAD 0 n <load n>
[5, 0] [6, 3] [5] [3, 3]
LIT 2
[5, 0] [6, 3] [5] [3, 3, 2]
BOP ==
[5, 0] [6, 3] [5] [3, 0]
FALSEBRANCH else<<6>> -> 29
[5, 0] [6, 3] [5] [3]
LOAD 0 n <load n>
[5, 0] [6, 3] [5] [3, 3]
LIT 2
[5, 0] [6, 3] [5] [3, 3, 2]
BOP -
[5, 0] [6, 3] [5] [3, 1]
ARGS 1
[5, 0] [6, 3] [5] [3] [1]
CALL fib<<2>> -> 11
[5, 0] [6, 3] [5] [3] [1]
LOAD 0 n <load n>
[5, 0] [6, 3] [5] [3] [1, 1]
LIT 1
[5, 0] [6, 3] [5] [3] [1, 1, 1]
BOP <=
[5, 0] [6, 3] [5] [3] [1, 1]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6, 3] [5] [3] [1]
LIT 1
[5, 0] [6, 3] [5] [3] [1, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5] [3, 1]
LOAD 0 n <load n>
[5, 0] [6, 3] [5] [3, 1, 3]
LIT 1
[5, 0] [6, 3] [5] [3, 1, 3, 1]
BOP -
[5, 0] [6, 3] [5] [3, 1, 2]
ARGS 1
[5, 0] [6, 3] [5] [3, 1] [2]
CALL fib<<2>> -> 11
[5, 0] [6, 3] [5] [3, 1] [2]
LOAD 0 n <load n>
[5, 0] [6, 3] [5] [3, 1] [2, 2]
LIT 1
[5, 0] [6, 3] [5] [3, 1] [2, 2, 1]
BOP <=
[5, 0] [6, 3] [5] [3, 1] [2, 0]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6, 3] [5] [3, 1] [2]
LOAD 0 n <load n>
[5, 0] [6, 3] [5] [3, 1] [2, 2]
LIT 2
[5, 0] [6, 3] [5] [3, 1] [2, 2, 2]
BOP ==
[5, 0] [6, 3] [5] [3, 1] [2, 1]
FALSEBRANCH else<<6>> -> 29
[5, 0] [6, 3] [5] [3, 1] [2]
LIT 1
[5, 0] [6, 3] [5] [3, 1] [2, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5] [3, 1, 1]
BOP +
[5, 0] [6, 3] [5] [3, 2]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5, 2]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2, 5]
LIT 1
[5, 0] [6, 3] [5, 2, 5, 1]
BOP -
[5, 0] [6, 3] [5, 2, 4]
ARGS 1
[5, 0] [6, 3] [5, 2] [4]
CALL fib<<2>> -> 11
[5, 0] [6, 3] [5, 2] [4]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 4]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 4, 1]
BOP <=
[5, 0] [6, 3] [5, 2] [4, 0]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6, 3] [5, 2] [4]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 4]
LIT 2
[5, 0] [6, 3] [5, 2] [4, 4, 2]
BOP ==
[5, 0] [6, 3] [5, 2] [4, 0]
FALSEBRANCH else<<6>> -> 29
[5, 0] [6, 3] [5, 2] [4]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 4]
LIT 2
[5, 0] [6, 3] [5, 2] [4, 4, 2]
BOP -
[5, 0] [6, 3] [5, 2] [4, 2]
ARGS 1
[5, 0] [6, 3] [5, 2] [4] [2]
CALL fib<<2>> -> 11
[5, 0] [6, 3] [5, 2] [4] [2]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4] [2, 2]
LIT 1
[5, 0] [6, 3] [5, 2] [4] [2, 2, 1]
BOP <=
[5, 0] [6, 3] [5, 2] [4] [2, 0]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6, 3] [5, 2] [4] [2]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4] [2, 2]
LIT 2
[5, 0] [6, 3] [5, 2] [4] [2, 2, 2]
BOP ==
[5, 0] [6, 3] [5, 2] [4] [2, 1]
FALSEBRANCH else<<6>> -> 29
[5, 0] [6, 3] [5, 2] [4] [2]
LIT 1
[5, 0] [6, 3] [5, 2] [4] [2, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5, 2] [4, 1]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1, 4]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 1, 4, 1]
BOP -
[5, 0] [6, 3] [5, 2] [4, 1, 3]
ARGS 1
[5, 0] [6, 3] [5, 2] [4, 1] [3]
CALL fib<<2>> -> 11
[5, 0] [6, 3] [5, 2] [4, 1] [3]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 3]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 1] [3, 3, 1]
BOP <=
[5, 0] [6, 3] [5, 2] [4, 1] [3, 0]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6, 3] [5, 2] [4, 1] [3]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 3]
LIT 2
[5, 0] [6, 3] [5, 2] [4, 1] [3, 3, 2]
BOP ==
[5, 0] [6, 3] [5, 2] [4, 1] [3, 0]
FALSEBRANCH else<<6>> -> 29
[5, 0] [6, 3] [5, 2] [4, 1] [3]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 3]
LIT 2
[5, 0] [6, 3] [5, 2] [4, 1] [3, 3, 2]
BOP -
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1]
ARGS 1
[5, 0] [6, 3] [5, 2] [4, 1] [3] [1]
CALL fib<<2>> -> 11
[5, 0] [6, 3] [5, 2] [4, 1] [3] [1]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1] [3] [1, 1]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 1] [3] [1, 1, 1]
BOP <=
[5, 0] [6, 3] [5, 2] [4, 1] [3] [1, 1]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6, 3] [5, 2] [4, 1] [3] [1]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 1] [3] [1, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1, 3]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1, 3, 1]
BOP -
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1, 2]
ARGS 1
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2]
CALL fib<<2>> -> 11
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2, 2]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2, 2, 1]
BOP <=
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2, 0]
FALSEBRANCH else<<4>> -> 20
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2]
LOAD 0 n <load n>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2, 2]
LIT 2
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2, 2, 2]
BOP ==
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2, 1]
FALSEBRANCH else<<6>> -> 29
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2]
LIT 1
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1] [2, 1]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5, 2] [4, 1] [3, 1, 1]
BOP +
[5, 0] [6, 3] [5, 2] [4, 1] [3, 2]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5, 2] [4, 1, 2]
BOP +
[5, 0] [6, 3] [5, 2] [4, 3]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3] [5, 2, 3]
BOP +
[5, 0] [6, 3] [5, 5]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0] [6, 3, 5]
BOP +
[5, 0] [6, 8]
RETURN fib<<2>> EXIT fib<<2>>
[5, 0, 8]
ARGS 1
[5, 0] [8]
CALL Write -> 4
[5, 0] [8]
LOAD 0 dummyFormal <load dummyFormal>
[5, 0] [8, 8]
8
WRITE
[5, 0] [8, 8]
RETURN 
[5, 0, 8]
STORE 1 k <store k>
[5, 8]
LIT 0 x <load x>
[5, 8, 0]
LIT 7
[5, 8, 0, 7]
STORE 2 x <store x>
[5, 8, 7]
LIT 8
[5, 8, 7, 8]
STORE 2 x <store x>
[5, 8, 8]
POP 1
[5, 8]
POP 2
[5]

[5]

~~~
    


## Function Args Verbose Test



~~~bash
VERBOSE ON
[]
GOTO CONTINUE<<1>> -> 24
[]
GOTO CONTINUE<<2>> -> 2
[]
GOTO CONTINUE<<3>> -> 26
[]
GOTO CONTINUE<<4>> -> 4
[]
LIT 0
[0]
LIT 1
[0, 1]
ARGS 2
[] [0, 1]
CALL doublePrint<<1>> -> 28
[] [0, 1]
LOAD 0
[] [0, 1, 0]
0
WRITE
[] [0, 1, 0]
POP 1
[] [0, 1]
LOAD 1
[] [0, 1, 1]
1
WRITE
[] [0, 1, 1]
POP 1
[] [0, 1]
RETURN doublePrint<<1>> EXIT doublePrint<<1>>
[1]
POP 1
[]
LIT 0
[0]
LIT 1
[0, 1]
LIT 2
[0, 1, 2]
ARGS 3
[] [0, 1, 2]
CALL triplePrint<<1>> -> 36
[] [0, 1, 2]
LOAD 0
[] [0, 1, 2, 0]
0
WRITE
[] [0, 1, 2, 0]
POP 1
[] [0, 1, 2]
LOAD 1
[] [0, 1, 2, 1]
1
WRITE
[] [0, 1, 2, 1]
POP 1
[] [0, 1, 2]
LOAD 2
[] [0, 1, 2, 2]
2
WRITE
[] [0, 1, 2, 2]
POP 2
[] [0, 1, 2]
RETURN triplePrint<<1>> EXIT triplePrint<<1>>
[2]
POP 1
[]
LIT 0
[0]
LIT 1
[0, 1]
LIT 2
[0, 1, 2]
LIT 3
[0, 1, 2, 3]
ARGS 4
[] [0, 1, 2, 3]
CALL quadruplePrint<<1>> -> 47
[] [0, 1, 2, 3]
LOAD 0
[] [0, 1, 2, 3, 0]
0
WRITE
[] [0, 1, 2, 3, 0]
POP 1
[] [0, 1, 2, 3]
LOAD 1
[] [0, 1, 2, 3, 1]
1
WRITE
[] [0, 1, 2, 3, 1]
POP 1
[] [0, 1, 2, 3]
LOAD 2
[] [0, 1, 2, 3, 2]
2
WRITE
[] [0, 1, 2, 3, 2]
POP 1
[] [0, 1, 2, 3]
LOAD 3
[] [0, 1, 2, 3, 3]
3
WRITE
[] [0, 1, 2, 3, 3]
POP 1
[] [0, 1, 2, 3]
RETURN quadruplePrint<<1>> EXIT quadruplePrint<<1>>
[3]
POP 66
[]

[]

~~~
    


## Function Args Test



~~~bash
GOTO CONTINUE<<1>> -> 23
[]
GOTO CONTINUE<<2>> -> 1
[]
GOTO CONTINUE<<3>> -> 25
[]
GOTO CONTINUE<<4>> -> 3
[]
LIT 0
[0]
LIT 1
[0, 1]
ARGS 2
[] [0, 1]
CALL doublePrint<<1>> -> 27
[] [0, 1]
LOAD 0
[] [0, 1, 0]
0
WRITE
[] [0, 1, 0]
POP 1
[] [0, 1]
LOAD 1
[] [0, 1, 1]
1
WRITE
[] [0, 1, 1]
POP 1
[] [0, 1]
RETURN doublePrint<<1>> EXIT doublePrint<<1>>
[1]
POP 1
[]
LIT 0
[0]
LIT 1
[0, 1]
LIT 2
[0, 1, 2]
ARGS 3
[] [0, 1, 2]
CALL triplePrint<<1>> -> 35
[] [0, 1, 2]
LOAD 0
[] [0, 1, 2, 0]
0
WRITE
[] [0, 1, 2, 0]
POP 1
[] [0, 1, 2]
LOAD 1
[] [0, 1, 2, 1]
1
WRITE
[] [0, 1, 2, 1]
POP 1
[] [0, 1, 2]
LOAD 2
[] [0, 1, 2, 2]
2
WRITE
[] [0, 1, 2, 2]
POP 2
[] [0, 1, 2]
RETURN triplePrint<<1>> EXIT triplePrint<<1>>
[2]
POP 1
[]
LIT 0
[0]
LIT 1
[0, 1]
LIT 2
[0, 1, 2]
LIT 3
[0, 1, 2, 3]
ARGS 4
[] [0, 1, 2, 3]
CALL quadruplePrint<<1>> -> 46
[] [0, 1, 2, 3]
LOAD 0
[] [0, 1, 2, 3, 0]
0
WRITE
[] [0, 1, 2, 3, 0]
POP 1
[] [0, 1, 2, 3]
LOAD 1
[] [0, 1, 2, 3, 1]
1
WRITE
[] [0, 1, 2, 3, 1]
POP 1
[] [0, 1, 2, 3]
LOAD 2
[] [0, 1, 2, 3, 2]
2
WRITE
[] [0, 1, 2, 3, 2]
POP 1
[] [0, 1, 2, 3]
LOAD 3
[] [0, 1, 2, 3, 3]
3
WRITE
[] [0, 1, 2, 3, 3]
POP 1
[] [0, 1, 2, 3]
RETURN quadruplePrint<<1>> EXIT quadruplePrint<<1>>
[3]
POP 66
[]

[]

~~~
    
