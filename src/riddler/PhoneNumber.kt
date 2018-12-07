package riddler

fun main(args: Array<String>) {
    val range = (0..999999)
    val map = range.groupingBy { it.decompose() }.eachCount()
    for ((k, v) in map) {
        println("$k -> ${v * 10}")
    }
}

fun Int.decompose(): String {
    val arr = MutableList<Int>(10) { 0 }
    var i = this
    repeat(7) {
        arr[i % 10]++
        i /= 10
    }
    arr.sortDescending()
    return arr.joinToString(separator = "") { if (it == 0) "" else it.toString() }
}
