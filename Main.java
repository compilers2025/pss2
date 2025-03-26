// Main.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Main <input file>");
            System.exit(1);
        }

        String filePath = args[0];

        try {
            String code = new String(Files.readAllBytes(Paths.get(filePath)));
            Scanner scanner = new Scanner(code);
            List<Token> tokens = scanner.scanTokens();
            
            Parser parser = new Parser(tokens);
            String asmCode = parser.parseProgram();

            String asmFilePath = "./output.s";
            Files.write(Paths.get(asmFilePath), asmCode.getBytes());

            System.out.println("Assembly code generated successfully.");

            // Assemble and Link the generated assembly file
            String outputFileName = asmFilePath.substring(0, asmFilePath.lastIndexOf('.'));
            assembleAndLink(outputFileName);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void assembleAndLink(String output) throws IOException, InterruptedException {
        Process assembleProcess = new ProcessBuilder("as", "-o", output + ".o", output + ".s").inheritIO().start();
        int assembleResult = assembleProcess.waitFor();

        if (assembleResult != 0) {
            System.err.println("Assembly process failed.");
            System.exit(1);
        }

        Process linkProcess = new ProcessBuilder("ld", "-o", output, output + ".o").inheritIO().start();
        int linkResult = linkProcess.waitFor();

        if (linkResult != 0) {
            System.err.println("Linking process failed.");
            System.exit(1);
        }

        System.out.println("Executable file generated successfully.");
    }
}
