
.data

one_str:
	.asciiz "1\n"

two_str:
	.asciiz "2\n"

large_str:
	.asciiz "Large value!\n"

small_str:
	.asciiz "Small value!\n"

N:
	.word 10

.text

main:
	
	li $v0, 4 					# 4 = print_str syscall
	la $a0, one_str 			# load address of string
	syscall

	la $a0, N
	lw $t0, 0($a0) 
	ble $t0, 64, small_value

	li $v0, 4 						# 4 = print_str syscall
	la $a0, large_str 				# load address of string
	syscall

	j skip

small_value:

	li $v0, 4 						# 4 = print_str syscall
	la $a0, small_str 				# load address of string
	syscall

skip:

	li $v0, 4 					# 4 = print_str syscall
	la $a0, two_str 			# load address of string
	syscall

	li $v0, 10 					# exit
	syscall
