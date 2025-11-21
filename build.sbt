import sbt._
import Keys._

import sbtassembly.AssemblyPlugin.autoImport._
import sbtassembly.MergeStrategy
import sbtassembly.PathList

ThisBuild / scalaVersion := "3.3.5"
ThisBuild / version      := "0.1.0"
ThisBuild / organization := "com.github.scalerock.snv"

lazy val root = (project in file("."))
  .settings(
    name := "SimpleNetworkViue",

    // Test framework
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "1.2.1" % Test,
      "org.openjfx" % "javafx-controls" % "21.0.9",
      "org.openjfx" % "javafx-fxml" % "21.0.9",
    ),

    assembly / mainClass := Some("com.github.scalerock.snv.Main")
  )

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "substate", "config", _ @_*) =>
    MergeStrategy.discard
  case PathList("META-INF", "substate", "config", "reflectionconfig.json") => MergeStrategy.discard
  case PathList("META-INF", "substate", "config", xs @ _*) => MergeStrategy.discard
  case "module-info.class" => MergeStrategy.discard
  case PathList("META-INF", xs @ _*) =>
    xs.map(_.toLowerCase) match {
      case "manifest.mf" :: Nil =>
        MergeStrategy.discard
      case _ =>
        MergeStrategy.discard
    }
  case _ =>
    MergeStrategy.first
}