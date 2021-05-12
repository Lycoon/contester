import twitter4j.Query
import twitter4j.TwitterFactory


fun main() {
    val twitter = TwitterFactory.getSingleton()
    val query = Query("(#contest OR #giveaway OR #concours OR #prize) +exclude:retweets +exclude:replies -airdrop")
    query.resultType(Query.ResultType.popular)
    query.count = 1

    for (i in 1..1) {
        val result = twitter.search(query)
        for (status in result.tweets) {
            val text = status.text.toLowerCase()
            print("@" + status.user.screenName.toString() + ": ")
            print("" + text.contains("follow") + " ")

            println(
                status.hashtagEntities.joinToString(
                    separator = " | ",
                    prefix = "\t[",
                    postfix = "]"
                ) { entity -> entity.text })

            println(
                status.userMentionEntities.joinToString(
                    separator = " | ",
                    prefix = "\t[",
                    postfix = "]"
                ) { entity -> entity.text })
        }

        query.maxId = result.tweets.last().id - 1
    }
}
