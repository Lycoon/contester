import twitter4j.*

fun replyTo(tweets: List<Status>, message: String) {
    val twitter = TwitterFactory().instance
    var reply: Status?
    for (tweet in tweets) {
        try {
            reply = twitter.updateStatus(
                StatusUpdate(
                    "@" + tweet.user.screenName + " " + message
                ).inReplyToStatusId(tweet.id)
            )
            println("Posted reply " + reply.id + " in response to tweet " + reply.inReplyToStatusId)
        } catch (e: TwitterException) {
            e.printStackTrace()
        }
    }
}


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
