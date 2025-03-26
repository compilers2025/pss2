# 📖 Assignment README - Simple Arithmetic Language

## 🔍 Overview
This assignment implements a simple arithmetic language parser and code generator. The language supports basic addition and subtraction operations between **two single-digit operands** and checks if the result matches the value specified after the `=`.

## 📜 Updated EBNF Grammar
```ebnf
program      ::= expression "=" number ;
expression   ::= number ("+" | "-") number ;
number       ::= digit ;
digit        ::= "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" ;
```

## ✅ Explanation of the Grammar
1. **`program`**: A valid program consists of an expression followed by an equal sign (`=`) and a single-digit number.
2. **`expression`**: Two numbers separated by either `+` or `-` operator. Example: `3 + 5`
3. **`number`**: A single digit from `0` to `9`.
4. **`digit`**: Valid characters are `0` to `9`.

## 🔍 Example Valid Input Code
```
3 + 5 = 8
2 - 1 = 1
9 - 4 = 5
```

## 📌 Description of Parsing and Code Generation
- The **Parser** evaluates the arithmetic expression involving **two operands only**.
- The **Code Generator** compares the calculated result with the provided result after the `=`.
- Outputs a success message if the calculation is correct, or an error message otherwise.

## 🚀 Usage Instructions
1. Implement the solutions according to the provided EBNF grammar.
2. Test with sample inputs provided above.
3. Display the output tokens and evaluation results.

## 📣 Additional Notes
- Ensure error handling for invalid inputs is properly implemented.
- Follow the EBNF specification strictly - only two operands are allowed per expression.

