package computer3bit.util

enum Instruction(private val code: Int):
  case xdv extends Instruction(0)
  case yxl extends Instruction(1)
  case yst extends Instruction(2)
  case jnz extends Instruction(3)
  case yxz extends Instruction(4)
  case out extends Instruction(5)
  case ydv extends Instruction(6)
  case zdv extends Instruction(7)

object Instruction:
  def fromBit3(bit: Bit3): Instruction =
    Instruction.values(bit.value)