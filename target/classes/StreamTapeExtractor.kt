import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup

object StreamTapeExtractor {
    private val client = OkHttpClient()

    fun extract(link: String): String? {
        // 1. Get the page HTML
        val request = Request.Builder()
            .url(link)
            .header("User-Agent", "Mozilla/5.0")
            .build()
        val response = client.newCall(request).execute()
        val html = response.body?.string() ?: return null

        // 2. Find the obfuscated JS part (StreamTape uses a JS trick)
        val regex = Regex("""document\.getElementById\('ideoooolink'\).*?innerHTML = (.*?);""")
        val match = regex.find(html) ?: return null
        val js = match.groupValues[1]

        // 3. Evaluate the JS (very basic, only works for simple concatenation)
        // Example: "'/get_video?id=abc123&expires=...' + 'moretext'"
        val cleaned = js.replace("'", "").replace("+", "").replace(" ", "")
        val videoPath = cleaned

        // 4. Build the final link
        val baseUrl = "https://streamtape.com"
        return baseUrl + videoPath
    }
}