package jp.aquabox.data.analyzer.model

import java.sql.Timestamp
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.lifted.ProvenShape

/**
 * Created by motonari on 15/03/22.
 */

trait SiteInformationTable {
  val siteinformation = TableQuery[SiteInformation]
}

/**
 * サイト情報取得
 */
trait SiteInformationReader extends DatabaseInformation with SiteInformationTable {
  def getAll = Database.forURL(dsn, driver = driver) withSession {
    implicit session => siteinformation.list
  }
}

/**
 * サイト情報更新
 */
trait SiteInformationWriter extends DatabaseInformation with SiteInformationTable {
}

class SiteInformation(tag: Tag) extends Table[(String, String, String, String, String, String, Option[Timestamp])](tag, "site_data"){
  /**
   * ID
   * @return
   */
  def id = column[String]("id", O.PrimaryKey)

  /**
   * URL
   * @return
   */
  def url = column[String]("url")

  /**
   * ドメイン
   * @return
   */
  def domain = column[String]("host")

  /**
   * タイトル
   * @return
   */
  def title = column[String]("title", O.DBType("VARCHAR(2048)"))

  /**
   * 詳細
   * @return
   */
  def description = column[String]("description", O.DBType("VARCHAR(2048)"))

  /**
   * イメージ
   * @return
   */
  def image = column[String]("image")

  /**
   * 取得日時
   * @return
   */
  def date = column[Option[Timestamp]]("date")

  def * : ProvenShape[(String, String, String, String, String, String, Option[Timestamp])] = (id, url, domain, title, description, image, date)
}
