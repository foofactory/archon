
assemblyJarName in assembly := "archon.jar"

mainClass in assembly := Some("archon.Boot")

name := "archon"

version := "1.0"

scalaVersion := "2.11.2"

version       := "0.1"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= {
  val akkaVersion = "2.3.6"
  val sprayVersion = "1.3.2"
  Seq(
    "io.spray"                %% "spray-can"          % sprayVersion,
    "io.spray"                %% "spray-routing"      % sprayVersion,
    "io.spray"                %% "spray-testkit"      % sprayVersion,
    "com.typesafe.akka"       %% "akka-actor"         % akkaVersion,
    "org.scalaz.stream"       %% "scalaz-stream"      % "0.5a",
    "com.orientechnologies"   % "orientdb-graphdb"    % "2.0-M3"
  )
}

Revolver.settings: Seq[sbt.Def.Setting[_]]

Twirl.settings: Seq[sbt.Def.Setting[_]]