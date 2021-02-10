fun reverseText(text: String): String {
    var n: Int = text.length
    var reversedText = ""
    while (n > 0) {
        n--
        reversedText += text[n]
    }
    return reversedText
}