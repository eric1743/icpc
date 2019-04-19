package icpc.naipc.f2019

fun main(args: Array<String>) {
    val s = readLine()!!
    val t = readLine()!!

    var seq = nextSubSeq(s, t, MutableList(t.length){-1})
    val seqs = mutableListOf(seq)
    while (seq[t.length - 1] != -1){
        seq = nextSubSeq(s, t, seq)
        seqs += seq
    }
    var out = 0L
    for(i in 0 until seqs.size - 1){
        val p = seqs[i]
        val lead = p[0] + 1L
        val nextLast = seqs[i+1][t.length - 1]
        val tail =
                if(nextLast == -1) s.length - p[t.length - 1]
                else nextLast - p[t.length - 1]
        out += lead * tail
//        println("$p, $nextSecond, $lead, $tail")
    }
    println(out)
}

fun nextSubSeq(s: String, t: String, prev: MutableList<Int>): MutableList<Int>{
    var lastIdx = prev[0]
    val cur = MutableList(t.length){-1}
    for(tIdx in 0 until t.length){
        lastIdx = nextMatchChar(s, t[tIdx], maxOf(lastIdx + 1, prev[tIdx]))
        cur[tIdx] = lastIdx
        if (lastIdx == -1) break
    }
    return cur
}

fun nextMatchChar(s: String, c: Char, i: Int): Int{
    var ret = -1
    for(idx in i until s.length){
        if( s[idx] == c){
            ret = idx
            break
        }
    }
    return ret
}