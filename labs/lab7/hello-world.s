.data

str:
	.asciiz "Hello, World!"

.text

main:
	
	li $v0, 4 				# 4 = print_str syscall
	la $a0, str 			# load address of string
	syscall

	li $v0, 10 				# exit
	syscall
