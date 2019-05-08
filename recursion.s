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
# r10 Used for temporary values
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
    add r23, r0, r31 #save input to r23
    add r8, r31, #64
    ldr r0, newLine
    syscall

	sub r23, r23, #2
	cbz r23, exit	#If user input is 2: Exit program

	#If user input is greater than 2 or less than 0: Repeat request for input

    add r9, r31, #2 #value 2

	sub r10, r9, r23 #Sub num from 2: NEGATIVE if num>2
	lsr r10, r10, r8 #shift right 63: 1 if num>2
	cbnz r10, main

	lsr r10, r23, r8 #shift right 63: 1 if num<0
	cbnz r10, main


	#Prompt for the input to pass the procedure
	add r8, r31, #64
	ldr r0, enterTwo
	syscall
	ldr r0, newLine
	syscall
	ldr r0, a
	syscall
	add r8, r31, #63		#Takes input for 'a'
	syscall
	add r19, r0, r31		#Stores 'a' in r19
	add r8, r31, #64
    ldr r0, newLine
    syscall



    add r8, r31, #64
    ldr r0, b
    syscall
	add r8, r31, #63		#Takes input for 'b'
	syscall
	add r20, r0, r31		#Store 'b' in r20
	add r8, r31, #64
    ldr r0, newLine
    syscall



	#Calls the procedure specified by the user
	cbnz r23, call_two		#Note that r23 is still 1 from line 45
	bl start_mystery1
	b main				#Start over after procedure finishes

#Calls second procedure
call_two:		
	bl start_mystery2
	b main				#Start over after procedure finishes


#Start of "Mystery One" procedure: Computes least common multiple of a and b
start_mystery1:
	#Decrement stack pointer and store registers.
	add r28, r28, #-40
	stur r19, [r28,0]
	stur r20, [r28,8]
	stur r9, [r28,16]
	stur r10, [r28,24]
	stur r30, [r28,32]
	
	#Load temp value(used as counter for recursion(and catches answer)) with 1
	add r9, r31, #1
	add r2, r31, #63

	#Checks (b < a) and branches if false
	sub r1, r19, r20
	lsr r1, r1, r2
	cbnz r1, myst_one_opt_two

	bl mystery1
	b end_myst

# (Mystery One) Flips arguments a,b to b,a. Then continues
myst_one_opt_two:
	#if second option, flip a,b to b,a. Then continue.
	add r10, r19, r31
	add r19, r20, r31
	add r20, r10, r31

	bl mystery1
	b end_myst

# (Mystery One) Main Recursive Procedure
mystery1:
	#Decrement stack pointer and store previous variable values
	add r28, r28, #-40
	stur r19, [r28,0]
	stur r20, [r28,8]
	stur r9, [r28,16]
	stur r10, [r28,24]
	stur r30, [r28,32]

	#Get variables from argument registers
#	add r0, r19, r31
#	add r1, r20, r31

	#Gets remainder of counter/b and checking if 0
	div r11, r9, r20
	mul r12, r11, r20
	sub r10, r9, r12 #remainder
	cbnz r10, end_if_check_false

	#Gets remainder of counter/a and checking if 0
	div r11, r9, r19
	mul r12, r11, r20
	sub r10, r9, r12 #remainder
	cbnz r10, end_if_check_false
	
	#Move answer to return register and begin to return
	add r8, r9, r31

# (Mystery One) Deallocates the stack and restores variables, then returns
return_one:
	ldur r19, [r28,0]
	ldur r20, [r28,8]
	ldur r9, [r28,16]
	ldur r10, [r28,24]
	ldur r30, [r28,32]
	add r28, r28, #40
	br r30
	
# (Mystery One) Increments counter and recurs
end_if_check_false:
	add r9, r9, #1
	bl mystery1
	b return_one

#Used by both recursive procedures to print answer and return
end_myst:
	add r10, r8, r31 #get answer from mystery return

	#Prints answer to terminal
	add r8, r31, #64
	ldr r0, answer
	syscall
	add r8, r31, #1
	add r0, r10, r31
	syscall
	add r8, r31, #64
	ldr r0, newLine
	syscall

# Decrements stack and loads previous variables into registers. Returns to Main
end:
	ldur r19, [r28,0]
	ldur r20, [r28,8]
	ldur r9, [r28,16]
	ldur r10, [r28,24]
	ldur r30, [r28,32]
	add r28, r28, #40
	b r30


# Start of "Mystery Two" procedure: Computes greatest common divisor of a and b
start_mystery2:
	#Store return address in stack
	add r28, r28, #-40
	stur r19, [r28,0]
	stur r20, [r28,8]
	stur r9, [r28,16]
	stur r10, [r28,24]
	stur r30, [r28,32]

	#Run main recursion
	bl mystery2
	b end_myst


# (Mystery Two) Main Recursive Procedure
mystery2:
	#Decrement stack pointer and store previous variable values
	add r28, r28, #-24
	stur r19, [r28,0]
	stur r20, [r28,8]
	stur r30, [r28,16]

	add r2, r31, #63

	# If (a == b) are equal, answer is found. Else, continue recursion.

    sub r11, r19, r20
    cbz r11, myst2_return

	# If (a < b), then (b = b - a). Else (a = a - b)

	sub r10, r20, r19
	lsr r10, r10, r2 #0 if b>a

	cbnz r10, if_false
	sub r19, r19, r20
	bl mystery2

# (Mystery Two) Deallocates the stack and restores variables, then returns
return_two:
	ldur r19, [r28,0]
	ldur r20, [r28,8]
	ldur r30, [r28,16]
	add r28, r28, #24
	br r30

# (Mystery Two) a = a - b
if_false:
	sub r20, r20, r19
	bl mystery2
	b return_two

# (Mystery Two) Loads final result into the return register and begins return
myst2_return:
	add r8, r19, r31
	b return_two

# Program exit.
exit:
	add, r8, r31, #93
	syscall
