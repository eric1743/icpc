import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()

    var max = 2000;

    var combos = hashSetOf<Int>(0)

    repeat(n) {
        val newWeight = br.readLine().toInt()
        val newCombos = hashSetOf<Int>()
        combos.forEach {
            if (it < max) {
                newCombos.add(it)
                if (it + newWeight <= max) {
                    newCombos.add(it + newWeight)
                    if (Math.abs(it + newWeight - 1000) < (max - 1000)) {
                        max = 1000 + Math.abs(it + newWeight - 1000)
                    }
                }
            }
        }
        combos = newCombos
    }

    var out = 0
    combos.forEach {
        if (Math.abs(it - 1000) < Math.abs(out - 1000)) {
            out = it
        } else if (it > 1000 && (Math.abs(it - 1000) == Math.abs(out - 1000))) {
            out = it
        }
    }
    println(out)
}

