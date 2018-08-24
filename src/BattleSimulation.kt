fun main(args: Array<String>) {
    val line = readLine() ?: ""
    val len = line.length
    var map = mapOf('R' to 'S', 'B' to 'K', 'L' to 'H')

    var idx = 0
    var sum = line.nextThree(0)
    while (idx < len){
        if (sum == 224){
            print("C")
            idx += 3
            sum = line.nextThree(idx)
            continue
        }
        print(map[line[idx]])
        sum -= line[idx].toInt()
        if (len - idx >= 4)
            sum += line[idx+3].toInt()
        idx++
    }
}

fun String.nextThree(idx: Int): Int{
    if(this.length - idx >= 3)
        return this[idx].toInt() + this[idx+1].toInt() + this[idx+2].toInt()
    else return 0
}