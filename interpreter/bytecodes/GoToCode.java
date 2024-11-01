package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class GoToCode extends ByteCode {
    private String label;
    private int labelAddress;

    @Override
    public void init(List<String> args) {
        this.label = args.get(0);
    }

    public String getLabel() {
        return label;
    }

    public void setTargetAddress(int address) {
        this.labelAddress = address;
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setProgramCounter(labelAddress);
    }

    @Override
    public String toString() {
        return "GOTO " + label + " -> " + labelAddress;
    }
}
