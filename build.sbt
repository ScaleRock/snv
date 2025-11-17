import sbt._
import Keys._

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

    
  )
  
