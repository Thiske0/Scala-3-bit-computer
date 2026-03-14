package computer3bit.instructions

import org.scalatest.funsuite.AnyFunSuite
import computer3bit.util.{Program, Instruction, Bit3, Register}
import computer3bit.Computer

class YxzTest extends AnyFunSuite:
    test("yxz instruction should XOR Y and Z and store in Y") {
        val program = Program(List(
            Instruction.yxz.toBit3(),
            Bit3(2)
        ))
        val computer = Computer(Register(21), Register(4), Register(5), program)

        computer.step() // Execute yxz
        assert(computer.Y.read() == 1) // 4 XOR 5 = 1
    }

    test("yxz instruction should XOR Y and Z and store in Y (opcode operand ignored)") {
        val program = Program(List(
            Instruction.yxz.toBit3(),
            Bit3(5)
        ))
        val computer = Computer(Register(21), Register(4), Register(5), program)

        computer.step() // Execute yxz
        assert(computer.Y.read() == 1) // 4 XOR 5 = 1
    }

    test("yxz instruction should still increase IP by 2") {
        val program = Program(List(
            Instruction.yxz.toBit3(),
            Bit3(5)
        ))
        val computer = Computer(Register(21), Register(4), Register(5), program)

        computer.step() // Execute yxz
        assert(computer.IP.read() == 2) // IP should have increased by 2
    }