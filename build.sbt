name := "Squeryl KeyedEntity Equality"

version := "1.0"

scalaVersion := "2.11.5"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked", // Enable additional warnings where generated code depends on assumptions
  "-feature",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-Xlint", // Enable recommended additional warnings.
  "-Xfatal-warnings",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture"
  )

{
  libraryDependencies ++= Seq(
    "org.squeryl"               %% "squeryl"           % "0.9.6-RC3",
    "com.h2database"            %  "h2"                 % "1.4.185"
)}

