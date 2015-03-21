package jp.aquabox.data.analyzer.paser

import org.jsoup.Jsoup

/**
 * Created by motonari on 15/03/21.
 */
object HtmlParser {
  def get(html:String) = {
    val value = Jsoup.parse(html)

    new HtmlData(
      value.title,
      value.head.getElementsByAttributeValue("property", "og:image").get(0).attributes().get("content"),
      value.head.getElementsByAttributeValue("name", "description").get(0).attributes().get("content"),
      value.head.getElementsByAttributeValue("rel", "canonical").get(0).attributes().get("href")
    )
  }

  class HtmlData(title:String, thumbnail:String, description:String, url:String)
}
