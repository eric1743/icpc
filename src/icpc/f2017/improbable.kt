package icpc.f2017

import java.io.BufferedReader
import java.io.InputStreamReader

//https://open.kattis.com/problems/improbable

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var line = br.readLine().split(" ").map { it.toInt() }
    val r = line[0]
    val c = line[1]
    val map = List(r) { br.readLine().split(" ").map { it.toInt() } }

        
}