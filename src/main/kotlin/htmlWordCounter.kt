import org.jsoup.Jsoup

fun main() {
    val map: MutableMap<String, Int> = mutableMapOf()
    val doc = Jsoup.connect("https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%B2%D1%82%D0%BE%D1%80").get()
    val text: String = doc.body().text()
    val listWords = text.split(" ")
    println("Текст с html страницы: $text")
    println(listWords.sorted())
    var counter = 1
    for (word in 0..listWords.size - 2) {
        if (listWords[word] == listWords[word + 1]) {
            counter++
            continue
        }
        map[listWords[word]] = counter
        counter = 1
    }
    if (listWords[listWords.lastIndex] !in map) {
        map[listWords[listWords.lastIndex]] = 1
    }
    println("Частота использования слов: ${map.toSortedMap()}")
    println("Общее число слов: ${listWords.count()}")
}

