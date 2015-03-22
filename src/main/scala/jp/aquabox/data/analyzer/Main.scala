package analyzer

import jp.aquabox.data.analyzer.model.{SiteInformationWriter, TagInformationWriter, TagInformation}
import jp.aquabox.data.analyzer.paser.HtmlParser

/**
 * Created by motonari on 15/03/21.
 */
object Main extends TagInformationWriter with SiteInformationWriter{

  def main(args: Array[String]): Unit = {
    val html = HtmlParser.parseFromUrl("http://headlines.yahoo.co.jp/hl?a=20150320-00000289-sph-soci")
    println(html.description)
    println(html.title)
  }
}

