@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.*

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var module = 0.0
    for (i in v) {
        module += i * i
    }
    return sqrt(module)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var sum = 0.0
    for (i in list) {
        sum += i
    }
    if (list.size == 0) return list.size.toDouble()
    return sum / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.size == 0) return list
    var mean = mean(list)
    for (i in 0..list.size - 1) {
        list[i] -= mean
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var vector = 0
    for (i in 0..a.size - 1) {
        vector += a[i] * b[i]
    }
    return vector
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var ans = 0
    var xPow = 1
    for (i in p) {
        ans += xPow * i
        xPow *= x
    }
    return ans
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1..list.size - 1) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var num = n
    var divisors = mutableListOf<Int>()
    while (num % 2 == 0) {
        num /= 2
        divisors.add(2)
    }
    var divisor = 3
    while (num > 1 && divisor <= sqrt(n.toDouble())) {
        while (num % divisor == 0) {
            divisors.add(divisor)
            num /= divisor
        }
        divisor += 2
    }
    if (num > 1)
        divisors.add(num)
    return divisors
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    var divisors = factorize(n)
    var ans = ""
    for (i in divisors) {
        ans += "$i*"
    }
    ans = ans.substring(0, ans.length - 1)
    return ans
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var num = n
    if (n == 0)
        return listOf(0)
    var list = mutableListOf<Int>()
    while (num > 0) {
        list.add(num % base)
        num /= base
    }
    list.reverse()
    return list
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var list = convert(n, base)
    var ans = ""
    if (n == 0) return "0"
    for (i in list.indices) {
        if (list[i] > 9) {
            ans += (87 + list[i]).toChar()
        } else {
            ans += (48 + list[i]).toChar()
        }
    }
    return ans
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var maxBasePower = base.toDouble().pow(digits.count() - 1).toInt()
    var ans = 0
    for (i in digits) {
        ans += i * maxBasePower
        maxBasePower /= base
    }
    return ans
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var number = 0
    var maxBasePower = base.toDouble().pow(str.length - 1).toInt()
    for (i in str) {
        if (i in 'a'..'z') {
            number += i.minus(87).toInt() * maxBasePower
        } else {
            number += (i.toInt() - 48) * maxBasePower
        }
        maxBasePower /= base
    }
    return number
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var num = n
    var ans = ""
    while (num / 1000 > 0) {
        num -= 1000
        ans += "M"
    }
    while (num / 900 > 0) {
        num -= 900
        ans += "CM"
    }
    while (num / 500 > 0) {
        num -= 500
        ans += "D"
    }
    while (num / 400 > 0) {
        num -= 400
        ans += "CD"
    }
    while (num / 100 > 0) {
        num -= 100
        ans += "C"
    }
    while (num / 90 > 0) {
        num -= 90
        ans += "XC"
    }
    while (num / 50 > 0) {
        num -= 50
        ans += "L"
    }
    while (num / 40 > 0) {
        num -= 40
        ans += "XL"
    }
    while (num / 10 > 0) {
        num -= 10
        ans += "X"
    }
    while (num / 9 > 0) {
        num -= 9
        ans += "IX"
    }
    while (num / 5 > 0) {
        num -= 5
        ans += "V"
    }
    while (num / 4 > 0) {
        num -= 4
        ans += "IV"
    }
    while (num / 1 > 0) {
        num--
        ans += "I"
    }
    return ans
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var ans = ""
    var num = n
    var digit = 1
    while (num > 0) {
        if (digit == 1 || digit == 4) {
            if (num % 100 /10 == 1) {
                if (digit == 4) ans = "тысяч $ans"
                    when (num % 10) {
                    1 -> ans = "одиннадцать $ans"
                    2 -> ans = "двенадцать $ans"
                    3 -> ans = "тринадцать $ans"
                    4 -> ans = "четырнадцать $ans"
                    5 -> ans = "пятнадцать $ans"
                    6 -> ans = "шестнадцать $ans"
                    7 -> ans = "семнадцать $ans"
                    8 -> ans = "восемнадцать $ans"
                    9 -> ans = "девятнадцать $ans"
                    0 -> ans = "десять $ans"
                }
                digit++
                num /= 10
            } else {
                when (num % 10) {
                    1 -> if (digit == 4) ans = "одна тысяча $ans"
                    else ans += "один"
                    2 -> if (digit == 4) ans = "две тысячи $ans"
                    else ans += "два"
                    3 -> if (digit == 4) ans = "три тысячи $ans"
                    else ans += "три"
                    4 -> if (digit == 4) ans = "четыре тысячи $ans"
                    else ans += "четыре"
                    5 -> if (digit == 4) ans = "пять тысяч $ans"
                    else ans += "пять"
                    6 -> if (digit == 4) ans = "шесть тысяч $ans"
                    else ans += "шесть"
                    7 -> if (digit == 4) ans = "семь тысяч $ans"
                    else ans += "семь"
                    8 -> if (digit == 4) ans = "восемь тысяч $ans"
                    else ans += "восемь"
                    9 -> if (digit == 4) ans = "девять тысяч $ans"
                    else ans += "девять"
                    0 -> if (digit == 4) ans = "тысяч $ans"
                }
            }
        }
        if (digit == 2 || digit == 5) {
            when (num % 10) {
                2 -> ans = "двадцать $ans"
                3 -> ans = "тридцать $ans"
                4 -> ans = "сорок $ans"
                5 -> ans = "пятьдесят $ans"
                6 -> ans = "шестьдесят $ans"
                7 -> ans = "семьдесят $ans"
                8 -> ans = "восемьдесят $ans"
                9 -> ans = "девяносто $ans"
            }
        }
        if (digit == 3 || digit == 6) {
            when (num % 10) {
                1 -> ans = "сто $ans"
                2 -> ans = "двести $ans"
                3 -> ans = "триста $ans"
                4 -> ans = "четыреста $ans"
                5 -> ans = "пятьсот $ans"
                6 -> ans = "шестьсот $ans"
                7 -> ans = "семьсот $ans"
                8 -> ans = "восемьсот $ans"
                9 -> ans = "девятьсот $ans"
            }
        }
        num /= 10
        digit++
    }
    ans = ans.trim()
    return ans
}