name := "DosProtection"

version := "1.0"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.2.1",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "ch.qos.logback" % "logback-core" % "1.1.3",
  "commons-io" % "commons-io" % "2.6",
  "org.apache.httpcomponents" % "httpclient" % "4.5.4",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.mockito" % "mockito-core" % "2.10.0" % "test"
)