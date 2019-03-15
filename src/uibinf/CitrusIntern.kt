package uibinf

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val cost = MutableList(n) { -1L }
    val parent = MutableList(n) { -1 }
    val children = List(n) { mutableListOf<Int>() }
    repeat(n) {
        val line = br.readLine().split(" ").map { it.toInt() }
        cost[it] = line[0].toLong()
        for (i in 2 until line.size) {
            parent[line[i]] = it
            children[it] += line[i]
        }
    }
    var top = -1;
    for ((idx, par) in parent.withIndex()) {
        if (par == -1) {
            top = idx
            break;
        }
    }
    val grid = List(n) { mutableListOf(0L, 0L, -1L) }
    populate(top, children, grid, cost)
    if(grid[top][2] == -1L) grid[top][2] = Long.MAX_VALUE
    println(minOf(grid[top][1], grid[top][2]))
}

fun populate(idx: Int, childList: List<MutableList<Int>>, grid: List<MutableList<Long>>, cost: MutableList<Long>){
    val children = childList[idx]
    if(children.isEmpty()){
        grid[idx][1] = cost[idx]
        return
    }
    for(child in children)
        populate(child, childList, grid, cost)

    var notBribed = 0L
    var bribed = cost[idx]
    var minDiff = Long.MAX_VALUE
    for(child in children){
        val (cNotBribed, cBribed, cChildBribed) = grid[child]
        if(cChildBribed == -1L){
            notBribed += cBribed
            bribed += cNotBribed
        } else {
            notBribed += minOf(cBribed, cChildBribed)
            bribed += minOf(cNotBribed, cChildBribed)
            minDiff = minOf(minDiff, cBribed - cChildBribed)
        }
    }
    if(minDiff == Long.MAX_VALUE){
        minDiff = 0
    }
    grid[idx][2] = notBribed + maxOf(minDiff, 0)
    grid[idx][0] = notBribed
    grid[idx][1] = bribed
}