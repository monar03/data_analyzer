package jp.aquabox.morphological

import org.atilika.kuromoji.{Token, Tokenizer}

/**
 * Created by motonari on 15/03/21.
 */
trait KuromojiAnalysis {
  /**
   * パーサ
   */
  def parse(str:String) = Tokenizer.builder.mode(Tokenizer.Mode.NORMAL).build.tokenize(str).toArray map {
    s => new WordData(s.asInstanceOf[Token].getSurfaceForm, s.asInstanceOf[Token].getAllFeatures)
  }
}

case class WordData(surface:String, feature:String)
