package jp.aquabox.morphological

import jp.aquabox.morphological.filter.MophologicalMatchingFilter

/**
 * 重要文抜き出し
 *
 * Created by motonari on 15/03/21.
 */
object Similar extends MophologicalMatchingFilter {
  /**
   * 重要分の取得
   *
   * @param main
   * @param content
   * @return
   */
  def get(main:String, content:String) = liners(content) filter (p => filter(main, p.trim, 0.5f)) map (p => p.trim) mkString("\n")

  private[this] def liners(text:String) = text.split("\n")
}
