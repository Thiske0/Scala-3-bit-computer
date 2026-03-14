package computer3bit


import org.scalatest.funsuite.AnyFunSuite
import computer3bit.util.{Program, Instruction, Bit3, Register}
import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}

class maintTest extends AnyFunSuite:
    test("CLI should succesfully execute a simple program") {
        val input =
            "10\n" +
            "0\n" +
            "0\n" +
            "5,0,5,1,5,4\n"
        val inStream = new ByteArrayInputStream(input.getBytes)
        val outStream = new ByteArrayOutputStream()

        Console.withIn(inStream) {
            Console.withOut(new PrintStream(outStream)) {
                runComputer()
            }
        }

        val output = outStream.toString
        val expectedOutput = "Please enter the initial value for the X register:\r\n" +
                             "Please enter the initial value for the Y register:\r\n" +
                             "Please enter the initial value for the Z register:\r\n" +
                             "Please enter the program (as a sequence of 3 bit integers (0-7), separated by commas):\r\n" +
                             "Program has halted.\r\n" +
                             "Output: 0, 1, 2\r\n"

        assert(output == expectedOutput)
    }

    test("CLI should succesfully handle invalid input") {
        val input =
            "a\n" +
            "10\n" +
            "0\n" +
            "b\n" +
            "0\n" +
            "5,0,5,c,5,4\n" +
            "5,0,5,11,5,4\n" +
            "5,\t0,5    ,1 , 5,4\n"
        val inStream = new ByteArrayInputStream(input.getBytes)
        val outStream = new ByteArrayOutputStream()

        Console.withIn(inStream) {
            Console.withOut(new PrintStream(outStream)) {
                runComputer()
            }
        }

        val output = outStream.toString
        val expectedOutput = "Please enter the initial value for the X register:\r\n" +
                             "Please enter a valid integer.\r\n" +
                             "Please enter the initial value for the Y register:\r\n" +
                             "Please enter the initial value for the Z register:\r\n" +
                             "Please enter a valid integer.\r\n" +
                             "Please enter the program (as a sequence of 3 bit integers (0-7), separated by commas):\r\n" +
                             "Please enter a valid sequence of integers (0-7), separated by commas.\r\n" +
                             "Please enter a valid sequence of integers (0-7), separated by commas.\r\n" +
                             "Program has halted.\r\n" +
                             "Output: 0, 1, 2\r\n"
                             
        assert(output == expectedOutput)
    }

    test("CLI should succesfully gracefully handle invalid instructions") {
        val input =
            "10\n" +
            "0\n" +
            "0\n" +
            "5,0,5,7,5,4\n"
        val inStream = new ByteArrayInputStream(input.getBytes)
        val outStream = new ByteArrayOutputStream()

        Console.withIn(inStream) {
            Console.withOut(new PrintStream(outStream)) {
                runComputer()
            }
        }

        val output = outStream.toString
        val expectedOutput = "Please enter the initial value for the X register:\r\n" +
                             "Please enter the initial value for the Y register:\r\n" +
                             "Please enter the initial value for the Z register:\r\n" +
                             "Please enter the program (as a sequence of 3 bit integers (0-7), separated by commas):\r\n" +
                             "Invalid instruction encountered. Halting.\r\n" +
                             "Final state: X=10, Y=0, Z=0, IP=2\r\n" +
                             "Output: 0\r\n"

        assert(output == expectedOutput)
    }