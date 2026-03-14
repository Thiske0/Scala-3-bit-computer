package computer3bit.instructions

import org.scalatest.funsuite.AnyFunSuite
import computer3bit.util.{Program, Instruction, Bit3, Register}
import computer3bit.Computer

// Note: all behaviour is idential to xdv, except the result is stored in Z instead of X.
// So we only need one test case here to verify that the instruction correctly stores in Z instead of X.
class JnzTest extends AnyFunSuite:
    test("jnz instruction should jump to literal if X is not zero") {
        val program = Program(List(
            Instruction.jnz.toBit3(),
            Bit3(1)
        ))
        val computer = Computer(Register(21), Register(4), Register(7), program)

        computer.step() // Execute jnz
        assert(computer.IP.read() == 1)
    }

    test("jnz instruction should jump to different literal if X is not zero") {
        val program = Program(List(
            Instruction.jnz.toBit3(),
            Bit3(5)
        ))
        val computer = Computer(Register(-21), Register(4), Register(7), program)

        computer.step() // Execute jnz
        assert(computer.IP.read() == 5)
    }

    test("jnz instruction should not jump if X is zero") {
        val program = Program(List(
            Instruction.jnz.toBit3(),
            Bit3(5)
        ))
        val computer = Computer(Register(0), Register(4), Register(7), program)

        computer.step() // Execute jnz
        assert(computer.IP.read() == 2)
    }