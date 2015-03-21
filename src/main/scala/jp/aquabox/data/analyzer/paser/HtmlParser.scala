package jp.aquabox.data.analyzer.paser

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by motonari on 15/03/21.
 */
object HtmlParser {
  def parseFromUrl(url:String) = get(Jsoup.connect(url).get, url)

  def parse(html:String, url:String="") = get(Jsoup.parse(html), url)

  private[this] def get(doc:Document, url:String) = {
    val title = doc.title match {
      case s if s.isEmpty => throw new Exception("empty title")
      case s => s
    }

    var turl = ""
    try {
      turl = doc.head.getElementsByAttributeValue("rel", "canonical").get(0).attributes().get("href")
    } catch {
      case e:Exception => turl = url
    }

    var description = ""
    try {
      description = doc.head.getElementsByAttributeValue("name", "description").get(0).attributes().get("content")
    } catch {
      case e:Exception => description = ""
    }

    var image = ""
    try {
      image = doc.head.getElementsByAttributeValue("property", "og:image").get(0).attributes().get("content")
    } catch {
      case e:Exception => image = ""
    }

    new HtmlData(
      title,
      image,
      description,
      turl
    )
  }
}

case class HtmlData(title:String, thumbnail:String, description:String, url:String)

