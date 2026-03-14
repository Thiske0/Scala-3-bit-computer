package computer3bit.instructions

import org.scalatest.funsuite.AnyFunSuite
import computer3bit.util.{Program, Instruction, Bit3, Register}
import computer3bit.Computer

class XdvTest extends AnyFunSuite:
    test("xdv instruction should divide X by 2**literal and store in X") {
        val program = Program(List(
            Instruction.xdv.toBit3(),
            Bit3(2)
        ))
        val computer = Computer(Register(21), Register(4), Register(5), program)

        computer.step() // Execute xdv
        assert(computer.X.read() == 5) // 21 / 2**2 = 21 / 4 = 5
    }

    test("xdv instruction should divide X by 2**X and store in X") {
        val program = Program(List(
            Instruction.xdv.toBit3(),
            Bit3(4)
        ))
        val computer = Computer(Register(21), Register(4), Register(5), program)

        computer.step() // Execute xdv
        assert(computer.X.read() == 0) // 21 / 2**21 = 0
    }

    test("xdv instruction should divide X by 2**Y and store in X") {
        val program = Program(List(
            Instruction.xdv.toBit3(),
            Bit3(5)
        ))
        val computer = Computer(Register(21), Register(2), Register(5), program)

        computer.step() // Execute xdv
        assert(computer.X.read() == 5) // 21 / 2**2 = 5
    }

    test("xdv instruction should divide X by 2**Z and store in X") {
        val program = Program(List(
            Instruction.xdv.toBit3(),
            Bit3(6)
        ))
        val computer = Computer(Register(21), Register(4), Register(2), program)

        computer.step() // Execute xdv
        assert(computer.X.read() == 5) // 21 / 2**2 = 5
    }
    
    test("xdv instruction should correctly handle negative numerators") {
        val program = Program(List(
            Instruction.xdv.toBit3(),
            Bit3(2)
        ))
        val computer = Computer(Register(-21), Register(4), Register(5), program)

        computer.step() // Execute xdv
        assert(computer.X.read() == -5) // -21 / 2**2 = -21 / 4 = -5
    }
    
    test("xdv instruction should correctly handle negative denominators") {
        val program = Program(List(
            Instruction.xdv.toBit3(),
            Bit3(5)
        ))
        val computer = Computer(Register(21), Register(-2), Register(5), program)

        computer.step() // Execute xdv
        assert(computer.X.read() == 84) // 21 / 2**-2 = 21 * 4 = 84
    }
    
    test("xdv instruction should correctly handle negative numerators and denominators") {
        val program = Program(List(
            Instruction.xdv.toBit3(),
            Bit3(5)
        ))
        val computer = Computer(Register(-21), Register(-2), Register(5), program)

        computer.step() // Execute xdv
        assert(computer.X.read() == -84) // -21 / 2**-2 = -21 * 4 = -84
    }