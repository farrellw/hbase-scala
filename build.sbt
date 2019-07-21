import Dependencies._

ThisBuild / scalaVersion := "2.13.0"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.farrellw"
ThisBuild / organizationName := "farrellw"

lazy val root = (project in file("."))
  .settings(
    name := "Hbase Hello World",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.apache.hbase" % "hbase-client" % "1.3.1",
    libraryDependencies += "org.apache.hbase" % "hbase-common" % "1.3.1",
    libraryDependencies += "org.apache.hbase" % "hbase" % "1.3.1",
    libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.5.1"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
