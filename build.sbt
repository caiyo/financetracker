name := "FinanceTracker"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "postgresql" % "postgresql" % "8.4-702.jdbc4"
)     

val appDependencies = Seq(
  javaJdbc,
  javaEbean
)

play.Project.playJavaSettings
