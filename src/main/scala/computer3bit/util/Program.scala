package computer3bit.util

final case class Program(instructions: List[Bit3]):
    def readOpcode(instructionPointer: Register): Instruction =
        val rawInstruction = instructions(instructionPointer.read())
        Instruction.fromBit3(rawInstruction)
    
    def readOperand(instructionPointer: Register): Operand =
        // The operand is located immediately after the opcode
        val rawOperand = instructions(instructionPointer.read() + 1)
        Operand(rawOperand)