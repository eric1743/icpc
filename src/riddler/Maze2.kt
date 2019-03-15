package riddler

fun main(args: Array<String>) {
    val raw = "VU20RRRXV3H1H00XUV3V" +
            "RHDURL2VX01V2U13VDRV" +
            "HV2VH3LDVVLDHLVV2VXR" +
            "VHHHHLHR0V3V3VH1HHL1" +
            "HUHDXVHH1XV0L0R13LLH" +
            "1VVHV1VHHV3HDHLHVRHH" +
            "VDVHVXVHH1V2HHVVHHR1" +
            "32HHHVH3RV3V3UUHVVDU" +
            "R0UR12RVHV33V0LXHHVD" +
            "VHDRL2VDH0V1HVULH0VV" +
            "VH2V1DVUU0G2H3VVHUHH" +
            "HDXH3LVVVHVL2VHVHVUV" +
            "VXULHVVUVVLH10H3LDHV" +
            "2XRDVVVVX1VVH0VR03HH" +
            "XV2RRDH1UV0H31LHXHHV" +
            "LDDH1HVVDHHVV10UVVV0" +
            "HD3HVH3DHUHL0X2H1HHV" +
            "X02HHVVD2002HURHHHVV" +
            "HH1HVV3VH3VV20UUH2RH" +
            "LHHH3VVVRHLH12H2HDVU"
    val map = List(400) { Space2(it, raw[it]) }
    val queue = mutableListOf<Path2>()
    repeat(20) { queue.add(Path2(null, it, 0)) }
    repeat(20) { queue.add(Path2(null, it * 20, 0)) }
    repeat(20) { queue.add(Path2(null, it + 380, 0)) }
    repeat(20) { queue.add(Path2(null, it * 20 + 19, 0)) }

    while (queue.isNotEmpty()) {
        val path = queue.removeAt(0)
        queue.addAll(path.forward(map[path.loc]))
    }
    println("${map[210].path.cost} ${map[210].path.route}")
}

class Space2(val id: Int, code: Char) {
    val row = id / 20
    val col = id % 20
    var path = Path2(null, id, Int.MAX_VALUE)
    val instruction = when (code) {
        'U' -> Instruction2.UP
        'D' -> Instruction2.DOWN
        'L' -> Instruction2.LEFT
        'R' -> Instruction2.RIGHT
        'V' -> Instruction2.VERT
        'H' -> Instruction2.HORIZ
        '0' -> Instruction2.ZERO
        '1' -> Instruction2.ONE
        '2' -> Instruction2.TWO
        '3' -> Instruction2.THREE
        'X' -> Instruction2.END
        'G' -> Instruction2.GOAL
        else -> throw Exception()
    }

    fun validMove(dir: Int): Boolean {
        when (dir) {
            0 -> return row != 0
            1 -> return col != 19
            2 -> return row != 19
            3 -> return col != 0
            else -> throw Exception()
        }
    }
}

class Path2(prev: Path2?, val loc: Int, val cost: Int) : Comparable<Path2> {
    val route: List<Int> = if (prev != null) prev.route + loc else listOf(loc)
    fun forward(space: Space2): List<Path2> {
        if (space.path <= this) return emptyList()
        space.path = this
        val ret = mutableListOf<Path2>()
        repeat(4){
            if (space.instruction.nextDir[it] && space.validMove(it)) {
                ret.add(spawn(it, space.instruction.cost))
            }
        }
//        when (space.instruction) {
//            Instruction.STOP, Instruction.GOAL -> {
//            }
//            Instruction.ANY ->
//                return (0..3).filter { dir -> space.validMove(dir) }.map { dir -> this.spawn(dir) }
//            else -> { //straight, right, uturn, left
//                val nextDir = space.instruction.nextDir(dir)
//                if (space.validMove(nextDir)) return listOf(this.spawn(nextDir))
//            }
//        }
        return ret
    }

    fun spawn(nextDir: Int, spaceCost: Int): Path2 {
        val nextLoc = when (nextDir) {
            0 -> loc - 20
            1 -> loc + 1
            2 -> loc + 20
            3 -> loc - 1
            else -> throw Exception()
        }
        return Path2(this, nextLoc, cost + spaceCost)
    }

    override fun compareTo(other: Path2): Int {
        if (cost != other.cost) return cost.compareTo(other.cost)
        return route.size.compareTo(other.route.size)
    }
}

enum class Instruction2(val nextDir: List<Boolean>, val cost: Int) {
    UP(listOf(true, false, false, false), 0),
    DOWN(listOf(false, false, true, false), 0),
    LEFT(listOf(false, false, false, true), 0),
    RIGHT(listOf(false, true, false, false), 0),
    VERT(listOf(true, false, true, false), 0),
    HORIZ(listOf(false, true, false, true), 0),
    ZERO(listOf(true, true, true, true), 0),
    ONE(listOf(true, true, true, true), 1),
    TWO(listOf(true, true, true, true), 2),
    THREE(listOf(true, true, true, true), 3),
    END(listOf(false, false, false, false), 0),
    GOAL(listOf(false, false, false, false), 0);
}