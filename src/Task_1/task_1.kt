package Task_1

fun countConsecutiveCharacters(input: String): String {
    var result = ""
    var count = 1

    for (i in 1 until input.length) {
        if (input[i] == input[i - 1]) {
            count++
        } else {
            if (count > 1) {
                result += input[i - 1] + count.toString()
            } else {
                result += input[i - 1]
            }
            count = 1
        }
    }

    if (count > 1) {
        result += input[input.length - 1] + count.toString()
    } else {
        result += input[input.length - 1]
    }

    return result
}

fun countDifferentCharacters(input: String): Map<Char, Int> {
    val charCountMap = mutableMapOf<Char, Int>()

    for (char in input) {
        charCountMap[char] = charCountMap.getOrDefault(char, 0) + 1
    }

    return charCountMap.toSortedMap()
}

fun decimalToBinary(decimal: Int): String {
    return Integer.toBinaryString(decimal)
}

fun performArithmeticOperation(input: String): Double {
    val parts = input.split(" ")
    val number1 = parts[0].toDouble()
    val number2 = parts[1].toDouble()
    val operator = parts[2]

    return when (operator) {
        "+" -> number1 + number2
        "-" -> number1 - number2
        "*" -> number1 * number2
        "/" -> number1 / number2
        else -> throw IllegalArgumentException("Invalid operator")
    }
}

fun checkIntegerExponent(n: Int, x: Int): Int? {
    var exponent = 0
    var result = 1

    while (result < n) {
        result *= x
        exponent++
    }

    return if (result == n) exponent else null
}

fun createOddNumber(digit1: Int, digit2: Int): Int? {
    return if (digit1 % 2 == 0 && digit2 % 2 == 0) {
        null
    } else {
        digit1 * 10 + (digit2 % 2) + 1
    }
}


fun main() {
    val input = "aaabbbccc"
    val consecutiveCharacters = countConsecutiveCharacters(input)
    println("Consecutive Characters: $consecutiveCharacters")

}
