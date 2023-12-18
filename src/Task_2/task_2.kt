fun areCoprime(m: Int, n: Int): Boolean {
    if (m == 0 || n == 0) {
        throw IllegalArgumentException("Значения m и n не могут быть равны нулю.")
    }

    var a = m
    var b = n
    while (b != 0) {
        val temp = b
        b = a % b
        a = temp
    }
    return a == 1
}

fun hasPerfectSquare(m: Int, n: Int): Boolean {
    if (m < 0 || n < 0) {
        throw IllegalArgumentException("Значения m и n не могут быть отрицательными.")
    }

    for (i in m..n) {
        val sqrt = Math.sqrt(i.toDouble())
        if (sqrt % 1 == 0.0) {
            return true
        }
    }
    return false
}

fun reverseDigits(n: Int): Int {
    var number = n
    var reversed = 0
    while (number != 0) {
        val digit = number % 10
        reversed = reversed * 10 + digit
        number /= 10
    }
    return reversed
}

fun findNthDigit(n: Int): Int {
    var number = 1
    var count = 0
    while (true) {
        val square = number * number
        val digits = square.toString().length
        if (count + digits >= n) {
            val nthDigit = square.toString()[n - count - 1].toString().toInt()
            return nthDigit
        }
        count += digits
        number++
    }
}

fun main() {
    val m = 25
    val n = 49

    val areCoprime = areCoprime(m, n)
    println("Числа $m и $n ${if (areCoprime) "являются" else "не являются"} взаимно простыми.")

    val hasPerfectSquare = hasPerfectSquare(m, n)
    println("Между числами $m и $n ${if (hasPerfectSquare) "существует" else "не существует"} точный квадрат.")

    val reversedDigits = reverseDigits(13478)
    println("Число 13478 в обратном порядке: $reversedDigits")

    val nthDigit = findNthDigit(2)
    println("2-я цифра последовательности из квадратов целых чисел: $nthDigit")
}




