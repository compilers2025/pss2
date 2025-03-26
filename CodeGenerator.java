import java.util.ArrayList;
import java.util.List;


public class CodeGenerator {
    private final List<String> instructions = new ArrayList<>();

    public void generateProgramHeader() {
        instructions.add(".section .text");
        instructions.add(".globl _start");
        instructions.add("_start:");
    }

    public void generateOperand1(String operand) {
        instructions.add("mov $" + operand + ", %rax");
    }

    public void generateOperand2(String operand) {
        instructions.add("mov $" + operand + ", %rbx");
    }

    public void generateMathOp(String op) {
        if (op.equals("+")) {
            instructions.add("add %rbx, %rax");
        } else {
            instructions.add("sub %rbx, %rax");
        }
        instructions.add("mov %rax, %rdi");
    }

    public void generateResult(String result) {
        instructions.add("mov $" + result + ", %rbx");
        genrateCompare();
    }

    private void genrateCompare() {
        instructions.add("cmp %rbx, %rdi");
        instructions.add("jne not_equal");
        instructions.add("je end");
        genrateNotEqualLabel();
    }

    private void genrateNotEqualLabel() {
        instructions.add("not_equal:");
        instructions.add("mov $0, %rdi");
    }

    public void generateEnd() {
        instructions.add("end:");
        instructions.add("mov $60, %rax");
        instructions.add("syscall");
    }

    public String getCode() {
        return String.join("\n", instructions);
    }
}
