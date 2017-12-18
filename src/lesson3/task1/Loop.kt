@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.OutputUtil.writeln
import lesson1.task1.sqr
import java.lang.Math.*

/**
 * Пример
 *
 * Вычисление факториала.
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var n0 = n
    var result = 0
    if (n == 0) return 1
    while (n0 != 0) {
        n0 /= 10
        result += 1
    }
    return result
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var fib1 = 1
    var fib2 = 1
    var fibSum = 0
    if ((n == 1) || (n == 2)) return 1
    for (i in 3..n) {
        fibSum = fib1 + fib2
        fib1 = fib2
        fib2 = fibSum
    }
    return fibSum
}

fun gcd(m: Int, n: Int): Int {
    var m1 = m
    var n1 = n
    while (m1 != n1) {
        if (m1 > n1) m1 -= n1 else n1 -= m1
    }
    return m1
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int = m / gcd(m, n) * n

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..Math.sqrt(n.toDouble()).toInt())
        if (n % i == 0) return i
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n / 2 downTo Math.sqrt(n.toDouble()).toInt())
        if (n % i == 0) return i
    return 1
}


/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = gcd(m, n) == 1


/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in m..n) {
        val sqrtDigit = sqrt(i.toDouble()).toInt()
        if (sqr(sqrtDigit.toDouble()).toInt() == i) return true
    }
    return false
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun decisionSinCos(x: Double, eps: Double, pow: Double): Double {
    var sinOrCos = if (pow == 1.0) 0.0 else 1.0
    val sequence = x % (2 * PI)
    var n = sequence
    var start = if (pow == 1.0) sequence else sqr(sequence)
    var meter = pow
    var minus1 = if (pow == 1.0) 1 else -1
    var factorial = meter
    while (eps < abs(n)) {
        n = start / factorial
        sinOrCos += n * minus1
        minus1 *= -1
        meter += 2.0
        factorial *= meter * (meter - 1.0)
        start *= sqr(sequence)
    }
    return sinOrCos
}

fun sin(x: Double, eps: Double): Double {
    val pow = 1.0
    return decisionSinCos(x, eps, pow)
}


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    val pow = 2.0
    return decisionSinCos(x, eps, pow)
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var n1 = n
    var rev = 0
    while (n1 != 0) {
        rev = rev * 10 + (n1 % 10)
        n1 /= 10
    }
    return rev
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    var right = 0
    val digit = digitNumber(n)
    var left = n
    for (i in 1..digit / 2) {
        right *= 10
        right += left % 10
        left /= 10
    }
    if (digit % 2 != 0) left /= 10
    return left == right
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    if (n / 10 == 0) return false
    val c = n % 10
    var n1 = n
    for (i in 1 until digitNumber(n)) {
        val pow = i.toDouble()
        n1 /= 10
        val result = n1 % pow(10.0, pow)
        if (c.toDouble() != result) return true
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun digNumber(digNumber: Int, result: Int): Int {
    var r = result
    for (i in 0 until digNumber) r /= 10
    return r % 10
}

fun squareSequenceDigit(n: Int): Int {
    var digits = 0
    var result = 1
    var digNumber = 0
    while (digNumber < n) {
        digits += 1
        result = digits * digits
        digNumber += digitNumber(result)
    }
    return digNumber(digNumber - n, result)
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var digits = 0
    var result = 1
    var digNumber = 0
    while (digNumber < n) {
        digits += 1
        result = lesson3.task1.fib(digits)
        digNumber += digitNumber(result)
    }
    return digNumber(digNumber - n, result)
}