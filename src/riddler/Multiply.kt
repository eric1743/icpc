package riddler

import java.math.BigInteger

fun main(args: Array<String>) {
//    var multiplicand = BigInteger.ONE
//    for(i in 1L .. 99L){
//        multiplicand = multiplicand.times(BigInteger.valueOf(i))
//    }
    //    println(multiplicand)

//    "530,131,801,762,787,739,802,889,792,754,109,70_,139,358,547,710,066,257,652,050,346,294,484,433,323,974,747,960,297,803,292,989,236,183,040,000,000,000")
//       var end = BigInteger("53013180176278773980288979275410970_139358547710066257652050346294484433323974747960297803292989236183040000000000")
    var end = BigInteger("530131801762787739802889792754109706139358547710066257652050346294484433323974747960297803292989236183040000000000")
    for(i in 100 downTo 2){
        if (end.mod(BigInteger("$i")) == BigInteger.ZERO){
            println(i)
            end = end.divide(BigInteger("$i"))
        }
    }
    println(end)

}