import de.johoop.jacoco4sbt._
import JacocoPlugin._
 
name := "vlc"

organization := "com.github.dr3amr2.vlc"

version := "1.0"

scalaVersion := "2.10.3"

//Define dependencies. These ones are only required for Test and Integration Test scopes.
libraryDependencies ++= Seq(
    "org.scalatest" % "scalatest_2.10" % "2.0.1-SNAP" % "test,it",
    "org.scalacheck" %% "scalacheck" % "1.11.2" % "test,it",
    "uk.co.caprica" % "vlcj" % "3.0.1",
    "net.java.dev.jna" % "jna" % "3.5.2",
    "net.java.dev.jna" % "platform" % "3.5.2",
    "com.miglayout" % "miglayout" % "3.7.4",
    "log4j" % "log4j" % "1.2.17"
)

// For Settings/Task reference, see http://www.scala-sbt.org/release/sxr/sbt/Keys.scala.html

// Compiler settings. Use scalac -X for other options and their description.
// See Here for more info http://www.scala-lang.org/files/archive/nightly/docs/manual/html/scalac.html 
scalacOptions ++= List("-feature","-deprecation", "-unchecked")

// ScalaTest settings.
// Ignore tests tagged as @Slow (they should be picked only by integration test)
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-l", "org.scalatest.tags.Slow")

//Code Coverage section
jacoco.settings

//itJacoco.settings

//Style Check section 
org.scalastyle.sbt.ScalastylePlugin.Settings
 
org.scalastyle.sbt.PluginKeys.config <<= baseDirectory { _ / "src/main/config" / "scalastyle-config.xml" }

// Generate Eclipse project with sources for dependencies
//EclipseKeys.withSource := true
