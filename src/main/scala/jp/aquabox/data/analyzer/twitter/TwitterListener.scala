package jp.aquabox.data.analyzer.twitter

import jp.aquabox.data.analyzer.DataAnalyzerCalculator
import twitter4j._

/**
 * Created by motonari on 15/03/23.
 */
/**
 * Twitterからデータを取得
 */
object TwitterListener extends UserStreamListener {
  override def onDeletionNotice(l: Long, l1: Long): Unit = {}

  override def onFriendList(longs: Array[Long]): Unit = {}

  override def onUserListUnsubscription(user: User, user1: User, userList: UserList): Unit = {}

  override def onBlock(user: User, user1: User): Unit = {}

  override def onUserListSubscription(user: User, user1: User, userList: UserList): Unit = {}

  override def onFollow(user: User, user1: User): Unit = {}

  override def onUserListMemberAddition(user: User, user1: User, userList: UserList): Unit = {}

  override def onDirectMessage(directMessage: DirectMessage): Unit = {}

  override def onUserListUpdate(user: User, userList: UserList): Unit = {}

  override def onUnblock(user: User, user1: User): Unit = {}

  override def onUnfollow(user: User, user1: User): Unit = {}

  override def onUserProfileUpdate(user: User): Unit = {}

  override def onUserListMemberDeletion(user: User, user1: User, userList: UserList): Unit = {}

  override def onFavorite(user: User, user1: User, status: Status): Unit = {}

  override def onUnfavorite(user: User, user1: User, status: Status): Unit = {}

  override def onUserListDeletion(user: User, userList: UserList): Unit = {}

  override def onUserListCreation(user: User, userList: UserList): Unit = {}

  override def onStallWarning(stallWarning: StallWarning): Unit = {}

  override def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice): Unit = {}

  override def onScrubGeo(l: Long, l1: Long): Unit = {}

  override def onTrackLimitationNotice(i: Int): Unit = {}

  override def onException(e: Exception): Unit = { println(e.getMessage) }

  override def onStatus(status: Status): Unit = {
    println("onStatus")
    DataAnalyzerCalculator.add(status.getURLEntities.toList)
  }
}
