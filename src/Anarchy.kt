import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.max

fun main(args: Array<String>) {
    var lastAttack = LocalDate.of(2000, 1, 1)
    var cnt = 0
    var high = 0
    var longestPeaceTime: Long = 0
    val mostAttacks = mutableListOf<Int>()
    val noAttacks = mutableListOf<Int>()
    for (year in 2001..2099) {
        var yearcnt = 0
        for (month in 1..12) {
            val len = LocalDate.of(year, month, 1).lengthOfMonth()
            for (day in 1..len) {
                if (day * month == (year-2000)) {
                    val date = LocalDate.of(year, month, day)
                    val peaceTime = ChronoUnit.DAYS.between(lastAttack, date)
                    longestPeaceTime = max(longestPeaceTime, peaceTime)
                    lastAttack = date
                    yearcnt++
//                    print("$month/$day/${2000 + year}")
//                    println(" \t Days since last attack: $peaceTime")
                }
            }
        }
        if (yearcnt > high) {
            high = yearcnt
            mostAttacks.clear()
            mostAttacks.add(year)
        }
        cnt += yearcnt
        if (yearcnt == 0) noAttacks.add(year)
    }
    println("Number of Attacks: $cnt")
    println("Longest Peace Time: $longestPeaceTime days")
    println("Years with $high attacks: (${mostAttacks.size})")
    for (year in mostAttacks) println("\t$year")
    println("Years without attacks: (${noAttacks.size})")
    for (year in noAttacks) println("\t$year")
}