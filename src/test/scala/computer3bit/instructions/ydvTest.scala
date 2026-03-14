package computer3bit.instructions

import org.scalatest.funsuite.AnyFunSuite
import computer3bit.util.{Program, Instruction, Bit3, Register}
import computer3bit.Computer

// Note: all behaviour is idential to xdv, except the result is stored in Y instead of X.
// So we only need one test case here to verify that the instruction correctly stores in Y instead of X.
class YdvTest extends AnyFunSuite:
    test("ydv instruction should divide X by 2**literal and store in Y") {
        val program = Program(List(
            Instruction.ydv.toBit3(),
            Bit3(2)
        ))
        val computer = Computer(Register(21), Register(4), Register(5), program)

        computer.step() // Execute ydv
        assert(computer.Y.read() == 5) // 21 / 2**2 = 21 / 4 = 5
    }