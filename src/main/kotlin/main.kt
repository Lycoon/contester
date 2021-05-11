import twitter4j.Query
import twitter4j.TwitterFactory

fun main() {
    val twitter = TwitterFactory.getSingleton()
    val query = Query("(#contest OR #giveaway OR #concours OR #prize) +exclude:retweets +exclude:replies -airdrop")
    query.resultType(Query.ResultType.popular)

    var result = twitter.search(query)

    //twitter.updateStatus("bruh")

    for (status in result.tweets) {
        print("@" + status.user.screenName.toString() + ": ")

        for (entity in status.hashtagEntities)
            print(entity.text + " | ")

        println()
    }

    query.maxId = result.tweets.last().id - 1
    result = twitter.search(query)

    for (status in result.tweets) {
        print("@" + status.user.screenName.toString() + ": ")

        for (entity in status.hashtagEntities)
            print(entity.text + " | ")

        println()
    }
}
