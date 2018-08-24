fun main(args: Array<String>) {
    val line = readLine() ?: ""
    val n = Integer.parseInt(line.split(" ")[0])
    val k = Integer.parseInt(line.split(" ")[1])

    val arr = Array<Boolean>(n + 1, { true })
    arr[0] = false
    arr[1] = false
    var count = 0
    var prime = 1
    while (true) {
        prime = nextPrime(arr, prime)
        var idx = prime
        while (idx < n+1) {
            if(arr[idx]){
                arr[idx] = false
                if(++count == k){
                    println(idx)
                    return
                }
            }
            idx += prime
        }
    }
}

fun nextPrime(arr: Array<Boolean>, last: Int): Int {
    var idx = last
    while (idx < arr.size){
        if (arr[++idx])
            return idx
    }
    return -1
}