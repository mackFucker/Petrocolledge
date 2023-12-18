import kotlin.math.sqrt
import kotlin.io.readLine

data class Point(val x: Double, val y: Double)

fun convertSecondsToTime(seconds: Int): Triple<Int, Int, Int> {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60
    return Triple(hours, minutes, remainingSeconds)
}

fun calculateDistance(point1: Point, point2: Point): Double {
    val dx = point2.x - point1.x
    val dy = point2.y - point1.y
    return sqrt(dx * dx + dy * dy)
}

fun calculateGCD(a: Int, b: Int): Int {
    return if (b == 0) a else calculateGCD(b, a % b)
}

fun calculateLCM(a: Int, b: Int): Int {
    return (a * b) / calculateGCD(a, b)
}


fun calculateSecondsFromMidnight(time: String): Int {
    val parts = time.split(":")
    if (parts.size == 3) {
        val hours = parts[0].toIntOrNull()
        val minutes = parts[1].toIntOrNull()
        val seconds = parts[2].toIntOrNull()

        if (hours != null && minutes != null && seconds != null) {
            if (hours in 0..23 && minutes in 0..59 && seconds in 0..59) {
                return hours * 3600 + minutes * 60 + seconds
            }
        }
    }
    return -1
}

fun checkBrickPassesThroughHole(brick: Triple<Int, Int, Int>, hole: Pair<Int, Int>): Boolean {
    val (brickWidth, brickHeight, brickLength) = brick
    val (holeWidth, holeHeight) = hole

    return (brickWidth <= holeWidth && brickHeight <= holeHeight) ||
            (brickWidth <= holeHeight && brickHeight <= holeWidth) ||
            (brickHeight <= holeWidth && brickLength <= holeHeight) ||
            (brickHeight <= holeHeight && brickLength <= holeWidth) ||
            (brickLength <= holeWidth && brickWidth <= holeHeight) ||
            (brickLength <= holeHeight && brickWidth <= holeWidth)
}

fun main() {
    print("Введите количество секунд: ")
    val seconds = readLine()?.toIntOrNull()
    if (seconds != null) {
        val time = convertSecondsToTime(seconds)
        println("$seconds секунд = ${time.first} часов ${time.second} минут ${time.third} секунд")
    } else {
        println("Ошибка ввода данных")
        return
    }

    print("Введите координаты первой точки (x1 y1): ")
    val (x1, y1) = readLine()?.split(" ")?.map { it.toDoubleOrNull() } ?: throw IllegalArgumentException("Ошибка ввода данных")

    print("Введите координаты второй точки (x2 y2): ")
    val (x2, y2) = readLine()?.split(" ")?.map { it.toDoubleOrNull() } ?: throw IllegalArgumentException("Ошибка ввода данных")

    if (x1 != null && y1 != null && x2 != null && y2 != null) {
        val point1 = Point(x1, y1)
        val point2 = Point(x2, y2)
        val distance = calculateDistance(point1, point2)
        println("Расстояние между точками: $distance")
    } else {
        println("Ошибка ввода данных")
        return
    }

    print("Введите первое число: ")
    val num1 = readLine()?.toIntOrNull()
    print("Введите второе число: ")
    val num2 = readLine()?.toIntOrNull()

    if (num1 != null && num2 != null) {
        val gcd = calculateGCD(num1, num2)
        val lcm = calculateLCM(num1, num2)
        println("НОД: $gcd")
        println("НОК: $lcm")
    } else {
        println("Ошибка ввода данных")
        return
    }

    print("Введите текущее время (час:минута:секунда): ")
    val time = readLine()
    val seconds1 = calculateSecondsFromMidnight(time ?: "")

    if (seconds1 >= 0) {
        println("Количество секунд с начала суток: $seconds1")
    } else {
        println("Ошибка ввода данных")
        return
    }

    print("Введите размеры кирпича (ширина высота длина): ")
    val brickDimensions = readLine()?.split(" ")?.map { it.toIntOrNull() }
    print("Введите размеры отверстия (ширина высота): ")
    val holeDimensions = readLine()?.split(" ")?.map { it.toIntOrNull() }

    if (brickDimensions != null && holeDimensions != null) {
        val brick = Triple(brickDimensions[0] ?: 0, brickDimensions[1] ?: 0, brickDimensions[2] ?: 0)
        val hole = Pair(holeDimensions[0] ?: 0, holeDimensions[1] ?: 0)
        val canPass = checkBrickPassesThroughHole(brick, hole)
        println("Кирпич ${if (canPass) "пройдет" else "не пройдет"} через отверстие")
    } else {
        println("Ошибка ввода данных")
        return
    }
}