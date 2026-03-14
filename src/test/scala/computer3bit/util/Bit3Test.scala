package computer3bit.util

import org.scalatest.funsuite.AnyFunSuite

class Bit3Test extends AnyFunSuite:

  test("Bit3 should store values between 0 and 7") {
    val b = Bit3(5)
    assert(b.value == 5)
  }

  test("Bit3 should reject values greater than 7") {
    assertThrows[IllegalArgumentException] {
      Bit3(8)
    }
  }

  test("Bit3 should reject negative values") {
    assertThrows[IllegalArgumentException] {
      Bit3(-1)
    }
  }