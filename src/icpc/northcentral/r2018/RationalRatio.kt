package icpc.northcentral.r2018

fun main(args: Array<String>) {
    val line = readLine()!!.split(Regex("[. ]"))
    val shift = line[1].length - line[2].toInt()

    var num = (line[0] + line[1]).toLong()
    num -= (line[0] + line[1].substring(0 until shift)).toLong()
    val denom = tenPow(line[1].length) - tenPow(shift)

    val gcd = gcd(num, denom)
    println("${num/gcd}/${denom/gcd}")
}


fun tenPow(a: Int): Long{
    var ret = 1L
    repeat(a){
        ret*= 10
    }
    return ret
}

fun gcd(a: Long, b: Long): Long{
    if(b >= 1L){
        return gcd(b, a % b)
    }
    return a
}