package computer3bit.util

final class Register(private var value: Int):
    def read(): Int = value
    def write(newValue: Int): Unit = {
        value = newValue
    }
