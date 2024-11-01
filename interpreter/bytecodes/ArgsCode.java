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
