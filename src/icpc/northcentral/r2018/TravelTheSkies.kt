package icpc.northcentral.r2018

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val line = br.readLine().split(" ").map { it.toInt() }
    val k = line[0]
    val n = line[1]
    val m = line[2]

    val flights = MutableList(m) { Flight(br.readLine().split(" ").map { it.toInt() }) }
    flights.sortBy { it.day }

    val bookings = MutableList(n * k) { Booking(br.readLine().split(" ").map { it.toInt() }) }
    bookings.sort()

    var bIdx = 0
    var fIdx = 0
    val people = MutableList(k) { 0 }
    for (day in 0 until n) {
        for (loc in 0 until k) {
            people[loc] += bookings[bIdx++].num
        }
        val buff = MutableList(k) { 0 }
        while (fIdx < m) {
            val flight = flights[fIdx]
            if(flight.day != day) break
            people[flight.start] -= flight.capacity
            buff[flight.end] += flight.capacity
            if(people[flight.start] < 0){
                println("suboptimal")
                return
            }
            fIdx++
        }
        for(loc in 0 until k){
          people[loc] += buff[loc]
        }
    }
    println("optimal")
}

class Flight(line: List<Int>) {
    val start = line[0] - 1
    val end = line[1] - 1
    val day = line[2] - 1
    val capacity = line[3]
}

class Booking(line: List<Int>) : Comparable<Booking> {
    val start = line[0] - 1
    val day = line[1] - 1
    val num = line[2]

    override fun compareTo(other: Booking): Int {
        if (this.day == other.day) return this.start.compareTo(other.start)
        return this.day.compareTo(other.day)
    }
}