@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.OutputUtil.writeln
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
    var fibn1 = 1
    var fibn2 = 1
    var fibSum = 0
    if ((n == 1) || (n == 2)) return 1
    for (i in 3..n) {
        fibSum = fibn1 + fibn2
        fibn1 = fibn2
        fibn2 = fibSum
    }
    return fibSum
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */

fun lcm(m: Int, n: Int): Int {
    var gcd1 = m
    var gcd2 = n
    while (gcd1 != 0 && gcd2 != 0) if (gcd1 > gcd2) gcd1 %= gcd2 else gcd2 %= gcd1
    return abs(m * n) / (gcd1 + gcd2)
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var minDiv = 2
    while (n % minDiv != 0) minDiv += 1
    return minDiv
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var maxDiv = n - 1
    for (i in maxDiv downTo 1)
    if (n % maxDiv != 0) maxDiv -= 1
    return maxDiv
}


/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var m1 = m
    var n1 = n
    while (m1 != n1 )  {
    if (m1 >= n1) m1 -= n1 else n1 -= m1
    }
    return m1 == 1 && n1 == 1
}



/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    if (m == 0 && n == 0 ) return false
    for (i in minOf(m,n)..maxOf(m,n)) if (i % sqrt(i.toDouble()) == 0.0)  return true
    return false
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double = TODO()
        // minus *= -1
        //sinx += minus * pow(x, pow)/factorial(pow.toInt())



/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431. 
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var n1 = n
    var rev1 = 0
    while (n1 != 0) {
       val rev = n1 % 10
        rev1 = (rev1 + rev) * 10
        n1 /= 10
    }
    return rev1 / 10
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
    var n1 = n
    var sumDigit = 0
    var left = n
    while (n1 != 0) {
        n1 /= 10
        sumDigit += 1
    }
    if (sumDigit == 1) return true
    for (i in 1..sumDigit / 2) {
        right *= 10
        right += left % 10
        left /= 10
    }
    if (sumDigit % 2 != 0)  left /= 10
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
    var nMeter = n
    var firDigit = 0
    var secDigit = 0
    while (nMeter > 0) {
        if (firDigit != secDigit) return firDigit != secDigit
        firDigit = nMeter % 10
        nMeter /= 10
        if (nMeter / 10 != 0) secDigit = nMeter % 10
    }
  return firDigit != secDigit
}



/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int = TODO()
