package computer3bit

import computer3bit.util.{Register, Bit3, Program, Instruction}

class Computer(var X: Register, var Y: Register, var Z: Register, val program: Program):
    var IP: Register = Register(0) // Instruction Pointer
    var output: List[Int] = List() // To store output values

    def step(): Unit =
        val instruction = program.readOpcode(IP)
        execute(instruction)

    private def execute(instruction: Instruction): Unit = {
        instruction match
            case Instruction.xdv => {
                /* The xdv instruction (opcode 0) performs division.
                 * The numerator is the value in the X register.
                 * The denominator is found by raising 2 to the power of the instruction’s combo operand. (So, an operand of 2 would divide X by 4 (2^2); an operand of 5 would divide X by 2^Y.)
                 * The result of the division operation is truncated to an integer and written to the X register.
                 */
                X.write(divide_instr())
            }
            case Instruction.yxl => {
                /* The yxl instruction (opcode 1) calculates the bitwise XOR of register Y and the instruction’s literal operand
                 * It stores the result in register Y (overwrites the previous value).
                 */
                val operand = program.readOperand(IP).literal
                val result = Y.read() ^ operand
                Y.write(result)
            }
            case Instruction.yst => {
                /* The yst instruction (opcode 2) calculates the value of its combo operand modulo 8 (thereby keeping only its lowest 3 bits)
                 * then writes that value to the Y register.
                 */
                val operand = program.readOperand(IP).combo(X, Y, Z)
                val result = operand % 8
                Y.write(result)
            }
            case Instruction.jnz => {
                /* The jnz instruction (opcode 3) does nothing if the X register is 0.
                 * However, if the X register is not zero, it jumps by setting the instruction pointer to the value of its literal operand.
                 * If this instruction jumps, the instruction pointer is not increased by 2 after this instruction.
                 */
                val operand = program.readOperand(IP).literal
                if X.read() != 0 then
                    IP.write(operand)
                else
                    IP.write(IP.read() + 2) // Move to the next instruction (opcode + operand)
            }
            case Instruction.yxz => {
                /* The yxz instruction (opcode 4) calculates the bitwise XOR of the register Y and register Z, then stores the result in register Y.
                 */
                val result = Y.read() ^ Z.read()
                Y.write(result)
            }
            case Instruction.out => {
                /* The out instruction (opcode 5) calculates the value of its combo operand modulo 8, then outputs that value.
                 */
                val operand = program.readOperand(IP).combo(X, Y, Z)
                val outputValue = operand % 8
                output = output :+ outputValue // Append to output list
            }
            case Instruction.ydv => {
                /* The ydv instruction (opcode 6) works exactly like the xdv instruction except that the result is stored in the Y register.
                 * The numerator is still read from the X register.
                 * As is the case for the xdv instruction, the operand of the ydv instruction is also a combo operand.
                 */
                Y.write(divide_instr())
            }
            case Instruction.zdv => {
                /* The zdv instruction (opcode 7) works exactly like the xdv instruction except that the result is stored in the Z register.
                 * The numerator is still read from the X register.
                 * As is the case for the xdv instruction, the operand of the zdv instruction is also a combo operand.
                 */
                Z.write(divide_instr())
            }
        
        if instruction != Instruction.jnz then
            IP.write(IP.read() + 2) // Move to the next instruction (opcode + operand)
    }

    private def divide_instr(): Int =
        val numerator = X.read()
        val operand = program.readOperand(IP).combo(X, Y, Z)
        val denominator = 1 << operand // 2^operand
        numerator / denominator // Integer division (truncated)
