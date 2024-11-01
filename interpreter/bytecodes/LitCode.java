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
