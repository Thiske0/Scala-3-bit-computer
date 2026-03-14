package computer3bit

import org.scalatest.funsuite.AnyFunSuite
import computer3bit.util.{Program, Instruction, Bit3, Register}

class Programs extends AnyFunSuite:
    test("program 1") {
        val program = Program(List(
            Bit3(2),
            Bit3(6)
        ))
        val computer = Computer(Register(0), Register(0), Register(9), program)
        
        computer.step()

        assert(computer.Y.read() == 1)

        assertThrows[IndexOutOfBoundsException] {
            computer.step()
        }
    }

    test("program 2") {
        val rawProgram = List(5,0,5,1,5,4)
        val program = Program(rawProgram.map(Bit3.apply))
        val computer = Computer(Register(10), Register(0), Register(0), program)
        
        for _ <- 0 until 3 do
            computer.step()

        assert(computer.output == List(0, 1, 2))

        assertThrows[IndexOutOfBoundsException] {
            computer.step()
        }
    }

    test("program 3") {
        val rawProgram = List(0,1,5,4,3,0)
        val program = Program(rawProgram.map(Bit3.apply))
        val computer = Computer(Register(2024), Register(0), Register(0), program)
        
        for _ <- 0 until 33 do
            computer.step()

        assert(computer.output == List(4,2,5,6,7,7,7,7,3,1,0))
        assert(computer.X.read() == 0)

        assertThrows[IndexOutOfBoundsException] {
            computer.step()
        }
    }

    test("program 4") {
        val rawProgram = List(1,7)
        val program = Program(rawProgram.map(Bit3.apply))
        val computer = Computer(Register(0), Register(29), Register(0), program)
        
        computer.step()

        assert(computer.Y.read() == 26)

        assertThrows[IndexOutOfBoundsException] {
            computer.step()
        }
    }

    test("program 5") {
        val rawProgram = List(4,0)
        val program = Program(rawProgram.map(Bit3.apply))
        val computer = Computer(Register(0), Register(2024), Register(43690), program)
        
        computer.step()

        assert(computer.Y.read() == 44354)

        assertThrows[IndexOutOfBoundsException] {
            computer.step()
        }
    }

    test("program 6") {
        val rawProgram = List(0,1,5,4,3,0)
        val program = Program(rawProgram.map(Bit3.apply))
        val computer = Computer(Register(3729), Register(0), Register(0), program)
        
        for _ <- 0 until 36 do
            computer.step()

        assert(computer.output == List(0,4,2,1,4,2,5,6,7,3,1,0))

        assertThrows[IndexOutOfBoundsException] {
            computer.step()
        }
    }

    test("program 7") {
        val rawProgram = List(0,3,5,4,3,0)
        val program = Program(rawProgram.map(Bit3.apply))
        val computer = Computer(Register(8642024), Register(0), Register(0), program)
        
        for _ <- 0 until 24 do
            computer.step()

        assert(computer.output == List(5,7,6,5,7,0,4,0))

        assertThrows[IndexOutOfBoundsException] {
            computer.step()
        }
    }