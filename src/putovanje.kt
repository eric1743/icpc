import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var red = br.readLine().split(" ")

    val n = red[0].toInt()
    val c = red[1].toInt()

    red = br.readLine().split(" ")

    val m = Array(n) {i -> red[i].toInt()}

    var fruitMax = 0

    repeat(n){
        var eaten = 0
        var fruit = 0
        for(i in it until n){
//            print(" " + i)
            val pile = m[i]
            if(eaten + pile <= c){
                eaten += pile
                fruit++
            }
        }
//        println(" " + fruit + " " + eaten)
        fruitMax = Math.max(fruit, fruitMax)
    }

    bw.append(fruitMax.toString())
    bw.flush()
}