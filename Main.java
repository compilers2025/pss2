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
        System.out.println("filePath---->" + filePath);
        try {
            String code = new String(Files.readAllBytes(Paths.get(filePath)));
            Scanner scanner = new Scanner(code);
            List<Token> tokens = scanner.scanTokens();
            
            Parser parser = new Parser(tokens);
            String asmCode = parser.parseProgram();

            String asmFilePath = "./output.s";
            Files.write(Paths.get(asmFilePath), asmCode.getBytes());

            System.out.println("Assembly code generated successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}