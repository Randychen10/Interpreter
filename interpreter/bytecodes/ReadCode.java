package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;
import java.util.Scanner;
import java.util.List;

public class ReadCode extends ByteCode {

    @Override
    public void init(List<String> args) {
        // No arguments needed for Read bytecode
    }

    @Override
    public void execute(VirtualMachine vm) {
        Scanner scanner = new Scanner(System.in);
        int inputValue = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Please enter an integer: ");
            if (scanner.hasNextInt()) {
                inputValue = scanner.nextInt();
                validInput = true;
            } else {
                System.out.println("Invalid input. Please try again.");
                scanner.next();
            }
        }

        vm.push(inputValue);
    }

    @Override
    public String toString() {
        return "READ";
    }
}
