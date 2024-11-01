package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class RunTimeStack {

    private List<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial frame pointer value, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    public String verboseDisplay(){
        /**
         * Used for displaying the current state of the runTimeStack .
         * It will print portions of the stack based on respective
         * frame markers .
         * Example [1 ,2 ,3] [4 ,5 ,6] [7 ,8]
         * Frame pointers would be 0 ,3 ,6
         */

        StringBuilder result = new StringBuilder();
        int previousFrame = 0;

        for (int i = 1; i < framePointer.size(); i++) {
            int currentFrame = framePointer.get(i);
            result.append(runTimeStack.subList(previousFrame, currentFrame)).append(" ");
            previousFrame = currentFrame;
        }

        result.append(runTimeStack.subList(previousFrame, runTimeStack.size()));
        return result.toString();
    }

    public int peek(){
        /**
         * returns the top of the runtime stack , but does not remove
         * @return copy of the top of the stack .
         */

        // Get the amount of items from the stack
        return this.runTimeStack.get(this.runTimeStack.size() - 1);
    }

    public int push(int value){
        /**
         * push the value i to the top of the stack .
         * @param i value to be pushed .
         * @return value pushed
         */

        // Adds an item to the stack
        this.runTimeStack.add(value);
        return value;
    }

    public int pop(){
        /**
         * removes to the top of the runtime stack .
         * @return the value popped .
         */

        // Removes one item from the stack
        return this.runTimeStack.remove(this.runTimeStack.size() - 1);
    }

    public int store(int offsetInFrame){
        /**
         * Takes the top item of the run time stack , and stores
         * it into a offset starting from the current frame .
         * @param offsetInFrame number of slots above current frame
         marker
         * @return the item just stored
         */

        // Stores an item into a offset in the current frame
        int location = framePointer.peek() + offsetInFrame;
        int value = runTimeStack.remove(runTimeStack.size() - 1);
        runTimeStack.set(location, value);
        return value;
    }

    public int load(int offsetInFrame){
        /**
         * Takes a value from the run time stack that is at offset
         * from the current frame marker and pushes it onto the top of
         * the stack .
         * @param offsetInFrame number of slots above current frame
         marker
         * @return item just loaded into the offset
         */

        int value = this.runTimeStack.get(framePointer.peek() + offsetInFrame);
        return push(value);
    }

    public void newFrameAt(int offsetFromTopOfRunStack){
        /**
         * create a new frame pointer at the index offset slots down
         * from the top of the runtime stack .
         * @param offsetFromTopOfRunStack slots down from the top of
         the runtime stack
         */

        framePointer.push(runTimeStack.size() - offsetFromTopOfRunStack);
    }

    public void popFrame() {
        /**
         * pop the current frame off the runtime stack . Also removes
         * the frame pointer value from the FramePointer Stack .
         */

        int start = framePointer.pop();

        while (runTimeStack.size() > start) {
            this.pop();
        }
    }

    public int size(){
        // Returns the size of the stack.
        return this.runTimeStack.size();
    }
}
