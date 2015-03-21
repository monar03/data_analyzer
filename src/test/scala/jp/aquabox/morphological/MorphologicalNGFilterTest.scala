package jp.aquabox.morphological

import org.scalatest.{Matchers, FunSpec}

/**
 * Created by motonari on 15/03/21.
 */
class MorphologicalNGFilterTest extends FunSpec with Matchers with MophologicalNGFilter {
  describe("形態素解析テスト") {
    describe("NGワード") {
      it("NGワードを含む") {
        this.filter("アダルト") shouldBe true
      }

      it("NGワードを含まない") {
        this.filter("ほげ") shouldBe false
      }
    }
  }
}

class MorphologicalMachingFilterTest extends FunSpec with Matchers with MophologicalMatchingFilter {
  describe("類似性") {
    it("同一文章") {
      this.filter("今日はいい天気ですね。", "今日はいい天気ですね。") shouldBe true
    }

    it("似ている文章") {
      this.filter("今日はいい天気ですね。", "今日はいい天気ですよ。") shouldBe true
    }

    it("違う文章") {
      this.filter("ほげ", "ふが") shouldBe false
    }

    it("同一文章 配列") {
      this.filter(List("今日","は","いい","天気","です","ね","。"), List("今日","は","いい","天気","です","ね","。")) shouldBe true
    }

    it("似ている文章 配列") {
      this.filter(List("今日","は","いい","天気","です","ね","。"), List("今日","は","いい","天気","です")) shouldBe true
    }

    it("違う文章 配列") {
      this.filter(List("ほげ"), List("ふが")) shouldBe false
    }
  }
}
