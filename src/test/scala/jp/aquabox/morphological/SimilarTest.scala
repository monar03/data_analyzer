package jp.aquabox.morphological

import org.scalatest.{Matchers, FunSpec}

/**
 * Created by motonari on 15/03/21.
 */
class SimilarTest extends FunSpec with Matchers {
  describe("重要分抽出") {
    it("抜き出し") {
      Similar.get(
        "今日はいい天気",
        """
          今日はいい天気
          ほげほげ
          今日はいい天気
          ふがふが
        """.trim
      ) shouldBe
        """
          今日はいい天気
          今日はいい天気
        """.trim
    }
  }
}
