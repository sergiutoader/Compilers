.data

one_str:
	.asciiz "1\n"

two_str:
	.asciiz "2\n"

str:
	.asciiz "Large value!\n"

N:
	.word 100

.text

main:
	
	li $v0, 4 					# 4 = print_str syscall
	la $a0, one_str 			# load address of string
	syscall

	la $a0, N
	lw $t0, 0($a0) 
	ble $t0, 64, skip

	li $v0, 4 					# 4 = print_str syscall
	la $a0, str 				# load address of string
	syscall


skip:

	li $v0, 4 					# 4 = print_str syscall
	la $a0, two_str 			# load address of string
	syscall

	li $v0, 10 					# exit
	syscall

