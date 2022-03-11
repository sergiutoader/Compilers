.data

i:
	.word 20

.text

main:
	
	la $a0, i
	lw $t0, 0($a0)
	li $t1, 0

next_1:
	move $a0, $t0
	sw $a0, 0($sp)
	addiu $sp $sp -4

	sub $t0, 1

	ble $t1, $t0, next_1


la $a0, i
	lw $t0, 0($a0)
	li $t1, 0

next_2:
	addiu $sp $sp 4
	li $v0, 1
	lw $a0, 0($sp)
	syscall
	

	sub $t0, 1

	ble $t1, $t0, next_2


	li $v0, 10 					# exit
	syscall
