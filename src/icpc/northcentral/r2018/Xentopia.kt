package icpc.northcentral.r2018

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var line = br.readLine().split(" ").map { it.toInt() }
    val n = line[0]
    val m = line[1]
    val k1 = line[2]
    val k2 = line[3]

    val map = List(n) { Node(it, k1, k2) }
    repeat(m) {
        line = br.readLine().split(" ").map { it.toInt() }
        map[line[0] - 1].routes.add(Route(line[1] - 1, line[2], line[3]))
        map[line[1] - 1].routes.add(Route(line[0] - 1, line[2], line[3]))
    }
    line = br.readLine().split(" ").map { it.toInt() }
    val s = line[0]
    val t = line[1]

    val q = PriorityQueue<Inst>()
    q.add(Inst(s-1,k1,k2,0L))
    while(!q.isEmpty()){
        val inst = q.poll()
        q.addAll(map[inst.dest].bfs(inst))
    }
    var out = map[t-1].best[0][0]
    if (out == Long.MAX_VALUE) out = -1
    println(out)
}

data class Route(val dest: Int, val t: Int, val color: Int)
class Inst(val dest: Int, val red: Int, val blue: Int, val t: Long): Comparable<Inst>{
    override fun compareTo(other: Inst): Int {
        return this.t.compareTo(other.t)
    }

}

class Node(val id: Int, val k1: Int, val k2: Int){
    val routes = mutableListOf<Route>()
    val best = List(k1+1){ MutableList(k2+1){Long.MAX_VALUE}}

    fun bfs(inst: Inst): List<Inst>{
        val ret = mutableListOf<Inst>()
        if (inst.t >= best[inst.red][inst.blue])
            return ret
        best[inst.red][inst.blue] = inst.t
        for(route in routes){
            when (route.color){
                0 -> ret.add(Inst(route.dest, inst.red, inst.blue, inst.t + route.t))
                1 -> if (inst.red > 0) ret.add(Inst(route.dest, inst.red-1, inst.blue, inst.t + route.t))
                2 -> if (inst.blue > 0) ret.add(Inst(route.dest, inst.red, inst.blue-1, inst.t + route.t))
            }
        }
        return ret
    }
}

