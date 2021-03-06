package jp.aquabox.data.analyzer.model

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.ProvenShape

/**
 * Created by motonari on 15/03/22.
 */
object TagInformationDao extends TagInformationReader with TagInformationWriter

trait TagInformationTable extends DatabaseInformation{
  val tag = TableQuery[TagInformation]

  try {
    Database.forURL(dsn, driver = driver) withSession {
      implicit session => tag.ddl.create
    }
  } catch {
    case e:Exception => println(e.getMessage)
  }
}

/**
 * タグ情報取得
 */
trait TagInformationReader extends TagInformationTable {
  def getAll = Database.forURL(dsn, driver = driver) withSession {
    implicit session => tag.list
  }
}

/**
 * タグ情報書き込み
 */
trait TagInformationWriter extends TagInformationTable {
  def set(url:String, host:String, tag_str:String, score:Int) = Database.forURL(dsn, driver = driver) withSession {
    implicit session => tag +=(url, host, tag_str, score)
  }
}

class TagInformation(tag: Tag) extends Table[(String, String, String, Int)](tag, "tags"){
  /**
   * ID
   * @return
   */
  def id = column[String]("id")

  /**
   * Host
   * @return
   */
  def host = column[String]("host")

  /**
   * タグ名
   * @return
   */
  def tag_str = column[String]("tag")

  /**
   * スコア
   * @return
   */
  def score = column[Int]("score")

  def * : ProvenShape[(String, String, String, Int)] = (id, host, tag_str, score)
}