import kotlin.math.min

fun main(args: Array<String>) {
//    val coins = arrayOf(1,5,10,25)
    val numberOfCoins = 4
    val coins = Array(numberOfCoins) { i -> numberOfCoins - i }
    var best = coins.count()
    var combo = coins.out()


    while (coins.progress()) {
        var temp = coins.count()
        if (temp == best) {
            combo += coins.out()
        } else if (temp < best) {
            best = temp
            combo = coins.out()
        }
    }

    println(best)
    println(combo)
}

fun Array<Int>.count(): Int {
    val arr = Array(100) { 1000 }
    arr[0] = 0
    for (i in arr.indices) {
        val here = arr[i]
        this.forEach {
            if (i + it < 100) {
                arr[i + it] = min(here + 1, arr[i + it])
            }
        }
    }
    var sum = 0
    for (i in 1..99) {
        sum += arr[i]
    }
    return sum
}

fun Array<Int>.progress(): Boolean {
    for (i in 0..this.size - 2) { //0,1,2
        if (this[i] < 99 - i) {
            this[i]++
            for (j in (i - 1) downTo 0) {
                this[j] = this[j + 1] + 1
            }
            return true
        }
    }
    return false
}

fun Array<Int>.out(): String {
    return "[" + this[0] + ", " + this[1] + ", " + this[2] + ", " + this[3] + "] "
}
