.section .text
.globl _start
_start:
mov $3, %rax
mov $5, %rbx
add %rbx, %rax
mov %rax, %rdi
mov $8, %rbx
cmp %rbx, %rdi
jne not_equal
je end
not_equal:
mov $0, %rdi
end:
mov $60, %rax
syscall