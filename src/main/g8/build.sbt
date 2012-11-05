/* basic project info */
name := "$project_artifact_id$"

organization := "$project_group_id$"

version := "$project_version$"

description := "$project_description$"

homepage := Some(url("https://github.com/$github_username$/$github_repo_name$"))

startYear := Some(2012)

licenses := Seq(
  ("$project_license_name$", url("$project_license_url$"))
)

scmInfo := Some(
  ScmInfo(
    url("https://github.com/$github_username$/$github_repo_name$"),
    "scm:git:https://github.com/$github_username$/$github_repo_name$.git",
    Some("scm:git:git@github.com:$github_username$/$github_repo_name$.git")
  )
)

// organizationName := "My Company"

/* scala versions and options */
scalaVersion := "2.9.2"

// crossScalaVersions := Seq("2.9.1")

offline := false

scalacOptions ++= Seq("-deprecation", "-unchecked")

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

/* entry point */
mainClass in (Compile, packageBin) := Some("$project_group_id$.$project_artifact_id$.Main")

mainClass in (Compile, run) := Some("$project_group_id$.$project_artifact_id$.Main")

/* dependencies */
libraryDependencies ++= Seq (
  // "org.scalaz" %% "scalaz-core" % "7.0.0-M3",
  // "org.scalaz" %% "scalaz-effect" % "7.0.0-M3",
  // "org.scalacheck" %% "scalacheck" % "1.10.0" % "test"
)

/* you may need these repos */
resolvers ++= Seq(
  // Resolvers.sonatypeRepo("snapshots")
  // Resolvers.typesafeIvyRepo("snapshots")
  // Resolvers.typesafeIvyRepo("releases")
  // Resolvers.typesafeRepo("releases")
  // Resolvers.typesafeRepo("snapshots")
  // JavaNet2Repository,
  // JavaNet1Repository
)

/* sbt behavior */
logLevel in compile := Level.Warn

traceLevel := 5

releaseSettings

/* publishing */
publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) Some(
    "snapshots" at nexus + "content/repositories/snapshots"
  )
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
                      }

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <developers>
    <developer>
      <id>$developer_handle$</id>
      <name>$developer_full_name$</name>
      <email>$developer_email$</email>
      <!-- <url>$developer_homepage$</url> -->
    </developer>
  </developers>
)

// Josh Suereth's step-by-step guide to publishing on sonatype
// httpcom://www.scala-sbt.org/using_sonatype.html

/* assembly plugin */
mainClass in AssemblyKeys.assembly := Some("$project_group_id$.$project_artifact_id$.Main")

assemblySettings

test in AssemblyKeys.assembly := {}
