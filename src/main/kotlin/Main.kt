package com.evolix

fun main() {
    println("Enter StreamTape link:")
    val link = readLine() ?: return
    val extracted = StreamTapeExtractor.extract(link)
    if (extracted != null) {
        println("Extracted video link: $extracted")
    } else {
        println("Failed to extract video link.")
    }
}