package jp.aquabox.data.analyzer.paser

import org.scalatest.{Matchers, FunSpec}

/**
 * Created by motonari on 15/03/21.
 */
class HtmlParserTest extends FunSpec with Matchers {
  describe("HTMLパーサ") {

    it("正常パース") {
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
  }
}
