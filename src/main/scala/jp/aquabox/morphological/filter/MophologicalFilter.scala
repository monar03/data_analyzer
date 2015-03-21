package jp.aquabox.morphological.filter

import jp.aquabox.morphological.{KuromojiAnalysis, WordData}

/**
 * NGワードのフィルタ
 */
trait MophologicalNGFilter extends KuromojiAnalysis {
  def filter(str:String) = parse(str) map {
    case WordData(s, f) => ngList exists( _ == s )
  } exists( s => s == true )

  def ngList() = {
    Array("アダルト")
  }
}

/**
 * 類似フィルター
 */
trait MophologicalMatchingFilter extends KuromojiAnalysis {
  def filter(str1:String, str2:String, p:Double = 0.7f) = {
    val sigmaXY:Double = parse(str1) intersect parse(str2) length
    val sigmaX:Double = Math.sqrt(parse(str1).length)
    val sigmaY:Double = Math.sqrt(parse(str2).length)

    sigmaXY / (sigmaX * sigmaY) > p
  }


  def filter(str_list1:List[String], str_list2:List[String]) = {
    val sigmaXY:Double = str_list1 intersect str_list2 length
    val sigmaX:Double = Math.sqrt(str_list1.length)
    val sigmaY:Double = Math.sqrt(str_list2.length)

    sigmaXY / (sigmaX * sigmaY) > 0.7f
  }
}

