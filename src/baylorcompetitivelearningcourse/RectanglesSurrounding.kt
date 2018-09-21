package baylorcompetitivelearningcourse

import java.io.DataInputStream

fun main(args: Array<String>) {
    val reader = Reader()
    var n = reader.nextInt()
    while (n != 0) {
        val board = MutableList<MutableList<Int>?>(500) { null }
        repeat(n) {
            val a = reader.nextInt()
            val b = reader.nextInt()
            val c = reader.nextInt()
            val d = reader.nextInt()
            for (i in a until c) {
                if (board[i] == null) board[i] = mutableListOf()  //todo implement lazy
                val list = board[i]!!
                //BinSearch, idx if found, (-insertionIndex - 1) if not
                val l = list.binarySearch(b)
                val r = list.binarySearch(d)
                if (l == r && l % 2 == 0) {
                    continue
                } else {
                    val lidx = if (l >= 0) l else -l - 1                //if found stay same, else next to the right
                    val ridx = if (r >= 0) r else -r - 2                //if found stay same, else next to the left
                    for (idx in ridx downTo lidx) list.removeAt(idx)    //remove overlapped
                    if (r % 2 != 0) list.add(lidx, d)                   //if external(-odd) or closing (+odd)
                    if ((l < 0) xor (l % 2 == 0)) list.add(lidx, b)     //if external (-odd) or open (+even)   neg xor even
                }
            }
        }
        var out = 0
        for (list in board)
            if (list != null)
                for (j in 0 until list.size)
                    out += if (j % 2 == 0) -list[j] else list[j]

        println(out)
        n = reader.nextInt()
    }
}

//class Reader() {
//    val br = BufferedReader(InputStreamReader(System.`in`))
//    val digit = 48..57
//
//    fun nextInt(): Int {
//        var ret = 0
//        var c = br.read()
//        while (!(c in digit)) c = br.read()
//        while (c in digit) {
//            ret = ret * 10 + (c - 48)
//            c = br.read()
//        }
//        return ret
//    }
//}

private class Reader() {
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
        while (!c.isDigit()) c = read()
        while (c.isDigit()) {
            ret = ret * 10 + (c - '0'.toByte())
            c = read()
        }
        return ret
    }
}

fun Byte.isDigit() = (this >= '0'.toByte() && this <= '9'.toByte())

//class Reader(){
//    val br = BufferedReader(InputStreamReader(System.`in`))
//    var st = StringTokenizer(br.readLine())
//    fun next(): String{
//        if(!st.hasMoreTokens()){
//            st = StringTokenizer(br.readLine())
//        }
//        return st.nextToken()
//    }
//    fun nextInt(): Int{
//        return next().toInt()
//    }
//}

