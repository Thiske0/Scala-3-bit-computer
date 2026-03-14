package computer3bit.instructions

import org.scalatest.funsuite.AnyFunSuite
import computer3bit.util.{Program, Instruction, Bit3, Register}
import computer3bit.Computer

class YstTest extends AnyFunSuite:
    test("yst instruction should store the literal value in Y") {
        val program = Program(List(
            Instruction.yst.toBit3(),
            Bit3(2)
        ))
        val computer = Computer(Register(3), Register(4), Register(5), program)

        computer.step() // Execute yst
        assert(computer.Y.read() == 2)
    }

    test("yst instruction should output the X register value") {
        val program = Program(List(
            Instruction.yst.toBit3(),
            Bit3(4)
        ))
        val computer = Computer(Register(3), Register(4), Register(5), program)

        computer.step() // Execute yst
        assert(computer.Y.read() == 3)
    }

    test("yst instruction should store modulo 8") {
        val program = Program(List(
            Instruction.yst.toBit3(),
            Bit3(6)
        ))
        val computer = Computer(Register(3), Register(4), Register(35), program)

        computer.step() // Execute yst

        assert(computer.Y.read() == 3) // 35 % 8 = 3
    }

    test("yst instruction should store modulo 8 (negative value)") {
        val program = Program(List(
            Instruction.yst.toBit3(),
            Bit3(6)
        ))
        val computer = Computer(Register(3), Register(4), Register(-5), program)

        computer.step() // Execute yst

        assert(computer.Y.read() == 3) // -5 % 8 = 3
    }