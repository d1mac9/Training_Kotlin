import org.jsoup.Jsoup

fun main() {
    val map: MutableMap<String, Int> = mutableMapOf()
    val doc = Jsoup.connect("https://championat.com/").get()
    val text: String = doc.body().text()
    val listWords = text.split(" ")
    println("Текст с html страницы: $text")
    listWords.sorted()
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

