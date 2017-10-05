@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import java.lang.Math.abs
import kotlin.reflect.jvm.internal.impl.util.Check

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val a = age%10 // извлечь последнюю цифру от числа.
    val b = age%100 //числа от 11 до 19,  ""11 лет, 12 лет и тд.".
         if (b in 11..19)             return "$age лет"
         else
         if (a == 1)                  return "$age год"
         else
         if ((a in 5..9) || (a == 0)) return "$age лет"
         else
         if (a in 2..4)               return "$age года"
    return "Error"
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val s1 = v1 * t1                //  расстояние №1.
    val s2 = v2 * t2                //  расстояние №2.
    val s3 = v3 * t3                //  расстояние №3.
    val sHalf = ((s1 + s2 + s3) / 2) // половина всего расстояния.
    val sDecision1 = t1 + (sHalf - s1) / v2                // Решение условия: ((sHalf > s1) && (sHalf <= s1 + s2)).
    val sDecision2 = sHalf / v1                            // Решение условия: (sHalf < s1).
    val sDecision3 = ((sHalf - (s1 + s2)) / v3) + t1 + t2  // Решение условия: (sHalf > s1 + s2).
    when {
        ((sHalf > s1) && (sHalf <= s1 + s2)) -> return sDecision1    // Подставляем решения условий
        (sHalf < s1)                         -> return sDecision2
        (sHalf > s1 + s2)                    -> return sDecision3
    }
    return Double.NaN
}


/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    when {
        // 1. Если атакуют обе ладьи.
        ((kingX == rookX1) || (kingY == rookY1)) && ((kingX == rookX2) || (kingY == rookY2)) -> return 3
        // 2. Если атакует только вторая ладья.
        ((kingX == rookX2) || (kingY == rookY2)) && ((kingX != rookX1) || (kingY != rookY1)) -> return 2
        // 3. Если атакует только первая ладья.
        ((kingX == rookX1) || (kingY == rookY1)) && ((kingX != rookX2) || (kingY != rookY2)) -> return 1
        // 4. Если угрозы нет.
        ((kingY != rookY1) && (kingY != rookY2) && (kingX != rookX1) && (kingX != rookX2))    -> return 0

    }
    return 404
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    when {
        ((abs(kingX - bishopX)) == (abs(kingY - bishopY)))  && ((kingX == rookX) || (kingY == rookY)) -> return 3
        ((abs(kingX - bishopX)) == (abs(kingY - bishopY)))  -> return 2
        ((kingX == rookX) || (kingY == rookY)) -> return 1
        else -> return 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val CheckAngleForA = sqr(b) + sqr(c)
    val CheckAngleForC = sqr(a) + sqr(b)
    val CheckAngleForB = sqr(a) + sqr(c)
    if ((a < b + c) && (b < a + c) && (c < b + a)) else return -1  // Если треугольник не существует
    if ((a >= b) && (a >= c))
        when {
            CheckAngleForA == sqr(a) -> return 1
            CheckAngleForA > sqr(a)  -> return 0
            CheckAngleForA < sqr(a)  -> return 2
        }
    else
    if ((c >= a) && (c >= b))
    when {
        CheckAngleForC == sqr(c) -> return 1
        CheckAngleForC > sqr(c)  -> return 0
        CheckAngleForC < sqr(c)  -> return 2
    }
    else
    if ((b >= a) && (b >= c))
    when {
        CheckAngleForB == sqr(b) -> return 1
        CheckAngleForB > sqr(b)  -> return 0
        CheckAngleForB < sqr(b)  -> return 2
    }
    return -1
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
        when {
            // 1. Если пересечение является отрезоком cb.
            (c > a) && (c < b) && (b < d)            -> return (b - c)
            // 2. Если пересечение является отрезоком cd.
            (c < b) && (c > a) && ((b > d) || (b == d)) && (d > a) -> return (d - c)
            (c < b) && (c == a) && (b > d) && (d > a)              -> return (d - c)
            // 3. Если пересечение является отрезоком ab.
            (c < a) && (c < b) && ((d > b) || (d ==b)) && (d > a)  -> return (b - a)
            (c == a) && (c < b) && (d > b) && (d > a)              -> return (b - a)
            ((a == c) && (b == d)) -> return (b - a)
            // 4. Если пересечение является отрезком  ad.
            (c < a) && (c < b) && (d > a) && (d < b) -> return (d - a)
            // 5. Если точки не персекаются
            (a > c) && (a > d) && (b > c) && (b > d) -> return -1
            (c > a) && (c > b) && (d > a) && (d > b) -> return -1
            // 6. Если точки отрезков лежат друг на друге или только одна точка одного отрезка лежит на одной точке другого отрезка.
            else -> return 0

        }
}
