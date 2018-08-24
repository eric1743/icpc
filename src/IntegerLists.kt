import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val p = br.readLine().toInt()

    repeat(p){
        val prog = br.readLine()
        val n = br.readLine().toInt()
        val line = br.readLine().removeSurrounding(prefix = "[", suffix = "]").split(",")
        val arr: List<Int>
        if (line[0].isEmpty()) arr = emptyList()
        else arr = line.map { it.toInt() }

        var idxl = 0
        var idxr = arr.size
        var rev = false

        for (cmd in prog){
            if (cmd == 'R') rev = !rev
            else if(rev) idxr--
            else idxl++
        }

        if (idxr < idxl) {
            bw.write("error")
            bw.newLine()
        } else if (idxr == idxl) {
            bw.write("[]")
            bw.newLine()
        } else {
            idxr--
            bw.write("[")
            if(rev) {
                for(ent in idxr downTo idxl+1) bw.write(arr[ent].toString() + ",")
                bw.write(arr[idxl].toString() + "]\n")
            } else {
                for(ent in idxl until idxr) bw.write( arr[ent].toString() + ",")
                bw.write(arr[idxr].toString() + "]\n" )
            }
        }
    }
    bw.flush()
}