import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var line = br.readLine().split(" ").map { it.toInt() }

    val c = line[0]
    val p = line[1]
    val x = line[2]
    val l = line[3]

    val union = List<Partner>(c){Partner()}
    repeat(p) {
        line = br.readLine().split(" ").map { it.toInt() }
        union[line[0]-1].link(union[line[1]-1])
        union[line[1]-1].link(union[line[0]-1])
    }

    union[l-1].membership = 0
    val mine = union[x-1]

    val q = LinkedList<Partner>()
    q.add(union[l-1])
    while(!q.isEmpty()){
        val leaving = q.pop()
        if (leaving == mine) {
            println("leave")
            return
        }
        leaving.doLeave(q)
    }

    println("stay")
}

class Partner() {
    var membership = 1
    val links = mutableListOf<Partner>()
    var maxLink = 0

    fun link(toLink: Partner) {
        links.add(toLink)
        maxLink++
    }

    fun unlink(toUnlink: Partner): Boolean {
        if (membership == 1) {
            links.remove(toUnlink)
            if (links.size <= maxLink / 2) {
                membership = 0
                return true
            }
        }
        return false
    }

    fun doLeave(q: Queue<Partner>) {
        if (membership == 0) { //leaving
            membership = -1
            links.forEach {
                if (it.unlink(this)) q.add(it)
            }
        }
    }

}