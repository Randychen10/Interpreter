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
