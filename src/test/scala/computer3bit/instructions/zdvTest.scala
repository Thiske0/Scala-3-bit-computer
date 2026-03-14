package computer3bit.instructions

import org.scalatest.funsuite.AnyFunSuite
import computer3bit.util.{Program, Instruction, Bit3, Register}
import computer3bit.Computer

// Note: all behaviour is idential to xdv, except the result is stored in Z instead of X.
// So we only need one test case here to verify that the instruction correctly stores in Z instead of X.
class ZdvTest extends AnyFunSuite:
    test("zdv instruction should divide X by 2**literal and store in Z") {
        val program = Program(List(
            Instruction.zdv.toBit3(),
            Bit3(2)
        ))
        val computer = Computer(Register(21), Register(4), Register(7), program)

        computer.step() // Execute zdv
        assert(computer.Z.read() == 5) // 21 / 2**2 = 21 / 4 = 5
    }