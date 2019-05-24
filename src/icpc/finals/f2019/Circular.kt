package icpc.finals.f2019

fun main() {
    readLine() //ignore n
    val genes = readLine()!!.split(" ").map { Gene(it) }

    val map = mutableMapOf<Int, GeneType>()
    for (gene in genes) {
        val geneType = map.getOrPut(gene.type) { GeneType(gene.type) }
        geneType.tot += gene.se
        geneType.track = maxOf(gene.se + geneType.track, 0) //if track goes negative reset it to 0
    }

    var cur = 0
    for (geneType in map.values) {
        if (geneType.tot == 0) geneType.valid = true
        else if (geneType.track == 0) cur++
    }

    var hi = cur
    var hiIdx = 1
    for ((idx, gene) in genes.withIndex()) {
        val geneType = map.get(gene.type)!!
        if (!geneType.valid) continue
        if (geneType.track == 0) cur--
        geneType.track += gene.se
        if (geneType.track == 0) cur++
        if (cur > hi) {
            hi = cur
            hiIdx = idx + 2
        }
    }
    println("$hiIdx $hi")
}

data class Gene(val gene: String) {
    val se = if (gene[0] == 's') 1 else -1
    val type = gene.substring(1).toInt()
}

data class GeneType(val type: Int) {
    var tot = 0
    var track = 0
    var valid = false
}