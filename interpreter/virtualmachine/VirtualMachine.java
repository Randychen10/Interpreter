package interpreter.virtualmachine;

import java.util.Stack;
import interpreter.bytecodes.ByteCode;
import interpreter.loaders.Program;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;
    private boolean        verboseMode;

    public VirtualMachine(Program program) {
        this.program = program;
        this.runTimeStack = new RunTimeStack();
        this.returnAddress = new Stack<>();
        this.programCounter = 0;
        this.verboseMode = true;
        this.isRunning = true;
    }

    // Executes the program once program is started
    public void executeProgram() {
        isRunning = true;

        while (isRunning) {
            ByteCode code = program.getCode(programCounter);
            code.execute(this);
            if (verboseMode) {
                System.out.println(code);
                System.out.println(runTimeStack.verboseDisplay());
            }
            programCounter++;
        }
    }

    // Turns verbose mode on or off
    public void setVerboseMode(Boolean mode) {
        this.verboseMode = mode;
    }

    // Stops the program when HaltCode is called
    public void haltExecution() {
        this.isRunning = false;
    }

    //
    public void setProgramCounter(int address) {
        this.programCounter = address;
    }

    public int getProgramCounter() {
        return this.programCounter;
    }

    // Calls the store method from the RTS
    public int store(int offset) {
        return this.runTimeStack.store(offset);
    }

    // Calls the load method from the RTS
    public int load(int offset) {
        return this.runTimeStack.load(offset);
    }

    // Calls the push method from the RTS
    public void push(int value) {
        this.runTimeStack.push(value);
    }

    // Pushes the return address
    public void pushReturnAddress(int address) {
        this.returnAddress.push(address);
    }

    // Pops the return address
    public int popReturnAddress() {
        return returnAddress.pop();
    }

    // Calls the peek method from the RTS
    public int peek() {
       return this.runTimeStack.peek();
    }

    // Calls the popFrame method from the RTS
    public void popFrame() {
        this.runTimeStack.popFrame();
    }

    // Calls the pop method from the RTS
    public int popRunStack() {
        return this.runTimeStack.pop();
    }

    // Calls the push method from the RTS
    public void pushRunStack(int value) {
        this.runTimeStack.push(value);
    }

    // Calls newFrame method from the RTS
    public void newFrame(int numArgs) {
        this.runTimeStack.newFrameAt(numArgs);
    }
}
