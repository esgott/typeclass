ThisBuild / scalaVersion := "2.12.10"

lazy val root = (project in file("."))
  .settings(
    name := "typeclass",
    organization := "com.hiya",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "kittens"       % "2.0.0",
      "io.circe"      %% "circe-core"    % "0.12.3",
      "io.circe"      %% "circe-generic" % "0.12.3",
      "io.circe"      %% "circe-parser"  % "0.12.3"
    ),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
  )
