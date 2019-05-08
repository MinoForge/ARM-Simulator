########
#Author: Peter Gardner and Andy Silver
#Version: April 6, 2017
# This program may find the least common multiple or greatest common divisor of
# two specified integers using recursion.
########

.data

	enterOp: .asciz "Enter operation: 0 or 1, 2 to End >>>"
	enterTwo: .asciz "Enter any two positive integers"
	answer: .asciz "The answer is "
	newLine: .asciz "\n"
	a: .asciz "a >>> "
	b: .asciz "b >>> "
	
.text
########
#Register Legend
# $s0 First integer given by the user.  (a)
# $s1 Second integer given by the user. (b)
# $s2 'Temp' counter. Used to keep track of number of times through recursion
#	and is checked for the answer. Loaded with 1 before use.
# $s7 Used for temporary values
########

# Asks user to choose operation, then calls the respective procedure.
main: 
	#Asks user for input of 0, 1, or 2
    add r8, r31, #64
	ldr r0, enterOp
	syscall 
    
    add r8, r31, #64
	ldr r0, newLine
	syscall
			#Takes input
	add r8, r31, #63 
    syscall

	sub r0, r0, #2
	cbz r0, exit	#If user input is 2: Exit program

	#If user input is greater than 2 or less than 0: Repeat request for input

    add r9, r31, #2

	sub r10, r9, r0 #Sub num from 2: NEGATIVE if num>2
	lsr r10, r10, r8 #shift right 63: 1 if num>2
	cbnz r10, main

	lsr r10, r0, r8 #shift right 63: 1 if num<0
	cbnz r10, main


	#Prompt for the input to pass the procedure
	add r8, r31, #64
	ldr r0, enterTwo
	syscall
	add r8, r31, #64
	ldr r0, newLine
	syscall
	add r8, r31, #63		#Takes input for 'a'
	syscall

	add r0, r8, r31		#Stores 'a' in r0

	add r8, #63		#Takes input for 'b'
	syscall

	add $a1, r8, r31		#Store 'b' in $a1

	#Calls the procedure specified by the user
	bne $s0, $s7, call_two		#Note that $s7 is still 1 from line 45
	bl start_mystery1
	b main				#Start over after procedure finishes

#Calls second procedure
call_two:		
	bl start_mystery2
	b main				#Start over after procedure finishes


#Start of "Mystery One" procedure: Computes least common multiple of a and b
start_mystery1:
	#Decrement stack pointer and store registers.
	add r28, r28, #-20
	stur r0, 0(r28)
	stur $a1, 4(r28)
	stur $s2, 8(r28)
	stur $s7, 12(r28)
	stur r30, 16(r28)
	
	#Load temp value(used as counter for recursion(and catches answer)) with 1
	add $s2, r31, #1

	#Checks (b < a) and branches if false
	slt $s7, $a1, r0
	beq $s7, r31, myst_one_opt_two #if (b > a), switches a,b to b,a
	bl mystery1
	b end_myst

# (Mystery One) Flips arguments a,b to b,a. Then continues
myst_one_opt_two:
	#if second option, flip a,b to b,a. Then continue.
	add $s7, r0, r31
	add r0, $a1, r31
	add $a1, $s7, r31

	bl mystery1
	b end_myst

# (Mystery One) Main Recursive Procedure
mystery1:
	#Decrement stack pointer and store previous variable values
	add r28, r28, #-20
	stur r0, 0(r28)
	stur $a1, 4(r28)
	stur $s2, 8(r28)
	stur $s7, 12(r28)
	stur r30, 16(r28)

	#Get variables from argument registers
#	add $s0, r0, r31
#	add $s1, $a1, r31

	#Gets remainder of counter/b and checking if 0
	div $s2, $a1
	mfhi $s7 
	bne $s7, r31, end_if_check_false

	#Gets remainder of counter/a and checking if 0
	div $s2, r0
	mfhi $s7
	bne $s7, r31, end_if_check_false
	
	#Move answer to return register and begin to return
	add r8, $s2, r31

# (Mystery One) Deallocates the stack and restores variables, then returns
return_one:
	ldur r0, 0(r28)
	ldur $a1, 4(r28)
	ldur $s2, 8(r28)
	ldur $s7, 12(r28)
	ldur r30, 16(r28)
	add r28, r28, #20
	jr r30
	
# (Mystery One) Increments counter and recurs
end_if_check_false:
	add $s2, $s2, #1
	bl mystery1
	b return_one

#Used by both recursive procedures to print answer and return
end_myst:
	add $s7, r8, r31 #get answer from mystery return

	#Prints answer to terminal
	add r8, r31, #64
	ldr r0, answer
	syscall
	add r8, ,r31 0x00000001 //TODO look up this syscall
	add r0, $s7, r31
	syscall
	add r8, r31, #64
	ldr r0, newLine
	syscall

# Decrements stack and loads previous variables into registers. Returns to Main
end:
	ldur r0, 0(r28) 
	ldur $a1, 4(r28)
	ldur $s2, 8(r28)
	ldur $s7, 12(r28)
	ldur r30, 16(r28)
	add r28, r28, #20
	b r30


# Start of "Mystery Two" procedure: Computes greatest common divisor of a and b
start_mystery2:
	#Store return address in stack
	add r28, r28, #-20
	stur r0, 0(r28)
	stur $a1, 4(r28)
	stur $s2, 8(r28)
	stur $s7, 12(r28)
	stur r30, 16(r28)

	#Run main recursion
	bl mystery2
	b end_myst


# (Mystery Two) Main Recursive Procedure
mystery2:
	#Decrement stack pointer and store previous variable values
	add r28, r28, #-12
	stur r0, 0(r28)
	stur $a1, 4(r28)
	stur r30, 8(r28)

	# If (a == b) are equal, answer is found. Else, continue recursion.
	beq r0, $a1, myst2_return

	# If (a < b), then (b = b - a). Else (a = a - b)
	slt $s7, $a1, r0
	beq $s7, r31, if_false
	sub r0, r0, $a1
	bl mystery2

# (Mystery Two) Deallocates the stack and restores variables, then returns
return_two:
	ldur r0, 0(r28)
	ldur $a1, 4(r28)
	ldur r30, 8(r28)
	add r28, r28, #12
	jr r30

# (Mystery Two) a = a - b
if_false:
	sub $a1, $a1, r0
	bl mystery2
	b return_two

# (Mystery Two) Loads final result into the return register and begins return
myst2_return:
	add r8, r0, r31
	b return_two

# Program exit.
exit:
	add, r8, r31, #93
	syscall
