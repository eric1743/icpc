fun main(args: Array<String>) {
    var map = readLine()!!.toInt()
    val arr = Array(25) {false}
    repeat(25){
        if(map % 2 != 0) arr[it]=true
        map = map shr 1
    }
    var idx = 20
    repeat(5){
        print("|")
        repeat(5){
            print(if(arr[idx+it]) "X" else ".")
        }
        println()
        idx -= 5
    }
    print("------")
}