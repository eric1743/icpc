package icpc.naipc.f2018

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.absoluteValue
import kotlin.math.sqrt

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val a = br.readLine().toInt()
    val pa = Array<Point>(a, { Point(br.readLine()) })

    val b = br.readLine().toInt()
    val pb = Array<Point>(b, { Point(br.readLine()) })

    val es = Array(b, { arrayOfNulls<Point>(2) })
    repeat(b) {
        val s1 = Segment(pb[it], pb[it + 1 % b])
        repeat(a) {

        }
    }
}


class Point : Comparable<Point> {
    var x = 0.0
    var y = 0.0
    val eps = 1e-10

    constructor(x: Double, y: Double) {
        this.x = x
        this.y = y
    }

    constructor(str: String) {
        val line = str.split(" ")
        Point(line[0].toDouble(), line[1].toDouble())
    }

    override fun equals(o: Any?): Boolean {
        o as Point
        return (x - o.x).absoluteValue < eps && (y - o.y).absoluteValue < eps
    }

    override fun compareTo(o: Point): Int {
        if ((x - o.x).absoluteValue > eps)
            return x.compareTo(o.x)
        if ((y - o.y).absoluteValue > eps)
            return y.compareTo(o.y)
        return 0
    }
}

class Segment(val a: Point, val b: Point) {
    val length = distance(a, b)
}

fun distance(a: Point, b: Point): Double {
    return fastHypot(a.x - b.x, a.y - b.y)
}

fun fastHypot(x: Double, y: Double): Double {
    return sqrt(x * x + y * y)
}

fun ccw(p: Point, q: Point, r: Point) {

}