fun main(args: Array<String>) {
    var map = mutableMapOf(1 to 1, 2 to 2, 3 to 3)
    println(map.put(4, 1))
    println(map.put(4, 2))
    println(map.put(4, 3))

    println(map.get(4))

}