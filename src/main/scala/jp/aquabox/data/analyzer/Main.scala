package analyzer

import java.net.URL

import jp.aquabox.data.analyzer.model.{TagInformationDao, SiteInformationDao}
import jp.aquabox.data.analyzer.paser.HtmlParser
import jp.aquabox.morphological.{WordData, KuromojiAnalysis}

/**
 * Created by motonari on 15/03/21.
 */
object Main extends KuromojiAnalysis {

  def main(args: Array[String]): Unit = {
    val html = HtmlParser.parseFromUrl("http://headlines.yahoo.co.jp/hl?a=20150322-00000004-asahi-soci")
    try {
      SiteInformationDao.set(
        html.url,
        html.url,
        (new URL(html.url)).getHost,
        html.title,
        html.description,
        html.thumbnail
      )

      parse(html.title + "\n" + html.description) map {
        case WordData(s, f) => f.split(",")(0) match {
          case f if f == "名詞" => TagInformationDao.set(html.url, s, 1)
        }
      }
    } catch {
      case e:Exception => println("information already exists")
    }
  }
}

