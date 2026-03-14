package computer3bit.util

case class Bit3(value: Int):
  require(value >= 0 && value < 8, "3-bit value must be between 0 and 7")