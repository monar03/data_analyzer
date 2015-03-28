package jp.aquabox.data.analyzer.paser

import de.l3s.boilerpipe.extractors.ArticleExtractor
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by motonari on 15/03/21.
 */
object HtmlParser {
  /**
   * URLからHTMLデータをパースする
   *
   * @param url
   * @return
   */
  def parseFromUrl(url:String) = get(Jsoup.connect(url).get, url)

  /**
   * HTMLデータのパース
   *
   * @param html
   * @param url
   * @return
   */
  def parse(html:String, url:String="") = get(Jsoup.parse(html), url)


  /**
   * HTMLデータの取得
   *
   * @param doc
   * @param url
   * @return
   */
  private[this] def get(doc:Document, url:String) = {
    val title = doc.title match {
      case s if s.isEmpty => throw new Exception("empty title")
      case s => s
    }

    var turl = ""
    try {
      turl = doc.head.getElementsByAttributeValue("rel", "canonical").get(0).attributes().get("href")
    } catch {
      case e:Exception => {
        try {
          turl = doc.head.getElementsByAttributeValue("rel", "alternate").get(0).attributes().get("href")
        } catch {
          case e:Exception => turl = url
        }
      }
    }

    var description = ""
    try {
      doc.select("input").remove()
      doc.select("script").remove()
      doc.select("noscript").remove()
      doc.select("style").remove()

      description = ArticleExtractor.getInstance.getText(doc.html)
      if(description.isEmpty) {
        description = doc.head.getElementsByAttributeValue("name", "description").get(0).attributes().get("content")
      }
    } catch {
      case e:Exception => description = ""
    }

    var image = ""
    try {
      image = doc.head.getElementsByAttributeValue("property", "og:image").get(0).attributes().get("content")
    } catch {
      case e:Exception => image = ""
    }

    var site_name = ""
    try {
      site_name = doc.head.getElementsByAttributeValue("property", "og:site_name").get(0).attributes().get("content")
    } catch {
      case e:Exception => site_name = ""
    }

    new HtmlData(
      title,
      image,
      description,
      turl,
      site_name
    )
  }
}

case class HtmlData(title:String, thumbnail:String, description:String, url:String, site_name:String)

