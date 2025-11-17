import Dependencies._

ThisBuild / scalaVersion     := "3.3.5"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.github.scalerock.snv"


lazy val root = (project in file("."))
  .settings(
    name := "SimpleNetworkViue",
    libraryDependencies ++= {
  val osName = System.getProperty("os.name") match {
    case n if n.startsWith("Linux")   => "linux"
    case n if n.startsWith("Mac")     => "mac"
    case n if n.startsWith("Windows") => "win"
    case _                            => throw new Exception("Unknown platform!")
  }
  Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
    .map(m => "org.openjfx" % s"javafx-$m" % "15.0.1" classifier osName) ++
    Seq(munit % Test)
}

    
  )
