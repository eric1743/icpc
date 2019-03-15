package icpc.nordiccolegiateprogrammingcontest.r2018

fun main(args: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    //n = number of players on a team, m = number of teams
    val names = List(n * m) { Player(it, n) }
    val byesNeeded = (n * m) % 2 != 0
    val rounds = n * (m - 1) + if (byesNeeded) 1 else 0 //if byesneeded add one
    repeat(rounds){
        val played = MutableList(n*m){false}

    }

}

class Player(idx: Int, n: Int) {
    val team = 'A' + idx / n
    val id = idx % n
    val played = mutableSetOf<Player>()
}

