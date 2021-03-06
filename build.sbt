name := "freeacs-next"

version := "1.0"

lazy val `untitled` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
resolvers += "jitpack" at "https://jitpack.io"

scalaVersion := "2.12.8"

val slickVersion = "3.3.0"

val playSlick = "4.0.1"

scalacOptions += "-Xfatal-warnings"

libraryDependencies ++= Seq( ehcache , ws , specs2 % Test , guice )
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.16"
libraryDependencies += "com.github.jarlah" % "AuthenticScala" % "v1.0.3"
libraryDependencies += "com.adrianhurt" %% "play-bootstrap" % "1.4-P26-B4"
libraryDependencies += "com.zaxxer" % "HikariCP" % "3.3.1"
libraryDependencies += "com.h2database" % "h2" % "1.4.199"
libraryDependencies += "com.typesafe.play" %% "play-slick" % playSlick
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % playSlick
libraryDependencies += "com.typesafe.slick" %% "slick" % slickVersion
libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % slickVersion
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.0" % "test"
libraryDependencies += "com.lihaoyi" %% "pprint" % "0.5.3"
libraryDependencies += "org.kohsuke" % "groovy-sandbox" % "1.19"

enablePlugins(JavaAppPackaging)

// sourceGenerators in Compile += slick.taskValue // Automatic code generation on build

/*
lazy val slick = taskKey[Seq[File]]("Generate Tables.scala")
slick := {
  val dir = (sourceDirectory in Compile).value
  val outputDir = dir
  val url = sys.env.getOrElse("PLAY_DB_URL", "jdbc:mysql://127.0.0.1:3306/acs")
  val username = sys.env.getOrElse("PLAY_DB_USER", "acs")
  val pwd = sys.env.getOrElse("PLAY_DB_PASSWORD", "acs")
  val jdbcDriver = "com.mysql.jdbc.Driver"
  val slickDriver = "slick.jdbc.MySQLProfile"
  val pkg = "daos"
  val cp = (dependencyClasspath in Compile).value
  val s = streams.value

  runner.value.run("slick.codegen.SourceCodeGenerator",
    cp.files,
    Array(slickDriver, jdbcDriver, url, outputDir.getPath, pkg, username, pwd),
    s.log).failed foreach (sys error _.getMessage)

  val file = outputDir / pkg / "Tables.scala"

  Seq(file)
}
*/
