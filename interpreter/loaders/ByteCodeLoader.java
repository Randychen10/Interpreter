package interpreter.loaders;

import interpreter.bytecodes.ByteCode;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class ByteCodeLoader {
    private String codSourceFileName;

    /**
     * Constructs ByteCodeLoader object given a COD source code
     * file name
     *
     * @param fileName name of .cod File to load.
     */
    public ByteCodeLoader(String fileName) {
        this.codSourceFileName = fileName;
    }

    /**
     * Loads a program from a .cod file.
     *
     * @return a constructed Program Object.
     * @throws InvalidProgramException thrown when
     *                                 loadCodes fails.
     */
    public Program loadCodes() throws InvalidProgramException {
        final Program program = new Program(); // Create a new Program instance to hold the ByteCodes

        // Try-with-resources to automatically close the BufferedReader after use
        try (BufferedReader reader = new BufferedReader(new FileReader(this.codSourceFileName))) {

            // Read each line of the file while there is data available
            while (reader.ready()) {
                String[] items = reader.readLine().split("\\s+"); // Split the line into parts using whitespace as the delimiter
                String className = CodeTable.getClassName(items[0]); // Get the corresponding class name for the ByteCode

                // Dynamically load the ByteCode class and create an instance of it
                ByteCode bc = (ByteCode) Class.forName("interpreter.bytecodes." + className)
                        .getDeclaredConstructor().newInstance();

                // Initialize the ByteCode with its arguments (skipping the first item which is the ByteCode name)
                bc.init(Arrays.asList(items).subList(1, items.length));

                // Add the instantiated and initialized ByteCode to the program
                program.addCode(bc);
            }
        } catch (IOException | ClassNotFoundException | InvocationTargetException | InstantiationException
                 | IllegalAccessException | NoSuchMethodException ex) {
            // Catch any exceptions related to file I/O or reflection, and wrap them in an InvalidProgramException
            throw new InvalidProgramException(ex);
        }
        return program; // Return the constructed program containing all ByteCodes
    }
}
