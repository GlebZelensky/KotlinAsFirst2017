@file:Suppress("UNUSED_PARAMETER")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.digitNumber
import lesson3.task1.minDivisor
import java.lang.Double.NaN
import java.lang.Math.pow
import java.lang.Math.sqrt
import java.util.Collections.list
import javax.lang.model.element.NestingKind

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
                val root = Math.sqrt(y)
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
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
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
    var result = 0.0
    for (element in v) {
        result += sqr(element)
    }
    return sqrt(result)
}


/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isEmpty()) 0.0 else list.sum() / list.size


/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mid = mean(list)
    for (i in 0 until list.size) {
        list[i] -= mid
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var ci = 0.0
    for (i in 0 until a.size) {
        ci += a[i] * b[i]
    }
    return ci
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var poly = 0.0
    var powX = 1.0
    for (i in 0 until p.size) {
        poly += p[i] * powX
        powX *= x
    }
    return poly
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
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    for (i in 1 until list.size) {
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
    val resultList = mutableListOf<Int>()
    var n1 = n
    while (n1 > 1) {
        val div = minDivisor(n1)
        resultList.add(div)
        n1 /= div
    }
    return resultList
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val p = mutableListOf<Int>()
    var n1 = n
    if (n == 0) return listOf(0)
    while (n1 > 0) {
        p.add(n1 % base)
        n1 /= base
    }
    return p.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val listOfChars = StringBuilder()
    val convert = convert(n, base)
    for (i in 0 until convert.size) {
        if (convert[i] > 9) {
            listOfChars.append('a' + convert[i] - 10)
        } else {
            listOfChars.append(convert[i])
        }
    }
    return listOfChars.toString()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    var powBase = 1
    var p = digits.size - 1
    for (i in 0 until digits.size) {
        result += digits[p] * powBase
        powBase *= base
        p--
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    val abcChars = "abcdefghijklmnopqrstuvwxyz"
    val listOfInt = mutableListOf<Int>()
    for (i in 0 until str.length) {
        if (str[i] in abcChars) listOfInt.add(abcChars.indexOf(str[i], 0) + 10)
        else listOfInt.add(str[i].toString().toInt())
    }
    return decimal(listOfInt.toList(), base)
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
    val result = StringBuilder()
    if (n < 1000) return romanHundreds(n)
    else for (i in 1..n / 1000) {
        result.append("M")
    }
    return result.toString() + romanHundreds(n % 1000)
}

fun romanHundreds(n: Int): String {
    val romanUnits = listOf("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")
    val romanDozens = listOf("X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
    val romanHundreds = listOf("C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
    val listOfResults = StringBuilder()
    var n1 = n
    if (n >= 100) {
        listOfResults.append(romanHundreds[n / 100 - 1])
        n1 %= 100
    }
    if (n1 >= 10) {
        listOfResults.append(romanDozens[n1 / 10 - 1])
        n1 %= 10
    }
    if (n1 > 0) {
        listOfResults.append(romanUnits[n1 - 1])
    }
    return if (listOfResults.isEmpty()) return ""
    else listOfResults.toString()
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    if (n < 1000) return forHundreds(n).trim()
    else {
        val resultList = mutableListOf<String>()
        val thousands = listOf("одна", "две", "три", "четыре",
                "пять", "шесть", "семь", "восемь", "девять")
        val tester = n % 100000
        val hundreds = forHundreds(n % 1000)
        if (n >= 100000) {
            resultList.add(lesson4.task1.hundreds[n / 100000 - 1])
        }
        if (tester >= 10000) {
            if (tester in 10000..19999) {
                resultList.add(decade[(tester / 1000 % 10)])
                return resultList.joinToString(separator = " ") + " тысяч" + hundreds
            } else resultList.add(dozens[tester / 10000 - 1])
        }
        if (tester >= 1000) {
            val howManyThousands = n % 10000 / 1000
            when (howManyThousands) {
                0 -> resultList.add("тысяч")
                1 -> resultList.add("одна тысяча")
                in 2..4 -> resultList.add(thousands[howManyThousands - 1] + " тысячи")
                in 5..9 -> resultList.add(thousands[howManyThousands - 1] + " тысяч")
            }
        } else return resultList.joinToString(separator = " ") + " тысяч" + hundreds
        return resultList.joinToString(separator = " ") + hundreds
    }
}

val dozens = listOf("десять", "двадцать", "тридцать", "сорок", "пятьдесят",
        "шестьдесят", "семьдесят", "восемьдесят", "девяносто")
val decade = listOf("десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
        "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать")
val hundreds = listOf("сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот")

fun forHundreds(n: Int): String {
    val units = listOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val result = mutableListOf<String>()
    var changer = n
    if (n >= 100) {
        result.add(hundreds[n / 100 - 1])
        changer = n % 100
    }
    if (changer >= 10) {
        if (changer in 10..19) {
            result.add(decade[(changer % 10)])
            return result.joinToString(separator = " ")
        }
        result.add(dozens[changer / 10 - 1])
        changer = n % 10
    }
    if (changer > 0) {
        result.add(units[changer - 1])
    }
    return if (result.isNotEmpty()) " " + result.joinToString(separator = " ")
    else return ""
}
