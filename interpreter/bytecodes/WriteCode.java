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
