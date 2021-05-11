import twitter4j.Query
import twitter4j.TwitterFactory

fun main() {
    val twitter = TwitterFactory.getSingleton()
    val query = Query("(#contest OR #giveaway OR #concours OR #prize) +exclude:retweets +exclude:replies -airdrop")
    query.resultType(Query.ResultType.popular)
    query.count = 1

    twitter.updateStatus("bruh")

    for (i in 1..4) {
        val result = twitter.search(query)
        for (status in result.tweets) {
            val text = status.text.toLowerCase()
            print("@" + status.user.screenName.toString() + ": ")
            print("" + text.contains("follow") + " ")

            for (entity in status.hashtagEntities)
                print(entity.text + " | ")
            println()
            print("\t")

            for (entity in status.userMentionEntities)
                print(entity.text + " | ")

            println()
        }

        query.maxId = result.tweets.last().id - 1
    }
}
