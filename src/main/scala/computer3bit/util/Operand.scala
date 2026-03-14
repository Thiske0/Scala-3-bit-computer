package computer3bit.util

final case class Operand(value: Bit3):
    def literal: Int = value.value
    def combo(X: Register, Y: Register, Z: Register): Int =
        if value.value < 4 then
            value.value
        else
            value.value match
                case 4 => X.read()
                case 5 => Y.read()
                case 6 => Z.read()
                case 7 => throw new IllegalArgumentException("Combo operand value 7 is invalid")