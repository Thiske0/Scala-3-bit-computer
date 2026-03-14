package computer3bit

import computer3bit.util.{Program, Instruction, Bit3, Register}
import computer3bit.Computer
import scala.io.StdIn.readLine

@main def runComputer(): Unit =
    println("Please enter the initial value for the X register:")
    val X = readRegister()
    println("Please enter the initial value for the Y register:")
    val Y = readRegister()
    println("Please enter the initial value for the Z register:")
    val Z = readRegister()
    println("Please enter the program (as a sequence of 3 bit integers (0-7), separated by commas):")
    val program = readProgram()

    val computer = Computer(X, Y, Z, program)
    try
        while true do
            computer.step()
    catch
        case _: IndexOutOfBoundsException =>
            println("Program has halted.")
            println(s"Output: ${computer.output.mkString(", ")}")
        case _: IllegalArgumentException =>
            println("Invalid instruction encountered. Halting.")
            println(s"Final state: X=${computer.X.read()}, Y=${computer.Y.read()}, Z=${computer.Z.read()}, IP=${computer.IP.read()}")
            println(s"Output: ${computer.output.mkString(", ")}")


def readRegister(): Register =
  val number = Iterator.continually {
    readLine()
  }.map { input =>
    try Some(input.toInt)
    catch case _: NumberFormatException =>
      println("Please enter a valid integer.")
      None
  }.collectFirst { case Some(n) => n }.get
  Register(number)


def readProgram(): Program =
  val program = Iterator.continually {
    readLine()
  }.map { input =>
    try Some(input.split(",").map(_.trim.toInt).map(Bit3.apply).toList)
    catch case _: NumberFormatException | _: IllegalArgumentException =>
      println("Please enter a valid sequence of integers (0-7), separated by commas.")
      None
  }.collectFirst { case Some(p) => p }.get
  Program(program)