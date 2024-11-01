package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;
import java.util.List;

public class LabelCode extends ByteCode {
    private String label;

    @Override
    public void init(List<String> args) {
        if (!args.isEmpty()) {
            this.label = args.get(0);
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
