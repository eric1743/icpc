package icpc.finals.f2019

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = File("/Users/z00327d/src/tools/icpc/src/A-azulejos/secret-05.in").bufferedReader()
//    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val bpArr = br.readLine().split(" ").map { it.toInt() }
    val bhArr = br.readLine().split(" ").map { it.toInt() }
    val fpArr = br.readLine().split(" ").map { it.toInt() }
    val fhArr = br.readLine().split(" ").map { it.toInt() }

    val backTree: SortedMap<Int, TreeSet<Tile>> = sortedMapOf()
    val frntTree: SortedMap<Int, TreeSet<Tile>> = sortedMapOf()

    for (i in 0 until n) {
        backTree.getOrPut(bpArr[i]) { sortedSetOf() }
                .add(Tile(i + 1, bpArr[i], bhArr[i]))
        frntTree.getOrPut(fpArr[i]) { sortedSetOf(reverseOrder()) }
                .add(Tile(i + 1, fpArr[i], fhArr[i]))
    }

    var bPricePoint = backTree.pop()
    var fPricePoint = frntTree.pop()
    val bOut = mutableListOf<Int>()
    val fOut = mutableListOf<Int>()
    for (i in 0 until n) {
        if (bPricePoint.isEmpty()) {
            bPricePoint = backTree.pop()
        }
        if (fPricePoint.isEmpty()) {
            fPricePoint = frntTree.pop()
        }
        var bTile: Tile?
        var fTile: Tile?
        if (bPricePoint.size > fPricePoint.size) {
            bTile = bPricePoint.pollLast()
            fTile = fPricePoint.pop(bTile)
        } else {
            fTile = fPricePoint.pollFirst()
            bTile = bPricePoint.pop(fTile)
        }
        if (bTile == null || fTile == null || fTile.height >= bTile.height) {
            println("impossible")
            return
        }
        bOut += bTile.id
        fOut += fTile.id
    }
    for (idx in bOut) {
        print("$idx ")
    }
    println()
    for (idx in fOut) {
        print("$idx ")
    }
}

private fun SortedMap<Int, TreeSet<Tile>>.pop(): TreeSet<Tile> {
    return this.remove(this.firstKey())!!
}

private fun TreeSet<Tile>.pop(tile: Tile): Tile? {
    val newTile = this.higher(tile)
    if (newTile != null)
        this.remove(newTile)
    return newTile
}

data class Tile(val id: Int, val price: Int, val height: Int) : Comparable<Tile> {
    override fun compareTo(other: Tile): Int {
        if (height == other.height) return id.compareTo(other.id)
        return height.compareTo(other.height)
    }
}