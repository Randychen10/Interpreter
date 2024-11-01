package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class VerboseCode extends ByteCode{
    private Boolean mode;
    private String value;

    public void init(List<String> args) {
        this.value = args.get(0);
    }

    public void execute(VirtualMachine vm) {
        if (value.equals("ON")){
            this.mode = true;
        }

        if (value.equals("OFF")){
            this.mode = false;
        }

        vm.setVerboseMode(mode);
    }

    public String toString(){
        String val = "VERBOSE " + value;

        return val;
    }
}
