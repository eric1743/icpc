fun main(args: Array<String>) {
    var line = readLine()!!.split(" ")
    val n = line[0].toInt()
    val m = line[1].toInt()
    val arr = Array<Int>(n, {0})

    repeat(m){
        line = readLine()!!.split(" ")
        arr[line[0].toInt()]++
        arr[line[1].toInt()]++
    }

    val origMap = mutableMapOf<Int,Int>()
    repeat(arr.size){
        origMap.put(it, arr[it])
    }
    val sorted = origMap.entries.sortedBy {it.value}

    val ends = (n-1)*2
    


}