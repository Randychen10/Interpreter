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
