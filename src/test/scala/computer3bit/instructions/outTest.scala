package computer3bit.instructions

import org.scalatest.funsuite.AnyFunSuite
import computer3bit.util.{Program, Instruction, Bit3, Register}
import computer3bit.Computer

class OutTest extends AnyFunSuite:
    test("out instruction should output the literal value") {
        val program = Program(List(
            Instruction.out.toBit3(),
            Bit3(2)
        ))
        val computer = Computer(Register(3), Register(4), Register(5), program)

        computer.step() // Execute out
        assert(computer.output.length == 1)
        assert(computer.output(0) == 2)
    }

    test("out instruction should output the X register value") {
        val program = Program(List(
            Instruction.out.toBit3(),
            Bit3(4)
        ))
        val computer = Computer(Register(3), Register(4), Register(5), program)

        computer.step() // Execute out
        assert(computer.output.length == 1)
        assert(computer.output(0) == 3)
        
    }

    test("out instruction should output the Y register value") {
        val program = Program(List(
            Instruction.out.toBit3(),
            Bit3(5)
        ))
        val computer = Computer(Register(3), Register(4), Register(5), program)

        computer.step() // Execute out
        assert(computer.output.length == 1)
        assert(computer.output(0) == 4)
        
    }

    test("out instruction should output the Z register value") {
        val program = Program(List(
            Instruction.out.toBit3(),
            Bit3(6)
        ))
        val computer = Computer(Register(3), Register(4), Register(5), program)

        computer.step() // Execute out
        assert(computer.output.length == 1)
        assert(computer.output(0) == 5)
        
    }

    test("out instruction should halt on invalid operand") {
        val program = Program(List(
            Instruction.out.toBit3(),
            Bit3(7)
        ))
        val computer = Computer(Register(3), Register(4), Register(5), program)

        assertThrows[IllegalArgumentException] {
            computer.step() // Execute out
        }
    }

    test("out instruction should output modulo 8") {
        val program = Program(List(
            Instruction.out.toBit3(),
            Bit3(6)
        ))
        val computer = Computer(Register(3), Register(4), Register(35), program)

        computer.step() // Execute out

        assert(computer.output.length == 1)
        assert(computer.output(0) == 3) // 35 % 8 = 3
    }

    test("out instruction should output modulo 8 (negative value)") {
        val program = Program(List(
            Instruction.out.toBit3(),
            Bit3(6)
        ))
        val computer = Computer(Register(3), Register(4), Register(-5), program)

        computer.step() // Execute out

        assert(computer.output.length == 1)
        assert(computer.output(0) == 3) // -5 % 8 = 3
    }