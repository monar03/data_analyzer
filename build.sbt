name := "data_analyzer"

version := "1.0"

scalaVersion := "2.11.4"

resolvers += "Atilika Open Source repository" at "http://www.atilika.org/nexus/content/repositories/atilika"

libraryDependencies ++= Seq(
  "org.atilika.kuromoji" % "kuromoji" % "0.7.7",
  "org.scalatest" %% "scalatest" % "2.2.4"
)