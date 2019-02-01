package icpc.nordiccolegiateprogrammingcontest.r2018

fun main(args: Array<String>) {
    readLine()
    val b = readLine()!!.split(" ")
    var offset = -1
    try {
        for((idx, bite) in b.withIndex()){
            if (bite == "mumble") continue
            val num = bite.toInt() - 1
            if (offset == -1){
                offset = num - idx
                if (offset < 0) throw Exception()
            } else if (offset != num - idx) throw Exception()
        }
        println("makes sense")
    } catch (e: Exception){
        println("something is fishy")
    }
}