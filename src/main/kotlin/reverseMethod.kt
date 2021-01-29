fun reverseText(text: String) {
    var n: Int = text.length
    print("Перевернутая фраза: ")
    while (n > 0) {
        n--
        print(text[n])
    }
}