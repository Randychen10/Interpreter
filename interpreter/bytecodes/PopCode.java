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
