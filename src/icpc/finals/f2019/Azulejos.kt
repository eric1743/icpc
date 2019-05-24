package icpc.finals.f2019

import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val backTree = Tree(br, n, false)
    val frntTree = Tree(br, n, true)

    val bOut = BufferedWriter(OutputStreamWriter(System.out), 4000000)
    val fOut = BufferedWriter(OutputStreamWriter(System.out), 4000000)
    repeat(n) {

        val (bTile, fTile) = matchTiles(backTree, frntTree)

        if (bTile == null || fTile == null) {
            println("impossible")
            return
        }

        bOut.append("${bTile.id} ")
        fOut.append("${fTile.id} ")
    }

    bOut.flush()
    println()
    fOut.flush()
}

fun matchTiles(bTree: Tree, fTree: Tree): Pair<Tile?, Tile?> {
    if (bTree.checkSize() < fTree.checkSize()) {
        val tile = bTree.poll()
        return Pair(tile, fTree.poll(tile))
    }
    val tile = fTree.poll()
    return Pair(bTree.poll(tile), tile)
}

class Tree(br: BufferedReader, n: Int, isFront: Boolean) {
    val tree: SortedMap<Int, TreeSet<Tile>> = sortedMapOf()
    private lateinit var pricePoint: TreeSet<Tile>

    init {
        val prices = br.readLine().split(" ").map { it.toInt() }
        val heights = br.readLine().split(" ").map { it.toInt() }
        repeat(n) {
            tree.getOrPut(prices[it]) {
                if (isFront) sortedSetOf(reverseOrder())
                else sortedSetOf()
            }
                    .add(Tile(it + 1, prices[it], heights[it], isFront))
        }
        checkSize()
    }

    fun checkSize(): Int {
        if (!this::pricePoint.isInitialized || pricePoint.isEmpty()) {
            pricePoint = tree.remove(tree.firstKey())!!
        }
        return pricePoint.size
    }

    fun poll() = run { pricePoint.pollFirst() }

    fun poll(tile: Tile): Tile? {
        val newTile = pricePoint.higher(tile)
        newTile?.let { pricePoint.remove(newTile) } //only removes if newTile isn't null
        return newTile
    }
}

data class Tile(val id: Int, val price: Int, val height: Int, val isFront: Boolean) : Comparable<Tile> {
    override fun compareTo(other: Tile): Int {
        if (height == other.height && isFront == other.isFront) {
            return id.compareTo(other.id)
        }
        return height.compareTo(other.height)
    }
}