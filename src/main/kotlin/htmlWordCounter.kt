import org.jsoup.Jsoup

fun main() {
    val doc = Jsoup.connect("https://ya.ru/").get()
    val text: String = doc.body().text()
    val words = text.split(" ").count()
    println(text)
    println(words)
}