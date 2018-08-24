import java.util.*
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    var totalSteps = 0
    var minSteps = Int.MAX_VALUE
    var maxSteps = 0
    val arr = IntArray(10)
    for(i in 0..100000){
        var steps = 0
        var stepsSinceLast = 0
        var unmatchedPairs = 10
        while (unmatchedPairs > 0) {
            steps++
            stepsSinceLast++
            if((1..2*unmatchedPairs).rand() == 1){
                arr[10-unmatchedPairs--] += stepsSinceLast
                stepsSinceLast = 0
            }
        }
        totalSteps += steps
        minSteps = min(steps, minSteps)
        maxSteps = max(steps, maxSteps)
    }

    println("average: " + totalSteps/100000.0)
    println("max: " + maxSteps)
    println("min: " + minSteps)
    for (i in arr){
        println(i/100000.0)
    }


}

fun ClosedRange<Int>.rand() =
        Random().nextInt(endInclusive - start) + start