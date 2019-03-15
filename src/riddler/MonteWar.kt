package riddler

fun main(args: Array<String>) {
    val redDeck = mutableListOf(2, 2, 2, 2, 4, 4, 4, 4, 9, 9, 9, 9)
    val blueDeck = mutableListOf(1, 1, 1, 1, 6, 6, 6, 6, 8, 8, 8, 8)
    val blackDeck = mutableListOf(3, 3, 3, 3, 5, 5, 5, 5, 7, 7, 7, 7)

    var iWin = 0
    var uWin = 0

    repeat(10000000) {
        val iDeck = blueDeck.toMutableList()
        val uDeck = redDeck.toMutableList()
        iDeck.shuffle()
        uDeck.shuffle()

        var iCt = 0
        var uCt = 0
        var idx = 0
        while(iCt < 5 && uCt < 5){
            if(iDeck[idx] > uDeck[idx]) iCt++ else uCt++
            idx++
        }
        if (iCt == 5) iWin++ else uWin++
        //println(idx)
    }
    println("$iWin, $uWin")
}