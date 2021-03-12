import org.jsoup.Jsoup
import java.util.concurrent.ConcurrentHashMap
import java.util.function.BiFunction

fun main() {
    val map: MutableMap<String, Int> = mutableMapOf()
//    val link = readLine()
    val html = Jsoup.connect("https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0").get()
    val text = html.text().toLowerCase()
    println(text)
    val prepared = text.replace("""[^а-я a-z]""".toRegex(), "")
    val listWords = prepared.split(" ")
    for (word in listWords) {
        map.compute(word
        ) { k: String, v: Int? -> if (v == null) 1 else v + 1 }
    }
    println("Частота использования слов: ${map}")
    println("Общее число слов: ${listWords.count()}")
}

