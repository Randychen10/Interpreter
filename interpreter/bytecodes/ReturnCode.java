package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class ReturnCode extends ByteCode {
    private String label;

    @Override
    public void init(List<String> args) {
        if (!args.isEmpty()) {
            label = args.get(0);
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
