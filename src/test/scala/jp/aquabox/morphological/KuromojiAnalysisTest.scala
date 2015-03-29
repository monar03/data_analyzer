package jp.aquabox.morphological

import org.scalatest.{Matchers, FunSpec}

/**
 * Created by motonari on 15/03/29.
 */
class KuromojiAnalysisTest extends FunSpec with Matchers with KuromojiAnalysis {
  describe("文字列サンプル") {
    describe("形態素解析") {
      it("パース") {
        parse("文字列リスト")(0).surface shouldBe "文字"
      }
    }
    describe("文字列リスト") {
      it("取得") {
        nounlist("文字列リスト")(0) shouldBe "文字列リスト"
      }
    }
  }

}
