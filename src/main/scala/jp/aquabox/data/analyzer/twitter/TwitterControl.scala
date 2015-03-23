package jp.aquabox.data.analyzer.twitter

import twitter4j.TwitterStreamFactory
import twitter4j.conf.ConfigurationBuilder

/**
 * Created by motonari on 15/03/23.
 */
object TwitterControl extends TwitterTokens {

  private val cb = new ConfigurationBuilder
  cb.setOAuthAccessToken(this.token)
  cb.setOAuthAccessTokenSecret(this.secret_token)
  cb.setOAuthConsumerKey(this.consumer_key)
  cb.setOAuthConsumerSecret(this.consumer_secret_key)

  def execute = {
    val stream = new TwitterStreamFactory(cb.build()).getInstance
    stream.addListener(TwitterListener)
    stream.user
  }
}
