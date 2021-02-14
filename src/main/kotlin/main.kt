import kotlin.random.Random

fun main() {
    val s: Array<String> = arrayOf("qwerty", "zxcvb")
    val symbol = 'v'
    for (i in s.indices) {
        for(char in s[i]) {
            if (char == symbol) {
                println(s[i])
            }
        }
    }
}