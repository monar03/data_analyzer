package jp.aquabox.data.analyzer

import java.net.URL

import akka.actor.{Props, ActorSystem, Actor}

import jp.aquabox.data.analyzer.model.{TagInformationDao, SiteInformationDao}
import jp.aquabox.data.analyzer.paser.HtmlParser
import jp.aquabox.data.analyzer.twitter.TwitterControl
import jp.aquabox.morphological.{KuromojiAnalysis, WordData}
import twitter4j.URLEntity

import scala.collection.mutable

/**
 * Created by motonari on 15/03/23.
 */
object DataAnalyzerCalculator {
  val queue:mutable.Queue[List[URLEntity]] = mutable.Queue[List[URLEntity]]()

  val actor = ActorSystem().actorOf(Props[DataAnalyzerActor])

  /**
   * キューへ挿入
   *
   * @param v
   * @return
   */
  def add(v:List[URLEntity]) = v match {
    case v:List[URLEntity] => queue += v
    case v =>
  }

  def calc = {
    TwitterControl.execute

    while(true) {
      try {
        Thread.sleep(3000)
        this.queue.dequeue() match {
          case v:List[URLEntity] => actor ! v
          case v =>
        }
      }
      catch {
        case e:Exception => println(e.getMessage)
      }
    }
  }
}

/**
 * データ取得用のアクタークラス
 */
class DataAnalyzerActor extends Actor with KuromojiAnalysis {
  /**
   * Actorの実行
   *
   * @return
   */
  override def receive: Receive = {
    case v:List[URLEntity] => v map {
        url => calc(url.getExpandedURL)
      }
      case v =>
  }

  def calc(url:String) = {
    println("calc start :" + url)
    val html = HtmlParser.parseFromUrl(url)
    try {
      val host = (new URL(html.url)).getHost
      if(host != "bit.ly" && host != "dlvr.it" && host != "ift.tt") {
        SiteInformationDao.set(
          html.url,
          html.url,
          host,
          html.title,
          html.description,
          html.thumbnail,
          html.site_name
        )

        parse(html.title + "\n" + html.description) map {
          case WordData(s, f) => f.split(",")(0) match {
            case f if f == "名詞" =>
              try {
                TagInformationDao.set(html.url, s, 1)
              } catch {
                case e: Exception => println(e.getMessage)
              }
            case f => println(s + ":" + f)
          }
        }
      }
    } catch {
      case e:Exception => println(e.getMessage)
    }
  }
}
