name := "mooDadosAbertos"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

libraryDependencies += "org.mongodb" % "mongo-java-driver" % "2.12.2"

libraryDependencies += "org.apache.commons" % "commons-math3" % "3.0"


play.Project.playJavaSettings
