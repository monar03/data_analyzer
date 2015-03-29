package jp.aquabox.morphological

import org.atilika.kuromoji.{Token, Tokenizer}

import scala.collection.mutable.ListBuffer

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

  def nounlist(str:String) = {
    val list = ListBuffer.empty[String]
    var prev_element:Option[String] = None
    parse(str).map {
      case WordData(s, f) => {
        f.split(",")(0) match {
          case v if v == "名詞" => prev_element match {
            case Some(v) => {
              val noun = list.remove(list.length - 1) + s
              list += noun

              prev_element = Some(noun)
            }
            case None => {
              list += s

              prev_element = Some(s)
            }
          }
          case v => prev_element = None
        }
      }
      case v =>
    }

    list.toList
  }
}

case class WordData(surface:String, feature:String)
