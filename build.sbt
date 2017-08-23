name := """tillbox-webapp"""

version := "0.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  jdbc,
  "org.mongodb.morphia" % "morphia" % "1.3.2",
  "com.eclipsesource.minimal-json" % "minimal-json" % "0.9.4"
)

libraryDependencies += "org.postgresql" % "postgresql" % "42.1.1"
