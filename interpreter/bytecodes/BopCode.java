package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class BopCode extends ByteCode {
    private String operator;  // The binary operator (e.g., +, -, *, /, etc.)

    /**
     * Initialize the BopCode with the binary operator.
     *
     * @param args The list of arguments passed to this bytecode. Expects the operator as the first argument.
     */
    @Override
    public void init(List<String> args) {
        // Check if an operator is provided and initialize
        if (args.size() > 0) {
            this.operator = args.get(0);  // Assuming the operator is the first argument
        } else {
            throw new IllegalArgumentException("Operator argument is missing.");
        }
    }

    /**
     * Execute the BopCode operation by performing the binary operation
     * on the top two elements of the runtime stack.
     *
     * @param vm The virtual machine instance on which this bytecode is executed.
     */
    @Override
    public void execute(VirtualMachine vm) {
        // Pop two operands from the runtime stack
        int rightOperand = vm.popRunStack();
        int leftOperand = vm.popRunStack();
        int result = 0;

        // Perform the binary operation based on the operator
        switch (operator) {
            case "+" -> result = leftOperand + rightOperand;
            case "-" -> result = leftOperand - rightOperand;
            case "*" -> result = leftOperand * rightOperand;
            case "/" -> {
                if (rightOperand == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = leftOperand / rightOperand;
            }
            case "==" -> result = (leftOperand == rightOperand) ? 1 : 0;
            case "!=" -> result = (leftOperand != rightOperand) ? 1 : 0;
            case "<" -> result = (leftOperand < rightOperand) ? 1 : 0;
            case ">" -> result = (leftOperand > rightOperand) ? 1 : 0;
            case "<=" -> result = (leftOperand <= rightOperand) ? 1 : 0;
            case ">=" -> result = (leftOperand >= rightOperand) ? 1 : 0;
            case "&" -> result = (leftOperand != 0 && rightOperand != 0) ? 1 : 0;  // Logical AND
            case "|" -> result = (leftOperand != 0 || rightOperand != 0) ? 1 : 0;  // Logical OR
            default -> throw new RuntimeException("Unknown operator: " + operator);
        }

        // Push the result of the operation back onto the stack
        vm.push(result);
    }

    /**
     * This is for verbose mode output where it shows the bytecode being executed.
     * It returns a string representation of the operation for the VERBOSE mode.
     *
     * @return The string format of the bytecode operation.
     */
    @Override
    public String toString() {
        return "BOP " + operator;
    }
}
