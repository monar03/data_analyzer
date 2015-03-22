package analyzer

import jp.aquabox.data.analyzer.paser.HtmlParser

/**
 * Created by motonari on 15/03/21.
 */
object Main {

  def main(args: Array[String]): Unit = {
    val html = HtmlParser.parseFromUrl("http://headlines.yahoo.co.jp/hl?a=20150321-00000065-jij-pol")
    println(html.description)
    println(html.title)
  }
}

