package computer3bit

import org.scalatest.funspec.AnyFunSpec
import computer3bit.util.{Program, Instruction, Bit3, Register}
import org.scalatest.Suites

class ComputerTest extends AnyFunSpec:
    describe("Computer") {
        it("should initialise with given registers and program") {
            val program = Program(List(
                Instruction.out.toBit3(),
                Bit3(5)
            ))
            val computer = Computer(Register(3), Register(4), Register(5), program)

            assert(computer.X.read() == 3)
            assert(computer.Y.read() == 4)
            assert(computer.Z.read() == 5)
            assert(computer.IP.read() == 0)
            assert(computer.output.isEmpty)
        }

        it("should halt on empty program") {
            val program = Program(List())
            val computer = Computer(Register(3), Register(4), Register(5), program)
            assertThrows[IndexOutOfBoundsException] {
                computer.step()
            }
        }
    }
