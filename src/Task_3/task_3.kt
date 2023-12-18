fun countDifferentDigits() {
    // Запрос количества строк и столбцов
    println("Введите количество строк:")
    val rows = readLine()?.toIntOrNull() ?: return

    println("Введите количество столбцов:")
    val columns = readLine()?.toIntOrNull() ?: return

    // Создание двумерного массива
    val array = Array(rows) { IntArray(columns) }

    // Заполнение массива трехзначными числами
    println("Введите трехзначные числа:")
    for (i in 0 until rows) {
        for (j in 0 until columns) {
            val input = readLine()?.toIntOrNull()
            if (input != null && input in 100..999) {
                array[i][j] = input
            } else {
                println("Некорректный ввод. Попробуйте еще раз.")
                return
            }
        }
    }

    // Подсчет количества различных цифр
    val digitsSet = mutableSetOf<Int>()
    for (i in 0 until rows) {
        for (j in 0 until columns) {
            val number = array[i][j]
            val digits = number.toString().toCharArray().map { it.toString().toInt() }
            digitsSet.addAll(digits)
        }
    }

    // Вывод двумерного массива и количества различных цифр
    println("Двумерный массив:")
    for (i in 0 until rows) {
        for (j in 0 until columns) {
            print("${array[i][j]}\t")
        }
        println()
    }
    println("В массиве использовано ${digitsSet.size} различных цифр.")
}

fun isSymmetric(array: Array<Array<Int>>): Boolean {
    for (i in 0 until array.size) {
        for (j in 0 until array[i].size) {
            if (array[i][j] != array[j][i]) {
                return false
            }
        }
    }
    return true
}

fun encryptText(text: String, keyword: String): String {
    val alphabet = listOf(
        "А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ь", "Ы", "Ъ", "Э", "Ю", "Я"
    )
    val encryptedText = StringBuilder()

    for (char in text) {
        val index = alphabet.indexOf(char.toString().toUpperCase())
        if (index != -1) {
            val shift = alphabet.indexOf(keyword[index % keyword.length].toString().toUpperCase())
            encryptedText.append(alphabet[(index + shift) % alphabet.size])
        } else {
            encryptedText.append(char)
        }
    }

    return encryptedText.toString()
}

fun decryptText(text: String, keyword: String): String {
    val alphabet = listOf(
        "А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ь", "Ы", "Ъ", "Э", "Ю", "Я"
    )
    val decryptedText = StringBuilder()

    for (char in text) {
        val index = alphabet.indexOf(char.toString().toUpperCase())
        if (index != -1) {
            val shift = alphabet.indexOf(keyword[index % keyword.length].toString().toUpperCase())
            decryptedText.append(alphabet[(index - shift + alphabet.size) % alphabet.size])
        } else {
            decryptedText.append(char)
        }
    }

    return decryptedText.toString()
}

fun findIntersection(array1: IntArray, array2: IntArray): IntArray {
    return array1.intersect(array2.asIterable()).toIntArray()
}

fun groupWordsByLetters(words: List<String>): Map<String, List<String>> {
    return words.groupBy { it.toCharArray().sorted().joinToString("") }
}


fun main() {
    countDifferentDigits()

    val array = arrayOf(
        arrayOf(5, 9, 6, 7, 2),
        arrayOf(9, 8, 4, 5, 3),
        arrayOf(6, 4, 3, 8, 7),
        arrayOf(7, 5, 8, 4, 8),
        arrayOf(2, 3, 7, 8, 1)
    )

    val isSymmetric = isSymmetric(array)
    if (isSymmetric) {
        println("Массив симметричен относительно главной диагонали")
    } else {
        println("Массив не симметричен относительно главной диагонали")
    }

    val keyword = "ПОЛЕ"
    val originalText = "СООБЩЕНИЕ"

    val encryptedText = encryptText(originalText, keyword)
    println("Зашифрованный текст: $encryptedText")

    val decryptedText = decryptText(encryptedText, keyword)
    println("Расшифрованный текст: $decryptedText")


    val array1 = intArrayOf(1, 2, 3, 2, 0)
    val array2 = intArrayOf(5, 1, 2, 7, 3, 2, 2)

    val intersection = findIntersection(array1, array2)
    println("Пересечение массивов: ${intersection.contentToString()}")

    val words = listOf("eat", "tea", "tan", "ate", "nat", "bat")

    val groupedWords = groupWordsByLetters(words)
    for ((key, value) in groupedWords) {
        println("$key: ${value.joinToString(", ")}")
    }
}