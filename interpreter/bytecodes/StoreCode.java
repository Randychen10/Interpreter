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
