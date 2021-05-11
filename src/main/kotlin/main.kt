import twitter4j.Query
import twitter4j.TwitterFactory

fun main() {
    val twitter = TwitterFactory.getSingleton()
    val query = Query("#contest")
    val result = twitter.search(query)

    for (status in result.tweets)
        println("@" + status.user.screenName.toString() + ":" + status.text)
}
