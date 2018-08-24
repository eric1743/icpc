import kotlin.math.absoluteValue

fun main(args: Array<String>) {
    val map = Array(21, {Array(61, {0.0})})
    val uFaster = Array(21, {Array(61, {0})}) // -1 regular 0 same 1 U
    for (i in map.indices) {
        for (j in map[i].indices) {
            val regTime = 18.0 * ((i-5).absoluteValue + (j-19).absoluteValue)
            val uTime = 18.0 * (21-i + 15) + (1.8 * (j-19).absoluteValue)

            if( regTime < uTime) {
                map[i][j] = regTime
                uFaster[i][j] = -1
                print("R ")
            } else if ( regTime > uTime) {
                map[i][j] = uTime
                uFaster[i][j] = 1
                print("U ")
            } else {
                map[i][j] = regTime
                print("= ")
            }
            if (j == 60) println()
        }
    }
}