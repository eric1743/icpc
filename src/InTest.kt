import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val reader = Reader()
    val n = reader.nextInt()
    val k = reader.nextInt()
    var out = 0
    repeat(n) {
        val m = reader.nextInt()
        if (m % k == 0) out++
    }
    println(out)
}

//class Reader() { //.70
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


//class Reader() {  //.20
//    val buffSize = 1.shl(16)
//    val din = DataInputStream(System.`in`)
//    var buffer = ByteArray(buffSize)
//    var bufferPointer = 0
//    var bytesRead = 0
//
//    fun read(): Byte {
//        if (bufferPointer == bytesRead) {
//            bufferPointer = 0
//            bytesRead = din.read(buffer, bufferPointer, buffSize)
//        }
//        return buffer[bufferPointer++]
//    }
//
//    fun nextInt(): Int {
//        var ret = 0
//        var c = read()
//        while (!c.isDigit()) c = read()
//        while (c.isDigit()) {
//            ret = ret * 10 + (c - '0'.toByte())
//            c = read()
//        }
//        return ret
//    }
//}
//
//fun Byte.isDigit(): Boolean {
//    return (this >= '0'.toByte() && this <= '9'.toByte())
//}

class Reader() {   // 0.59
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    fun next(): String {
        if (!st.hasMoreTokens()) {
            st = StringTokenizer(br.readLine())
        }
        return st.nextToken()
    }

    fun nextInt(): Int {
        return next().toInt()
    }
}
