package riddler

import java.util.*

fun main(args: Array<String>) {
    val runs = 1000000
    val len = 7175
    val songs = 100
    var hits = 0
    repeat(runs){
        if (simShift(len, songs)) hits++
    }
    println("${hits*100/runs.toDouble()}%")

    println("${calcProb(len, songs) * 100}%")
}

fun calcProb(len: Int, songs: Int): Double{
    var prob = 1.0
    repeat(songs){
        prob *= 1 - it.toDouble()/len
    }
    return prob
}

fun simShift(len: Int, songs: Int): Boolean {
    val rand = Random()
    val playlist = MutableList(len) {false}
    repeat(songs){
        val idx = rand.nextInt(len)
        if(playlist[idx]) return false
        playlist[idx] = true
    }
    return true
}
