import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private final String input;
    private int position;
    private final char[] symbols = {'+', '-', '='};

    public Scanner(String input) {
        this.input = input;
        this.position = 0;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isSymbol(char c) {
        for (char symbol : symbols) {
            if (symbol == c) return true;
        }
        return false;
    }

    private char current() {
        if (position >= input.length()) return '\0';
        return input.charAt(position);
    }

    private char next() {
        if (position >= input.length()) return '\0';
        return input.charAt(position++);
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(current())) {
            next();
        }
    }

    public List<Token> scanTokens() {
        List<Token> tokens = new ArrayList<>();
        while (true) {
            skipWhitespace();
            char currentChar = current();
            if (currentChar == '\0') break;

            if (isDigit(currentChar)) {
                tokens.add(new Token(TokenType.NUMBER, String.valueOf(next())));
            } else if (isSymbol(currentChar)) {
                tokens.add(new Token(TokenType.SYMBOL, String.valueOf(next())));
            } else {
                tokens.add(new Token(TokenType.ILLEGAL, String.valueOf(next())));
            }
        }
        return tokens;
    }
}