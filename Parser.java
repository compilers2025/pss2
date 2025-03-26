import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int position = 0;
    private final CodeGenerator generator = new CodeGenerator();

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token current() {
        return tokens.get(position);
    }

    private Token consume() {
        return tokens.get(position++);
    }

    public String parseProgram() {
        generator.generateProgramHeader();
        parseExpression();
        generator.generateEnd();
        return generator.getCode();
    }

    private void parseExpression() {
        parseOperand();

        generator.generateOperand1(current().value);
        consume();

        Token mathOp = current();
        if (!isMathOp(mathOp)) throw new IllegalArgumentException("Expected math operation");
        consume();

        parseOperand();
        generator.generateOperand2(current().value);
        generator.generateMathOp(mathOp.value);

        consume();
        Token nextToken = current();

        if (nextToken == null || !nextToken.value.equals("=")) {
            throw new IllegalArgumentException("Expected '=' sign");
        }

        consume();
        parseOperand();
        generator.generateResult(current().value);
    }

    private void parseOperand() {
        Token token = current();
        if (token.type != TokenType.NUMBER) {
            throw new IllegalArgumentException("Expected a number operand");
        }
    }

    private boolean isMathOp(Token token) {
        return token.value.equals("+") || token.value.equals("-");
    }
}