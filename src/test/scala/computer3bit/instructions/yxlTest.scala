package computer3bit.instructions

import org.scalatest.funsuite.AnyFunSuite
import computer3bit.util.{Program, Instruction, Bit3, Register}
import computer3bit.Computer

class YxlTest extends AnyFunSuite:
    test("yxl instruction should XOR Y and literal and store in Y") {
        val program = Program(List(
            Instruction.yxl.toBit3(),
            Bit3(2)
        ))
        val computer = Computer(Register(21), Register(4), Register(5), program)

        computer.step() // Execute yxl
        assert(computer.Y.read() == 6) // 4 XOR 2 = 6
    }

    test("yxl instruction should XOR Y and Z and store in Y (opcode operand ignored)") {
        val program = Program(List(
            Instruction.yxl.toBit3(),
            Bit3(5)
        ))
        val computer = Computer(Register(21), Register(4), Register(7), program)

        computer.step() // Execute yxl
        assert(computer.Y.read() == 1) // 4 XOR 5 = 1
    }