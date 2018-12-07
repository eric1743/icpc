//package baylorcompetitivelearningcourse
//
//import java.io.DataInputStream
//
//fun main(args: Array<String>) {
//    val reader = Reader2()
//    while (true) {
//        val seed = reader.nextInt()
//        if (!reader.hasNext) return
//        val r = Rand(seed)
//        val n = reader.nextInt()
//        val uMap = MutableList(10000) { -1 }
//        val planted = mutableListOf<Int>()
//        var out = 0
//        repeat(n) {
//            if (it != 0 && it % 100 == 0) {
//                print("$out ")
//                out = 0
//            }
//            while (uMap[r.incMod(10000)] != -1) { }
//            val tree = r(10000)
//            planted += tree
//            uMap[tree] = tree
//            uMap.joinAdj(r(10000))
//            val A = r.incMod(it + 1)
//            val B = r.incMod(it + 1)
//            if (uMap(planted[A]) == uMap(planted[B])) out++
//        }
//        println(out)
//    }
//}
//
//operator fun MutableList<Int>.invoke(child: Int): Int {  //find parent
//    val parent = this[child]
//    if (parent == child) return child
//    this[child] = this(parent)
//    return this[child]
//}
//
//fun MutableList<Int>.joinAdj(tree: Int) {
//    if (tree % 100 != 0 && this[tree - 1] != -1) //check if on left
//        this[this(tree - 1)] = tree
//    if (tree % 100 != 99 && this[tree + 1] != -1) //check if on right
//        this[this(tree + 1)] = tree
//    if (tree / 100 != 0 && this[tree - 100] != -1) //check if on bottom
//        this[this(tree - 100)] = tree
//    if (tree / 100 != 99 && this[tree + 100] != -1) //check if on top
//        this[this(tree + 100)] = tree
//}
//
//class Rand(var seed: Int) {
////    operator fun inc() = Rand((seed * 5171 + 13297) % 50021)
//    fun inc() = this.apply { seed =  (seed * 5171 + 13297) % 50021 }
//    fun incMod(mod: Int) = this.inc()(mod)
//    operator fun invoke(mod: Int) = seed % mod
//}
//
//private class Reader2() {
//    val buffSize = 1.shl(16)
//    val din = DataInputStream(System.`in`)
//    var buffer = ByteArray(buffSize)
//    var bufferPointer = 0
//    var bytesRead = 0
//    var hasNext = true
//
//    fun read(): Byte {
//        if (bufferPointer == bytesRead) {
//            bufferPointer = 0
//            bytesRead = din.read(buffer, bufferPointer, buffSize)
//            if (bytesRead == -1) hasNext = false
//        }
//        return buffer[bufferPointer++]
//    }
//
//    fun nextInt(): Int {
//        var ret = 0
//        var c = read()
//        while (!c.isDigit2()) c = read()
//        while (c.isDigit2()) {
//            ret = ret * 10 + (c - '0'.toByte())
//            c = read()
//        }
//        return ret
//    }
//}
//
//fun Byte.isDigit2() = (this >= '0'.toByte() && this <= '9'.toByte())
