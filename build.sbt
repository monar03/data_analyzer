name := "data_analyzer"

version := "1.0"

scalaVersion := "2.11.4"

resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika"

libraryDependencies ++= Seq(
  "org.atilika.kuromoji" % "kuromoji" % "0.7.7",
  "org.scalatest" %% "scalatest" % "2.2.4",
  "mysql" % "mysql-connector-java" % "5.1.34",
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "org.slf4j" % "slf4j-nop" % "1.7.7",
  "org.jsoup" % "jsoup" % "1.8.1",
  "org.twitter4j" % "twitter4j-core" % "4.0.2",
  "org.twitter4j" % "twitter4j-stream" % "4.0.2",
  "de.l3s.boilerpipe" % "boilerpipe" % "1.1.0",
  "xerces" % "xercesImpl" % "2.11.0",
  "net.sourceforge.nekohtml" % "nekohtml" % "1.9.21",
  "com.typesafe.akka" %% "akka-actor" %  "2.3.7"
)