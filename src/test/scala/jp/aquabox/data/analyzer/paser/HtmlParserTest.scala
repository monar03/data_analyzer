package jp.aquabox.data.analyzer.paser

import org.scalatest.{Matchers, FunSpec}

import scala.util.control.Exception

/**
 * Created by motonari on 15/03/21.
 */
class HtmlParserTest extends FunSpec with Matchers {
  describe("HTMLパーサ") {
    it("正常") {
      val html = HtmlParser parse
        """
          <html>
           <head>
             <title>test</title>
             <meta property="og:image" content="thumbnail" />
             <meta name="description" content="description" />
             <meta rel="canonical" href="canonical" />
           </head>
          </html>
        """
      html.title shouldBe "test"
      html.description shouldBe "description"
      html.thumbnail shouldBe "thumbnail"

      html.url shouldBe "canonical"
    }

    it("Urlから取得") {
      val html = HtmlParser parseFromUrl "http://www.yahoo.co.jp"

      html.title shouldBe "Yahoo! JAPAN"
      html.description shouldBe "日本最大級のポータルサイト。検索、オークション、ニュース、メール、コミュニティ、ショッピング、など80以上のサービスを展開。あなたの生活をより豊かにする「ライフ・エンジン」を目指していきます。"
      html.thumbnail shouldBe ""

      html.url shouldBe "http://www.yahoo.co.jp"
    }

    it("タイトルなし") {
      intercept[Exception] {
        HtmlParser parse
          """
          <html>
           <head>
             <meta property="og:image" content="thumbnail" />
             <meta name="description" content="description" />
             <meta rel="canonical" href="canonical" />
           </head>
          </html>
        """
      }
    }
  }
}
