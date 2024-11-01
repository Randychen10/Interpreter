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