package baylorcompetitivelearningcourse

import java.io.BufferedReader
import java.io.DataInputStream
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
//    val br = BufferedReader(InputStreamReader(System.`in`))
    val reader = Reader2()
    while (true) {
//        val raw = br.readLine()
//        if (raw.isNullOrBlank()) break
//        val line = raw.split(" ").map { it.toInt() }
        val r = Rand(reader.nextInt())
        val n = reader.nextInt()
        val map = MutableList(10000) { -1 }
        val trees = mutableListOf<Int>()
        var out = 0
        repeat(n) {
            if (it != 0 && it % 100 == 0){
                print(out.toString() + " ")
                out = 0
            }
            var m: Int
            while (true) {
                m = r.next() % 10000
                if (map[m] == -1) break
            }
            trees.add(m)
            map[m] = m
            map.joinAdj(m)
            val A = r.next() % (it + 1)
            val B = r.next() % (it + 1)
            if (map.find(trees[A]) == map.find(trees[B])) out++
        }
        println()
    }
}

fun MutableList<Int>.find(child: Int): Int {
    val parent = this[child]
    if (parent == child) return child
    this[child] = this.find(parent)
    return this[child]
}

fun MutableList<Int>.joinAdj(tree: Int) {
    if (tree % 100 != 0 && this[tree - 1] != -1)
        this[this.find(tree - 1)] = tree
    if (tree % 100 != 99 && this[tree + 1] != -1)
        this[this.find(tree + 1)] = tree
    if (tree / 100 != 0 && this[tree - 100] != -1)
        this[this.find(tree - 100)] = tree
    if (tree / 100 != 99 && this[tree + 100] != -1)
        this[this.find(tree + 100)] = tree
}

class Rand(var seed: Int) {
    fun next(): Int {
        seed = (seed * 5171 + 13297) % 50021
        return seed
    }
}

private class Reader2(){
    val buffSize = 1.shl(16)
    val din = DataInputStream(System.`in`)
    var buffer = ByteArray(buffSize)
    var bufferPointer = 0
    var bytesRead = 0

    fun read(): Byte {
        if (bufferPointer == bytesRead) {
            bufferPointer = 0
            bytesRead = din.read(buffer, bufferPointer, buffSize)
        }
        return buffer[bufferPointer++]
    }

    fun nextInt(): Int {
        var ret = 0
        var c = read()
        while (!c.isDigit2()) c = read()
        while (c.isDigit2()) {
            ret = ret * 10 + (c - '0'.toByte())
            c = read()
        }
        return ret
    }
}

fun Byte.isDigit2() = (this >= '0'.toByte() && this <= '9'.toByte())
