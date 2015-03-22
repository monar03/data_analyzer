package jp.aquabox.data.analyzer

import java.net.URL

import analyzer.Main._
import jp.aquabox.data.analyzer.model.{TagInformationDao, SiteInformationDao}
import jp.aquabox.data.analyzer.paser.HtmlParser
import jp.aquabox.morphological.{KuromojiAnalysis, WordData}

/**
 * Created by motonari on 15/03/23.
 */
object DataAnalyzerCalculator extends KuromojiAnalysis {
  def calc = {
    val html = HtmlParser.parseFromUrl("http://news.searchina.net/id/1562440")
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
          case f if f == "名詞" =>
            try {
              TagInformationDao.set(html.url, s, 1)
            } catch {
              case e:Exception => println(e.getMessage)
            }
          case f => println(s + ":" + f)
        }
      }
    } catch {
      case e:Exception => println(e.getMessage)
    }
  }
}
