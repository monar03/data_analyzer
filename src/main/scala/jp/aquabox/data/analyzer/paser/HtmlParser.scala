package jp.aquabox.data.analyzer.paser

import org.jsoup.Jsoup

/**
 * Created by motonari on 15/03/21.
 */
object HtmlParser {
  def parse(html:String) = {
    val doc = Jsoup.parse(html)

    new HtmlData(
      doc.title,
      doc.head.getElementsByAttributeValue("property", "og:image").get(0).attributes().get("content"),
      doc.head.getElementsByAttributeValue("name", "description").get(0).attributes().get("content"),
      doc.head.getElementsByAttributeValue("rel", "canonical").get(0).attributes().get("href")
    )
  }
}

case class HtmlData(title:String, thumbnail:String, description:String, url:String)

