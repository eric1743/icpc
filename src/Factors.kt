import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.math.BigInteger

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val primes = primes(100)
    val facts = factorial(64)

    var line = br.readLine()
    while (!line.isNullOrEmpty()) {
        val n = line.toLong()
        var totalFactors = 0
        val nBig = BigInteger.valueOf(n)
        for((i, it) in facts.withIndex()) {
            if (it % nBig == BigInteger.ZERO) {
                totalFactors = i
                break
            }
        }

        var numer = facts[totalFactors].divide(nBig)
        val denom = mutableListOf<Int>()
        var counted = 0
        while (numer != BigInteger.ONE) {
            var next = 1
            for((i, it) in facts.withIndex()) {
                if(it > numer) break
                if(numer % it == BigInteger.ZERO)
                    next = i
            }
            denom.add(next)
            counted += next
            numer /= facts[next]
        }

        repeat(totalFactors - counted) {denom.add(1)}

        denom.sortedDescending()

        var out = 1L
        for((i, it) in denom.withIndex()){
            repeat(it) { out *= primes[i] }
        }
        bw.write(n.toString() + " " + out.toString())
        bw.newLine()
        bw.flush()

        line = br.readLine()
    }
    bw.flush()
}

fun factorial(n: Int): List<BigInteger> {
    val ret = mutableListOf<BigInteger>(BigInteger.ONE, BigInteger.ONE)
    var carry = BigInteger.ONE
    var idx = BigInteger.ONE
    repeat(n){
        idx = idx.add(BigInteger.ONE)
        carry = carry.multiply(idx)
        ret.add(carry)
    }
    return ret
}

fun primes(n: Int): List<Int> {
    val ret = mutableListOf<Int>()
    val primes = Array<Boolean>(n) { true }
    primes[0] = false
    primes[1] = false
    primes.forEachIndexed() { i, it ->
        if (it) {
            ret.add(i)
            for (j in i * 2 until n step i) primes[j] = false
        }
    }
    return ret
}